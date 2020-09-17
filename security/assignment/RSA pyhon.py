import time
start = time.time()

def gcd(a, b): #최대 공약수
    while b!=0:
        a, b = b, a%b
    return a

def decrypt(pk, ciphertext): #복호
    key, n = pk
    plain = [chr((char ** key) % n) for char in ciphertext]
    return ''.join(plain)

def encrypt(pk, plaintext):
    key, n = pk
    cipher = [(ord(char) ** key) % n for char in plaintext]
    return cipher

def get_private_key(e, tot):
    k=1
    while (e*k)%tot != 1 or k == e: #de = mod Q(n) 에서 d구하기
        k+=1
    return k

def prime_number(num):#소인수 분해
    result = []
    while num != 1:
        for i in range(2, num + 1):
            if num % i == 0:
                result.append(i)
                num = num // i
                break
    p,q = result[0], result[1]

    return p,q

def get_public_key(tot):
    e=2
    while e<tot and gcd(e, tot)!=1: #서로소인 값 구하기
        e += 1
    return e



# m = input("Enter the text to be encrypted:")
#
# # Step 1. Choose two prime numbers
# p,q = prime_number(2236853)
#
# print("Two prime numbers(p and q) are:", str(p), "and", str(q))
#
# n = p*q #2236853이다.
# print("n(p*q)=", str(p), "*", str(q), "=", str(n))
#
# totient = (p-1)*(q-1)
# print("(p-1)*(q-1)=", str(totient))
#
# e = get_public_key(totient) #e = 1243 즉
# print("Public key(n, e):("+str(n)+","+str(e)+")")
#
# # Step 5. Find private key d
# d = get_private_key(e, totient)
# print("Private key(n, d):("+str(n)+","+str(d)+")")
#
# # Step 6.Encrypt message
# encrypted_msg = encrypt((e,n), m)
# print('Encrypted Message:', ''.join(map(lambda x: str(x), encrypted_msg)))
# print(encrypted_msg)
# # Step 7.Decrypt message
# #print('Decrypted Message:', decrypt((d,n),encrypted_msg))
# print('Decrypted Message:', chr(pow(363979, d, n)))
n = 2236853 #n은 임의의 소수 p,q의 곱
e =  1243 #e의 값 즉 n과 서로소가 되고 소수인 값

p,q = prime_number(n) # n을 소인수 분해 한다.
Euler = (p-1)*(q-1)  #Q(n)의 값이다. 즉 n의 서로소의 개수
d = get_private_key(e,Euler) #de = mod Q(n) 에서 d구하기 즉 개인키 구한다.

ciphertext =  [363979, 2151536, 1433078, 831681, 2216718, 1049552, 1433078, 1287309, 138813, 837410, 392312, 1433078, 1287309, 138813, 1763771, 1763771, 1433078, 837410, 335995, 831681, 1433078, 901308, 1049552, 1067071, 1598260, 1049552, 2107458, 1433078, 831681, 1749980, 2039930, 1049552, 1433078, 831681, 335995, 1433078, 831681, 2216718, 1049552, 1433078, 335995, 1749980, 1067071, 901308, 543586]
#ciphertext = [7222151, 859006, 6414, 8864485, 4796962, 1479512, 6414, 2461726, 3682510, 7731216, 6006343, 6414, 2461726, 3682510, 2717396, 2717396, 6414, 7731216, 4659894, 8864485, 6414, 1631820, 1479512, 1545787, 2452210, 1479512, 4320240, 6414, 8864485, 6495654, 6896421, 1479512, 6414, 8864485, 4659894, 6414, 8864485, 4796962, 1479512, 6414, 4659894, 6495654, 1545787, 1631820, 1533449]
#ciphertext = [25715424, 5855752, 28132373, 2926262, 1708340, 25922042, 28132373, 17122738, 12765043, 15847565, 3152476, 28132373, 17122738, 12765043, 27779926, 27779926, 28132373, 15847565, 7219414, 2926262, 28132373, 23835222, 25922042, 7508927, 20657869, 25922042, 27488811, 28132373, 2926262, 6419072, 5494962, 25922042, 28132373, 2926262, 7219414, 28132373, 2926262, 1708340, 25922042, 28132373, 7219414, 6419072, 7508927, 23835222, 12751448]

result = ''
for i in ciphertext:
   result += chr(pow(i,d,n))

print(result)
print(time.time() - start)






