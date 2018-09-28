<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<title>退货管理--商品退货</title>
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
  </div>
      <div class="layui-form-item"  style="text-align: center;">
       <a href="javascript:void(0)" class="search_btn layui-btn">查询</a>
       <button type="reset" class="layui-btn layui-btn-warm" >清空</button>
   </div>
 </form>
<script type="text/javascript" src="${ctx}/resources/layui/layui.js"></script>
<script type="text/javascript" src="${ctx }/resources/zTree/js/jquery-1.4.4.min.js"></script>
	<table id="outportList" lay-filter="outportList" ></table>
	<!-- 表头按钮 -->
	<script type="text/html" id="tableToolBar">
	<a  class="layui-btn layui-btn-normal" lay-event="add">添加</a>
    <a  class="layui-btn layui-btn-danger" lay-event="batchDel">批量删除</a>
	</script>
	<!-- 操作 -->
	<script type="text/html" id="outportListBar">
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
       
    //登入退货列表
        tableIns = table.render({
        elem: '#outportList',
        url : '${ctx}/outport/loadAllOutport.action',
        cellMinWidth : 95,
        toolbar: "#tableToolBar",
        page : true,
        height : "full-180",
        limit : 10,
        limits : [10,15,20,25],
        defaultToolbar: ['filter'],
        id : "outportListTable",
        cols : [[
            {type: "checkbox", fixed:"left", width:50},
            {field: 'id', title: '退货ID',width:50,align:"center"},
            {field: 'providername', title: '供应商',width:150, align:'center'},
            {field: 'goodsname', title: '商品名称',width:150, align:'center'},
            {field: 'size', title: '规格', align:'center'},
            {field: 'outputtime', title: '退货时间',width:180,  align:'center'},
            {field: 'number', title: '数量',align:'center'},
            {field: 'outportprice', title: '退货价格',align:'center'},
            {field: 'operateperson', title: '操作人',width:150,align:'center'},
            {field: 'paytype', title: '支付方式',  align:'center'},
            {field: 'remark', title: '注释',width:200,  align:'center'},
            {title: '操作',templet:'#outportListBar',fixed:"right", width:120,align:"center"}
        ]]
    });
    //搜索【此功能需要后台配合，所以暂时没有动态效果演示】
    $(".search_btn").on("click",function(){
    	reloadTable(null);
    });
    
    //批量删除
    function batchDel(){
        var checkStatus = table.checkStatus('outportListTable'),
            data = checkStatus.data,
            url="${ctx}/outport/delOutports.action?1=1";
        if(data.length > 0) {
            for (var i in data) {
                url+="&ids="+data[i].id;
            }
            layer.confirm('确定删除选中的退货？', {icon: 3, title: '提示信息'}, function (index) {
                $.post(url,function(result){
                	layer.msg(result.msg);
	                tableIns.reload();
	                layer.close(index);
                });
            });
        }else{
            layer.msg("请选择需要删除的退货");
        }
    }
    
    //监听单元格按钮
    table.on('tool(outportList)', function(obj){
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
    table.on('toolbar(outportList)',function(obj){
     	switch (obj.event) {
 		case "add":
 			toAdd();
 			break;
 		case "batchDel":
 			batchDel();
 			break;
 		}
    });
  //删除退货
    function toDelete(id){
    	  layer.confirm('确定删除选中的退货？', {icon: 3, title: '提示信息'}, function (index) {
    		  $.post("${ctx}/outport/delOutport.action?id="+id,function(result){
    	          	layer.msg(result.msg);
		            layer.close(index);
		            tableIns.reload();
    	          });
		      });
		    }
		})
//添加退货
function toAdd(){
		layer.open({
			title:'添加退货',  
			type:2,
			content:'${ctx}/outport/toAddOutport.action',
			area:['800px','450px'],
			maxmin:true,
			success : function(layero, index){
                var body = layui.layer.getChildFrame('body', index);
                setTimeout(function(){
                    layui.layer.tips('点击此处返回退货列表', '.layui-layer-setwin .layui-layer-close', {
                        tips: 3
                    });
                },500)
            }
		});
    }
 
 function toUpdate(id){
		layer.open({
			title:'修改退货',  
			type:2,
			content:'${ctx}/outport/toUpdateOutport.action?id='+id,
			area:['800px','450px'],
			maxmin:true,
			success : function(layero, index){
             var body = layui.layer.getChildFrame('body', index);
             setTimeout(function(){
                 layui.layer.tips('点击此处返回退货列表', '.layui-layer-setwin .layui-layer-close', {
                     tips: 3
                 });
             },500)
           }
	   });
   }
</script>
</body>
</html>