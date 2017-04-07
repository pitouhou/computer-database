<%@ page pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
<title>Computer Database</title>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<!-- Bootstrap -->
<link href="<c:url value="/resources/css/bootstrap.min.css" />" rel="stylesheet" media="screen">
<link href="<c:url value="/resources/css/font-awesome.css" />" rel="stylesheet" media="screen">
<link href="<c:url value="/resources/css/main.css" />" rel="stylesheet" media="screen">
</head>
<body>
    <header class="navbar navbar-inverse navbar-fixed-top">
        <div class="container">
            <a class="navbar-brand" href="dashboard.html"> Application - Computer Database </a>
        </div>
    </header>
    <section id="main">
        <div class="container">
            <div class="row">
                <div class="col-xs-8 col-xs-offset-2 box">
                    <div class="label label-default pull-right">
                        id: 0
                    </div>
                    <h1>Edit Computer</h1>

                    <form:form action="/computer-database-db/editComputer" commandName="computer" method="POST">
                        <form:input type="hidden" value="${ computerId }" path="id" id="computerId"/> <!-- TODO: Change this value with the computer id -->
                        <fieldset>
                            <div class="form-group">
                                <label for="computerName">Computer name</label>
                                <form:input type="text" class="form-control" id="computerName" value="${ computerName }" path="name" />
                            </div>
                            <div class="form-group">
                                <label for="introduced">Introduced date</label>
                                <form:input type="date" class="form-control" id="introduced" path="introduced" value="${ computerIntroduced }" />
                            </div>
                            <div class="form-group">
                                <label for="discontinued">Discontinued date</label>
                                <form:input type="date" class="form-control" id="discontinued" path="discontinued" value="${ computerDiscontinued }" />
                            </div>
                            <div class="form-group">
                                <label>Company</label>
                                <form:select path="company.id">
                                	<form:option value="0" label="--- Select ---"/>
   									<form:options items="${ list }" itemValue="id" itemLabel="name"/>
								</form:select>
                            </div>            
                        </fieldset>
                        <div class="actions pull-right">
                            <input type="submit" value="Edit" class="btn btn-primary">
                            or
                            <a href="dashboard" class="btn btn-default">Cancel</a>
                        </div>
                    </form:form>
                </div>
            </div>
        </div>
    </section>
</body>
</html>