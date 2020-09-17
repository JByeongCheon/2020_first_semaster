movie = [['1917',2020],['괴물',2006],['곡성',2016],['인터스텔라',2014],['어벤져스',2019]]
#movie = [['부산행',2016],['기생충',2019],['곡성',2016],['겨울왕국2',2019],['어벤져스',2019]]

#아래 세게는 정렬을 사용하지 않고 함수 사용
#movie = sorted(movie, key=lambda movie: (-movie[1],movie[0])) #영화 연도순 - 이름 순
#movie = sorted(movie) #영화 제목순
#movie = sorted(movie, key=lambda movie: movie[1]) #영화 연도순

def movie_title_year_sort(movie):
    movie = sorted(movie, key=lambda movie: (-movie[1], movie[0]))  # 영화 연도순 - 이름 순
    return movie


def movie_title_sort():
    for i in range(1,len(movie)): #영화 제목 순 #삽입 정렬은 키가 2번째 것 부터이다.
        key = movie[i][0]
        j = i - 1
        while(key < movie[j][0] and j >= 0): #0번째아래로 내려가는 거 방지
            movie[j+1][0],movie[j][0] = movie[j][0],movie[j+1][0] #키 값과 서로 교환
            movie[j + 1][1], movie[j][1] = movie[j][1], movie[j + 1][1] #년도 따라서 교환
            j-=1
    return movie


def movie_year_sort():
    for i in range(1,len(movie)): #영화 제목 순
        key = movie[i][1]
        j = i - 1
        while(key > movie[j][1] and j >= 0):
            movie[j+1][1],movie[j][1] = movie[j][1],movie[j+1][1] #키 값과 서로 교환
            movie[j + 1][0], movie[j][0] = movie[j][0], movie[j + 1][0] #년도 따라서 교환
            j-=1
    return movie

def movie_file_list_sort():
    movie= [] #무비 리스트 선언
    with open(r'C:\Users\JBC\iCloudDrive\학교\2020년 1학기\알고리즘\과제\정령문제 과제A\movie1.txt','r') as f:
        for movie_list in f: #파일에서 모든 영화 제목들 가져옴
            movie_list = movie_list.rstrip('\n') #줄바꿈 기준으로 분리
            movie_list_value = movie_list.split('\t')#tab기준으로 리스트
            movie_list_value[1] = int(movie_list_value[1])#리스트화 된 파일의 1을 정수로 바꿔준다.(영화 개봉연도)
            movie.append(movie_list_value)

    movie = sorted(movie, key=lambda movie: (-movie[1],movie[0])) #영화 연도순 - 이름 순
    for i in movie:
        print(i)


#print(", ".join(map(str,movie_title_sort())))
#print(movie_title_sort())
#print(", ".join(map(str,movie_year_sort())))
#print(movie_year_sort())
#print(", ".join(map(str,movie_title_year_sort(movie))))
#print(movie_title_year_sort(movie)) #이건 야매;;;; 언젠가 제대로 해보자
movie_file_list_sort()






