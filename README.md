# At-a-Glance Backend

## 📌 프로젝트 개요
At-a-Glance는 뉴스 영상 요약 및 카드 뉴스 생성 서비스를 제공하는 웹 애플리케이션입니다. 이 저장소는 해당 서비스의 백엔드 코드로, RESTful API를 통해 주요 기능을 제공합니다.

## 🚀 Tech Stack & 선택 이유
| 기술 | 사용 이유 |
|------|---------|
| **Java** | OOP를 기반으로 MVC 패턴을 구성 |
| **Spring (MVC 기반)** | 명확한 계층 구조와 확장성을 제공하며, 직접 XML 설정 및 Servlet을 활용한 유연한 구성 가능 |
| **IntelliJ IDEA** | Git, AWS와의 연동으로 생산적인 개발 환경을 구축 |
| **AWS (EC2, S3, API Gateway, RDS)** | 서버 호스팅, 파일 저장, API 관리 및 DB 운영 |
| **MySQL** | DB를 설계하고, SQL을 사용하여 CRUD 작업을 수행 |
| **Redis** | API 응답 속도 향상을 위한 캐싱 시스템 |

## 📌 주요 기능
- **뉴스 영상 요약**: YouTube 영상에서 제목과 게시일을 크롤링하여 저장
- **카드 뉴스 생성**: 영상 내용을 분석하고 요약하여 카드 뉴스 제공
- **RESTful API 제공**: 클라이언트와의 통신을 위한 API 엔드포인트 구현
- **S3 기반 미디어 저장**: 영상 및 카드 뉴스 이미지를 S3에 저장 후 URL 제공
- **API Gateway 보안**: HTTPS 통신 및 보안 정책 적용

## 🏗️ 프로젝트 구조
```
├── src
│   ├── main
│   │   ├── java/com/ataglance
│   │   │   ├── controller  # API 컨트롤러
│   │   │   ├── service      # 비즈니스 로직
│   │   │   ├── repository   # 데이터베이스 인터페이스
│   │   │   ├── dto          # 데이터 전송 객체
│   │   │   ├── config       # 설정 파일
│   ├── resources
│   │   ├── application.xml  # 환경 설정 파일 (Spring XML 설정 활용)
```

## ⚡ API 엔드포인트 예시
```http
GET /api/cardnews/all
Response: [{ "newsId": 1, "title": "뉴스 제목", "thumbnail": "s3://..." }]
```

## 🛠️ 설치 및 실행 방법
```bash
# 프로젝트 클론
git clone https://github.com/your-repo/at-a-glance-backend.git
cd at-a-glance-backend

# 환경 변수 설정
cp .env.example .env
vi .env  # 필요 설정 변경
```

## 🔥 배포 방법
1. 코드 수정 및 커밋
2. Docker 이미지 빌드 및 푸시
   ```bash
   docker build -t at-a-glance-backend .
   docker tag at-a-glance-backend:latest your-docker-repo/ataglance:latest
   docker push your-docker-repo/ataglance:latest
   ```
3. AWS EC2에서 컨테이너 실행
   ```bash
   docker pull your-docker-repo/ataglance:latest
   docker run -d -p 8080:8080 your-docker-repo/ataglance
   ```

## 📜 라이선스
본 프로젝트는 MIT 라이선스를 따릅니다.
