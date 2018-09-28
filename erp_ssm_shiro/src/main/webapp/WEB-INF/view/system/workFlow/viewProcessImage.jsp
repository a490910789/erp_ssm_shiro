<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>查看流程图</title>
<style type="text/css">
.mydiv{
	border:2px solid red;
	border-radius: 5px;
}
</style>
</head>
<body>
   <div style="position: relative;margin: -50px -200px">
	<img alt="流程图" style="position: absolute;" src="${ctx }/workFlow/viewProcessImage.action?deploymentId=${workFlowVo.deploymentId}">
    <c:if test="${!empty c }">
    	<div class="mydiv" style="position: absolute;left: ${c.x -2}px;top: ${c.y -2}px;width: ${c.width }px;height: ${c.height }px"></div>
    </c:if>
    </div>
</body>
</html>