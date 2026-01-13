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
    alert("내용을 모두 입력해주십시오.");
    return;
  }


  document.querySelectorAll('input[name="deleteUuid"]').forEach(el => el.remove());

  // deleteUuid 배열을 hidden input으로 추가
  deleteUuid.forEach(uuid => {
    const input = document.createElement('input');
    input.type = 'hidden';
    input.name = 'deleteUuid';   // controller 파라미터명과 동일
    input.value = uuid;
    f.appendChild(input);
  });

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

// -------------------------------- 첨부 파일 수정&삭제--------------------------------------

console.log('upload.js 실행....');

// 파일 업로드 
const regex = new RegExp("(.*?)\.(exe|sh|zip|alz)$");
const MAX_SIZE = 5242880; // 5MB

function checkExtenstion(fileName, fileSize){
  if(fileSize >= MAX_SIZE){
    alert("파일 사이즈 초과");
    return
  }
  if(regex.test(fileName)){
    alert("해당 종류의 파일은 업로드할 수 없습니다.")
    return;
  }
  return true;
}

// 비어있는 요소 복사해두기
let uploadDiv = document.querySelector(".uploadDiv");
// 하위 노드까지 복사
let cloneObj = uploadDiv.firstElementChild.cloneNode(true);
// 추가 파일 배열
let attachList = [];
// 삭제할 파일의 uuid 배열
let deleteUuid = [];

// 파일 리스트 출력
(function(){
  
  fetch(`/board/getAttachList/${f.bno.value}`)
  .then(response=> response.json())
  .then(data => {
    data.forEach(file => file.old = true);
    attachList = data;
    showUploadedFile(attachList);
  })
  .catch(err => console.log(err));
})();

// 실제 파일 업로드 이벤트
document.querySelector('input[type="file"]').addEventListener('change', e=>{
  const inputFile = document.querySelector('input[type="file"]');
  const formData = new FormData();
  const files = inputFile.files;

  for(let i=0; i<files.length; i++){

    if(!checkExtenstion(files[i].name, files[i].size)){
      return false;
    }
    formData.append('uploadFile', files[i]);
  }

  fetch(`/uploadAsyncAction`,
    {
      method : 'post',
      body : formData
    })
    .then(response => response.json()) // controller에서 받은 정보를 json으로 출력
    .then(data=>{
      attachList = attachList.concat(data)
      showUploadedFile(attachList);
    })
    .catch(err => console.log(err));

})

// 업로드 완료된 목록 보여주는 함수
let uploadResult = document.querySelector('.uploadResult ul')
function showUploadedFile(uploadResultArr){
  
  let str = ``;
  uploadResultArr.forEach(file => {
    let fileCallPath = encodeURIComponent( // 인코딩 : &같은 특수 문자를 기존 문법처럼 인식하지 않도록 하게 해줌
      file.uploadPath + "/" +
      file.uuid + "_" +
      file.fileName
    );
    
    str += `<li path="${file.uploadPath}" uuid="${file.uuid}" fileName="${file.fileName}" data-old="${file.old ? 'true' : 'false'}">`
    str += `${file.fileName}`;
    str += `<span data-file="${fileCallPath}"> X</span>`;
    str += `</li>`;
  });
  
  uploadResult.innerHTML = str;
}


uploadResult.addEventListener('click', e => {

  const span = e.target.closest('span');
  if (!span) return;

  const li = span.closest('li');

  const uuid = li.getAttribute('uuid');
  const fileName = li.getAttribute('fileName');
  const uploadPath = li.getAttribute('path');

  if (li.dataset.old === 'true') {
    deleteUuid.push(uuid);
  }

  attachList = attachList.filter(file =>
    !(file.uuid === uuid &&
      file.fileName === fileName &&
      file.uploadPath === uploadPath)
  );

  showUploadedFile(attachList);
});

