<%@ include file="common/header.jspf"%>
<%@ include file="common/navigation.jspf"%>

<div class="container">
	<div>
		<a type="button" class="btn btn-primary btn-md" href="/add-project">Add Project</a>
	</div>
	<br>
	<div class="panel panel-primary">
		<div class="panel-heading">
			<h3>List of Projects</h3>
		</div>
		<div class="panel-body">
			<table class="table table-striped" >
			<caption> </caption>
				<thead>
					<tr>
					    <th>ID</th>
                        <th>Name</th>
                        <th>Manager</th>
                        <th>Start Date</th>
                        <th>Expected End Date</th>
                        <th>End Date</th>
                        <th>Description</th>
                        <th>Budget</th>
                        <th>Status</th>
                        <th>Risk</th>
						<th></th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${projects}" var="project">
						<tr>
							<td>${project.id}</td>
                            <td>${project.name}</td>
                            <td>${project.manager.name}</td>
                            <td><fmt:formatDate value="${project.startDate}" type="date" pattern="dd/MM/yyyy" /></td>
                            <td><fmt:formatDate value="${project.expectedEndDate}" type="date" pattern="dd/MM/yyyy" /></td>
                            <td><fmt:formatDate value="${project.endDate}" type="date" pattern="dd/MM/yyyy" /></td>
                            <td>${project.description}</td>
                            <td>${project.budget}</td>
                            <td>${project.status}</td>
                            <td>${project.risk}</td>
							<td><a type="button" class="btn btn-warning"
								href="/update-project?id=${project.id}">Update</a>

                            <%-- the delete button will only be displayed for permitted status --%>
                            <c:if test="${not fn:containsIgnoreCase(statusCanNotDelete, project.status)}">
							    <a type="button" class="btn btn-danger"
                                    href="/delete-project?id=${project.id}">Delete</a></td>
							</c:if>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
	</div>

</div>
<%@ include file="common/footer.jspf"%>