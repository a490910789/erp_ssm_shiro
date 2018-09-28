<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
    
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>流程管理</title>
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <meta name="apple-mobile-web-app-status-bar-style" content="black">
    <meta name="apple-mobile-web-app-capable" content="yes">
    <meta name="format-detection" content="telephone=no">
    <link rel="stylesheet" href="${ctx }/resources/layui/css/layui.css" media="all" />
    <link rel="stylesheet" href="${ctx }/resources/css/public.css" media="all" />
</head>
<body class="childrenBody">
<fieldset class="layui-elem-field layui-field-title">
    <legend>查询条件</legend>
</fieldset>
<form class="layui-form" id="searchForm" method="post">
    <div class="layui-form-item">
        <div class="layui-inline">
            <label class="layui-form-label" >部署名称:</label>
            <div class="layui-input-inline">
                <input type="text" name="deploymentName" id="deploymentName" autocomplete="off" class="layui-input">
            </div>
        </div>
        <a class="layui-btn search_btn" >查询</a>
        <button type="reset" class="layui-btn layui-btn-warm">清空</button>
    </div>
</form>
<table id="processDeploymentList" lay-filter="processDeploymentList"></table>
<table id="processDefList" lay-filter="processDefList"></table>
<!--表格工具条-->
<script type="text/html" id="tableToolBar">
	<a class="layui-btn layui-btn layui-btn" lay-event="add">添加部署</a>
</script>
<!--操作-->
<script type="text/html" id="processDeploymentListBar">
    <a class="layui-btn layui-btn-xs layui-btn-danger" lay-event="del">删除</a>
    <a class="layui-btn layui-btn-xs layui-btn-warm" lay-event="viewProcessImage">查看流程图</a>
</script>
<script type="text/javascript" src="${ctx }/resources/layui/layui.js"></script>
</body>
<script type="text/javascript">
    var tableInsDeployment;
    var tableInsProcessDef;
    layui.use(['form','layer','table'],function(){
        var form = layui.form,
            layer = parent.layer === undefined ? layui.layer : parent.layer,
            $ = layui.jquery,
            table = layui.table;
        //流程部署列表
        tableInsDeployment = table.render({
            elem: '#processDeploymentList',
            url : '${ctx }/workFlow/loadAllProcessDeployment.action',
            cellMinWidth : 95,
            toolbar: '#tableToolBar',
            page : true,
            height : "full-380",
            limits : [10,15,20,25],
            defaultToolbar: ['filter'],
            limit : 10,
            id : "processDeploymentListTable",
            cols : [[
                {type: "checkbox", fixed:"left", width:50},
                {field: 'id', title: '部署ID', minWidth:100, align:"center"},
                {field: 'name', title: '部署名称', minWidth:100, align:"center"},
                {field: 'deploymentTime', title: '部署时间', minWidth:100, align:"center"},
                {title: '操作', templet:'#processDeploymentListBar',fixed:"right",width:175,align:"center"}
            ]]
        });
        
      //流程定义列表
       tableInsProcessDef = table.render({
            elem: '#processDefList',
            url : '${ctx }/workFlow/loadAllProcessDef.action',
            cellMinWidth : 95,
            toolbar: true,
            page : true,
            height : "full-380",
            limits : [10,15,20,25],
            defaultToolbar: ['filter'],
            limit : 10,
            id : "processDefListTable",
            cols : [[
                {type: "checkbox", fixed:"left", width:50},
                {field: 'id', title: '定义ID', minWidth:100, align:"center"},
                {field: 'name', title: '定义名称', minWidth:100, align:"center"},
                {field: 'key', title: '定义KEY', minWidth:100, align:"center"},
                {field: 'version', title: '定义版本', minWidth:100, align:"center"},
                {field: 'deploymentId', title: '部署ID', minWidth:100, align:"center"},
                {field: 'resourceName', title: '资源名称[bpmn]', minWidth:100, align:"center"},
                {field: 'diagramResourceName', title: '资源名称[png]', minWidth:100, align:"center"}
            ]]
        });

        //查询
        $(".search_btn").on("click",function(){
            var params=$("#searchForm").serialize();
            table.reload('processDeploymentListTable', {
                url: '${ctx }/workFlow/loadAllProcessDeployment.action?'+params
            });
            table.reload('processDefListTable', {
                url: '${ctx }/workFlow/loadAllProcessDef.action?'+params
            });
        });
        //监听头工具栏事件
        table.on('toolbar(processDeploymentList)', function(obj){
            switch(obj.event){
	            case 'add':
	               	toAddDeployment();
                	break;
	            case 'viewProcessImage':
	            	viewProcessImage(obj.data.id);
                	break;
            };
        });

        //打开  添加流程的弹出层
        function toAddDeployment(){
            var index = layui.layer.open({
                title : "部署流程",
                type : 2,//ifream层
                area:["800px","300px"],
                content : "${ctx }/workFlow/toAddDeployment.action",
                success : function(layero, index){
                    setTimeout(function(){
                        layui.layer.tips('点击此处返回流程列表', '.layui-layer-setwin .layui-layer-close', {
                            tips: 3
                        });
                    },500)
                }
            })
            //layui.layer.full(index);
            //改变窗口大小时，重置弹窗的宽高，防止超出可视区域（如F12调出debug的操作）
            $(window).on("resize",function(){
                layui.layer.full(index);
            })
        }
        //列表操作
        table.on('tool(processDeploymentList)', function(obj){
            var layEvent = obj.event,
                data = obj.data;
            if(layEvent === 'del'){ //删除
                layer.confirm('确定删【'+data.name+'】流程吗？',{icon:3, title:'提示信息'},function(index){
                    $.post("${ctx}/workFlow/delDeployment.action",{
                        deploymentId : data.id  //将需要删除的id作为参数传入
                    },function(data){
                        //刷新table
                        tableInsDeployment.reload();
                        tableInsProcessDef.reload();
                        //关闭提示框
                        layer.close(index);
                    })
                });
            }else if(layEvent==="viewProcessImage"){
            	viewProcessImage(data.id);
            }
        });
        //查看流程图
        function  viewProcessImage(id){
        	var index = layui.layer.open({
                title : "流程图",
                type : 2,
                area:["800px","550px"],
                maxmin:true,
                content : "${ctx }/workFlow/toViewProcessImage.action?deploymentId="+id,
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
    })
    
   
    
</script>
</html>