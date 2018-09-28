<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>添加商品</title>
<meta name="renderer" content="webkit">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta name="viewport"
	content="width=device-width, initial-scale=1, maximum-scale=1">
<link rel="stylesheet" href="${ctx }/resources/layui/css/layui.css" media="all" />
<link rel="stylesheet" href="${ctx }/resources/css/public.css" media="all" />
<link rel="stylesheet" href="${ctx }/resources/zTree/css/metroStyle/metroStyle.css" type="text/css">
<link rel="stylesheet" type="text/css" href="${ctx }/resources/plugin/css/index.css">
<!-- 注意：如果你直接复制所有代码到本地，上述css路径需要改成你本地的 -->
</head>
<body class="childrenBody">
	<form class="layui-form" action="" id="fm">
		<div style="margin: 10px 20px">

			<div class="layui-form-item ">
				<label class="layui-form-label">选择供应商</label>
				<div class="layui-input-inline">
					<div id="providerid" name="providerid"
						class="layui-form-select select-tree"></div>
				</div>
				<label class="layui-form-label">商品名称</label>
				<div class="layui-input-inline">
					<select id="goodsid" name="goodsid" autocomplete="off" class="layui-input">
					<option value=""></option>
					</select>
				</div>
			</div>
			<div class="layui-form-item">
				<label class="layui-form-label">进货数量</label>
				<div class="layui-input-inline">
				<input type="text" name="number" placeholder="数量" autocomplete="off"
						class="layui-input">
				</div>
				<label class="layui-form-label">进货价格</label>
				<div class="layui-input-inline">
					<input type="text" name="inportprice" placeholder="价格" autocomplete="off"
						class="layui-input">
				</div>
			</div>
			<div class="layui-form-item">
				<label class="layui-form-label">支付方式</label>
				<div class="layui-input-block">
					<input type="radio" name="paytype" title="支付宝" value="支付宝" checked>
					<input type="radio" name="paytype" title="微信" value="微信">
					<input type="radio" name="paytype" title="现金" value="现金">
				</div>
			</div>
			<div class="layui-form-item">
				<div class="layui-form-item">
					<label class="layui-form-label">备注</label>
					<div class="layui-input-block">
						<textarea name="remark" placeholder="请输入"
							class="layui-textarea"></textarea>
					</div>
				</div>
			</div>
		</div>
		<div class="layui-form-item">
			<div class="layui-input-block" style="text-align: center;">
				<a href="javascript:void(0)" class="layui-btn" lay-submit=""
					lay-filter="formSubmit">提交</a>
				<button type="reset" class="layui-btn layui-btn-warm reset">重置</button>
			</div>
		</div>
	</form>
<script type="text/javascript" src="${ctx }/resources/layui/layui.js"></script>
<script type="text/javascript" src="${ctx }/resources/zTree/js/jquery-1.4.4.min.js"></script>
<script type="text/javascript" src="${ctx }/resources/zTree/js/jquery.ztree.core.js"></script>
<script type="text/javascript" src="${ctx }/resources/plugin/js/selectTree.js"></script>
<script type="text/javascript">
var form;
		layui.use([ 'form', 'layer', 'jquery','upload' ], function() {
			form = layui.form; var layer = layui.layer, $ = layui.jquery,upload=layui.upload;
			form.render();
			
			form.on("submit(formSubmit)", function(data) {
				var params = $('#fm').serialize();
				$.post("${ctx}/inport/addInport.action?" + params, function(
						result) {
					layer.msg(result.msg);//弹出添加成功或失败
				    //关闭窗口
					var index = parent.layer.getFrameIndex(window.name);
					parent.layer.close(index);
					parent.tableIns.reload();//刷新表格	
				});
			});
		});
		$(document).ready(function() {
			initProviderTree();
		});
		function initProviderTree() {
			$.post("${ctx}/provider/loadTreeForProvider.action", function(
					zNodes) {
				initSelectTree("providerid", zNodes, false);
			});
		}
		
		 //选中供应商时 点击事件 查询该供应商下所有商品 生成下拉列表数据
		 function initGoodsName(id){
				$.post("${ctx}/goods/queryGoodsByProviderid.action?providerid="+id,function(data){
					layui.use('form',function(){
						  var form=layui.form;
					}); 	  
				 if(data.length>0){
					 var html="";
					for (var i = 0; i < data.length; i++) {
						  html+="<option value="+data[i].id+" >"+data[i].goodsname+data[i].size+"</option>";
					 }
						  $("#goodsid").html(html);
						  form.render("select");
				 }else{
					 $("#goodsid").html("");
					 form.render("select");
				 }
			 },'json');
	    }
	</script>
</body>
</html>