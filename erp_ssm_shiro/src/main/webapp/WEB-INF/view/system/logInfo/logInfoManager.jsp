<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<title>其他管理--登入日志</title>
	<link rel="stylesheet" href="${ctx}/resources/layui/css/layui.css" media="all" />
	<link rel="stylesheet" href="${ctx}/resources/css/public.css" media="all" />
</head>
<body class="childrenBody">
<fieldset class="layui-elem-field layui-field-title" style="margin-top: 5px;">
  <legend>查询条件</legend>
</fieldset>
<form class="layui-form" id="fm">
       <div class="layui-form-item">
    <div class="layui-inline">
      <label class="layui-form-label">登入名</label>
      <div class="layui-input-inline">
        <input type="text" name="loginname" id="loginname"   autocomplete="off" class="layui-input">
      </div>
    </div>
    <div class="layui-inline">
      <label class="layui-form-label">登入IP</label>
      <div class="layui-input-inline">
        <input type="text" name="loginip" id="loginip"   autocomplete="off" class="layui-input">
      </div>
    </div>
    <div class="layui-inline">
      <label class="layui-form-label">开始时间</label>
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
   <div class="layui-form-item" style="text-align: center;">
       <a href="javascript:void(0)" class="search_btn layui-btn">查询</a>
       <button type="reset" class="layui-btn layui-btn-warm">重置</button>
       </div>
 </form>
<script type="text/javascript" src="${ctx}/resources/layui/layui.js"></script>
	<table id="logInfoList" lay-filter="logInfoList" ></table>
<!-- 表格工具条 批量删除-->
    <script type="text/html" id="tableToolBar">
	 <a class="layui-btn layui-btn-sm layui-btn-danger " lay-event="batchDel"> 批量删除 </a> 
    </script>
	<!-- 操作 -->
	<script type="text/html" id="logInfoListBar">
		<a class="layui-btn layui-btn-xs layui-btn-danger" lay-event="del">删除</a>
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
    //登入日志列表
        tableIns = table.render({
        elem: '#logInfoList',
        url : '${ctx}/logInfo/loadAllLogInfo.action',
        cellMinWidth : 95,
        toolbar: "#tableToolBar",
        page : true,
        height : "full-180",
        limit : 10,
        limits : [10,15,20,25],
        defaultToolbar: ['filter'],
        id : "logInfoListTable",
        cols : [[
            {type: "checkbox", fixed:"left", width:50},
            {field: 'id', title: 'ID',align:"center"},
            {field: 'loginname', title: '登入名',align:"center"},
            {field: 'loginip', title: '登入IP', align:'center'},
            {field: 'logintime', title: '登入时间',  align:'center'},
            {title: '操作', width:80, templet:'#logInfoListBar',fixed:"right",align:"center"}
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
            table.reload("logInfoListTable",{
                page: {
                    curr: 1 //重新从第 1 页开始
                },
                where: {
                    loginname: $("#loginname").val(),  //搜索的关键字
                    loginip: $("#loginip").val(),  //搜索的关键字
                    startDate: $("#startDate").val(),  //搜索的关键字
                    endDate: $("#endDate").val()  //搜索的关键字
                }
            })
    });
    
    //监听按钮
    table.on('toolbar(logInfoList)', function(obj){
    	switch (obj.event) {
		case "batchDel":
			batchDel();
			break;
    	};
    });     
    
    //批量删除
    function batchDel(){
        var checkStatus = table.checkStatus('logInfoListTable'),
            data = checkStatus.data,
            url="${ctx}/logInfo/delLogInfos.action?1=1";
        if(data.length > 0) {
            for (var i in data) {
                url+="&ids="+data[i].id;
            }
            layer.confirm('确定删除选中的日志？', {icon: 3, title: '提示信息'}, function (index) {
                $.post(url,function(result){
                	layer.msg(result.msg);
	                tableIns.reload();
	                layer.close(index);
                });
            });
        }else{
            layer.msg("请选择需要删除的日志");
        }
    }

    //监听删除按钮
    table.on('tool(logInfoList)', function(obj){
    	  layer.confirm('确定删除选中的日志？', {icon: 3, title: '提示信息'}, function (index) {
    		  $.post("${ctx}/logInfo/delLogInfo.action?id="+obj.data.id,function(result){
    	          	layer.msg(result.msg);
		            tableIns.reload();
		            layer.close(index);
    	          });
          });
    });
})
</script>
</body>
</html>