<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<div>
    <h3>Choose dialog</h3>
    <table cellspacing="15">
        <c:forEach items="${dialogs}" var="dialog">
            <sec:authentication property="principal.username" var="login"/>
            <c:if test="${!dialog.nickname.equals(login)}">
                <tr>
                    <th><a href="<s:url value="/dialogs/${dialog.nickname}"/>">${dialog.nickname}</a></th>
                </tr>
            </c:if>
        </c:forEach>
    </table>
</div>