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
    }else if(type === "indexBtn"){
      location.href = "/board/list"
    }
  })
})

function modify(){
  location.href = `/board/modify?bno=` + f.bno.value;
}

