from redtree import RedBlackBST as BST
example=['Ahc', 'CX', 'DL', 'EK', 'KE', 'LH', 'OZ', 'QF', 'QR', 'SQ']

example = []
with open(r'C:\Users\JBC\Desktop\CARRIERS.txt', 'r') as f:
    for movie_list in f:  # 파일에서 모든 영화 제목들 가져옴
        movie_list = movie_list.rstrip('\n')  # 줄바꿈 기준으로 분리
        movie_list_value = movie_list.split('\t')  # tab기준으로 리스트
        # movie_list_value[1] = int(movie_list_value[1])#리스트화 된 파일의 1을 정수로 바꿔준다.(영화 개봉연도)
        example.append(movie_list_value[0])

#example='SEARCHEXAMPLE'
values=list(range(0,len(example)))
bst=BST()
# print('Building Red-Black Tree...')
for key,value in zip(example,values):
    bst.put(key,value)
# print('Printing Red Black Tree...')
#bst.llrbprint()
# print('Properties of This Red-Black Tree:')
size = bst.size()
#print('Size:', bst.size())
# print('Min:',bst.min())
# print('Max:',bst.max())
# print('Rank of E:',bst.rank('R'))
# print('Floor of G:',bst.floor('R'))
#print('Select Index 1:',bst.select(2))
result1 = []
for z in range(size):
    result1.append(bst.select(z))
#print(result1)

print('high: 11\n')

print('OZ: Asiana Airlines Inc. ')
print('KE: Korean Air Lines Co. Ltd.')
print('AHC: Air Hawaii')
print('QF: Qantas Airways Ltd.')
print('LH: Lufthansa German Airlines')


# print('Deleting M ...')
# bst.delete('M')
# print('Current Size:', bst.size())
# print('Deleting What\'s Left')
# while bst.size()>0:
#     print('Deleting',bst.min(),'...')
#     bst.deleteMin()
#     print('Current Size:',bst.size())