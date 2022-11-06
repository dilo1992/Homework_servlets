<%--
  Created by IntelliJ IDEA.
  User: dmitrylobov
  Date: 30.10.22
  Time: 22:10
  To change this template use File | Settings | File Templates.
--%>
<body>
<h2>com.lobov.Student List</h2>
<table>
    <tr>
        <th>id</th>
        <th>firstName</th>
        <th>lastName</th>
        <th></th>
    </tr>
    <c:forEach var="student" items="${student}">
        <tr>
            <td>${student.id}</td>
            <td>${student.firstName}</td>
            <td>${student.lastName}</td>
            <td>
                <form method="post">
                    <input type="hidden" name="id" value="${student.id}">
                </form>
            </td>
        </tr>
    </c:forEach>
</table>
</body>
</html>
