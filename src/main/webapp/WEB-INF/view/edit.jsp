<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div>
    <h2>Fill all fields</h2>
    <s:url value="/user?new" var="newUserUrl"/>
    <sf:form modelAttribute="authData" action="${newUserUrl}" method="post">
        <fieldset>
            <table cellspacing="0">
                <tr>
                    <th><label for="name">Name</label></th>
                    <td>
                        <sf:input path="profile.name" size="20" id="name"/>
                        <sf:errors path="profile.name" cssClass="error"/>
                    </td>
                </tr>
                <tr>
                    <th><label for="surname">Surname</label></th>
                    <td>
                        <sf:input path="profile.surname" size="20" id="surname"/>
                        <sf:errors path="profile.surname" cssClass="error"/>
                    </td>
                </tr>
                <tr>
                    <th><label for="login">Username</label></th>
                    <td>
                        <sf:input path="account.nickname" size="30" id="login"/>
                        <sf:errors path="account.nickname" cssClass="error"/>
                        <span class="error">${param.error}</span>
                    </td>
                </tr>
                <tr>
                    <th><label for="password">Password</label></th>
                    <td>
                        <sf:password path="account.password" showPassword="true" size="30" id="password"/>
                        <sf:errors path="account.password" cssClass="error"/>
                    </td>
                </tr>
                <tr>
                    <th></th>
                    <td><input name="commit" type="submit" value="Submit"/></td>
                </tr>
            </table>
        </fieldset>
    </sf:form>
</div>
