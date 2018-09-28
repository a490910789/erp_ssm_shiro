<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<title>其他管理--登入客户</title>
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
      <label class="layui-form-label">客户名称</label>
      <div class="layui-input-inline">
       <input type="text" name="customername" id="customername"   autocomplete="off" class="layui-input">
      </div>
    </div>
    <div class="layui-inline">
      <label class="layui-form-label">联系电话</label>
      <div class="layui-input-inline">
        <input type="text" name="phone" id="phone"   autocomplete="off" class="layui-input">
      </div>
    </div>
    
     <div class="layui-inline">
      <label class="layui-form-label">联系人</label>
      <div class="layui-input-inline">
        <input type="text" name="connectionperson" id="connectionperson"  autocomplete="off" class="layui-input">
      </div>
    </div>
  </div>
   <div class="layui-form-item"  style="text-align: center;">
       <a href="javascript:void(0)" class="search_btn layui-btn">查询</a>
       <button type="reset" class="layui-btn layui-btn-warm" >清空</button>
   </div>
 </form>
<script type="text/javascript" src="${ctx}/resources/layui/layui.js"></script>
	<table id="customerList" lay-filter="customerList" ></table>
	<!-- 表头按钮 -->
	<script type="text/html" id="tableToolBar">
	<a  class="layui-btn layui-btn-normal" lay-event="add">添加</a>
    <a  class="layui-btn layui-btn-danger" lay-event="batchDel">批量删除</a>
	</script>
	<!-- 操作 -->
	<script type="text/html" id="customerListBar">
		<a class="layui-btn layui-btn-xs layui-btn" lay-event="update">编辑</a>
		<a class="layui-btn layui-btn-xs layui-btn-danger" lay-event="delete">删除</a>
	</script>	
<script type="text/javascript">

//声明tableIns为全局变量 方便调用
  var tableIns;
  
layui.use(['form','layer','laydate','table','laytpl'],function(){
        var form = layui.form,
        layer = parent.layer === undefined ? layui.layer : top.layer,
        $ = layui.jquery,
        laydate = layui.laydate,
        laytpl = layui.laytpl,
        table = layui.table;
    //登入客户列表
        tableIns = table.render({
        elem: '#customerList',
        url : '${ctx}/customer/loadAllCustomer.action',
        cellMinWidth : 95,
        toolbar: "#tableToolBar",
        page : true,
        height : "full-180",
        limit : 10,
        limits : [10,15,20,25],
        defaultToolbar: ['filter'],
        id : "customerListTable",
        cols : [[
            {type: "checkbox", fixed:"left", width:50},
            {field: 'id', title: '客户ID',align:"center"},
            {field: 'customername', title: '姓名',align:"center"},
            {field: 'zip', title: '邮编', align:'center'},
            {field: 'address', title: '公司地址', align:'center'},
            {field: 'telephone', title: '公司电话', width:150,align:'center'},
            {field: 'connectionperson', title: '联系人',  align:'center'},
            {field: 'phone', title: '联系电话',width:150,  align:'center'},
            {field: 'bank', title: '开户银行',  align:'center'},
            {field: 'account', title: '银行账号', width:180,  align:'center'},
            {field: 'email', title: '邮箱', width:180, align:'center'},
            {field: 'fax', title: '传真',  align:'center'},
            {field: 'available ', title: '状态',  align:'center',templet:function(data){
            	return data.available==1?"<font color=blue>有效</font>":"<font color=red>无效<font>"
             }},
            {title: '操作', width:180, templet:'#customerListBar',fixed:"right",align:"center"}
        ]]
    });
    //搜索+模糊查询
    $(".search_btn").on("click",function(){
            table.reload("customerListTable",{
                page: {
                    curr: 1 //重新从第 1 页开始
                },
                where: {
                	customername: $("#customername").val(),  //搜索的关键字
                    phone: $("#phone").val(),  //搜索的关键字
                    connectionperson: $("#connectionperson").val(),  //搜索的关键字
                }
          })
    });
    
    
    //批量删除
    function batchDel(){
        var checkStatus = table.checkStatus('customerListTable'),
            data = checkStatus.data,
            url="${ctx}/customer/delCustomers.action?1=1";
        if(data.length > 0) {
            for (var i in data) {
                url+="&ids="+data[i].id;
            }
            layer.confirm('确定删除选中的客户？', {icon: 3, title: '提示信息'}, function (index) {
                $.post(url,function(result){
                	layer.msg(result.msg);
	                tableIns.reload();
	                layer.close(index);
                });
            });
        }else{
            layer.msg("请选择需要删除的客户");
        }
    }
    
    //监听单元格操作按钮
    table.on('tool(customerList)', function(obj){
       var id=obj.data.id;
    	switch (obj.event) {
		case "update":
			toUpdate(id);
			break;
		case "delete":
			toDelete(id);
			break;
		}
    });
    //监听表格按钮
    table.on('toolbar(customerList)',function(obj){
     	switch (obj.event) {
 		case "add":
 			toAdd();
 			break;
 		case "batchDel":
 			batchDel();
 			break;
 		}
    });
  //删除客户
    function toDelete(id){
    	  layer.confirm('确定删除选中的客户？', {icon: 3, title: '提示信息'}, function (index) {
    		  $.post("${ctx}/customer/delCustomer.action?id="+id,function(result){
    	          	layer.msg(result.msg);
		            layer.close(index);
		            tableIns.reload();
    	          });
        });
    }
})
//添加客户
function toAdd(){
		layer.open({
			title:'添加客户',  
			type:2,
			content:'${ctx}/customer/toAddCustomer.action',
			area:['1000px','480px'],
			maxmin:true,
			success : function(layero, index){
                var body = layui.layer.getChildFrame('body', index);
                setTimeout(function(){
                    layui.layer.tips('点击此处返回客户列表', '.layui-layer-setwin .layui-layer-close', {
                        tips: 3
                    });
                },500)
            }
		});
    }
 //修改客户
 function toUpdate(id){
		layer.open({
			title:'修改客户',  
			type:2,
			content:'${ctx}/customer/toUpdateCustomer.action?id='+id,
			area:['1000px','480px'],
			maxmin:true,
			success : function(layero, index){
             var body = layui.layer.getChildFrame('body', index);
             setTimeout(function(){
                 layui.layer.tips('点击此处返回客户列表', '.layui-layer-setwin .layui-layer-close', {
                     tips: 3
                 });
             },500)
           }
	   });
   }
</script>
</body>
</html>