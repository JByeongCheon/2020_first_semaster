class DoubleHashing:
    def __init__(self, size):
        self.M = size
        self.a = [None for x in range(size + 1)]  # 해시테이블
        self.d = [None for x in range(size + 1)]  # key관련 데이터 저장

        self.limit = 0
        self.N = 0  # 항목 수

    def hash(self, key):  # 나눗셈 함수
        return key % self.M

    def put(self, key, data):  # 삽입 연산
        initial_position = self.hash(key)  # 초기 위치
        i = initial_position
        d = 7 - (key % 7)  # 2번≳ 해시 함수
        j = 0

        while True:

            if self.a[i] == None:  # 삽입 위치 발견
                self.a[i] = key  # key를 해시테이블에 저장
                self.limit += 1
                self.N += 1



                if self.limit == int(len(self.a) / 2):
                    self.a = self.a + [None for x in range(self.M-1)]


                    for z in range(int(len(self.a)/2)):

                        self.a[z+15] = self.a[z]
                        self.a[z] = None

                return
            if self.a[i] == key:  # 이미 key 존재하면
                self.d[i] = data  # 데이터만 갱신
                return
            j += 1
            i = (initial_position + j * d) % self.M  # i의 다음 위치

            if self.N > self.M * 2:  # 테이블이 full이면
                print(self.a)
                break

    def print_table(self):
        i = 0
        for i in range(30):

            print(i, ':', '{:4}'.format(str(self.a[i])))
            i += 1

        print()


if __name__ == '__main__':
    t = DoubleHashing(15)

    a = [['QR', 2593], ['SQ', 2654], ['CX', 2165],
         ['EK', 2214], ['KE', 2394], ['LH', 2428],
         ['OZ', 2539], ['DL', 2184], ['AHC', 2087],
         ['QF', 2581]]
    for i in a:
        t.put(i[1], i[0])

    t.print_table()