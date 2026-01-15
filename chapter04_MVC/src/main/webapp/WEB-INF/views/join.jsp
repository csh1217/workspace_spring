<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>signUp</h1>
	<form method="post">
		<table>
			<thead>
				<th>
					<td colspan="2">회원가입</td>
				<th>
			</thead>
			<tbody>
				<tr>
					<td>이름</td>
					<td>
						<input type="text" name="userName">
						<div class="feedback-space">
                			<div class="invalid-feedback" id="uNameValidState"></div>
              			</div>
					</td>
				</tr>
				<tr>
					<td>계정</td>
					<td>
						<input type="text" name="userId">
						<div class="feedback-space">
	                		<div class="invalid-feedback" id="uIdValidState"></div>
	              		</div>
	              	</td>
					<td><button type="button" id="validateId">중복 확인</button></td>
				</tr>
				<tr>
					<td>비밀번호</td>
					<td>
						<input type="password" name="userPw">
						<div class="feedback-space">
	                		<div class="invalid-feedback" id="uPwValidState"></div>
	              		</div>
              		</td>
				</tr>
				<tr>
					<td>비밀번호 확인</td>
					<td>
						<input type="password" name="userPwRe">
						<div class="feedback-space">
	                		<div class="invalid-feedback" id="uPwReValidState"></div>
	              		</div>
              		</td>
				</tr>
			</tbody>
		</table>
	</form>
	<button type="submit" id="join">회원가입</button>
	<button type="reset" id="reset">재작성</button>
</body>
	<script type="text/javascript" src="/resources/js/join.js"></script>
</html>