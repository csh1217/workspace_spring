const CSS_FILE_PATH = '/resources/css/register.css';
let linkEle = document.createElement('link');
linkEle.rel = 'stylesheet';
linkEle.type = 'text/css';
linkEle.href = CSS_FILE_PATH;
document.head.appendChild(linkEle);

// 버튼 클릭 이벤트
let f = document.forms[0];
document.querySelectorAll('button').forEach(btn=>{
  btn.addEventListener('click', (e)=>{
    let type = e.target.id;
    if(type === "registerBtn"){
      register();
    }else if(type === "resetBtn"){
      f.reset();
    }
  })
})

function register() {
  //각 데이터 검증 후 데이터 전송
  
   if(!f.title.value ||!f.writer.value ||!f.content.value){
    alert("정보 입력 오류!");
    return;
  }
  f.action = "/board/register";
  f.submit();
}