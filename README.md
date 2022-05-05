# JPA-STUDY

Jpa를 공부하기 위해 만든 프로젝트입니다.

해당 프로젝트는 아래 인프런 강의에 맞춰 진행합니다. 
 - 실전! 스프링 부트와 JPA 활용1 - 웹 애플리케이션 개발 
 - 실전! 스프링 부트와 JPA 활용2 - API 개발과 성능 최적화
 - 실전! 스프링 데이터 JPA
 - 실전! Querydsl

---
## 개발 환경

---
spring boot version 2.6.7

java version 11

Docker Desktop 4.7.1

IntelliJ IDEA Ultimate 2021.3


## 로컬에서 실행하기

---
1. Docker 로 로컬 데이터 베이스 컨테이너 띄우기
2. 데이터 베이스 생성
3. jpa 스키마 생성
4. IntelliJ 환경 변수 추가
   - POSTGRES_URL
   - POSTGRES_USERNAME
   - POSTGRES_PASSWORD
5. 실행

## 테스트 측정

---

```
./gradlew clean test jacocoTestReport
```
위 명령어 실행 후 ```build > jacocoHtml > index.html(크롬으로 실행)``` 해당 경로의 html 파일에서 test 및 test coverage 확인
