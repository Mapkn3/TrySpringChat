<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<div>
    <table cellspacing="5">
        <c:forEach items="${history}" var="msg">
            <tr>
                <th>${msg.from.nickname}</th>
                <td>${msg.body}</td>
            </tr>
        </c:forEach>
    </table>
    <sf:form modelAttribute="message" method="post">
        <fieldset>
            <sf:input path="body" maxlength="255" />
            <input type="submit">
        </fieldset>
    </sf:form>
</div>