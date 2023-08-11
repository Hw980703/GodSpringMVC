<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원정보 수정</title>
</head>
<body>
	<h1>회원정보 수정</h1>
	<form action = "/member/update.do" method = "POST">
	
	아이디 : <input type="text" name = "memberId" value="${memberId }" readonly> <br>
	이름 : <input type="text" name = "memberName" value="${member.memberNAME }" readonly> <br>
	나이 : <input type="text" name = "memberAge" value="${member.memberAGE }" readonly> <br>
	성별 : <input type="text" name = "memberGender" value="${member.memberGender }" readonly> <br>
	이메일 : <input type="text" name = "memberEmail" value="${member.memberEmail }"> <br>
	전화번호 : <input type="text" name = "memberPhone" value="${member.memberPhone }"> <br>
	주소 : <input type="text" name = "memberAddress" value="${member.memberAddress }"> <br> 
	취미 : <input type="text" name = "memberHobby" value="${member.memberHobby }"> <br>
	가입날짜 : <input type="text" name = "memberDate" value="${member.memberDate }" > <br> 
	 
	<a href="/index.jsp"> 메인으로 이동</a>
	 <input type="submit" value="수정하기">
	</form>
</body>
</html>