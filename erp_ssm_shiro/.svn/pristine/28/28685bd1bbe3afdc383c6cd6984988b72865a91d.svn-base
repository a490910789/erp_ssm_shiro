<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<title>其他管理--登入进货</title>
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
      <label class="layui-form-label">商品名称</label>
      <div class="layui-input-inline">
        <input type="text" name="goodsname" id="goodsname"   autocomplete="off" class="layui-input">
      </div>
    </div>
    <div class="layui-inline">
      <label class="layui-form-label">供应商</label>
      <div class="layui-input-inline">
        <input type="text" name="providername" id="providername"   autocomplete="off" class="layui-input">
      </div>
    </div>
    <br>
     <div class="layui-inline">
      <label class="layui-form-label">开始时间</label>
      <div class="layui-input-inline">
        <input type="text" name="startDate" id="startDate"  autocomplete="off" class="layui-input">
      </div>
    </div>
      <div class="layui-inline">
      <label class="layui-form-label">结束时间</label>
      <div class="layui-input-inline">
        <input type="text" name="endDate" id="endDate"  autocomplete="off" class="layui-input">
      </div>
    </div>
  </div>
      <div class="layui-form-item"  style="text-align: center;">
       <a href="javascript:void(0)" class="search_btn layui-btn">查询</a>
       <button type="reset" class="layui-btn layui-btn-warm" >清空</button>
   </div>
 </form>
<script type="text/javascript" src="${ctx}/resources/layui/layui.js"></script>
<script type="text/javascript" src="${ctx }/resources/zTree/js/jquery-1.4.4.min.js"></script>
	<table id="inportList" lay-filter="inportList" ></table>
	<!-- 表头按钮 -->
	<script type="text/html" id="tableToolBar">
	<a  class="layui-btn layui-btn-normal" lay-event="add">添加</a>
    <a  class="layui-btn layui-btn-danger" lay-event="batchDel">批量删除</a>
	</script>
	<!-- 操作 -->
	<script type="text/html" id="inportListBar">
		<a class="layui-btn layui-btn-xs layui-btn" lay-event="update">编辑</a>
		<a class="layui-btn layui-btn-xs layui-btn-danger" lay-event="delete">删除</a>
	</script>	
<script type="text/javascript">
  var tableIns;
  function reloadTable(id){
		tableIns.reload({
	        page: {
	            curr: 1 //重新从第 1 页开始
	        },
	        where: {
	        	goodsname: $("#goodsname").val(),  //搜索的关键字
	        	providername: $("#providername").val(),  //搜索的关键字
                startDate: $("#startDate").val(),  //搜索的关键字
                endDate: $("#endDate").val(),  //搜索的关键字
                providerid:id
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
        
        //日期选择器渲染
        laydate.render({
        	elem:"#startDate",
        	type:"datetime"
        });
        laydate.render({
        	elem:"#endDate",
        	type:"datetime"
        });
       
    //登入进货列表
        tableIns = table.render({
        elem: '#inportList',
        url : '${ctx}/inport/loadAllInport.action',
        cellMinWidth : 95,
        toolbar: "#tableToolBar",
        page : true,
        height : "full-180",
        limit : 10,
        limits : [10,15,20,25],
        defaultToolbar: ['filter'],
        id : "inportListTable",
        cols : [[
            {type: "checkbox", fixed:"left", width:50},
            {field: 'id', title: '进货ID',width:50,align:"center"},
            {field: 'providername', title: '供应商',width:150, align:'center'},
            {field: 'goodsname', title: '商品名称',width:150, align:'center'},
            {field: 'size', title: '规格', align:'center'},
            {field: 'inporttime', title: '进货时间',width:180,  align:'center'},
            {field: 'number', title: '数量',align:'center'},
            {field: 'inportprice', title: '进货价格',align:'center'},
            {field: 'operateperson', title: '操作人',width:150,align:'center'},
            {field: 'paytype', title: '支付方式',  align:'center'},
            {field: 'remark', title: '注释',width:200,  align:'center'},
            {title: '操作',templet:'#inportListBar',fixed:"right", width:120,align:"center"}
        ]]
    });
    //搜索【此功能需要后台配合，所以暂时没有动态效果演示】
    $(".search_btn").on("click",function(){
    	reloadTable(null);
    });
    
    //批量删除
    function batchDel(){
        var checkStatus = table.checkStatus('inportListTable'),
            data = checkStatus.data,
            url="${ctx}/inport/delInports.action?1=1";
        if(data.length > 0) {
            for (var i in data) {
                url+="&ids="+data[i].id;
            }
            layer.confirm('确定删除选中的进货？', {icon: 3, title: '提示信息'}, function (index) {
                $.post(url,function(result){
                	layer.msg(result.msg);
	                tableIns.reload();
	                layer.close(index);
                });
            });
        }else{
            layer.msg("请选择需要删除的进货");
        }
    }
    
    //监听单元格按钮
    table.on('tool(inportList)', function(obj){
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
    table.on('toolbar(inportList)',function(obj){
     	switch (obj.event) {
 		case "add":
 			toAdd();
 			break;
 		case "batchDel":
 			batchDel();
 			break;
 		}
    });
  //删除进货
    function toDelete(id){
    	  layer.confirm('确定删除选中的进货？', {icon: 3, title: '提示信息'}, function (index) {
    		  $.post("${ctx}/inport/delInport.action?id="+id,function(result){
    	          	layer.msg(result.msg);
		            layer.close(index);
		            tableIns.reload();
    	          });
		      });
		    }
		})
//添加进货
function toAdd(){
		layer.open({
			title:'添加进货',  
			type:2,
			content:'${ctx}/inport/toAddInport.action',
			area:['800px','450px'],
			maxmin:true,
			success : function(layero, index){
                var body = layui.layer.getChildFrame('body', index);
                setTimeout(function(){
                    layui.layer.tips('点击此处返回进货列表', '.layui-layer-setwin .layui-layer-close', {
                        tips: 3
                    });
                },500)
            }
		});
    }
 
 function toUpdate(id){
		layer.open({
			title:'修改进货',  
			type:2,
			content:'${ctx}/inport/toUpdateInport.action?id='+id,
			area:['800px','450px'],
			maxmin:true,
			success : function(layero, index){
             var body = layui.layer.getChildFrame('body', index);
             setTimeout(function(){
                 layui.layer.tips('点击此处返回进货列表', '.layui-layer-setwin .layui-layer-close', {
                     tips: 3
                 });
             },500)
           }
	   });
   }
</script>
</body>
</html>