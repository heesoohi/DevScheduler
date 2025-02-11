## API 명세서

### User API

| 메서드 | 엔드포인트      | 설명               | 요청 본문 예시 | 응답 예시 |
|--------|---------------|------------------|-------------|---------|
| `POST` | `/users`       | 사용자 회원가입  | `{ "username": "홍길동", "email": "test@example.com", "password": "password123" }` | `{ "id": 1, "username": "홍길동", "email": "test@example.com" }` |
| `GET`  | `/users`       | 사용자 목록 조회 | 없음 | `[ { "id": 1, "username": "홍길동", "email": "test@example.com" }, { "id": 2, "username": "김철수", "email": "test2@example.com" } ]` |
| `GET`  | `/users/{id}`  | 사용자 정보 조회 | 없음 | `{ "id": 1, "username": "홍길동", "email": "test@example.com" }` |
| `PUT`  | `/users/{id}`  | 사용자 정보 수정 | `{ "username": "길동이", "email": "newemail@example.com", "password": "newpassword123" }` | `{ "id": 1, "username": "길동이", "email": "newemail@example.com" }` |
| `DELETE` | `/users/{id}`  | 사용자 삭제      | 없음 | `{ "message": "사용자가 삭제되었습니다." }` |

### Login API

| 메서드 | 엔드포인트      | 설명               | 요청 본문 예시 | 응답 예시 |
|--------|---------------|------------------|-------------|---------|
| `POST`   | `/login`        | 로그인            | `{ "email": "test@example.com", "password": "password123" }` | `"Login Successful"` |
| `POST`   | `/logout`       | 로그아웃          | 없음 | `"Logout Successful"` |

### Task API

| 메서드 | 엔드포인트        | 설명               | 요청 본문 예시 | 응답 예시 |
|--------|------------------|------------------|-------------|---------|
| `POST` | `/tasks`         | 일정 생성        | `{ "title": "회의", "content": "팀 미팅", "userId": 1 }` | `{ "id": 1, "title": "회의", "content": "팀 미팅", "userId": 1 }` |
| `GET`  | `/tasks`         | 일정 목록 조회    | 없음 | `[ { "id": 1, "title": "회의", "content": "팀 미팅", "userId": 1 }, { "id": 2, "title": "정기 회의", "content": "부서 회의", "userId": 2 } ]` |
| `GET`  | `/tasks/{id}`    | 일정 조회        | 없음 | `{ "id": 1, "title": "회의", "content": "팀 미팅", "userId": 1 }` |
| `PUT`  | `/tasks/{id}`    | 일정 수정        | `{ "title": "업무 회의", "content": "업무 진행 상황", "userId": 1 }` | `{ "id": 1, "title": "업무 회의", "content": "업무 진행 상황", "userId": 1 }` |
| `DELETE` | `/tasks/{id}`    | 일정 삭제        | 없음 | `{ "message": "일정이 삭제되었습니다." }` |
