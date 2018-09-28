<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>添加角色</title>
<link rel="stylesheet" href="${ctx }/resources/layui/css/layui.css" media="all" />
<link rel="stylesheet" href="${ctx }/resources/css/public.css" media="all" />
<link rel="stylesheet" type="text/css" href="${ctx }/resources/plugin/css/index.css">
</head>
<body class="childrenBody">
		<form lay-filter="fm" class="layui-form" action="#" method="post" id="fm">
			<div class="layui-form-item">
			<label class="layui-form-label">角色名称</label>
			<div class="layui-input-block">
	          <input type="text" name="name"   autocomplete="off" class="layui-input">
			</div>
			</div>
		 <div class="layui-form-item layui-form-text">
    <label class="layui-form-label">角色备注</label>
         <div class="layui-input-block">
             <textarea placeholder="请输入内容" name="remark" class="layui-textarea"></textarea>
         </div>
      </div>
			<div class="layui-form-item">
				<div class="layui-inline">
					<label class="layui-form-label">是否可用</label>
					<div class="layui-input-inline">
						<input type="radio" name="available" value="1" title="是" checked>
						<input type="radio" name="available" value="0" title="否">
					</div>
				</div>
			</div>
			<div class="layui-form-item">
				<div class="layui-input-block" style="text-align: center;">
					<a href="javascript:void(0)" class="layui-btn" lay-submit="" lay-filter="formSubmit">提交</a>
					<button type="reset" class="layui-btn layui-btn-primary">重置</button>
				</div>
			</div>
		</form>
</body>
<script type="text/javascript" src="${ctx }/resources/layui/layui.js"></script>
<script type="text/javascript">
	layui.use(['form','jquery'], function () {
			 var form=layui.form,
			 $=layui.jquery;
		     //提交
			 form.on("submit(formSubmit)",function(data){
				 var params=$('#fm').serialize();
			       $.post("${ctx}/role/addRole.action?"+params,function(result){
					    layer.msg(result.msg);//弹出添加成功或失败
						parent.tableIns.reload();
						var index=parent.layer.getFrameIndex(window.name);
						parent.layer.close(index);
				  });
		     });
	   });
	 
</script>
</html>