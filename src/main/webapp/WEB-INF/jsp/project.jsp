<%@ include file="common/header.jspf"%>
<%@ include file="common/navigation.jspf"%>
<div class="container">
	<div class="row">
		<div class="col-md-6 col-md-offset-3 ">
			<div class="panel panel-primary">
				<div class="panel-heading">Add Project</div>
				<div class="panel-body">
					<form:form method="post" modelAttribute="project">
						<form:hidden path="id" />

						<fieldset class="form-group">
                            <form:label path="name">Name</form:label>
                            <form:input path="name" type="text" class="form-control" required="required" />
                        	<form:errors path="name" cssClass="text-warning" />
                        </fieldset>

                        <fieldset class="form-group">
                        	<form:label path="startDate">Start Date</form:label>
                        	<form:input path="startDate" type="text" class="form-control" />
                        </fieldset>

                        <fieldset class="form-group">
                            <form:label path="manager">Manager</form:label>
                            <select id="manager" name="manager" path="manager" class="form-control" required="required">
                                <option value="">Choose a manager</option>
                                <c:forEach items="${managers}" var="manager">
                                    <option value="${manager.id}" ${project.manager != null && manager.id == project.manager.id ? 'selected' : ''}>${manager.name}</option>
                                </c:forEach>
                            </select>
                            <form:errors path="manager" cssClass="text-warning" />
                        </fieldset>

                        <fieldset class="form-group">
                            <form:label path="expectedEndDate">Expected Date</form:label>
                            <form:input path="expectedEndDate" type="text" class="form-control" />
                        </fieldset>

                        <fieldset class="form-group">
                            <form:label path="endDate">End Date</form:label>
                            <form:input path="endDate" type="text" class="form-control" />
                        </fieldset>

                        <fieldset class="form-group">
                        	<form:label path="description">Description</form:label>
                        	<form:input path="description" type="text" class="form-control" />
                        </fieldset>

						<fieldset class="form-group">
							<form:label path="budget">Budget</form:label>
							<form:input path="budget" type="number" class="form-control" />
						</fieldset>

						<button type="submit" class="btn btn-success">Save</button>
					</form:form>
				</div>
			</div>
		</div>
	</div>
</div>
<%@ include file="common/footer.jspf"%>