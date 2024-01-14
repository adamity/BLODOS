<%
    String action = session == null ? null : (String) session.getAttribute("action");
    String title = session == null ? null : (String) session.getAttribute("title");
%>

<!DOCTYPE html>
<html lang="en">

<head>
    <jsp:include page="layouts/head.jsp"/>
    <title>BLODOS - ${title != null ? title : 'Login'}</title>
</head>

<body class="bg-light-subtle">
    <jsp:include page="layouts/navbar.jsp"/>

    <main>
        <jsp:include page="pages/${action != null ? action : 'login'}.jsp"/>
    </main>

    <jsp:include page="layouts/footer.jsp"/>
</body>

</html>