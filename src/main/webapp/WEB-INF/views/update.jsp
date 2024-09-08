<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  User: shahriarmohaiminul
  Date: 9/8/24
  Time: 1:28 PM
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>Library Update</title>
    <style>
        /* Center container styling */
        .center-container {
            display: flex;
            flex-direction: column;
            justify-content: center;
            align-items: center;
            height: 100vh;
            font-family: Arial, sans-serif;
            background-color: #f4f4f4;
        }

        /* Form and content styling */
        .content-box {
            background-color: white;
            padding: 20px;
            border-radius: 10px;
            box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
            text-align: center;
            width: 400px;
        }

        .content-box h2 {
            margin-bottom: 20px;
        }

        /* Align text left */
        .align-left {
            margin-top: 20px;
            text-align: left;
            width: 100%;
        }

        .align-right {
            margin-top: 20px;
            text-align: right;
            width: 100%;
        }

        /* Button styling */
        .button {
            padding: 10px 20px;
            background-color: #4CAF50;
            color: white;
            border: none;
            border-radius: 5px;
            cursor: pointer;
            font-size: 16px;
        }

        .button:hover {
            background-color: #45a049;
        }

        /* Text styling */
        .label {
            font-weight: bold;
            margin-right: 10px;
        }

        .field-value {
            font-size: 16px;
            color: #333;
        }
        table {
            width: 80%;
            border-collapse: collapse;
            margin: 20px auto;
            font-family: Arial, sans-serif;
        }

        th, td {
            padding: 10px;
            text-align: left;
            border: 1px solid #ddd;
        }

        th {
            background-color: #4CAF50;
            color: white;
        }

        tr:nth-child(even) {
            background-color: #f2f2f2;
        }

        tr:hover {
            background-color: #ddd;
        }
    </style>
</head>
<body>
<form:form action="library/update" method="post" modelAttribute="library">
    <div class="center-container">
        <div class="content-box">
            <h2>Update Library</h2>

            <form:hidden path="id"/>

            <p>
                <span class="label">Library Name:</span>
                <span class="field-value"><c:out value="${library.name}"/></span>
            </p>

            <p>
                <span class="label">Update Count:</span>
                <span class="field-value"><c:out value="${library.updateCount}"/></span>
            </p>

            <table>
                <thead>
                <tr>
                    <th>ID</th>
                    <th>Title</th>
                    <th>Update Count</th>
                </tr>
                </thead>
                <tbody>

                <c:forEach var="book" items="${library.books}">
                    <tr>
                        <td><c:out value="${book.id}" /></td>
                        <td><c:out value="${book.title}" /></td>
                        <td><c:out value="${book.updateCount}" /></td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>

            <div class="align-right">
                <button name="_action_update" type="submit" class="button">Update</button>
            </div>
        </div>
    </div>
</form:form>
</body>
</html>