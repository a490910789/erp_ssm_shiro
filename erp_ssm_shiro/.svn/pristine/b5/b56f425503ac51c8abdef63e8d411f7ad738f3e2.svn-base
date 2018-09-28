<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>layui</title>
<meta name="renderer" content="webkit">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta name="viewport"
	content="width=device-width, initial-scale=1, maximum-scale=1">
<link rel="stylesheet" href="${ctx}/resources/layui/css/layui.css" media="all" />
<link rel="stylesheet" href="${ctx}/resources/css/public.css" media="all" />
<!-- 注意：如果你直接复制所有代码到本地，上述css路径需要改成你本地的 -->
</head>
<body>
	<form class="layui-form" id="fm" style="text-align: center;margin: 10px 20px">
	<input type="hidden" name="id" id="id" value="${notice.id }">
		<div class="layui-form-item">
			<label class="layui-form-label">公告标题</label>
			<div class="layui-input-block">
				<input type="text" name="title" id="title" value="${notice.title }" lay-verify="title"
					autocomplete="off" placeholder="请输入标题" class="layui-input">
			</div>
		</div>
		
		<div class="layui-form-item">
			<label class="layui-form-label">公告内容</label>
			<div class="layui-input-block">
		<textarea class="layui-textarea" id="LAY_content" name="content">${notice.content }</textarea>
			</div>
		</div>
		<div class="layui-form-item">
				<div class="layui-input-block" style="text-align: center;">
					<a href="javascript:void(0)"  class="layui-btn"  lay-submit="" lay-filter="formSubmit">提交</a>
					<button type="reset" class="layui-btn layui-btn-warm reset">重置</button>
				</div>
		 </div>
	</form>
</body>
<script src="${ctx }/resources/layui/layui.js" charset="utf-8"></script>
<script type="text/javascript"> 
		layui.use(['layedit','form'], function() {
			var layedit = layui.layedit, 
			$ = layui.jquery,
			form=layui.form;
			//构建一个默认的编辑器
			var num = layedit.build('LAY_content');
		    
			//清空内容
			$('.reset').on('click', function() {
				$('#LAY_layedit_1').contents().find('body').html('');
			});
			//提交
			 form.on("submit(formSubmit)",function(data){
				 var title=$('#title').val();
				 var content=layedit.getContent(num);
				 var id=$('#id').val();
			       $.post("${ctx}/notice/addOrUpdateNotice.action?title="+title+"&content="+content+"&id="+id,function(result){
					//关闭窗口
					    layer.msg(result.msg);//弹出添加成功或失败
						var index=parent.layer.getFrameIndex(window.name);
						parent.layer.close(index);
				        parent.tableIns.reload();//刷新表格	
				   });
			   });
		  });
	</script>
</html>