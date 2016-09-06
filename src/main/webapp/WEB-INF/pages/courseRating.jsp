<%@include file="header.jsp" %>

<h1><fmt:message key="feedback.form.heading"/></h1>

<p><fmt:message key="feedback.form.headerText"/></p>

<form:form action="/feedback/courseRating" method="post" modelAttribute="courseRatingForm" style="display: inline">
    <fieldset>
        <legend><span class="number">2</span><fmt:message key="courseRating.legend" /></legend>

        <label for="courseSection"><fmt:message key="feedback.form.favouriteSection"/></label>
        <div><form:errors path="courseSection.id" cssClass="error"/></div>
        <form:select path="courseSection.id" items="${sections}" itemLabel="name" itemValue="id" id="courseSection"/>

        <label for="rating"><fmt:message key="feedback.form.rating.long"/></label>
        <div><form:errors path="rating" cssClass="error"/></div>
        <form:radiobuttons path="rating" items="${ratingList}" id="rating"/>

        <label for="comment"><fmt:message key="feedback.form.comments.long"/></label>
        <form:textarea rows="6" cols="40" path="comment" id="comment"/>

    </fieldset>

    <input type="submit" name="back" value="<fmt:message key="button.back"/>"/>
    <input type="submit" name="continue" value="<fmt:message key="button.continue"/>"/>
</form:form>

</body>
</html>