<%@include file="header.jsp" %>

<c:choose>
    <c:when test="${feedbackResult eq 'OK'}">
        <div class="success"><fmt:message key="feedback.result.ok"/></div>
    </c:when>
    <c:when test="${feedbackResult eq 'CANCEL'}">
    <div class="info"><fmt:message key="feedback.result.cancel"/></div>
    </c:when>
    <c:when test="${feedbackResult eq 'ERROR'}">
    <div class="error"><fmt:message key="feedback.result.error"/></div>
    </c:when>
</c:choose>

<h1><s:message code="welcome.heading.main"/></h1>

<p>Please send your <a href="/feedback/basicInformation">feedback for any our training course</a>.</p>

<c:choose>
    <c:when test="${empty feedbacks}">
        <p><fmt:message key="welcome.noFeedbacks"/></p>
    </c:when>
    <c:otherwise>
        <h2><fmt:message key="welcome.heading.sentFeedbacks"/></h2>
        <!-- all sent feedbacks -->
        <c:forEach items="${feedbacks}" var="feedback">
            <div>
                <ul>
                    <li><c:out value="${feedback.firstName} ${feedback.lastName} (${feedback.emailAddress})"/></li>
                    <li>
                        <c:out value="${feedback.course} - ${feedback.courseSection}"/>,
                        <fmt:formatDate pattern="yyyy-MM-dd" value="${feedback.courseDate}"/>
                    </li>
                    <li><fmt:message key="feedback.rating" />: <c:out value="${feedback.rating}"/></li>
                    <c:if test="${not empty feedback.comment}">
                        <li><fmt:message key="feedback.comment" />: <c:out value="${feedback.comment}"/></li>
                    </c:if>
                </ul>
            </div>
        </c:forEach>
    </c:otherwise>
</c:choose>

</body>
</html>
