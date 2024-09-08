<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%--
  User: shahriarmohaiminul
  Date: 9/12/24
  Time: 7:14 PM
--%>

<html>
<head>
    <title>Library List</title>
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


<c:if test="${not empty message}">
    <div class="alert alert-success text-center" style="color: green; text-align: center">
        <c:out value="${message}"/>
    </div>
</c:if>

<table>
    <thead>
    <tr>
        <th>ID</th>

        <th>Update Count</th>

        <th>Issue Generated</th>

        <th>Action</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach items="${libraries}" var="library">
        <tr>
            <td><c:out value="${library.id}"/></td>

            <td><c:out value="${library.updateCount}"/></td>

            <td>
                <c:out value="${library.isIssueExists()}"/>
            </td>

            <td><a href="/lm/library/update/<c:out value="${library.id}"/>">Update</a></td>
        </tr>


    </c:forEach>
    </tbody>

</table>
<div style="text-align: right; margin-right: 150px">
    <a class="btn btn-default" href="/lm/library/create/">Create</a>
</div>

</body>
</html>