<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<title>修改密码</title>
	<meta name="renderer" content="webkit">
	<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
	<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
	<meta name="apple-mobile-web-app-status-bar-style" content="black">
	<meta name="apple-mobile-web-app-capable" content="yes">
	<meta name="format-detection" content="telephone=no">
	<link rel="stylesheet" href="${ctx }/resources/layui/css/layui.css" media="all" />
	<link rel="stylesheet" href="${ctx }/resources/css/public.css" media="all" />
</head>
<body class="childrenBody">
<form class="layui-form layui-row changePwd" id="fm">
	<div class="layui-col-xs12 layui-col-sm6 layui-col-md6">
		<div class="layui-form-item">
			<label class="layui-form-label">用户名</label>
			<div class="layui-input-block">
				<input type="text" value="${user.name }" name="loginname"  disabled class="layui-input layui-disabled">
			</div>
		</div>
		<div class="layui-form-item">
			<label class="layui-form-label">旧密码</label>
			<div class="layui-input-block">
				<input type="password" name="pwd" id="pwd" placeholder="请输入旧密码" lay-verify="required|oldPwd" class="layui-input pwd">
			</div>
		</div>
		<div class="layui-form-item">
			<label class="layui-form-label">新密码</label>
			<div class="layui-input-block">
				<input type="password"  name="newPwd" placeholder="请输入新密码" lay-verify="required|newPwd" id="oldPwd" class="layui-input pwd">
			</div>
		</div>
		<div class="layui-form-item">
			<label class="layui-form-label">确认密码</label>
			<div class="layui-input-block">
				<input type="password"  name="confirmPwd" placeholder="请确认密码" lay-verify="required|confirmPwd" class="layui-input pwd">
			</div>
		</div>
		  <span style="color: red;" id="msg"></span>
		<div class="layui-form-item">
			<div class="layui-input-block">
				<a href="javascript:void(0)" class="layui-btn" lay-submit="" lay-filter="changePwd">立即修改</a>
				<button type="reset" class="layui-btn layui-btn-primary">重置</button>
			</div>
		</div>
	</div>
</form>
<script type="text/javascript" src="${ctx }/resources/layui/layui.js"></script>
<script type="text/javascript">
   layui.use(['form','jquery','layer'],function(){
	   var form=layui.form,
	    $=layui.jquery
	    layer=layui.layer;
	   
	   form.on('submit(changePwd)',function(){
		   var params=$("#fm").serialize();
		   $.post("${ctx}/user/changePwd.action?"+params,function(result){
			  if(result==true){
				  alert("密码修改成功！请重新登入");
				  parent.location.href="../index.jsp";
			  }else{
				  $("#msg").html("旧密码错误");
			  }
		   });
	   })
	 
	   //添加验证规则
	    form.verify({
	       /*  oldPwd : function(value, item){
	        	var pwd=${user.pwd };
	       		  if(value!=12312312){
		             return "密码错误，请重新输入！";
		         }
	        }, */
	        newPwd : function(value, item){
	            if(value.length < 6){
	                return "密码长度不能小于6位";
	            }
	        },
	        confirmPwd : function(value, item){
	            if(!new RegExp($("#oldPwd").val()).test(value)){
	                return "两次输入密码不一致，请重新输入！";
	            }
	        }
	    })
   })
</script>
</body>
</html>