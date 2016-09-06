<%@include file="header.jsp" %>

<h1><fmt:message key="feedback.form.heading"/></h1>

<p><fmt:message key="feedback.form.headerText"/></p>

<form:form action="/feedback/basicInformation" method="post" modelAttribute="basicInformationForm" style="display: inline">
    <fieldset>
        <legend><span class="number">1</span><fmt:message key="basicInformation.legend" /></legend>

        <label for="firstName"><fmt:message key="feedback.form.firstName" /></label>
        <div><form:errors path="firstName" cssClass="error" /></div>
        <form:input type="text" path="firstName" id="firstName"/>

        <label for="lastName"><fmt:message key="feedback.form.lastName" /></label>
        <div><form:errors path="lastName" cssClass="error" /></div>
        <form:input type="text" path="lastName" id="lastName"/>

        <label for="emailAddress"><fmt:message key="feedback.form.email" /></label>
        <div><form:errors path="emailAddress" cssClass="error" /></div>
        <form:input type="text" path="emailAddress" id="emailAddress"/>

        <label for="trainingCourse"><fmt:message key="feedback.form.trainingCourse" /></label>
        <form:select path="course.id" items="${courseData}" itemLabel="name" itemValue="id" id="trainingCourse"/>

        <label for="trainingCourseDate"><fmt:message key="feedback.form.trainingCourseDate" /></label>
        <div><form:errors path="feedbackDate" cssClass="error" /></div>
        <form:input type="date" path="feedbackDate" value="" id="trainingCourseDate"/>

    </fieldset>

    <input type="submit" name="cancel" value="<fmt:message key="button.cancel"/>"/>
    <input type="submit" name="continue" value="<fmt:message key="button.continue"/>"/>
</form:form>

</body>
</html>