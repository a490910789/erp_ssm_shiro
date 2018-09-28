<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<title>其他管理--登入待办任务</title>
	<meta name="renderer" content="webkit">
	<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
	<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
	<meta name="apple-mobile-web-app-status-bar-style" content="black">
	<meta name="apple-mobile-web-app-capable" content="yes">
	<meta name="format-detection" content="telephone=no">
<link rel="stylesheet" href="${ctx}/resources/layui/css/layui.css" media="all" />
<link rel="stylesheet" href="${ctx}/resources/css/public.css" media="all" />
</head>
<body class="childrenBody">
	<table id="taskList" lay-filter="taskList"></table>
	<!-- 操作 -->
	<script type="text/html" id="taskListBar">
		<a class="layui-btn layui-btn-xs layui-btn-danger" lay-event="toDoTask">办理任务</a>
		<a class="layui-btn layui-btn-xs layui-btn-normal" lay-event="viewProcessImage">查看任务图</a>
	</script>
<script type="text/javascript" src="${ctx}/resources/layui/layui.js"></script>
<script type="text/javascript" src="${ctx }/resources/zTree/js/jquery-1.4.4.min.js"></script>
<script type="text/javascript">
var tableIns;
layui.use(['form','layer','table'],function(){
        var form = layui.form,
        layer=layui.layer,
        $ = layui.jquery,
        table = layui.table;
        
    //登入待办任务列表
        tableIns = table.render({
        elem: '#taskList',
        url : '${ctx }/workFlow/loadMyTasks.action',
        cellMinWidth : 95,
        toolbar:true,
        page : true,
        height : "full-120",
        limit : 10,
        limits : [10,15,20,25],
        defaultToolbar: ['filter'],
        id : "taskListTable",
        cols : [[
            {field: 'id', title: '任务ID',  align:"center"},
            {field: 'name', title: '任务名称',  align:"center"},
            {field: 'createTime', title: '创建时间',  align:"center"},
            {field: 'assignee', title: '办理人', align:'center' },
            {title: '操作', width:200, templet:'#taskListBar',fixed:"right",align:"center"}
        ]]
    });
    
    //监听按钮
    table.on('tool(taskList)', function(obj){
    	var id=obj.data.id;
    	switch (obj.event) {
		case "toDoTask":
			toDoTask(id);
			break;
		case "viewProcessImage":
			viewProcessImage(id);
			break;
		}
    });
    
    //办理任务
    function toDoTask(id){
    	layer.open({
			title:['办理任务'],  
			type:2,
			content:'${ctx}/workFlow/toDoTask.action?taskId='+id,
			area:['800px','600px'],
			maxmin:true,
			success : function(layero, index){
                var body = layui.layer.getChildFrame('body', index);
                setTimeout(function(){
                    layui.layer.tips('点击此处返回待办任务列表', '.layui-layer-setwin .layui-layer-close', {
                        tips: 3
                    });
                },500)
            }
		});
    }
    //查看流程图
    function  viewProcessImage(id){
    	var index = layui.layer.open({
            title : "流程图",
            type : 2,
            area:["700px","600px"],
            maxmin:true,
            content : "${ctx }/workFlow/toViewProcessImageForTask.action?taskId="+id,
            success : function(layero, index){
                setTimeout(function(){
                    layui.layer.tips('点击此处返回流程列表', '.layui-layer-setwin .layui-layer-close', {
                        tips: 3
                    });
                },500)
            }
        })
        //改变窗口大小时，重置弹窗的宽高，防止超出可视区域（如F12调出debug的操作）
        $(window).on("resize",function(){
            layui.layer.full(index);
        })  
    }
});

</script>
</body>
</html>