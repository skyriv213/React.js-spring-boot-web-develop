Todo 리스트 구현
-
- 레이어드 아키텍쳐 구조로 구현
  - 애플리케이션을 구성하는 요소들을 수평으로 나눠서 관리
  - 레이어 사이에 계층이 존재, 각 계층은 자기보다 한 단계 아래의 레이어만 사용가능
  - 위에서 아래로 일을 전달, 아래에서 위를 향해 보고
  - request 발생 → 컨트롤러에서 해당 요청을 servic에 전달 → Service는 Persistence에 전달
    → persistence에서 DB 레이어로 전달 → DB에서 persistence로 요청된 데이터를 반환(entity) 
    → entity는 service에 전달 → service에서 데이터를 검토 및 가공을 하여 controller에 반환 
<hr>

- 사용 기술 
    - 백엔드 : spring
      - Lombok / Data jpa / web / 
      - data base : h2 → mysql
