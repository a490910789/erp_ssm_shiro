<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>添加请假单</title>
<link rel="stylesheet" href="${ctx }/resources/layui/css/layui.css" media="all" />
<link rel="stylesheet" href="${ctx }/resources/css/public.css" media="all" />
</head>
<body class="childrenBody">
	<form class="layui-form" method="post" id="frm">
		<div class="layui-form-item">
			<label class="layui-form-label">请假标题</label>
			<div class="layui-input-block">
				<input type="text" name="title" lay-verify="title" readonly="readonly" value="${leaveBill.title }" autocomplete="off"
					placeholder="请输入请假标题" class="layui-input">
				<input type="hidden" name="state" value="0">
				<input type="hidden" name="userid" value="${leaveBill.id }">
			</div>
		</div>
		<div class="layui-form-item">
			<div class="layui-inline">
				<label class="layui-form-label">请假天数</label>
				<div class="layui-input-inline">
					<input type="text" name="days"  readonly="readonly" value="${leaveBill.days }" placeholder="请输入请假天数" lay-verify="number" autocomplete="off"
						class="layui-input">
				</div>
			</div>
			<div class="layui-inline">
				<label class="layui-form-label">开始时间</label>
				<div class="layui-input-inline">
					<input type="text" name="leavetime" value="<fmt:formatDate value="${leaveBill.leavetime }" pattern="yyyy-MM-dd"/>"  readonly="readonly" id="leavetime" autocomplete="off"
						class="layui-input">
				</div>
			</div>
		</div>
		<div class="layui-form-item">
			<label class="layui-form-label">请假原因</label>
			<div class="layui-input-block">
				<textarea placeholder="请输入请假原因"  readonly="readonly" name="content" class="layui-textarea">${leaveBill.content }</textarea>
			</div>
		</div>
  </form>
  <table id="commentList" lay-filter="commentList"></table>
<script type="text/javascript" src="${ctx }/resources/layui/layui.js"></script>
</body>
<script type="text/javascript">
	layui.use(['form','jquery','layer','table'],function(){
		var form=layui.form;
		var $=layui.jquery;
		var table=layui.table;
		//如果父页面有layer就使用父页的  没有就自己导入
		var layer = parent.layer === undefined ? layui.layer : parent.layer;
	 
	    //批注信息表
	      var  tableIns = table.render({
	        elem: '#commentList',
	        url : '${ctx }/workFlow/loadCommentsByLeaveBillId.action?id=${leaveBill.id}',
	        cellMinWidth : 95,
	        toolbar:false,
	        page : true,
	        height : "full-120",
	        limit : 10,
	        limits : [10,15,20,25],
	        cols : [[
	            {field: 'time', title: '批注时间',  align:"center"},
	            {field: 'userId', title: '批注人',  align:"center"},
	            {field: 'message', title: '批注内容',  align:"center"}
	        ]]
	    });
	});
</script>
</html>