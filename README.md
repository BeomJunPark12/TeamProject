# 팀프로젝트

학원에서 진행한 팀프로젝트입니다.

[프로젝트명]

기간: 2023.03.21 ~ 2023.04.24

참여인원: 5명

구현기능: 도서 구매 사이트

담당역할:

- [아임포트api를 이용한 결제 구현](https://github.com/BeomJunPark12/TeamProject/blob/0619dece01de45cd9ccfa3a0ad91ccde6b60964a/src/main/webapp/WEB-INF/views/payForm/payForm.jsp#L65)
- [ajax와 아임포트api를 사용하여 결제 정보 DB저장](https://github.com/BeomJunPark12/TeamProject/blob/0619dece01de45cd9ccfa3a0ad91ccde6b60964a/src/main/java/com/gdj59/bookmall/controller/PaymentController.java#L91)

# 구현 화면
![마이페이지](https://github.com/BeomJunPark12/TeamProject/assets/118503208/a04dbc19-b695-4e3f-85df-00e03095729b)
- 장바구니 내역이 없을 때 결제하기 버튼 작동이 안됩니다
- 회원 등급별(basic, vip)로 회원 등급이 vip면 총 가격에서 10%를 할인 받을 수 있습니다.

![카카오톡](https://github.com/BeomJunPark12/TeamProject/assets/118503208/1ea92237-3d94-4a14-8fad-5c0b0124570d)
- 아임포트 api를 이용해서 카카오톡 결제를 할 수 있습니다
- 결제를 하고 구매한 총 금액이 100,000원이 넘으면 회원등급이 vip로 변경됩니다.




# 개발 환경:

1. 언어: Java

2. DB: MySQL8.0

3. was: Tomcat 9.0

4. jdk: jdk 11

5. tool: sts3
