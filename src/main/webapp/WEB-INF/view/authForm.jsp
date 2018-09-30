<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ page isELIgnored="false"%>
<div>
    <h1>Sign in</h1>

    <form action="<s:url value="login" />" class="signin" method="post">
        <fieldset>
            <table cellspacing="0">
                <tr>
                    <th><label for="login">Username</label></th>
                    <td><input id="login" name="username" type="text" /></td>
                </tr>
                <tr>
                    <th><label for="password">Password</label></th>
                    <td><input id="password" name="password" type="password" /></td>
                </tr>
                <tr>
                    <th></th>
                    <td>
                        <input id="remember_me" name="_spring_security_remember_me" type="checkbox"/>
                        <label for="remember_me" class="inline">Remember me</label>
                    </td>
                </tr>
                <tr>
                    <th></th>
                    <td><input name="commit" type="submit" value="Sign In" /></td>
                </tr>
            </table>
        </fieldset>
    </form>
</div>