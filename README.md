# API 모음
<br>
개시판 API

      개발 환경 
            Language: Java, JPA, SpringBoot, RESTFUL API제작
            DB: Maria DB
            
       DB table 
            Board : 게시판 테이블
            BoardLike : 게시판 좋아요 테이블  
            User  : 회원 테이블

       BoardController
           authCheck : Header를 체크하여 request Header를 통하여 외부사용자("outUser")일 경우 false를 반환 게시글 작성 클릭시 활용 가능 
           insertBoard : 게시글을 등록합니다. user를 검색 먼저 사용자 인지 판단, 이후 사용자면 게시글 등록 JPA save 활용
           delete : 입력된 게시글의 Id 값을 받아 데이터상으로 삭제를 하지않고 delFlag에 true값을 줘 삭제 처리를 합니다.
           modify : 등록된 글을 수정.
           listBoards : 글목록을 나타내는 메소드, 목록을 불러올때 delFlag가 False인 목룍을 불러 삭제가 안된 사용자를 부릅니다.
                      추가적으로 BoardLike 좋아요 게시글의 현제 header의 유저아이디를 통하여 좋아요디비의 글들을 불러
                      전체 목록과 비교하여 일치한 글에 myLike컬럼에 true값을 줍니다. 추후 출력시 해당 값이 true면 따로 좋아요 표시.
                      mylike는 @Transient를 달아 디비에 저장은 되지 않습니다.
           convertAccountType : 공인중개사, 임대인, 임차인의 아이디값을 한글로 번경하여 저장하기 위한 메소드 

      BoardLikeController
           LikeBoard : 게시글의 boardId와 header의 userId 값을 이용하여 디비를 조회 조회값이 없을 경우 좋아요 DB에 게시글 아이디와
           이용자 아이디 추가, 게시글 DB에 좋아요수 추가
           likeList : 자기 자신의 좋아요를 누른 글의 리스트입니다.

      UserController
           inserUser : 간단히 유저를 추가하는 기능
<br>
암호화 


