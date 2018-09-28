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
<fieldset class="layui-elem-field layui-field-title" style="margin-top: 5px;">
  <legend>查询条件</legend>
</fieldset>
<form class="layui-form" id="fm" >
   <div class="layui-form-item" style="text-align: center;">
    <div class="layui-inline">
      <label class="layui-form-label">角色名称</label>
      <div class="layui-input-inline">
        <input type="text" name="name" id="name"  autocomplete="off" class="layui-input">
      </div>
      <label class="layui-form-label">角色备注</label>
      <div class="layui-input-inline">
        <input type="text" name="remark" id="remark"  autocomplete="off" class="layui-input">
      </div>
   </div>
  </div>
  <div  class="layui-form-item" style="text-align: center;">
  <a href="javascript:void(0)" class="search_btn layui-btn">查询</a>
       <button type="reset" class="layui-btn layui-btn-warm">清空</button>
       <a href="javascript:void(0)" type="reset" class="layui-btn layui-btn-normal add_btn">添加</a>
       <a href="javascript:void(0)" type="reset" class="layui-btn layui-btn-danger batchDel_btn">批量删除</a>
      </div>
 </form>
	<table id="roleList" lay-filter="roleList"></table>
	<!-- 操作 -->
	<script type="text/html" id="roleListBar">
		<a class="layui-btn layui-btn-xs layui-btn-normal" lay-event="setPermissions">分配权限</a>
		<a class="layui-btn layui-btn-xs layui-btn-warm" lay-event="update">修改</a>
		<a class="layui-btn layui-btn-xs layui-btn-danger" lay-event="delete">删除</a>
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
            remark: $("#remark").val(),  //搜索的关键字
       }
   });
}

layui.use(['form','layer','laydate','table','laytpl'],function(){
        var form = layui.form,
        layer=layui.layer,
        $ = layui.jquery,
        laydate = layui.laydate,
        laytpl = layui.laytpl,
        table = layui.table;
        
    //登入角色列表
        tableIns = table.render({
        elem: '#roleList',
        url : '${ctx }/role/loadAllRole.action',
        cellMinWidth : 95,
        page : true,
        height : "full-180",
        limit : 10,
        limits : [10,15,20,25],
        defaultToolbar: ['filter'],
        id : "roleListTable",
        cols : [[
            {type: "checkbox", fixed:"left", width:50},
            {field: 'id', title: 'ID',  align:"center"},
            {field: 'name', title: '角色名称',  align:"center"},
            {field: 'remark', title: '角色备注',  align:"center"},
            {field: 'available', title: '是否可用', align:'center',templet:function(data){
            	return data.available==1?"<font color=blue>可用</font>":"<font color=red>不可用</font>"
            }},
            {title: '操作', width:200, templet:'#roleListBar',fixed:"right",align:"center"}
        ]]
    });
    
      //搜索【此功能需要后台配合，所以暂时没有动态效果演示】
        $(".search_btn").on("click",function(){
        	  reloadTable(null);
        });

    //监听按钮
    table.on('tool(roleList)', function(obj){
    	var id=obj.data.id;
    	switch (obj.event) {
		case "delete":
			deleteRole(id);
			break;
		case "update":
			updateRole(id);
			break;
		case "setPermissions":
			setPermissions(id);
			break;
		}
    });
    
    //添加角色
     $(".add_btn").on("click",function(){
    	 addRole();
     });
    
    function addRole(){
    	 layer.open({
				title:['添加角色'],  
				type:2,
				content:'${ctx}/role/toAddRole.action',
				area:['800px','400px'],
				maxmin:true,
				success : function(layero, index){
	                var body = layui.layer.getChildFrame('body', index);
	                setTimeout(function(){
	                    layui.layer.tips('点击此处返回角色列表', '.layui-layer-setwin .layui-layer-close', {
	                        tips: 3
	                    });
	                },500)
	            }
		   });
    }
    //修改
    function updateRole(id){
    	layer.open({
			title:['修改角色'],  
			type:2,
			content:'${ctx}/role/toUpdateRole.action?id='+id,
			area:['800px','400px'],
			maxmin:true,
			success : function(layero, index){
                var body = layui.layer.getChildFrame('body', index);
                setTimeout(function(){
                    layui.layer.tips('点击此处返回角色列表', '.layui-layer-setwin .layui-layer-close', {
                        tips: 3
                    });
                },500)
            }
		});
    }
    //删除单个
    function deleteRole(id){
    	 layer.confirm('确定删除选中的角色？', {icon: 3, title: '提示信息'}, function (index) {
   		  $.post("${ctx}/role/delRole.action?id="+id,function(result){
   	          	layer.msg(result.msg);
   	          });
             tableIns.reload();
        });
     }
    //批量删除
    $(".batchDel_btn").on("click",function(){
    	 var checkStatus = table.checkStatus('roleListTable'),
         data = checkStatus.data,
         url="${ctx}/role/delRoles.action?1=1";
     if(data.length > 0) {
         for (var i in data) {
             url+="&ids="+data[i].id;
         }
         layer.confirm('确定删除选中的角色？', {icon: 3, title: '提示信息'}, function (index) {
             $.post(url,function(result){
             	layer.msg(result.msg);
             });
             tableIns.reload();
         });
        }else{
         layer.msg("请选择需要删除的角色");
      }
   });
    
    function setPermissions(id){
    	layer.open({
			title:['分配权限'],  
			type:2,
			content:'${ctx}/role/toSetPermissions.action?id='+id,
			area:['300px','300px'],
			maxmin:true,
			success : function(layero, index){
                var body = layui.layer.getChildFrame('body', index);
                setTimeout(function(){
                    layui.layer.tips('点击此处返回角色列表', '.layui-layer-setwin .layui-layer-close', {
                        tips: 3
                    });
                },500)
            }
		});
    }
});

</script>
</body>
</html>