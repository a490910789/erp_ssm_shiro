<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<title>其他管理--登入角色</title>
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
 <table id="roleList" lay-filter="roleList"></table>
 <script type="text/html" id="tableToolBar">
		<a  class="layui-btn  layui-btn-normal" lay-event="saveRoles">保存</a> 
</script>
<script type="text/javascript" src="${ctx}/resources/layui/layui.js"></script>
<script type="text/javascript">
var tableIns;
layui.use(['layer','table','jquery'],function(){
       var layer=layui.layer,
        $ = layui.jquery,
        table = layui.table;
        
    //登入角色列表
        tableIns = table.render({
        elem: '#roleList',
        url : '${ctx }/user/loadAllRoles.action?id=${userVo.id}',
        cellMinWidth : 95,
        limit : 10,
        toolbar: '#tableToolBar',
        limits : [10,15,20,25],
        id : "roleListTable",
        defaultToolbar:"",
        cols : [[
            {type: "checkbox", fixed:"left", width:50},
            {field: 'id', title: 'ID',  align:"center"},
            {field: 'name', title: '角色名称',  align:"center"},
            {field: 'remark', title: '角色备注',  align:"center"},
            {field: 'available', title: '是否可用', align:'center',templet:function(data){
            	return data.available==1?"<font color=blue>可用</font>":"<font color=red>不可用</font>"
            }}
        ]]
    });
        table.on("toolbar(roleList)",function(obj){
        	if(obj.event=="saveRoles"){
        	var checkStatus = table.checkStatus('roleListTable'),
            data = checkStatus.data,
            url="${ctx}/user/saveSetRoles.action?id=${userVo.id}";
            for (var i in data) {
                url+="&ids="+data[i].id;
            }
 	        $.post(url,function(result){
 			    layer.msg(result.msg);//弹出分配成功或失败
	 			//关闭窗口
	 			var index=layer.getFrameIndex(window.name);
	 			layer.close(index);
	 	        parent.tableIns.reload();//刷新表格
 			   });
        	}
      });
});
</script>
</body>
</html>