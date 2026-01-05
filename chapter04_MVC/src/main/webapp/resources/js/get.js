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
    }
  })
})

function modify(){
  location.href = `/board/modify?bno=` + f.bno.value;
}

//------------------댓글 관련 스크립트
const rs = replyService;  //reply.js에서 CURD 담당 객체
 rs.add(
   {
     bno : f.bno.value,
     reply : 'JS TEST',
     replyer : 'tester'
   }, 
   function(result){
     console.log(result);
   }
 );

 rs.getList(f.bno.value,
   function(result){
     console.log(result);
   });

 rs.remove(1,
   function(result){
     console.log(result);
   });

 rs.update(2, 'FIX',
   function(result){
     console.log(result);
   }
 )

 rs.get(4,
   function(result){
     console.log(result);
   }
 )