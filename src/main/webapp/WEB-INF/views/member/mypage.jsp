<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>마이페이지</title>
</head>
<body>
	<h1>마이페이지</h1>
	<form action ="/member/updateView.do" method = "post">
	
	아이디 : <input type="text" name = "memberId" value="${memberId }"> <br>
	비밀번호 : <input type="text" name = "memberId" value="${member.memberPw }"> <br>
	이름 : <input type="text" name = "memberId" value="${member.memberNAME }"> <br>
	나이 : <input type="text" name = "memberId" value="${member.memberAGE }"> <br>
	성별 : <input type="text" name = "memberId" value="${member.memberGender }"> <br>
	이메일 : <input type="text" name = "memberId" value="${member.memberEmail }"> <br>
	전화번호 : <input type="text" name = "memberId" value="${member.memberPhone }"> <br>
	주소 : <input type="text" name = "memberId" value="${member.memberAddress }"> <br> 
	취미 : <input type="text" name = "memberId" value="${member.memberHobby }"> <br>
	가입날짜 : <input type="text" name = "memberId" value="${member.memberDate }"> <br> 
	수정날짜 : <input type="text" name = "memberId" value="${memberupdateDate }"><br>
	회원여부 : <input type="text" name = "memberId" value="${member.memberYn }">
	 
	</form>
	<a href="/index.jsp"> 메인으로 이동</a>
	<a href="/member/updateView.do?memberId=${memberId }">수정하기</a>
	<a href="/member/delete.do?memberId=${memberId}"> 삭제하기</a>
</body>
</html>