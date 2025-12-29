// .header 하위에 있는 a태그들에 버튼 클릭 이벤트 부여
// 기본 이벤트 방지
// 해당 속성에서 값 꺼내서 분기 태우기
document.querySelectorAll('.header a').forEach(a=>{
	  a.addEventListener('click', (e)=>{
		    e.preventDefault();
		    let menu = e.target.getAttribute('href');
		    if(menu === 'mainPage'){
		      location.href = '/';
		    }else if(menu === 'boardList'){
		      location.href = '/board/list';
		    }
		})
})


function setStorageData(pageNum, amount){
	let pageData = {
		pageNum : pageNum,
		amount : amount
	};
	localStorage.setItem(
		'page_data', JSON.stringify(pageData)
	);
}

function getStorageData(){
	return JSON.parse(localStorage.getItem('page_data'));
}

// 메뉴에 버튼이 있기 때문에 무조건 가능(만약 버튼이 없는 페이지라면 null 처리를 해줘야 함)
document.querySelectorAll('button').forEach(btn=>{
	btn.addEventListener('click', e =>{
		let type = e.target.id;
		if(type === 'indexBtn'){
      const {pageNum, amount} = getStorageData();
      const sendData = `pageNum=${pageNum}&amount=${amount}`;
      location.href = "/board/list?" + sendData;
		}
	})
})