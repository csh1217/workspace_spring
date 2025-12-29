const CSS_FILE_PATH = '/resources/css/get.css';
let linkEle = document.createElement('link');
linkEle.rel = 'stylesheet';
linkEle.type = 'text/css';
linkEle.href = CSS_FILE_PATH;
document.head.appendChild(linkEle);

let f = document.forms[0];
document.querySelectorAll('button').forEach(btn=>{
  btn.addEventListener('click', (e)=>{
    let type = e.target.id;
    if(type === "modifyBtn"){
      modify();
    }else if(type === "removeBtn"){
      remove();
    }
  })
})

function modify(){
  if(!f.title.value || !f.content.value){
    alert("내용을 모두 입력해주십시오.")
    return;
  }
  f.action = "/board/modify";
  f.submit();
}

function remove(){
  const bnoEle = f.bno;   // bno를 담고 있는 input 태그
  f.innerHTML = '';       // form 태그 내부 비우기
  f.appendChild(bnoEle);  // form 태그 내부에 bno 태그 추가
  const result = confirm("삭제하시겠습니까?")
  if(result === false){
    return;
  }
  f.action = "/board/remove";
  f.submit();
}
