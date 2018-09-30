<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="t" uri="http://tiles.apache.org/tags-tiles" %>
<html>
<head>
    <title>TrySpringChat</title>
    <link href="<s:url value="/resources" />/css/style.css"
          rel="stylesheet"
          type="text/css" />
</head>

<body>
<div id="container">
    <div id="top">
        <t:insertAttribute name="top" />
    </div>
    <div id="side">
        <t:insertAttribute name="side" />
    </div>
    <div id="content">
        <t:insertAttribute name="content" />
    </div>
</div>
</body>
</html>