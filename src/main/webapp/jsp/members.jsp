<%@ page import="java.util.List" %>
<%@ page import="hello.servlet.domain.member.MemberRepository" %>
<%@ page import="hello.servlet.domain.member.Member" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    //import해서 필요한 데이터 가져옴
    MemberRepository memberRepository = MemberRepository.getInstance();
    List<Member> members = memberRepository.findAll();
%>
<html>
<head>
    <meta charset="UTF-8">
    <title>Title</title>
</head>
<body>
<a href="/index.html">메인</a>
<table>
    <thead>
    <th>id</th>
    <th>username</th>
    <th>age</th>
    </thead>
    <tbody>
<%
    //프로그램로직으로 루프돌리기
    for (Member member : members) { //members에서 member를 얻어와서 for문 돌림
    out.write("<tr>");
    out.write("     <td>" + member.getId() + "</td>");
    out.write("     <td>" + member.getUsername() + "</td>");
    out.write("     <td>" + member.getAge() + "</td>");
    out.write("</tr>");
    }
%>
    </tbody>
</table>
</body>
</html>