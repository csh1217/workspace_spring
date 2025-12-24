// -- CSS파일 추가
// 1. 파일 경로 설정
const CSS_FILE_PATH = '/resources/css/boardList.css';
// 2. line 태그 생성
let linkEle = document.createElement('link');
linkEle.rel = 'stylesheet';
linkEle.type = 'text/css';
linkEle.href = CSS_FILE_PATH;
// 3. head 태그에 link 엘리먼트 추가
document.head.appendChild(linkEle);

// 게시글 등록 버튼
document.querySelector('#registerBtn').addEventListener('click',()=>{
  location.href ='/board/register'
})

// 제목 클릭 이벤트 (get 이동)
// 클릭 대상들 판단
// 기본 이벤트 방지
// 전달받은 데이터 값 컨트롤러에 전달
document.querySelectorAll('td a').forEach(a=>{
  a.addEventListener('click', e=>{
    e.preventDefault();
    let bno = a.getAttribute('href');
    location.href = '/board/get?bno=' + bno;
  })
})