<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
  <meta charset="utf-8">
  <title>添加客户</title>
  <meta name="renderer" content="webkit">
  <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
  <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
	<link rel="stylesheet" href="${ctx}/resources/layui/css/layui.css" media="all" />
	<link rel="stylesheet" href="${ctx}/resources/css/public.css" media="all" />
  <!-- 注意：如果你直接复制所有代码到本地，上述css路径需要改成你本地的 -->
</head>
<body class="childrenBody">
<form class="layui-form layui-form-pane" action="" id="fm">
<input type="hidden"  name="id" value="${customer.id }"> 
<div style="margin: 10px 20px">
 <div class="layui-form-item"> 
    <label class="layui-form-label">客户名称</label>
    <div class="layui-input-inline">
      <input type="text" name="customername" value="${customer.customername }" autocomplete="off"  class="layui-input">
    </div>
    <label class="layui-form-label">公司座机</label>
    <div class="layui-input-inline">
      <input type="text" name="telephone"  value="${customer.telephone }" autocomplete="off" class="layui-input">
    </div>
  </div>
  <div class="layui-form-item layui-inline">
      <label class="layui-form-label">地址</label>
      <div class="layui-input-inline">
        <input type="tel" name="address" value="${customer.address }" autocomplete="off" class="layui-input">
      </div>
      <label class="layui-form-label">邮编</label>
      <div class="layui-input-inline">
        <input type="text" name="zip"  value="${customer.zip }"  autocomplete="off" class="layui-input">
    </div>
      <label class="layui-form-label">传真</label>
      <div class="layui-input-inline">
        <input type="text" name="fax"  value="${customer.fax }"  autocomplete="off" class="layui-input">
      </div>
  </div>
  
  <div class="layui-form-item layui-inline">
      <label class="layui-form-label">联系人</label>
      <div class="layui-input-inline">
        <input type="tel" name="connectionperson" value="${customer.connectionperson }"  autocomplete="off" class="layui-input">
    </div>
      <label class="layui-form-label">联系电话</label>
      <div class="layui-input-inline">
        <input type="text" name="phone" value="${customer.phone }"  autocomplete="off" class="layui-input">
    </div>
    <label class="layui-form-label">email</label>
      <div class="layui-input-inline">
        <input type="text" name="email"  value="${customer.email }"  autocomplete="off" class="layui-input">
      </div>
  </div>
  <div class="layui-form-item layui-inline">
      <label class="layui-form-label">开户银行</label>
      <div class="layui-input-inline">
        <input type="tel" name="bank" value="${customer.bank }" autocomplete="off" class="layui-input">
      </div>
      <label class="layui-form-label">银行卡号</label>
      <div class="layui-input-inline">
        <input type="text" name="account" value="${customer.account }"  autocomplete="off" class="layui-input">
    </div>
  </div>
   
  <div class="layui-form-item ">
    <label class="layui-form-label">是否有效</label>
    <div class="layui-input-inline">
    <input type="radio" name="available" title="可用" value="1"  ${customer.available eq 1?'checked':'' }>
      <input type="radio" name="available" title="不可用" value="0"  ${customer.available eq 0?'checked':'' }>
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
		       $.post("${ctx}/customer/updateCustomer.action?"+params,function(result){
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