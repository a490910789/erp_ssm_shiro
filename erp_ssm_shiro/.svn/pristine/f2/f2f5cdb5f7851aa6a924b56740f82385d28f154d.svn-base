<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<title>其他管理--登入公告</title>
	<link rel="stylesheet" href="${ctx}/resources/layui/css/layui.css" media="all" />
	<link rel="stylesheet" href="${ctx}/resources/css/public.css" media="all" />
</head>
<body class="childrenBody">
<fieldset class="layui-elem-field layui-field-title" style="margin-top: 5px;">
  <legend>查询条件</legend>
</fieldset>
<form class="layui-form" id="fm" lay-filter="fm">
       <div class="layui-form-item" style="text-align: center;">
    <div class="layui-inline">
      <label class="layui-form-label">公告标题</label>
      <div class="layui-input-inline">
        <input type="text" name="title" id="title"   autocomplete="off" class="layui-input">
      </div>
    </div>
    <div class="layui-inline">
      <label class="layui-form-label">发布时间</label>
      <div class="layui-input-inline">
        <input type="text" name="startDate" id="startDate" lay-verify="date" placeholder="yyyy-MM-dd HH:mm:ss" autocomplete="off" class="layui-input">
      </div>
    </div>
    
     <div class="layui-inline">
      <label class="layui-form-label">结束时间</label>
      <div class="layui-input-inline">
        <input type="text" name="endDate" id="endDate" lay-verify="date" placeholder="yyyy-MM-dd HH:mm:ss" autocomplete="off" class="layui-input">
      </div>
    </div>
  </div>
   <div class="layui-form-item" lay-filter="tableToolBar" style="text-align: center;">
       <a href="javascript:void(0)" class="search_btn layui-btn">查询</a>
       <button type="reset" class="layui-btn layui-btn-warm" >清空</button>
   </div>
 </form>
<script type="text/javascript" src="${ctx}/resources/layui/layui.js"></script>
	<table id="noticeList" lay-filter="noticeList" ></table>
	<!-- 表头操作按钮 -->
<script type="text/html" id="tableToolBar">
       <a class="layui-btn" lay-event="add">添加</a>
       <a class="layui-btn layui-btn-danger" lay-event="batchDel">批量删除</a>
</script>
	<!-- 操作 -->
	<script type="text/html" id="noticeListBar">
		<a class="layui-btn layui-btn-xs layui-btn" lay-event="update">编辑</a>
		<a class="layui-btn layui-btn-xs layui-btn-danger" lay-event="delete">删除</a>
		<a class="layui-btn layui-btn-xs layui-btn-normal" lay-event="find">查看</a>
	</script>	
<script type="text/javascript">
  var tableIns;
layui.use(['form','layer','laydate','table','laytpl'],function(){
        var form = layui.form,
        layer = parent.layer === undefined ? layui.layer : top.layer,
        $ = layui.jquery,
        laydate = layui.laydate,
        laytpl = layui.laytpl,
        table = layui.table;
    //登入公告列表
        tableIns = table.render({
        elem: '#noticeList',
        url : '${ctx}/notice/loadAllNotice.action',
        cellMinWidth : 95,
        toolbar: "#tableToolBar",
        page : true,
        height : "full-180",
        limit : 10,
        limits : [10,15,20,25],
        defaultToolbar: ['filter'],
        id : "noticeListTable",
        cols : [[
            {type: "checkbox", fixed:"left", width:50},
            {field: 'id', title: '公告ID',align:"center"},
            {field: 'title', title: '公告标题',align:"center"},
            {field: 'createtime', title: '发布时间', align:'center'},
            {field: 'opername', title: '发布人',  align:'center'},
            {title: '操作', width:180, templet:'#noticeListBar',fixed:"right",align:"center"}
        ]]
    });
    
    laydate.render({
    	elem:"#startDate",
    	type:"datetime"
    });
    laydate.render({
    	elem:"#endDate",
    	type:"datetime"
    });
   
    //搜索【此功能需要后台配合，所以暂时没有动态效果演示】
    $(".search_btn").on("click",function(){
            table.reload("noticeListTable",{
                page: {
                    curr: 1 //重新从第 1 页开始
                },
                where: {
                    title: $("#title").val(),  //搜索的关键字
                    startDate: $("#startDate").val(),  //搜索的关键字
                    endDate: $("#endDate").val(),  //搜索的关键字
                }
          })
    });
    
    //批量删除
    function batchDel(){
        var checkStatus = table.checkStatus('noticeListTable'),
            data = checkStatus.data,
            url="${ctx}/notice/delNotices.action?1=1";
        if(data.length > 0) {
            for (var i in data) {
                url+="&ids="+data[i].id;
            }
            layer.confirm('确定删除选中的公告？', {icon: 3, title: '提示信息'}, function (index) {
                $.post(url,function(result){
                	layer.msg(result.msg);
		            tableIns.reload();
		            layer.close(index);
                });
            });
        }else{
            layer.msg("请选择需要删除的公告");
        }
    }
    
  //查看内容
    function see(){
    	var checkStatus = table.checkStatus('noticeListTable');
        data = checkStatus.data;
        layer.open({
    		title:'公告内容',  
    		type:2,
    		content:data.content,
    		area:['800px','500px'],
    		maxmin:true
    	});
    }

    //监听单元格按钮
    table.on('tool(noticeList)', function(obj){
       var id=obj.data.id;
    	switch (obj.event) {
		case "update":
			toAddOrUpdate(id,"修改公告");
			break;
		case "delete":
			toDelete(id);
			break;
		case "find":
			find(id);
			break;
		}
    });
    //监听表格按钮
    table.on('toolbar(noticeList)',function(obj){
     	switch (obj.event) {
 		case "add":
 			toAddOrUpdate("","添加公告");
 			break;
 		case "batchDel":
 			batchDel();
 			break;
 		}
    });
  //删除公告
    function toDelete(id){
    	  layer.confirm('确定删除选中的公告？', {icon: 3, title: '提示信息'}, function (index) {
    		  $.post("${ctx}/notice/delNotice.action?id="+id,function(result){
    	          	layer.msg(result.msg);
		            layer.close(index);
		            tableIns.reload();
    	          });
        });
    }
})
//添加公告
function toAddOrUpdate(id,title){
	var title=title;
		layer.open({
			title:title,  
			type:2,
			content:'${ctx}/notice/toAddOrUpdateNotice.action?id='+id,
			area:['800px','500px'],
			maxmin:true,
			success : function(layero, index){
                var body = layui.layer.getChildFrame('body', index);
                setTimeout(function(){
                    layui.layer.tips('点击此处返回公告列表', '.layui-layer-setwin .layui-layer-close', {
                        tips: 3
                    });
                },500)
            }
		});
   }
   
//查看公告详细内容
function find(id){
		layer.open({
			title:['公告内容'],  
			type:2,
			content:'${ctx}/notice/findContent.action?id='+id,
			area:['800px','500px'],
			maxmin:true,
			success : function(layero, index){
                var body = layui.layer.getChildFrame('body', index);
                setTimeout(function(){
                    layui.layer.tips('点击此处返回公告列表', '.layui-layer-setwin .layui-layer-close', {
                        tips: 3
                    });
                },500)
            }
		});
   }

</script>
</body>
</html>