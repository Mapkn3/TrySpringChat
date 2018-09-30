<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="s" uri="http://java.sun.com/jsp/jstl/core" %>

<div>
    <sec:authorize access="!isAuthenticated()">
        <h1>Who are you?</h1>
        <s:url value="/user/signin" var="signInUrl"/>
        <s:url value="/user/signup" var="signUpUrl"/>
        <p>You can <a href="${signInUrl}">sign in</a> or <a href="${signUpUrl}">sign up</a>.</p>
    </sec:authorize>
    <sec:authorize access="isAuthenticated() && hasAuthority('User')">
        <sec:authentication property="principal.username" var="login"/>
        <s:url value="/dialogs" var="dialogsUrl"/>
        <s:url value="/user/${login}" var="detailUrl"/>
        <h3>Hello, ${login}</h3>
        <a href="${dialogsUrl}">Chats</a>
        <a href="${detailUrl}">Detail</a>
    </sec:authorize>
    <sec:authorize access="isAuthenticated() && hasAuthority('Moderator')">
        <sec:authentication property="principal.username" var="login"/>
        <s:url value="/dialogs" var="dialogsUrl"/>
        <s:url value="/user/${login}" var="detailUrl"/>
        <h3>Hello, ${login}</h3>
        <a href="${dialogsUrl}">Chats</a>
        <a href="${detailUrl}">Detail</a>
    </sec:authorize>
    <sec:authorize access="isAuthenticated() && hasAuthority('Administrator')">
        <sec:authentication property="principal.username" var="login"/>
        <s:url value="/dialogs" var="dialogsUrl"/>
        <s:url value="/user/${login}" var="detailUrl"/>
        <h3>Hello, ${login}</h3>
        <a href="${dialogsUrl}">Chats</a>
        <a href="${detailUrl}">Detail</a>
    </sec:authorize>
</div>