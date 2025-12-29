// -- CSS파일 추가
// 1. 파일 경로 설정
const CSS_FILE_PATH = ['/resources/css/boardList.css', '/resources/css/page.css'];
cssBinding(CSS_FILE_PATH);
function cssBinding(cssFiles){
    cssFiles.forEach(css=>{
    let linkEle = document.createElement('link');
    linkEle.rel = 'stylesheet';
    linkEle.type = 'text/css';
    linkEle.href = css;
    document.head.appendChild(linkEle);
  });
}


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

// 페이지 이동
// 1. 페이지 데이터 저장
let pageNum = new URLSearchParams(location.search).get('pageNum');
let amount = new URLSearchParams(location.search).get('amount');
if(!pageNum || !amount){
  pageNum = 1;
  amount = 12;
}
setStorageData(pageNum, amount);

document.querySelectorAll('.page-nation li a').forEach(a=>{
  a.addEventListener('click', (e)=>{
    e.preventDefault();
    let pageNum = a.getAttribute('href');
    let sendData = `pageNum=${pageNum}&amount=${amount}`;
    location.href = '/board/list?' + sendData;
    console.log(pageNum);
    
  })
})