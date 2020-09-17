
P10 = (3, 5, 2, 7, 4, 10, 1, 9, 8, 6)
P8 = (6, 3, 7, 4, 8, 5, 10, 9)
P4 = (2, 4, 3, 1)

IP = (2, 6, 3, 1, 4, 8, 5, 7)
IPi = (4, 1, 3, 5, 7, 2, 8, 6)

E = (4, 1, 2, 3, 2, 3, 4, 1)




S0 = [
        [1, 0, 3, 2],
        [3, 2, 1, 0],
        [0, 2, 1, 3],
        [3, 1, 3, 2]
     ]

S1 = [
        [0, 1, 2, 3],
        [2, 0, 1, 3],
        [3, 0, 1, 0],
        [2, 1, 0, 3]
     ]

def sort_p_key(key,P_list): #p순열 치환에 계속 사용
    sort_key = ""
    for i in P_list: #이걸 생각 못했네 기억 해 둘 것 - 2020-05-11
        sort_key += key[i - 1]
    return sort_key

def creat_firstkey(sorted_key): #첫 보조키 생성
    Ls_left = sorted_key[1:5] + sorted_key[0] #반씩 나고 왼쪽으로 한칸씩 이동 즉 맨 앞의 0이 끝으로 이동한다.
    Ls_right = sorted_key[6:10] + sorted_key[5] #10개 일 떄만 가능
    total_Ls = Ls_left + Ls_right #두개의 LS합치기
    first_key = sort_p_key(total_Ls, P8) #8개로 줄어든다.

    return first_key

def creat_secondkey(sorted_key): #첫 보조키 생성
    Ls_left = sorted_key[3:5] + sorted_key[0:3] #반씩 나누고 LS1의 기준으로 두칸씩 이동
    Ls_right = sorted_key[8:10] + sorted_key[5:8]
    total_Ls = Ls_left + Ls_right #두개의 LS합치기
    second_key = sort_p_key(total_Ls, P8)

    return second_key

def F_box(key,plaintext): #first key or second key를 key에 넣는다. F함수임

    left_block = plaintext[0:4] #4비트씩 반으로 나눈다.
    right_block = plaintext[4:8].zfill(4) #확장 zfill로 빈칸을 0으로 채움
    extend_value = sort_p_key(right_block,E) #이 값은 현재 8비트
    xor_value = bin(int(key,2)^int(extend_value,2))[2:].zfill(8) # XOR계산, 비트 연산자
    left_4 =xor_value[:4]
    right_4=xor_value[4:]
    l_sbox = Sbox(left_4,S0) #각각 2비트 값씩 나온다.
    r_sbox = Sbox(right_4,S1)
    total_sbox = l_sbox[2:4]+r_sbox[2:4]#합쳐서 4비트로 만든다.
    after_p4 = sort_p_key(total_sbox,P4)
    last_xor_value = bin(int(after_p4, 2) ^ int(left_block, 2))[2:].zfill(4)  # XOR계산
    after_f = last_xor_value + right_block #after_first_f = round1 result value

    return after_f


def second_F_box(key, plaintext): #두번쨰 라운드로 두번쨰 보조키와 첫 라운드에서 얻은 8비트 값을 삽입
    plaintext = plaintext[4:] + plaintext[:4] #스위치
    after_second_f = F_box(key,plaintext)


    return sort_p_key(after_second_f,IPi)

def Sbox(input, sbox): #4비트 값 삽입
    row = int(input[0] + input[3], 2) #행
    column = int(input[1] + input[2], 2) #열
    return bin(sbox[row][column])[2:].zfill(4) #00이 나오면 0이 캔슬 되므로


def encryption(key,plaintext): #암호화
    first_key = creat_firstkey(sort_p_key(key, P10))
    second_key = creat_secondkey(sort_p_key(key,P10))

    first_IP_sort = sort_p_key(plaintext, IP)  # 초기 IP치환을 한다.
    encrypt_value = F_box(first_key,first_IP_sort) #1라운드
    encrypt_value = second_F_box(second_key,encrypt_value)



    return encrypt_value

def decryption(key,encryptiontext): #복호화
    first_key = creat_firstkey(sort_p_key(key, P10))
    second_key = creat_secondkey(sort_p_key(key, P10))

    first_IP_sort = sort_p_key(encryptiontext, IP)  # 초기치환
    decryptiontext_value = F_box(second_key, first_IP_sort)  # 1라운드
    decryptiontext = second_F_box(first_key, decryptiontext_value) #키를 역순으로 입력

    return decryptiontext

def non_code_decoding(cryptogram,start_num,last_num,text,key): #키 없이 암호 해독

    decoding_code = cryptogram[start_num:last_num] #8비트씩 자른다.

    #기존의 복호화 과정
    first_key = creat_firstkey(sort_p_key(key, P10))
    second_key = creat_secondkey(sort_p_key(key, P10))
    first_IP_sort = sort_p_key(decoding_code, IP)  # 초기치환
    decryptiontext_value = F_box(second_key, first_IP_sort)  # 1라운드
    decryptiontext = second_F_box(first_key, decryptiontext_value) #해독된 2진수

    int_text = int(decryptiontext, 2)#2진수를 10진수로 변환
    if 65 < int_text < 123 or int_text == 32: #아스키 코드 알파벳, 공백(띄어쓰기) 포함되는지 확인
        ascii_text = chr(int_text) #문자로 변환

        text += ascii_text #문자열로 만들기 위해 text에 삽입

        if len(text) == 34: #34자리의 문자열이므로 34자리가 성사되면 완성된 문자열과 키를 출력하고 종료
            print(text)
            print('key:',key)
            return

        start_num += 8 #8자리씩 옮기기 위해
        last_num += 8

        non_code_decoding(cryptogram, start_num, last_num, text,key) #문자가 이루어지면 해당 과정 반복

    else: #문자가 되지 못하면 해당 키를 종료한다.
        return

def ECB_encoding_decoding(key,plain_text):
    encryption_text = ''
    for i in plain_text:
        ASC_value = ord(i) #문자열을 아스키코드로 변환
        eight_bit_value =  bin(ASC_value)[2:].zfill(8) #아스키 코드를 8비트의 2진수로 변환, [2:]로 2진수를 나타내는 ob제외
        encryption_text += encryption(key,eight_bit_value) #암호문

    decryption_text =''
    for y in [encryption_text[i:i + 8] for i in range(0,len(encryption_text), 8)]:#암호문을 8개씩 끊어 읽는다.
        two_bit_balue = decryption(key,y) #복호화
        ASC_value2 = int(two_bit_balue, 2) #2진수를 아스키 값으로 변환
        decryption_text += chr(ASC_value2) #아스키 값을 문자열로 변환 및 복호문

    print('ECB-Ciphertext: ',encryption_text)
    print('ECB-decoding_text: ',decryption_text)

    return

def CBC_encoding_decoding(key,plain_text,V):
    encryption_text = ''
    V_value = V
    for i in plain_text:
        ASC_value = ord(i)  # 문자열을 아스키코드로 변환
        eight_bit_value = bin(ASC_value)[2:].zfill(8)  # 아스키 코드를 8비트의 2진수로 변환, [2:]로 2진수를 나타내는 ob제외
        xor_value = bin(int(eight_bit_value,2) ^ int(V_value,2))[2:].zfill(8)# V값과 평문의 8비트를 xor연산한다.

        V_value = encryption(key, xor_value) #v에 활용
        encryption_text += V_value  # 암호문


    decryption_text = ''
    V_value2 = V
    for y in [encryption_text[i:i + 8] for i in range(0, len(encryption_text), 8)]:  # 암호문을 8개씩 끊어 읽는다.
        eight_bit_value = decryption(key, y)  # 복호화

        xor_value =  bin(int(eight_bit_value,2) ^ int(V_value2,2))[2:].zfill(8)  # V값과 복호화값의 8비트를 xor연산한다.
        V_value2 = y
        ASC_value2 = int(xor_value, 2)  # 2진수를 아스키 값으로 변환
        decryption_text += chr(ASC_value2)  # 아스키 값을 문자열로 변환 및 복호문

    print("CBC Ciphertext :", encryption_text)
    print("CBC decoding_text :",decryption_text)

    return

def OFB_encoding_decoding(key,plain_text,V):
    encryption_text = ''
    V_value = V
    for i in plain_text:
        encryption_v = encryption(key, V_value)
        V_value = encryption_v
        ASC_value = ord(i)  # 문자열을 아스키코드로 변환
        eight_bit_value = bin(ASC_value)[2:].zfill(8)  # 아스키 코드를 8비트의 2진수로 변환, [2:]로 2진수를 나타내는 ob제외
        xor_value = bin(int(eight_bit_value, 2) ^ int(encryption_v, 2))[2:].zfill(8)  # V값과 평문의 8비트를 xor연산한다.
        encryption_text += xor_value

    decryption_text = ''
    for y in [encryption_text[i:i + 8] for i in range(0, len(encryption_text), 8)]:  # 암호문을 8개씩 끊어 읽는다.
        encryption_v = encryption(key, V)
        V = encryption_v
        xor_value = bin(int(encryption_v, 2) ^ int(y, 2))[2:].zfill(8)
        ASC_value2 = int(xor_value, 2)  # 2진수를 아스키 값으로 변환
        decryption_text += chr(ASC_value2)  # 아스키 값을 문자열로 변환 및 복호

    print("OFB Ciphertext :", encryption_text)
    print("OFB decoding_text :", decryption_text)







if __name__ == '__main__':

    #key = "1010101010"
    #plaintext = "11001100"

    #normal SDES encryption,decryption

    # #암호화
    # encryption_text = encryption(key,plaintext)
    # print(encryption_text)

    # #복호화
    # decryption_text = decryption(key,encryption_text)
    # print(decryption_text)

    #decryption code NON key
    #cryptogram_code = '00010100100111011010111111000011010000001010111101001010011011001111111111111111011011001111111101001010110000110110110000001010110000111001110111001010110111000000010111000011111100000000010111000011000101001001110110101111110000110110111110011101111100001101110010101111'
    # start_num = 0  # 0~8까지 중에서 0
    # last_num = 8  # 위와 마찬가지로 범위 지정
    # text = ''#문자열 입력 하기위해서
    #
    # for x in range(1024): #키 구하기 sdes는 10bit키이므로 2의 10승인 1024까지
    #     key = bin(x)[2:].zfill(10) #10진수를 2진수로 변환하고 10비트 중 남는 자리에 0을 채운다.
    #     non_code_decoding(cryptogram_code,start_num,last_num,text,key)


    plain_text = 'happy poppy'
    #plain_text = 'JBC'
    key = '1010101010'
    V_value = '10101010'

    print(plain_text)
    #ECB 암,복호화
    ECB_encoding_decoding(key,plain_text)

    #CBC 암,복호화
    CBC_encoding_decoding(key, plain_text, V_value)

    #OFB 암,복호
    OFB_encoding_decoding(key, plain_text, V_value)


