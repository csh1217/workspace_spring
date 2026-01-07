export function displayTime(unixTimeStamp){
  let myDate = new Date(unixTimeStamp);
  let y = myDate.getFullYear();
  let m = String(myDate.getMonth() + 1).padStart(2,'0');
  let d = String(myDate.getDate()).padStart(2,'0');
  let date = y + '-' + m + '-' + d;
  return date;
}