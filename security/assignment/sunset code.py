
import math

earthRadius = 6367 #지구 반지름의 길이
earthAngleSecond = 0.00417 #24시간에 360를 회전하고 1시간에 15도이므로 1초에 약 0.00417도를 회전한다.

def horizonCalculation(height):
    #삼각근의 공식을 이용하여 지구반지름과 높이를 너한 관찰자의 높이에서 관측점이 직각이
    #될 경우 삼격형의 변의 공식을 이용하여 x**2 = sqrt(y**2 - z**2)을 이용한다.(단 y는 빗변으로 가장 길다)
    #최종적으로 구해진 길이에서 지구 대기 곡률(표준대기압 기준) 약 8퍼센트을 더하면 시야가 최대로 보이는 지평선의 거리가 나온다.    
    length =  (math.sqrt(pow(height,2) - pow(earthRadius,2))) * 1.08
    print('시야길이:',length,'킬로미터')
    return length

def angleCalculation(length,height):
    #구하려는 각도를x라고 할때 마주보는 변은 length이다.
    #length^2 = height^2 + earthRadius^2 - 2*height*earthRadius*cosx 이다.
    #즉 x = arccos((height^2 + earthRadius^2 - length^2)/(2*height*earthRadius))이다.
    centerAngle = math.acos((height**2 + earthRadius**2 - length**2)/(2*height*earthRadius))
  
    centerAngle = round(math.degrees(centerAngle),2) #소수점 3자리까지  반올림한다.
    print('지구 내부 각:',centerAngle)
    return centerAngle
    
def main():
    height = (float(input("높이(M로 입력):")) * 0.001) + earthRadius  #높이 입력 후 단위 변환
    angle = angleCalculation(horizonCalculation(height),height)
    timeSecond = round(angle / earthAngleSecond, 3) #1초동안 움직이는 각도로 총 각도를 나누면 시간을 알 수 있다.
                                                    #3자리까지 반올림하여 초를 구한다
    print('시간:',int(timeSecond//60),'분', round(timeSecond % 60,2),'초 만큼 0m의 해안보다 해가 빨리 뜬다' )
  

if __name__ == '__main__':
    main()
    
#참고 자료
#[과학을읽다]②착시(錯視), 수평선까지의 거리(2020.02.04 아시아경제 기사)
# https://www.asiae.co.kr/article/2018052515090482949
# 위키피디아-수평선(horizon)
# https://en.wikipedia.org/wiki/Horizon




