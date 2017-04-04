<%@ page pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
<title>Computer Database</title>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta charset="utf-8">
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
        <c:if test="${error != null}">
   			<div class="error">${error}<img id="fermer" alt="close" src="<c:url value="/resources/fonts/fermer.png" />"></div>
		</c:if>
            <h1 id="homeTitle">
                ${ nbComputer } Computers found
            </h1>
            <div id="actions" class="form-horizontal">
                <div class="pull-left">
                    <form id="Search" action="Search" method="GET" class="form-inline">

                        <input type="search" id="searchbox" name="search" class="form-control" placeholder="Search name" />
                        <input type="submit" id="searchsubmit" value="Filter by name"
                        class="btn btn-primary" />
                    </form>
                </div>
                <div class="pull-right">
                    <a class="btn btn-success" id="addComputer" href="addComputer">Add Computer</a> 
                    <a class="btn btn-default" id="editComputer" href="#" onclick="$.fn.toggleEditMode();">Edit</a>
                </div>
            </div>
        </div>

        <form:form id="deleteForm" action="/computer-database-db/delete" commandName="computer" method="POST">
            <input type="hidden" id="selection" name="selection" value="">
        </form:form>

        <div class="container" style="margin-top: 10px;">
            <table class="table table-striped table-bordered">
                <thead>
                    <tr>
                        <!-- Variable declarations for passing labels as parameters -->
                        <!-- Table header for Computer Name -->

                        <th class="editMode" style="width: 60px; height: 22px;">
                            <input type="checkbox" id="selectall" /> 
                            <span style="vertical-align: top;">
                                 -  <a id="deleteSelected" onclick="$.fn.deleteSelected();">
                                        <i class="fa fa-trash-o fa-lg"></i>
                                    </a>
                            </span>
                        </th>
                        <th>
                            Computer name
                        </th>
                        <th>
                            Introduced date
                        </th>
                        <!-- Table header for Discontinued Date -->
                        <th>
                            Discontinued date
                        </th>
                        <!-- Table header for Company -->
                        <th>
                            Company
                        </th>

                    </tr>
                </thead>
                <!-- Browse attribute computers -->
                <tbody id="results">
                <c:forEach items="${ list }" var="computer">
                    <tr>
                        <td class="editMode">
                            <input type="checkbox" name="cb" class="cb" value="${ computer.getId() }">
                        </td>
                        <td>
                            <a href="/computer-database-db/editComputer/id/${ computer.getId() }" onclick=""><c:out value="${ computer.getName() }" /></a>
                        </td>
                        <td>
						        <p>${ computer.getIntroduced() }</p>
                        </td>
                        <td>
						        <p>${ computer.getDiscontinued() }</p>
                        </td>
                        <td>
						        <p>${ computer.getCompany().getName() }</p>
                        </td>
                    </tr>
                    </c:forEach>
                </tbody>
            </table>
        </div>
    </section>

    <footer class="navbar-fixed-bottom">
        <div class="container text-center">
            <ul class="pagination">
                <li>
                    <a href="/computer-database-db/current/${ current - 1 }/range/${ range }" aria-label="Previous">
                      <span aria-hidden="true">&laquo;</span>
                  </a>
              </li>
              <c:forEach var="i" begin="1" end="${ nbPage }" step="1">
              <c:choose>
				<c:when test="${ (i>current+3)||(i<current-3) }">
				
				</c:when>
				<c:otherwise>
				    <li><a href="/computer-database-db/current/${ i }/range/${ range }">${ i }</a></li>
				</c:otherwise>
			  </c:choose>
              
              </c:forEach>
              <li>
                <a href="/computer-database-db/current/${ current + 1 }/range/${ range }" aria-label="Next">
                    <span aria-hidden="true">&raquo;</span>
                </a>
            </li>
        </ul>
        <div class="btn-group btn-group-sm pull-right" role="group" >
            <a type="button" class="btn btn-default" href="/computer-database-db/current/${ current }/range/10">10</a>
            <a type="button" class="btn btn-default" href="/computer-database-db/current/${ current }/range/50">50</a>
            <a type="button" class="btn btn-default" href="/computer-database-db/current/${ current }/range/100">100</a>
        </div>
	</div>
    </footer>
<script src="<c:url value="/resources/js/jquery.min.js" />"></script>
<script src="<c:url value="/resources/js/bootstrap.min.js" />"></script>
<script src="<c:url value="/resources/js/dashboard.js" />"></script>

</body>
</html>