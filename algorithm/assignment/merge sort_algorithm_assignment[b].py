a = [100, 34, 189, 56, 150, 140, 9, 123]

def merge_sort(list): #나눈다.
    if len(list) <= 1:
        return list #종료 조건
    mid = len(list) // 2 #정수로 나눔
    left = list[:mid]
    right = list[mid:]

    print('left:',", ".join(map(str,left)))
    print('left:',", ".join(map(str,right)))



    left = merge_sort(left)
    right = merge_sort(right)
    return merge(left, right)

def merge(left, right):
    result_sort = []

    while len(left) > 0 and len(right) > 0:
        if left[0] <= right[0]:
            result_sort.append(left[0])
            left = left[1:]

        else:
            result_sort.append(right[0])
            right = right[1:]

    if len(left) > 0:
        result_sort.append(left[0])
        left = left[1:]

    elif len(right) > 0:
        result_sort.append((right[0]))
        right = right[1:]

    print('합:',", ".join(map(str,result_sort)))

    return result_sort

def quick_sort(list):
    list_len = len(list)
    small_list = []
    big_list = []

    if list_len <= 1: #재귀 종료 조건
        return list
    else:
        pivot = list[0] #피벗 값은 리스트의 최초 값
        for i in list[1:]: #피벗 값 제외 크기 비교
            if i < pivot: #피벗 보다 작으면 small리스트에 추가
                small_list.append(i)
            else: #크면 빅 리스트에 추가
                big_list.append(i)

    print('pivot(기준 값):', pivot)
    print('left:',", ".join(map(str,small_list)))
    print('right:',", ".join(map(str,big_list)))
    print('정렬:',", ".join(map(str,small_list+[pivot]+big_list)))
    return quick_sort(small_list) + [pivot] + quick_sort(big_list) #각각 반복하면 비벗값은 계속 중간값에 오고 순차적으된다.
                                                                    #중간 pivot값은 int라 list로 줬다
print("퀵정렬")
print('최종 정렬:',", ".join(map(str,quick_sort(a))),'\n')

print("병합 정렬")
print('left:',", ".join(map(str,merge_sort(a))))
