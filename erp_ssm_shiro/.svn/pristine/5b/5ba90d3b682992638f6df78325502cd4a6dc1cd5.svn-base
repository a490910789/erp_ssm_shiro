<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<title>其他管理--登入用户</title>
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
<fieldset class="layui-elem-field layui-field-title" style="margin-top: 5px;">
  <legend>查询条件</legend>
</fieldset>
<form class="layui-form" id="fm" >
   <div class="layui-form-item">
    <div class="layui-inline">
      <label class="layui-form-label">用户名称</label>
      <div class="layui-input-inline">
        <input type="text" name="name" id="name"   autocomplete="off" class="layui-input">
      </div>
    </div>
    <div class="layui-inline">
      <label class="layui-form-label">用户地址</label>
      <div class="layui-input-inline">
        <input type="text" name="address"  id="address" autocomplete="off" class="layui-input">
      </div>
    </div>
  </div>
   <div class="layui-form-item" style="text-align: center;">
       <a href="javascript:void(0)" class="search_btn layui-btn">查询</a>
       <button type="reset" class="layui-btn layui-btn-warm">重置</button>
   </div>
 </form>
	<table id="userList" lay-filter="userList"></table>
	<!-- 表格工具条 批量删除-->
<script type="text/html" id="tableToolBar">
	    <a class="layui-btn layui-btn-sm" lay-event="add">添加</a>
		<a class="layui-btn layui-btn-sm layui-btn-danger" lay-event="batchDel">批量删除</a> 
</script>

	<!-- 操作 -->
	<script type="text/html" id="userListBar">
		<a class="layui-btn layui-btn-xs layui-btn-warm" lay-event="update">修改</a>
		<a class="layui-btn layui-btn-xs layui-btn-danger" lay-event="delete">删除</a>
		<a class="layui-btn layui-btn-xs layui-btn" lay-event="resetPwd">重置密码</a>
		<a class="layui-btn layui-btn-xs layui-btn-normal" lay-event="setRoles">分配角色</a>
	</script>
<script type="text/javascript" src="${ctx}/resources/layui/layui.js"></script>
<script type="text/javascript" src="${ctx }/resources/zTree/js/jquery-1.4.4.min.js"></script>
<script type="text/javascript">
var tableIns;
//根据id搜索
function reloadTable(id){
	tableIns.reload({
        page: {
            curr: 1 //重新从第 1 页开始
        },
        where: {
            name: $("#name").val(),  //搜索的关键字
            address: $("#address").val(),  //搜索的关键字
            deptid:id
       }
   });
}

layui.use(['form','layer','laydate','table','laytpl'],function(){
        var form = layui.form,
        layer = parent.layer === undefined ? layui.layer : top.layer,
        $ = layui.jquery,
        laydate = layui.laydate,
        laytpl = layui.laytpl,
        table = layui.table;
        
    //登入用户列表
        tableIns = table.render({
        elem: '#userList',
        url : '${ctx }/user/loadAllUser.action',
        cellMinWidth : 95,
        toolbar: '#tableToolBar',
        page : true,
        height : "full-180",
        limit : 10,
        limits : [10,15,20,25],
        defaultToolbar: ['filter'],
        id : "userListTable",
        cols : [[
        	{type: "checkbox", fixed:"left", width:50},
            {field: 'id', title: 'ID', width:60, align:"center"},
            {field: 'name', title: '用户姓名', width:120, align:"center"},
            {field: 'loginname', title: '登陆名称', width:120, align:"center"},
            {field: 'deptname', title: '所在部门', width:150, align:"center"},
            {field: 'leadername', title: '直接领导', width:120, align:"center"},
            {field: 'address', title: '用户地址',align:"center", width:150},
            {field: 'remark', title: '用户备注',align:"center", width:150},
            {field: 'hiredate', title: '入职时间',align:"center", width:150},
            {field: 'sex', title: '性别', align:'center',templet:function(data){
            	return data.sex==1?"<font color=blue>男</font>":"<font color=red>女</font>"
            }},
            {field: 'imgpath', title: '头像', align:'center',templet:function(data){
            	return "<img width=20px height=20px alt=无效地址  src='"+data.imgpath+"'></img>"
            }},
            {field: 'available', title: '是否可用',width:120, align:'center',templet:function(data){
            	return data.available==1?"<font color=blue>是</font>":"<font color=red>否</font>"
            }},
            {field: 'pwd', title: '密码', align:'center',templet:function(data){
            	return "******"
            }},
            {field: 'ordernum', title: '排序码',align:"center", width:80},
            {title: '操作', width:300, templet:'#userListBar',fixed:"right",align:"center"}
        ]]
    });
    
      //搜索【此功能需要后台配合，所以暂时没有动态效果演示】
        $(".search_btn").on("click",function(){
        	  reloadTable(null);
        });

       //监听按钮
    table.on('toolbar(userList)', function(obj){
    	switch (obj.event) {
		case "add":
			toAdd();
			break;
		case "batchDel":
			batchDel();
			break;
		default:
			break;
    	}
    }); 
  
    //监听按钮
    table.on('tool(userList)', function(obj){
    	var id=obj.data.id;
    	switch (obj.event) {
		case "delete":
			deleteUser(id);
			break;
		case "update":
			updateUser(id);
			break;
		case "resetPwd":
			resetPwd(obj);
			break;
		case "setRoles":
			setRoles(id);
			break;
    	}
    });
    
    //添加用户
    function toAdd(){
			layer.open({
				title:['添加用户'],  
				type:2,
				content:'${ctx}/user/toAddUser.action',
				area:['800px','550px'],
				maxmin:true,
				success : function(layero, index){
	                var body = layui.layer.getChildFrame('body', index);
	                setTimeout(function(){
	                    layui.layer.tips('点击此处返回用户列表', '.layui-layer-setwin .layui-layer-close', {
	                        tips: 3
	                    });
	                },500)
	            }
			});
       }
    //修改
    function updateUser(id){
    	layer.open({
			title:['修改用户'],  
			type:2,
			content:'${ctx}/user/toUpdateUser.action?id='+id,
			area:['800px','550px'],
			maxmin:true,
			success : function(layero, index){
                var body = layui.layer.getChildFrame('body', index);
                setTimeout(function(){
                    layui.layer.tips('点击此处返回用户列表', '.layui-layer-setwin .layui-layer-close', {
                        tips: 3
                    });
                },500)
            }
		});
    }
    //分配权限
    function setRoles(id){
    	layer.open({
			title:['分配权限'],  
			type:2,
			content:'${ctx}/user/toSetRoles.action?id='+id,
			area:['800px','380px'],
			maxmin:true,
			success : function(layero, index){
                var body = layui.layer.getChildFrame('body', index);
                setTimeout(function(){
                    layui.layer.tips('点击此处返回用户列表', '.layui-layer-setwin .layui-layer-close', {
                        tips: 3
                    });
                },500)
            }
		});
    }
    //删除单个
    function deleteUser(id){
    	 layer.confirm('确定删除选中的用户？', {icon: 3, title: '提示信息'}, function (index) {
   		  $.post("${ctx}/user/delUser.action?id="+id,function(result){
   	          	layer.msg(result.msg);
   	          });
             tableIns.reload();
        });
     }
    
    //批量删除
    function batchDel(){
        var checkStatus = table.checkStatus('userListTable'),
            data = checkStatus.data,
            url="${ctx}/user/delUsers.action?1=1";
        if(data.length > 0) {
            for (var i in data) {
                url+="&ids="+data[i].id;
            }
            layer.confirm('确定删除选中的用户？', {icon: 3, title: '提示信息'}, function (index) {
                $.post(url,function(result){
                	layer.msg(result.msg);
                });
                tableIns.reload();
            });
        }else{
            layer.msg("请选择需要删除的用户");
        }
    }
    
    //重置密码
   function resetPwd(obj){
	   layer.confirm("确定重置用户【"+obj.data.name+"】的密码吗？", {icon: 3, title: '提示信息'}, function (index) {
	   		  $.post("${ctx}/user/resetPwd.action?id="+obj.data.id,function(result){
	   	          	layer.msg(result.msg);
	   	          });
	             tableIns.reload();
	        });
        }
});

</script>
</body>
</html>