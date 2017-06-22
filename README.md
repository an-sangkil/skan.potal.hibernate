# skan.potal.hibernate

BootStrap 으로 Layout 변경한다.
기존 Master에서 분리하여 진행


## AWS URL
###S erver - Side
Spring DATA JPA / Hibernate / Criteria / QueryDSL / Spring java Configration 을 이용한 프로젝트.

### Fornt -End 
bootstrap.js 를 이용한 화면 구성 
tiles3 사용 
jstl Tag lib 사용

## Security Library
Spring Security 에서 ASE 256Bit를 사용하기 때문에 
	JAVA에서 기본 제공하는 128Bit 사용시 에러발생 이에 아래 사이트에서 파일을 다운 로드 받은후 Security 폴더에 암호화 비트스 제한을 해제한다.
	http://www.oracle.com/technetwork/java/javase/downloads/jce8-download-2133166.html
	%JAVA_HOME%/lib/jre/security/ > jar file 교체 

## VM argument 값 설정()
	경로 : run as -> run configuration -> vm argument
	라인 추가 : 
	-Dspring.profiles.active=dev
	
