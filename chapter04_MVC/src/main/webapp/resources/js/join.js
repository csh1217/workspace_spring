let f = document.forms[0];
const uNameValidState = document.querySelector("#uNameValidState");
const uIdValidState = document.querySelector("#uIdValidState");
const uPwValidState = document.querySelector("#uPwValidState");
const uPwReValidState = document.querySelector("#uPwReValidState");
let nameCk = idCk = pwCk = pwReCk = false;

const regExpName = /^[가-힣]{2,6}$/;
const regExpId = /^[A-Za-z][A-Za-z\d]{5,13}$/;
const regExpPw = /^(?=.*[A-Za-z])(?=.*\d)(?=.*[!@#$%^&*])[A-Za-z\d!@#$%^&*]{8,16}$/

// ------------- 버튼 클릭 이벤트 ---------------------
document.querySelectorAll("button").forEach(btn =>{
  btn.addEventListener('click', (e)=>{
    e.preventDefault();
    let type = e.currentTarget.id;
    
    if(type === 'validateId'){
      validateId();
    }else if(type === 'join'){
      join();
    }else if(type === 'reset'){
      f.reset();
    }
  })
})


// ----------------------- 데이터 검증 ----------------------
// 데이터 검증 완료 함수
function validated(inputTarget, resultState, comment){
	inputTarget.classList.add("is-valid");
	inputTarget.classList.remove("is-invalid");
	if(resultState){
		resultState.classList.add("valid-feedback");
		resultState.classList.remove("invalid-feedback");
		comment ? 
			resultState.innerHTML = comment : 
				resultState.innerHTML = '' ;
	}
}
// 데이터 검증 미완료 함수
function invalidate(inputTarget, resultState, comment){
	inputTarget.classList.remove("is-valid");
	inputTarget.classList.add("is-invalid");
	if(resultState){
		resultState.classList.remove("valid-feedback");
		resultState.classList.add("invalid-feedback");
		comment ? 
			resultState.innerHTML = comment : 
				resultState.innerHTML = '' ;
	}
}
// 검증 스타일 초기화 함수
function Initialization(inputTarget, resultState){
	inputTarget.classList.remove("is-valid");
	inputTarget.classList.remove("is-invalid");
	if(resultState){
		resultState.classList.remove("valid-feedback");
		resultState.classList.remove("invalid-feedback");
		resultState.innerHTML = '';
	}
}

// ------------------------ 파라미터 별 검증 ----------------------

f.userName.addEventListener('input', e => {
	let target = e.currentTarget;
	
	if(target.value == ''){
		Initialization(target, uNameValidState);
		nameCk = false;
	}else if(regExpName.exec(target.value)){
		validated(target, uNameValidState,"이름 입력 완료");
		nameCk = true;
	}else{
		invalidate(target, uNameValidState, "이름 형식이 올바르지 않습니다.");
		nameCk = false;
	}
});

async function validateId(){
  let target = f.userId;
  if(target.value == ''){
    Initialization(target, uIdValidState);
    alert("아이디를 입력하시오.");
    idCk = false;
    return;
  }else if (!regExpId.exec(target.value)){
    invalidate(target, uIdValidState, "형식에 맞지 않는 아이디입니다.");
    idCk = false;
    return;
  }
	try {
		const userId = target.value
		const response = await fetch('/member/validate',{
			method : 'POST',
			headers : {'Content-Type' : 'application/x-www-form-urlencoded'},
			body : `userId=${encodeURIComponent(userId)}`
		});

		const text = await response.text();
		console.log("서버 응답 원문:", text);
		const result = Number(text);
		console.log("변환 후:", result);


		if (result === 1) {
      invalidate(target, uIdValidState, "이미 있는 아이디 입니다.");
      idCk = false;
    } else if (result === 0) {
      validated(target, uIdValidState, "사용 가능한 아이디입니다.");
      idCk = true;
    }
	} catch (e) {
		console.error(e);
	}
}

f.userPw.addEventListener('keyup', e => {
	let target = e.currentTarget;
	
	if(target.value == ''){
		Initialization(target, uPwValidState);
		pwCk = false;
	}else if(regExpPw.exec(target.value)){
		validated(target, uPwValidState, "비밀번호 입력 완료");
		pwCk = true;
	}else{
		invalidate(target, uPwValidState, "올바른 형식이 아닙니다.");
		pwCk = false;
	}
}); 

f.userPwRe.addEventListener('keyup', e => {
	let target = e.currentTarget;
	
	if(target.value == ''){
		Initialization(target, uPwReValidState);
		pwReCk = false;
	}else if(target.value === f.userPw.value){
		validated(target, uPwReValidState, "비밀번호가 일치합니다.");
		pwReCk = true;
	}else{
		invalidate(target, uPwReValidState, "비밀번호가 일치하지 않습니다.");
		pwReCk = false;
	}
});

function join(){

  //검증 확인
  if(!nameCk || !idCk || !pwCk || !pwReCk){
		alert('입력 내용을 확인해주십시오.');
		return;
	}
	f.action = "/member/join";
	f.submit();
}