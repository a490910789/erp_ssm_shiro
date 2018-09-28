<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<title>其他管理--登入商品</title>
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
      <label class="layui-form-label">批准文号</label>
      <div class="layui-input-inline">
        <input type="text" name="promitcode" id="promitcode"   autocomplete="off" class="layui-input">
      </div>
    </div>
    
     <div class="layui-inline">
      <label class="layui-form-label">规格</label>
      <div class="layui-input-inline">
        <input type="text" name="size" id="size"  autocomplete="off" class="layui-input">
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
	<table id="goodsList" lay-filter="goodsList" ></table>
	<!-- 表头按钮 -->
	<script type="text/html" id="tableToolBar">
	<a  class="layui-btn layui-btn-normal" lay-event="add">添加</a>
    <a  class="layui-btn layui-btn-danger" lay-event="batchDel">批量删除</a>
	</script>
	<!-- 操作 -->
	<script type="text/html" id="goodsListBar">
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
            	promitcode: $("#promitcode").val(),  //搜索的关键字
                size: $("#size").val(),  //搜索的关键字
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
    //登入商品列表
        tableIns = table.render({
        elem: '#goodsList',
        url : '${ctx}/goods/loadAllGoods.action',
        cellMinWidth : 95,
        toolbar: "#tableToolBar",
        page : true,
        height : "full-180",
        limit : 10,
        limits : [10,15,20,25],
        defaultToolbar: ['filter'],
        id : "goodsListTable",
        cols : [[
            {type: "checkbox", fixed:"left", width:50},
            {field: 'id', title: '商品ID',align:"center"},
            {field: 'goodsname', title: '商品名称',width:150,align:"center"},
            {field: 'produceplace', title: '产地', align:'center'},
            {field: 'size', title: '规格', align:'center'},
            {field: 'goodspackage', title: '包装', width:150,align:'center'},
            {field: 'productcode', title: '生产批号',width:150,  align:'center'},
            {field: 'promitcode', title: '批准文号',width:150,  align:'center'},
            {field: 'description', title: '描述',width:150,  align:'center'},
            {field: 'price', title: '销售价格', width:180,  align:'center'},
            {field: 'providerid', title: '供应商编号', width:180, align:'center'},
            {field: 'number', title: '库存数量',  align:'center'},
            {field: 'dangernum', title: '预警数量',  align:'center'},
            {field: 'goodsimg', title: '商品图片',  align:'center',templet:function(data){
            	return "<img  src="+data.goodsimg+">"
            }},
            {field: 'available ', title: '状态',  align:'center',templet:function(data){
            	return data.available==1?"<font color=blue>有效</font>":"<font color=red>无效<font>"
             }},
            {title: '操作', width:180, templet:'#goodsListBar',fixed:"right",align:"center"}
        ]]
    });
    //搜索【此功能需要后台配合，所以暂时没有动态效果演示】
    $(".search_btn").on("click",function(){
    	reloadTable(null);
    });
    
    //批量删除
    function batchDel(){
        var checkStatus = table.checkStatus('goodsListTable'),
            data = checkStatus.data,
            url="${ctx}/goods/delGoodses.action?1=1";
        if(data.length > 0) {
            for (var i in data) {
                url+="&ids="+data[i].id;
            }
            layer.confirm('确定删除选中的商品？', {icon: 3, title: '提示信息'}, function (index) {
                $.post(url,function(result){
                	layer.msg(result.msg);
	                tableIns.reload();
	                layer.close(index);
                });
            });
        }else{
            layer.msg("请选择需要删除的商品");
        }
    }
    
    //监听单元格按钮
    table.on('tool(goodsList)', function(obj){
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
    table.on('toolbar(goodsList)',function(obj){
     	switch (obj.event) {
 		case "add":
 			toAdd();
 			break;
 		case "batchDel":
 			batchDel();
 			break;
 		}
    });
  //删除商品
    function toDelete(id){
    	  layer.confirm('确定删除选中的商品？', {icon: 3, title: '提示信息'}, function (index) {
    		  $.post("${ctx}/goods/delGoods.action?id="+id,function(result){
    	          	layer.msg(result.msg);
	                layer.close(index);
	                tableIns.reload();
    	          });
        });
    }
})
//添加商品
function toAdd(){
		layer.open({
			title:'添加商品',  
			type:2,
			content:'${ctx}/goods/toAddGoods.action',
			area:['900px','480px'],
			maxmin:true,
			success : function(layero, index){
                var body = layui.layer.getChildFrame('body', index);
                setTimeout(function(){
                    layui.layer.tips('点击此处返回商品列表', '.layui-layer-setwin .layui-layer-close', {
                        tips: 3
                    });
                },500)
            }
		});
    }
 
 function toUpdate(id){
		layer.open({
			title:'修改商品',  
			type:2,
			content:'${ctx}/goods/toUpdateGoods.action?id='+id,
			area:['1080px','400px'],
			maxmin:true,
			success : function(layero, index){
             var body = layui.layer.getChildFrame('body', index);
             setTimeout(function(){
                 layui.layer.tips('点击此处返回商品列表', '.layui-layer-setwin .layui-layer-close', {
                     tips: 3
                 });
             },500)
           }
	   });
   }
</script>
</body>
</html>