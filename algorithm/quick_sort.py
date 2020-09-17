def quick_sort(ARRAY):
    ARRAY_LENGTH = len(ARRAY)
    if( ARRAY_LENGTH <= 1):
        return ARRAY
    else:
        PIVOT = ARRAY[0]
        GREATER = [ element for element in ARRAY[1:] if element > PIVOT ]
        LESSER = [ element for element in ARRAY[1:] if element <= PIVOT ]
        return quick_sort(LESSER) + [PIVOT] + quick_sort(GREATER)

x = []

with open(r'C:\Users\JBC\Downloads\100random.txt','r') as f:
        for a in f:
            x.append(a.rstrip('\n'))

print(x)
print(quick_sort(x))