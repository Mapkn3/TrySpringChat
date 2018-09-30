<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div>
    <sec:authorize access="!isAuthenticated()">
        <div class="msg">
            <h2>Please Sign In!</h2>
        </div>

        <form class="signin" action="<s:url value="/user/login" />" method="post">
            <fieldset>
                <div>
                    <label for="login">Username</label>
                    <input id="login" type="text" name="username"/>
                </div>
                <div>
                    <label for="pass">Password</label>
                    <input id="pass" type="password" name="password"/>
                </div>
                <input id="remember_me" type="checkbox" value="1"
                       name="_spring_security_remember_me"/>
                <label for="remember_me"><small>Remember me</small></label>
                <input id="submit" type="submit" value="Sign In!"
                       name="commit"/>
            </fieldset>
        </form>
        <div class="notify">
            Want an account?
            <br/>
            <a class="join" href="<s:url value="/user/signup"/>">Join for Free!</a>
            <br/>
            It's fast and easy!
        </div>
    </sec:authorize>
    <sec:authorize access="isAuthenticated()">
        <span>Hello, <sec:authentication property="principal.username" /></span>
        <br/>
        <a href="<s:url value="/logout" />">Logout</a>
    </sec:authorize>
</div>
