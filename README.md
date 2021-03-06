# CookingClass
요리교실 사용자 프로그램
(2018년 2학기 융합프로젝트 팀 과제)

## 주제
관리자, 강사, 수강생으로 나뉘어, 각 사용자별 필요한 기능을 제공한다.

## 핵심 기능
### 1. 관리자
  1.1 Recipe load				// Excel 파일로부터 load.
						// delete existing recipe & load new recipe 
  1.2 강의일정표 load			// 지점별로 연간 일정이 고정		
  1.3 강의일자별 실습 메뉴 등록		// 주단위로 1개월 후의 식단(3개/day)이 결정.

### 2. 강사
  2.1 실습 메뉴(일자별) 조회
  2.2 식재료 신청자 명단(일자별) 조회	// 식재료비 이체내역 포함
  2.3 식재료 소요량 목록(일자별, 기간별) 조회
  2.4 계좌이체 내역 등재			// 자신의 계좌에 입금된 내역을 입력

### 3. 수강생
  3.1 실습 메뉴(일자별) 조회
  3.2 식재료 신청		// 매주 목요일 마감, 신청하면 송금할 식재료비 표시.
				// 식재료비는 강사 계좌로 이체. 메뉴별 1인분 단위로 신청.
				// 신청자 정보: 이름, 전화번호, 수강 장소(지점)
  3.3 식재료 취소		// 수업 2일전까지 취소 가능
  
  
## 구조도
![요리교실 구조](https://user-images.githubusercontent.com/51351974/71306450-e4d76c80-2423-11ea-8dd4-730f2e8aeaf3.jpg)

## UI
![1](https://user-images.githubusercontent.com/51351974/71305530-febf8200-2418-11ea-87a8-369a040b04c3.jpg)
![2](https://user-images.githubusercontent.com/51351974/71305531-ff581880-2418-11ea-962d-a275405b5b61.jpg)
![3](https://user-images.githubusercontent.com/51351974/71305532-ff581880-2418-11ea-93da-239b79c6c474.jpg)

## Made By
* 이민우 : 메인 기능, 강사 관련 기능 제작, 코드 병합
* 송*근 : DB 구축, JDBC를 활용한 DB 클래스 제작
* 최*준 : 관리자 관련 기능
* 손*정 : 학생 관련 기능
* 이*준 : 프론트 엔드
* 노*수 : 프론트 엔드
