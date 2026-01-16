import {displayTime} from './util.js'
const CSS_FILE_PATH = ['/resources/css/get.css', '/resources/css/modal.css'];
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

const replyUL = document.querySelector('.chat');// 댓글 목록 창
const replyUL2 = document.querySelector('.modal-title');// 댓글 등록 제목
replyUL.addEventListener('click', e =>{ // 댓글 목록 창 클릭 이벤트 설정
  const li = e.target.closest('li[data-rno]');
  if (!li) return;
  modifyModalPage(li);
})

let f = document.forms[0];
document.querySelectorAll('button').forEach(btn=>{
  btn.addEventListener('click', (e)=>{
    let type = e.target.id;
    if(type === "modifyBtn"){
      modify();
    }else if(type == 'replyBtn'){
      registerModalPage();
    }else if(type == 'addReplyBtn'){
      registerReply();
    }else if(type == 'closeModalBtn'){
      closeModal();
    }else if(type == 'modifyReplyBtn'){
      modifyReply();
    }else if(type == 'removeReplyBtn'){
      removeReply();
    }
  })
})

function modify(){
  location.href = `/board/modify?bno=` + f.bno.value;
}

//------------------댓글 관련 스크립트
const rs = replyService;  //reply.js에서 CURD 담당 객체

function showList(){
  let bno = f.bno.value;

  rs.getList(bno, jsonArray =>{
    let msg = ''; //li들을 문자열로 변형하기 위한 용도
    jsonArray.forEach(reply =>{
      if(!reply){
        replyUL.innerHTML = '';
        return;
      }

      msg += `<li data-rno="${reply.rno}">`;
      msg += `<div>`;
      msg += `<div class="chat-header">`;
      msg += `<strong>${reply.replyer}</strong>`;
      msg += `<small class="pull-right">${displayTime(reply.replydate)}</small>`;
      msg += `</div>`;
      msg += `<p>${reply.reply}</p>`;
      msg += `</div>`;
      msg += `</li>`;

    });
    replyUL.innerHTML = msg;
  })
}
showList();

// ------------------------- modal 관련 스크립트-----------------------
const modal = document.querySelector('#modal');
const inputReply = document.querySelector('input[name="reply"]');
const inputReplyer = document.querySelector('input[name="replyer"]');
const inputReplydate = document.querySelector('input[name="replydate"]');
const addReplyBtn = document.querySelector('#addReplyBtn');
const modifyReplyBtn = document.querySelector('#modifyReplyBtn');
const removeReplyBtn = document.querySelector('#removeReplyBtn');

function openModal(){
  modal.style.display = "block";
  document.body.style.overflow = "hidden";
}
function closeModal(){
  modal.style.display = "none";
  document.body.style.overflow = "auto";
}

// 댓글 등록 창 함수
function registerModalPage(){
  // 보여질 목록 수정
  regReplyModalStyle();
  replyUL2.innerHTML = '<a>새 댓글 등록</a>';
  // 입력 내용 초기화 & 불러오기
  inputReply.value = '';
  inputReplyer.value = `${principal.username}`;

  openModal();
}

function regReplyModalStyle(){
  if(principal.username == inputReplyer){
    modifyReplyBtn.classList.add('hide'); // classList : 클래스관리
    removeReplyBtn.classList.add('hide'); 
  }
  addReplyBtn.classList.remove('hide');
  inputReplyer.readOnly = true;
  inputReplydate.closest('div').classList.add('hide'); 
}

// 댓글 등록 함수
function registerReply(){
  if(!inputReply.value || !inputReplyer.value){
    alert("모든 내용을 입력하세요");
    return;
  }
  const sendData = {
    reply : inputReply.value,
    replyer : inputReplyer.value,
    bno : f.bno.value
  };
  rs.add(sendData, result =>{
    closeModal();
    showList();
  })
}

// 댓글 클릭 함수
let rno;
function modifyModalPage(li){
  rno = li.dataset.rno;
  const reply = li.querySelector('p').textContent;
  const replyer = li.querySelector('strong').textContent;
  const replydate = li.querySelector('small').textContent;
  
  replyUL2.innerHTML = '<a>댓글 삭제 & 수정</a>';
  // 보여질 목록 수정
  modReplyModalStyle();
  // 입력 내용 초기화 & 불러오기
  inputReply.value = `${reply}`;
  inputReplyer.value = `${replyer}`;
  inputReplydate.value = `${replydate}`;
  openModal();
}

function modReplyModalStyle(){
  if(principal.username == inputReplyer){
    modifyReplyBtn.classList.remove('hide');
    removeReplyBtn.classList.remove('hide');
  }
  addReplyBtn.classList.add('hide');
  inputReplyer.readOnly = true;
  inputReplydate.readOnly = true;
  inputReplydate.closest('div').classList.remove('hide'); 
}

function modifyReply(){
  if(!inputReply.value){
    alert("내용을 입력하세요.")
    return;
  }
  rs.update(rno, inputReply.value, result =>{
    closeModal();
    showList();
  })
}

function removeReply(){
  const check = confirm("삭제하시겠습니까?")
  if(check === true){
    rs.remove(rno, result =>{
      closeModal();
      showList();
    })
  }else{
    return;
  }
}

// ------------------- 첨부파일 관련 스크립트

// 파일 리스트 콘솔에 출력
(function(){
  
  fetch(`/board/getAttachList/${f.bno.value}`)
  .then(response=> response.json())
  .then(data => {
    showUploadedFile(data);
  })
  .catch(err => console.log(err));
})();

let uploadResult = document.querySelector('.uploadResult ul')
function showUploadedFile(uploadResultArr){
  
  let str = ``;
  uploadResultArr.forEach(file => {
    let fileCallPath = encodeURIComponent( // 인코딩 : &같은 특수 문자를 기존 문법처럼 인식하지 않도록 하게 해줌
      file.uploadPath + "/" +
      file.uuid + "_" +
      file.fileName
    );
    
    str += `<li path="${file.uploadPath}" uuid="${file.uuid}" fileName="${file.fileName}">`
    str += `<a href="/download?fileName=${fileCallPath}">`;
    str += `${file.fileName}`;
    str += `</a>`;
    //str += `<span data-file="${fileCallPath}"> X</span>`;
    str += `</li>`;
  });
  
  uploadResult.innerHTML = str;
  
}


// rs.add(
//   {
//     bno : f.bno.value,
//     reply : 'JS TEST',
//     replyer : 'tester'
//   }, 
//   function(result){
//     console.log(result);
//   }
// );
//
// rs.getList(f.bno.value,
//   function(result){
//     console.log(result);
//   });
//
// rs.remove(1,
//   function(result){
//     console.log(result);
//   });
//
// rs.update(2, 'FIX',
//   function(result){
//     console.log(result);
//   }
// )
//
// rs.get(4,
//   function(result){
//     console.log(result);
//   }
// )