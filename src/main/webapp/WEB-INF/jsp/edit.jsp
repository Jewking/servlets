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
    UserModel user = (UserModel) request.getAttribute("user");
    String error = (String) request.getAttribute("error");
%>
<div class="container-xl">
    <div class="table-responsive">
        <div class="table-wrapper">
            <div class="table-title">
                <div class="row">
                    <div class="col-sm-8"><a href="/testapp/main?command=Home"><h2>Home <b>page</b></h2></a></div>
                    <div class="col-sm-4">
                        <form id="add_new" method="get" action="">
                        <div class="search-box">
                            <form id="delete" method="get" action="testapp/main">
                                <button class="btn btn-warning" value="Delete" name="command">DELETE COMPLETELY</button>
                                <input type="hidden" id="action" name="id" value="${user.getId()}">
                            </form>
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
                        <th>ID</th>
                        <th>Name</th>
                        <th>Action</th>
                        <th>Age</th>
                        <th>Action</th>
                    </tr>
                </thead>
                <tbody>
                    <tr>
                        <td>${user.getId()}</td>
                        <td>${user.getName()}</td>
                        <td></td>
                        <td>${user.getAge()}</td>
                        <td><form id="disable" method="get" action="">
                            <button class="btn btn-danger" value="Disable" name="command">DISABLE</button>
                            <input type="hidden" id="action" name="id" value="${user.getId()}">
                        </form></td>
                    </tr>
                    <tr>
                        <td>#</td>
                        <form id="edit_name" method="get" action="">
                        <td><input name="name" required minlength="2" maxlength="24" type="text" class="form-control" placeholder="${user.getName()}&hellip;"></td>
                        <td>
                            <button type="submit" class="btn btn-success" value="EditName" name="command"><i class="material-icons">save</i> SAVE</button>
                            <input type="hidden" id="action" name="id" value="${user.getId()}">
                        </td>
                        </form>
                        <form id="edit_age" method="get" action="">
                        <td><input name="age" required type="number" min="15" max="55" class="form-control" placeholder="${user.getAge()}&hellip;"></td>
                        <td>
                            <button type="submit" class="btn btn-success" value="EditAge" name="command"><i class="material-icons">save</i> SAVE</button>
                            <input type="hidden" id="action" name="id" value="${user.getId()}">
                        </td>
                        </form>
                    </tr>
                </tbody>
            </table>
            <div class="clearfix">
                <a href="http://localhost:8090/testapp/api/user/list" class="link hint-text"><i class="material-icons">rss_feed</i> USER <b>API</b></a>
                <ul class="pagination"><div class="hint-text">by <b>Leonid Rakitin</b></div></ul>
            </div>
        </div>
    </div>
</div>
</body>
</html>
