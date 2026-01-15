const f = document.forms[0];
const uIdValidState = document.querySelector("#uIdValidState");
const uPwValidState = document.querySelector("#uPwValidState");
const uPwReValidState = document.querySelector("#uPwReValidState");
const uEmailValidState = document.querySelector("#uEmailValidState");
const uPhValidState = document.querySelector("#uPhValidState");
let idCk = pwCk = pwReCk = emailCk = ph_numCk = false;

const regExpId = /^[a-z]+[0-9a-z]{3,12}$/;
const regExpPw = /^[0-9a-zA-Z]{8,16}$/;
const regExpEmail = /^[a-zA-Z0-9+-\_.]+@[a-zA-Z0-9-]+\.[a-zA-Z0-9-.]+$/;
const regExpPh_num = /^\d{3}-\d{3,4}-\d{4}$/;

document.querySelectorAll("button").forEach(btn => {
	btn.addEventListener('click', (e) => {
		e.preventDefault();

		let type = btn.id;
		if (type === 'duplicateCkBtn') { //X
			validateId();
		} else if (type === 'signupBtn') { //X
			signup();
		} else if (type === 'resetBtn') {  //O
			f.reset();
		} else if (type === 'loginBtn') {  //O
			location.href = '../UserController?cmd=loginPage';
		}
	})
})

// 데이터 검증 완료 함수
function validated(inputTarget, resultState, comment) {
	inputTarget.classList.add("is-valid");
	inputTarget.classList.remove("is-invalid");
	if (resultState) {
		resultState.classList.add("valid-feedback");
		resultState.classList.remove("invalid-feedback");
		comment ?
			resultState.innerHTML = comment :
			resultState.innerHTML = '';
	}
}
// 데이터 검증 미완료 함수
function invalidate(inputTarget, resultState, comment) {
	inputTarget.classList.remove("is-valid");
	inputTarget.classList.add("is-invalid");
	if (resultState) {
		resultState.classList.remove("valid-feedback");
		resultState.classList.add("invalid-feedback");
		comment ?
			resultState.innerHTML = comment :
			resultState.innerHTML = '';
	}
}
// 검증 스타일 초기화 함수
function Initialization(inputTarget, resultState) {
	inputTarget.classList.remove("is-valid");
	inputTarget.classList.remove("is-invalid");
	if (resultState) {
		resultState.classList.remove("valid-feedback");
		resultState.classList.remove("invalid-feedback");
		resultState.innerHTML = '';
	}
}

function validateId() {
	let target = f.uId;
	if (target.value == '') {
		Initialization(target, uIdValidState);
		alert("아이디를 입력하시오.");
		idCk = false;
		return;
	} else if (!regExpId.exec(target.value)) {
		invalidate(target, uIdValidState, "형식에 맞지 않는 아이디입니다.");
		idCk = false;
		return;
	}
	const params = {
		cmd: 'validateId',
		uId: target.value
	};
	const queryString = Object.keys(params)
		.map(
			key => encodeURIComponent(key) + "=" +
				encodeURIComponent(params[key])
		)
		.join('&');

	fetch(`/MFF/UserControllerAJAX?${queryString}`)
		.then(response => response.json())
		.then(data => {
			if (data.result == 1) {
				invalidate(target, uIdValidState, "이미 사용중인 아이디입니다.");
				idCk = false;
			} else {
				validated(target, uIdValidState, "사용 가능한 아이디입니다.");
				idCk = true;
			}
		})
		.catch(err => console.log(err))
}

f.uPw.addEventListener('keyup', e => {
	let target = e.currentTarget;

	if (target.value == '') {
		Initialization(target, uPwValidState);
		pwCk = false;
	} else if (regExpPw.exec(target.value)) {
		validated(target, uPwValidState, "비밀번호 입력 완료");
		pwCk = true;
	} else {
		invalidate(target, uPwValidState, "올바른 형식이 아닙니다.");
		pwCk = false;
	}
});

f.uPwRe.addEventListener('keyup', e => {
	let target = e.currentTarget;

	if (target.value == '') {
		Initialization(target, uPwReValidState);
		pwReCk = false;
	} else if (target.value === f.uPw.value) {
		validated(target, uPwReValidState, "비밀번호가 일치합니다.");
		pwReCk = true;
	} else {
		invalidate(target, uPwReValidState, "비밀번호가 일치하지 않습니다.");
		pwReCk = false;
	}
});

f.uEmail.addEventListener('input', e => {
	let target = e.currentTarget;

	if (target.value == '') {
		Initialization(target, uEmailValidState);
		emailCk = false;
	} else if (regExpEmail.exec(target.value)) {
		validated(target, uEmailValidState, "이메일 입력 완료");
		emailCk = true;
	} else {
		invalidate(target, uEmailValidState, "이메일 형식이 올바르지 않습니다.");
		emailCk = false;
	}
});

f.uPh.addEventListener('input', e => {
	let target = e.currentTarget;

	if (target.value == '') {
		Initialization(target, uPhValidState);
		ph_numCk = false;
	} else if (regExpPh_num.exec(target.value)) {
		validated(target, uPhValidState, "전화번호 입력 완료");
		ph_numCk = true;
	} else {
		invalidate(target, uPhValidState, "전화번호 형식이 올바르지 않습니다.");
		ph_numCk = false;
	}
});

function signup() {
	if (!idCk || !pwCk || !pwReCk || !emailCk || !ph_numCk) {
		alert('입력 내용을 확인해주십시오.');
		return;
	}
	let formData = new FormData(f);
	let jsonData = JSON.stringify(
		Object.fromEntries(formData.entries())
	);
	fetch('/MFF/UserControllerAJAX', {
		method: 'POST',
		body: jsonData,
		headers: {
			'Content-type': 'application/json; charset=UTF-8'
		}
	})
		.then(response => response.json())
		.then(data => {
			if (data.result == 1) {
				alert("!회원가입 성공!");
				location.href = "/MFF/UserController?cmd=loginPage";
			} else {
				alert("!회원가입 실패!");
			}
		})
		.catch(err => console.log(err));
	console.log(jsonData);

}