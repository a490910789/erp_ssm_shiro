<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<title>其他管理--登入菜单</title>
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
      <label class="layui-form-label">菜单名称</label>
      <div class="layui-input-inline">
        <input type="text" name="name" id="name"  autocomplete="off" class="layui-input">
      </div>
     <a href="javascript:void(0)" class="search_btn layui-btn">查询</a>
       <button type="reset" class="layui-btn layui-btn-warm">清空</button>
       <a href="javascript:void(0)" type="reset" class="layui-btn layui-btn-normal add_btn">添加</a>
       <a href="javascript:void(0)" type="reset" class="layui-btn layui-btn-danger batchDel_btn">批量删除</a>
 </div>
  </div>
      
 </form>
	<table id="menuList" lay-filter="menuList"></table>
	<!-- 操作 -->
	<script type="text/html" id="menuListBar">
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
            id:id
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
        
    //登入菜单列表
        tableIns = table.render({
        elem: '#menuList',
        url : '${ctx }/menu/loadAllMenu.action',
        cellMinWidth : 95,
        page : true,
        height : "full-180",
        limit : 10,
        limits : [10,15,20,25],
        defaultToolbar: ['filter'],
        id : "menuListTable",
        cols : [[
            {type: "checkbox", fixed:"left", width:50},
            {field: 'id', title: 'ID', width:60, align:"center"},
            {field: 'pid', title: '父级菜单ID', width:120, align:"center"},
            {field: 'name', title: '菜单名称', width:180, align:"center"},
            {field: 'icon', title: '菜单图标',align:"center", width:150,templet:function(data){
            	return "<i class=layui-icon>"+data.icon+"</i>"
            }},
            {field: 'href', title: '菜单地址', width:180, align:"center"},
            {field: 'ordernum', title: '排序码', width:180, align:"center"},
            {field: 'target', title: 'TARGET', width:180, align:"center"},
            {field: 'spread', title: '是否展开', align:'center',templet:function(data){
            	return data.open==1?"<font color=blue>展开</font>":"<font color=red>不展开</font>"
            }},
            {field: 'parent', title: '是否父节点',width:100, align:'center',templet:function(data){
            	return data.parent==1?"<font color=blue>是</font>":"<font color=red>否</font>"
            }},
            {field: 'available', title: '是否可用', align:'center',templet:function(data){
            	return data.available==1?"<font color=blue>可用</font>":"<font color=red>不可用</font>"
            }},
            {title: '操作', width:170, templet:'#menuListBar',fixed:"right",align:"center"}
        ]]
    });
    
      //搜索【此功能需要后台配合，所以暂时没有动态效果演示】
        $(".search_btn").on("click",function(){
        	  reloadTable(null);
        });

    //监听按钮
    table.on('tool(menuList)', function(obj){
    	var id=obj.data.id;
    	switch (obj.event) {
		case "delete":
			deleteMenu(id);
			break;
		case "update":
			updateMenu(id);
			break;
		}
    });
    
    //添加菜单
     $(".add_btn").on("click",function(){
    	 layer.open({
				title:['添加菜单'],  
				type:2,
				content:'${ctx}/menu/toAddMenu.action',
				area:['800px','500px'],
				maxmin:true,
				success : function(layero, index){
	                var body = layui.layer.getChildFrame('body', index);
	                setTimeout(function(){
	                    layui.layer.tips('点击此处返回菜单列表', '.layui-layer-setwin .layui-layer-close', {
	                        tips: 3
	                    });
	                },500)
	            }
		   });
     });
    //修改
    function updateMenu(id){
    	layer.open({
			title:['修改菜单'],  
			type:2,
			content:'${ctx}/menu/toUpdateMenu.action?id='+id,
			area:['800px','500px'],
			maxmin:true,
			success : function(layero, index){
                var body = layui.layer.getChildFrame('body', index);
                setTimeout(function(){
                    layui.layer.tips('点击此处返回菜单列表', '.layui-layer-setwin .layui-layer-close', {
                        tips: 3
                    });
                },500)
            }
		});
    }
    //删除单个
    function deleteMenu(id){
    	 layer.confirm('确定删除选中的菜单？', {icon: 3, title: '提示信息'}, function (index) {
   		  $.post("${ctx}/menu/delMenu.action?id="+id,function(result){
   	          	layer.msg(result.msg);
   	          });
             tableIns.reload();
             parent.left.initzTree();
        });
     }
    //批量删除
    $(".batchDel_btn").on("click",function(){
    	 var checkStatus = table.checkStatus('menuListTable'),
         data = checkStatus.data,
         url="${ctx}/menu/delMenus.action?1=1";
     if(data.length > 0) {
         for (var i in data) {
             url+="&ids="+data[i].id;
         }
         layer.confirm('确定删除选中的菜单？', {icon: 3, title: '提示信息'}, function (index) {
             $.post(url,function(result){
             	layer.msg(result.msg);
             });
             tableIns.reload();
             parent.left.initzTree();
         });
        }else{
         layer.msg("请选择需要删除的菜单");
      }
   });
});

</script>
</body>
</html>