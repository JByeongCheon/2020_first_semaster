#삽입 정렬
def insert(v) :
    for i in range(1,len(v)) :
        j=i-1
        while(j>=0 and v[i]<v[j]):
            j-=1
        v.insert((j + 1), v[i])
        del v[i+1]
    return v

x = []
y = {}

with open(r'C:\Users\JBC\Downloads\100random.txt','r') as f:
        for a in f:
            z = a.rstrip('\n')
            one = int(z)
            two = 100 - one
            if two < 0:
                mi = -two
                y[two] = mi
                two = mi
            x.append(two)

x = insert(x)

result = []
for b in x:
    result.append(100-b)

i = 0

print(y)

for dic_z in y:
    t = -dic_z
    for v in result:
        if v == t:
            result[i] = y[dic_z]
            pass
        i = i + 1
print(result)


