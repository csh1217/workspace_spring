const replyService = (function(){

  // 추가
  function add(reply, callback){
    fetch('/reply/new',
      {
        method : 'post',
        body : JSON.stringify(reply),
        headers : {
          'Content-type' : 'application/json; charset=utf-8'
        }
      })
      .then(response => response.text())
      .then(data =>{
        callback(data);
      })
      .catch(err => console.log(err));
  }
  
  // 목록
  function getList(bno, callback){
    fetch(`/reply/pages/` + bno + '.json')
    .then(response => response.text())
    .then(data => {
      callback(data);
    })
    .catch(err => console.log(err));
  }

  // 삭제
  function remove(rno, callback){
    fetch('/reply/' + rno,
      {method : 'delete'})
      .then(response => response.text())
      .then(data =>{
        callback(data);
      })
      .catch(err => console.log(err));
  }

  // 수정
  function update(rno, reply, callback){
    fetch('/reply/' + rno,
      {
        method : 'put',
        headers: {
          'Content-Type': 'application/json; charset=utf-8'
        },
        body: JSON.stringify({
          rno: rno,
          reply: reply
        })
      })
      .then(response => response.text())
      .then(data =>{
        callback(data);
      })
      .catch(err => console.log(err));
  }

  // 조회
  function get(rno, callback){
    fetch(`/reply/` + rno + '.json')
    .then(response => response.text())
    .then(data => {
      callback(data);
    })
    .catch(err => console.log(err));
  }

  return {
    add : add,
    getList : getList,
    remove : remove,
    update : update,
    get: get
  };
})();
