<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

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
    <jsp:include page="layouts/alert.jsp"/>

    <main>
        <div class="container my-5 min-height-75vh">
            <jsp:include page="pages/${action != null ? action : 'login'}.jsp"/>
        </div>
    </main>

    <jsp:include page="layouts/footer.jsp"/>
</body>

</html>