<%@include file="header.jsp" %>

<h1><fmt:message key="feedback.form.heading"/></h1>

<p><fmt:message key="feedback.form.headerText"/></p>

<legend><span class="number">3</span><fmt:message key="summary.legend"/></legend>

<fieldset>
    <div><fmt:message key="feedback.form.firstName"/> ${basicInformationForm.firstName}</div>
    <div><fmt:message key="feedback.form.lastName"/> ${basicInformationForm.lastName}</div>
    <div><fmt:message key="feedback.form.email"/> ${basicInformationForm.emailAddress}</div>
    <div><fmt:message key="feedback.form.trainingCourse"/> ${basicInformationForm.course.name}</div>
    <div><fmt:message key="feedback.form.trainingCourseDate"/> <fmt:formatDate pattern="yyyy-MM-dd" value="${basicInformationForm.feedbackDate}" /></div>
    <div><fmt:message key="feedback.form.favouriteSection"/> ${courseRatingForm.courseSection.name}</div>
    <div><fmt:message key="feedback.form.rating"/> ${courseRatingForm.rating}</div>
    <div><fmt:message key="feedback.form.comments"/> ${courseRatingForm.comment}</div>
</fieldset>

<form action="/feedback/courseRating" method="get" style="display: inline">
    <input type="submit" value="<fmt:message key="button.back"/>"/>
</form>

<form action="/feedback/sendFeedback" method="post" style="display: inline">
    <input type="submit" value="<fmt:message key="button.sendFeedback"/>" style="font-weight: bold"/>
</form>
</body>
</html>
