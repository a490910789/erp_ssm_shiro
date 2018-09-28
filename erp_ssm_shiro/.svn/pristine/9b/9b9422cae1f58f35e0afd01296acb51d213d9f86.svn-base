<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<title>其他管理--登入部门</title>
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
      <label class="layui-form-label">部门名称</label>
      <div class="layui-input-inline">
        <input type="text" name="name" id="name"   autocomplete="off" class="layui-input">
      </div>
    </div>
    <div class="layui-inline">
      <label class="layui-form-label">部门地址</label>
      <div class="layui-input-inline">
        <input type="text" name="loc"  id="loc" autocomplete="off" class="layui-input">
      </div>
    </div>
    <div class="layui-inline">
      <label class="layui-form-label">部门备注</label>
      <div class="layui-input-inline">
        <input type="text" name="remark" id="remark"  autocomplete="off" class="layui-input">
      </div>
    </div>
  </div>
   <div class="layui-form-item" style="text-align: center;">
       <a href="javascript:void(0)" class="search_btn layui-btn">查询</a>
       <button type="reset" class="layui-btn layui-btn-warm">重置</button>
   </div>
 </form>
	<table id="deptList" lay-filter="deptList"></table>
	<!-- 表格工具条 批量删除-->
<script type="text/html" id="tableToolBar">
	    <a class="layui-btn layui-btn-sm" lay-event="add">添加</a>
		<a class="layui-btn layui-btn-sm layui-btn-danger" lay-event="batchDel">批量删除</a> 
</script>

	<!-- 操作 -->
	<script type="text/html" id="deptListBar">
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
            loc: $("#loc").val(),  //搜索的关键字
            remark: $("#remark").val(),  //搜索的关键字
            id:id
       }
   });
}

layui.use(['form','layer','laydate','table','laytpl'],function(){
        var form = layui.form,
        layer = layui.layer,
        $ = layui.jquery,
        laydate = layui.laydate,
        laytpl = layui.laytpl,
        table = layui.table;
        
    //登入部门列表
        tableIns = table.render({
        elem: '#deptList',
        url : '${ctx }/dept/loadAllDept.action',
        cellMinWidth : 95,
        toolbar: '#tableToolBar',
        page : true,
        height : "full-180",
        limit : 10,
        limits : [10,15,20,25],
        defaultToolbar: ['filter'],
        id : "deptListTable",
        cols : [[
            {type: "checkbox", fixed:"left", width:50},
            {field: 'id', title: 'ID', width:60, align:"center"},
            {field: 'pid', title: '父级部门ID', width:120, align:"center"},
            {field: 'name', title: '部门名称', width:180, align:"center"},
            {field: 'remark', title: '部门备注', width:180, align:"center"},
            {field: 'loc', title: '部门地址',align:"center", width:150},
            {field: 'available', title: '是否可用', align:'center',templet:function(data){
            	return data.available==1?"<font color=blue>可用</font>":"<font color=red>不可用</font>"
            }},
            {field: 'open', title: '是否展开', align:'center',templet:function(data){
            	return data.open==1?"<font color=blue>展开</font>":"<font color=red>不展开</font>"
            }},
            {field: 'parent', title: '是否父节点',width:120, align:'center',templet:function(data){
            	return data.parent==1?"<font color=blue>是</font>":"<font color=red>否</font>"
            }},
            {title: '操作', width:170, templet:'#deptListBar',fixed:"right",align:"center"}
        ]]
    });
    
      //搜索【此功能需要后台配合，所以暂时没有动态效果演示】
        $(".search_btn").on("click",function(){
        	  reloadTable(null);
        });

       //监听按钮
    table.on('toolbar(deptList)', function(obj){
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
    table.on('tool(deptList)', function(obj){
    	switch (obj.event) {
		case "delete":
			deleteDept(obj.data.id);
			break;
		case "update":
			updateDept(obj.data.id);
			break;
		default:
			break;
		}
    });
    
    //添加部门
    function toAdd(){
			layer.open({
				title:['添加部门'],  
				type:2,
				content:'${ctx}/dept/toAddDept.action',
				area:['800px','500px'],
				maxmin:true,
				success : function(layero, index){
	                var body = layui.layer.getChildFrame('body', index);
	                setTimeout(function(){
	                    layui.layer.tips('点击此处返回部门列表', '.layui-layer-setwin .layui-layer-close', {
	                        tips: 3
	                    });
	                },500)
	            }
			});
       }
    
    function updateDept(id){
    	layer.open({
			title:['修改部门'],  
			type:2,
			content:'${ctx}/dept/toUpdateDept.action?id='+id,
			area:['800px','500px'],
			maxmin:true,
			success : function(layero, index){
                var body = layui.layer.getChildFrame('body', index);
                setTimeout(function(){
                    layui.layer.tips('点击此处返回部门列表', '.layui-layer-setwin .layui-layer-close', {
                        tips: 3
                    });
                },500)
            }
		});
    }
    //删除单个
    function deleteDept(id){
    	 layer.confirm('确定删除选中的部门？', {icon: 3, title: '提示信息'}, function (index) {
   		  $.post("${ctx}/dept/delDept.action?id="+id,function(result){
   	          	layer.msg(result.msg);
   	          });
             tableIns.reload();
             parent.left.initzTree();
        });
     }
    
    //批量删除
    function batchDel(){
        var checkStatus = table.checkStatus('deptListTable'),
            data = checkStatus.data,
            url="${ctx}/dept/delDepts.action?1=1";
        if(data.length > 0) {
            for (var i in data) {
                url+="&ids="+data[i].id;
            }
            layer.confirm('确定删除选中的部门？', {icon: 3, title: '提示信息'}, function (index) {
                $.post(url,function(result){
                	layer.msg(result.msg);
                });
                tableIns.reload();
                parent.left.initzTree();
            });
        }else{
            layer.msg("请选择需要删除的部门");
        }
    }
   
});

</script>
</body>
</html>