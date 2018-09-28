<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
  <meta charset="utf-8">
  <title>添加商品</title>
  <meta name="renderer" content="webkit">
  <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
  <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
	<link rel="stylesheet" href="${ctx}/resources/layui/css/layui.css" media="all" />
	<link rel="stylesheet" href="${ctx}/resources/css/public.css" media="all" />
  <!-- 注意：如果你直接复制所有代码到本地，上述css路径需要改成你本地的 -->
</head>
<body class="childrenBody">
<form class="layui-form layui-form-pane" action="" id="fm">
<input type="hidden" name="id" value="${goods.id }">
<div style="margin: 10px 20px">
 <div class="layui-form-item"> 
    <label class="layui-form-label">商品名称</label>
    <div class="layui-input-inline">
      <input type="text" name="goodsname"  value="${goods.goodsname }"  autocomplete="off"  class="layui-input">
    </div>
    <label class="layui-form-label">产地</label>
    <div class="layui-input-inline">
      <input type="text" name="produceplace" value="${goods.produceplace }"  autocomplete="off"  class="layui-input">
    </div>
    <label class="layui-form-label">规格</label>
    <div class="layui-input-inline">
      <input type="text" name="size"  value="${goods.produceplace }" autocomplete="off" class="layui-input">
    </div>
  </div>
   <div class="layui-form-item"> 
    <label class="layui-form-label">包装</label>
    <div class="layui-input-inline">
      <input type="text" name="goodspackage" value="${goods.goodspackage }"  autocomplete="off"  class="layui-input">
    </div>
    <label class="layui-form-label">生产批号</label>
    <div class="layui-input-inline">
      <input type="text" name="productcode" value="${goods.productcode }"  autocomplete="off"  class="layui-input">
    </div>
    <label class="layui-form-label">批准文号</label>
    <div class="layui-input-inline">
      <input type="text" name="promitcode"  value="${goods.promitcode }" autocomplete="off" class="layui-input">
    </div>
  </div>
  
   <div class="layui-form-item"> 
    <label class="layui-form-label">描述</label>
    <div class="layui-input-inline">
      <input type="text" name="description"   value="${goods.description }" autocomplete="off"  class="layui-input">
    </div>
    <label class="layui-form-label">销售价格</label>
    <div class="layui-input-inline">
      <input type="text" name="price" value="${goods.price }"  autocomplete="off"  class="layui-input">
    </div>
    <label class="layui-form-label">供应商编号</label>
    <div class="layui-input-inline">
      <input type="text" name="providerid" value="${goods.providerid }"  autocomplete="off" class="layui-input">
    </div>
  </div>
  
   <div class="layui-form-item"> 
    <label class="layui-form-label">库存数量</label>
    <div class="layui-input-inline">
      <input type="text" name="number" value="${goods.number }"  autocomplete="off"  class="layui-input">
    </div>
    <label class="layui-form-label">预警库存</label>
    <div class="layui-input-inline">
      <input type="text" name="dangernum"  value="${goods.dangernum }" autocomplete="off"  class="layui-input">
    </div>
    <label class="layui-form-label">商品图片</label>
    <div class="layui-input-inline">
      <input type="text" name="goodsimg" value="${goods.goodsimg }"  autocomplete="off" class="layui-input">
    </div>
  </div>
  
  <div class="layui-form-item ">
    <label class="layui-form-label">是否有效</label>
    <div class="layui-input-inline">
    <input type="radio" name="available" title="可用" value="1"  checked>
      <input type="radio" name="available" title="不可用" value="0" >
    </div>
  </div>
  </div>
      <div class="layui-form-item">
			<div class="layui-input-block" style="text-align: center;">
				<a href="javascript:void(0)"  class="layui-btn"  lay-submit="" lay-filter="formSubmit">提交</a>
				<button type="reset" class="layui-btn layui-btn-warm reset">重置</button>
			</div>
	 </div>
</form>
<script type="text/javascript" src="${ctx}/resources/layui/layui.js"></script>
<script type="text/javascript">
   layui.use(['form','layer','jquery'], function(){
		  var form = layui.form
		  ,layer = layui.layer
		  ,$=layui.jquery;
		  form.render();
		  
		  form.on("submit(formSubmit)",function(data){
			 var params=$('#fm').serialize();
		       $.post("${ctx}/goods/updateGoods.action?"+params,function(result){
				    layer.msg(result.msg);//弹出添加成功或失败
				    //关闭窗口
					var index=parent.layer.getFrameIndex(window.name);
					parent.layer.close(index);
			        parent.tableIns.reload();//刷新表格	
				   });
		       });
	       });
</script>
</body>
</html>