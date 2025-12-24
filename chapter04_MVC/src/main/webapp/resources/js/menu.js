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
