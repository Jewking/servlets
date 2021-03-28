<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="com.anderson.model.UserModel" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <title>HomeWork</title>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Roboto">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://fonts.googleapis.com/icon?family=Material+Icons">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
    <script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.min.js"></script>
<style>
body {
    color: #566787;
    background: #f5f5f5;
    font-family: 'Roboto', sans-serif;
}
.table-responsive {
    margin: 30px 0;
}
.table-wrapper {
    min-width: 1000px;
    background: #fff;
    padding: 20px;
    box-shadow: 0 1px 1px rgba(0,0,0,.05);
}
.table-title {
    padding-bottom: 10px;
    margin: 0 0 10px;
    min-width: 100%;
}
.table-title h2 {
    margin: 8px 0 0;
    font-size: 22px;
}
.search-box {
    position: relative;
    float: right;
}
.search-box input {
    height: 34px;
    padding-left: 35px;
    border-color: #ddd;
    box-shadow: none;
}
.search-box input:focus {
    border-color: #3FBAE4;
}
.search-box i {
    color: #a0a5b1;
    position: absolute;
    font-size: 19px;
    top: 8px;
    left: 10px;
}
table.table tr th, table.table tr td {
    border-color: #e9e9e9;
}
table.table-striped tbody tr:nth-of-type(odd) {
    background-color: #fcfcfc;
}
table.table-striped.table-hover tbody tr:hover {
    background: #f5f5f5;
}
table.table th i {
    font-size: 13px;
    margin: 0 5px;
    cursor: pointer;
}
table.table td:last-child {
    width: 130px;
}
table.table td a {
    color: #a0a5b1;
    display: inline-block;
    margin: 0 5px;
}
table.table td a.view {
    color: #03A9F4;
}
table.table td a.edit {
    color: #0062cc;
}
table.table td a.delete {
    color: #E34724;
}
table.table td a.create {
    color: #1c9036;
}
table.table td a.return {
    color: #000000;
}
table.table td i {
    font-size: 20px;
}
.pagination {
    float: right;
    margin: 0 0 5px;
}
.pagination li a {
    border: none;
    font-size: 95%;
    width: 30px;
    height: 30px;
    color: #999;
    margin: 0 2px;
    line-height: 30px;
    border-radius: 30px !important;
    text-align: center;
    padding: 0;
}
.pagination li a:hover {
    color: #666;
}
.pagination li.active a {
    background: #03A9F4;
}
.pagination li.active a:hover {
    background: #0397d6;
}
.pagination li.disabled i {
    color: #ccc;
}
.pagination li i {
    font-size: 16px;
    padding-top: 6px
}
.hint-text {
    float: left;
    margin-top: 6px;
    font-size: 95%;
}
.error {
  color: red;
}
</style>
<script>
$(document).ready(function(){
	$('[data-toggle="tooltip"]').tooltip();
});
</script>
</head>
<body>
<%
    List<UserModel> users = (List) request.getAttribute("users");
    String error = (String) request.getAttribute("error");
%>
<div class="container-xl">
    <div class="table-responsive">
        <div class="table-wrapper">
            <div class="table-title">
                <div class="row">
                    <div class="col-sm-8"><a href="/testapp/main?command=Index"><h2>Home <b>Work</b></h2></a></div>
                    <div class="col-sm-4">
                        <form id="add_new" method="get" action="testapp/main">
                        <div class="search-box">
                            <i class="material-icons">&#xE8B6;</i>
                            <input name="search" type="text" class="form-control" placeholder="Search&hellip;">
                            <button class="btn" value="Search" name="command"></button>
                        </div>
                        </form>
                    </div>
                </div>
            </div>
            <c:if test="${not empty error}">
                <span class="error"><i class="material-icons">error_outline</i> ERROR: ${error}!</span>
            </c:if>
            <table class="table table-striped table-hover table-bordered">
                <thead>
                    <tr>
                        <th>#</th>
                        <th>Name <i class="fa fa-sort"></i></th>
                        <th>Age <i class="fa fa-sort"></i></th>
                        <th>Status <i class="fa fa-sort"></i></th>
                        <th>Actions</th>
                    </tr>
                </thead>
                <tbody>
                <c:forEach var="user" items="${users}" step="1" varStatus="count">
                    <tr>
                        <td>${count.count}</td>
                        <td>${user.getName()}</td>
                        <td>${user.getAge()}</td>
                        <td><c:choose>
                                <c:when test="${user.getStatus() == true}">
                                    <span class="badge badge-success">Active</span>
                                </c:when>
                                <c:otherwise>
                                    <span class="badge badge-danger">Removed</span>
                                </c:otherwise>
                            </c:choose>
                        </td>

                        <td><c:choose>
                                <c:when test="${user.getStatus() == true}">
                                    <button type="submit" class="btn"><a href="#" class="edit" title="Edit" data-toggle="tooltip"><i class="material-icons">settings</i></a></button>
                                </c:when>
                                <c:otherwise>
                                    <button type="submit" class="btn"><a href="#" class="return" title="Return" data-toggle="tooltip"><i class="material-icons">rotate_right</i></a></button>
                                </c:otherwise>
                            </c:choose>
                        </td>
                    </tr>
                </c:forEach>
                    <tr>
                    <form id="add_new" method="get" action="testapp/main">
                        <td>#</td>
                        <td><input name="name" required minlength="2" maxlength="24" type="text" class="form-control" placeholder="Name&hellip;"></td>
                        <td><input name="age" required type="number" min="15" max="55" class="form-control" placeholder="Age&hellip;"></td>
                        <td><a href="javascript:{}" onclick="document.getElementById('add_new').submit(); return false;" class="create submit" title="Create" data-toggle="tooltip"></a></td>
                        <td>
                            <button name="command" value="Add" type="submit" class="btn"><a href="" class="create" data-toggle="tooltip" data-original-title="Create"><i class="material-icons">person_add</i></a></button>
                        </td>
                    <form
                    </tr>
                </tbody>
            </table>
            <div class="clearfix">
                <i class="material-icons">filter_drama</i> <i>Based on Google Cloud</i>
                <ul class="pagination"><div class="hint-text">by <b>Leonid Rakitin</b></div></ul>
            </div>
        </div>
    </div>
</div>
</body>
</html>
