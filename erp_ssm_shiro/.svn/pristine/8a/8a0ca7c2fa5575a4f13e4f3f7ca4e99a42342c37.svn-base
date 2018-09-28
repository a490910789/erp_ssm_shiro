<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>添加流程部署</title>
<link rel="stylesheet" href="${ctx }/resources/layui/css/layui.css" media="all" />
<link rel="stylesheet" href="${ctx }/resources/css/public.css" media="all" />
</head>
<body class="childrenBody">
	<form class="layui-form" method="post" id="frm">
		<div class="layui-form-item">
			<label class="layui-form-label">部署名称</label>
			<div class="layui-input-block">
				<input type="text" name="deploymentName" id="deploymentName"
					lay-verify="required" autocomplete="off" placeholder="请输入流程部署名称"
					class="layui-input">
			</div>
		</div>
		<div class="layui-form-item">
			<label class="layui-form-label">选择流程图</label>
			<div class="layui-input-block">
				<div class="layui-upload">
					<button type="button" class="layui-btn layui-btn-normal"
						id="chouseZip">选择文件</button>
				</div>
			</div>
		</div>
		<div class="layui-form-item" style="text-align: center;">
			<button type="button" class="layui-btn" id="addDeployment">开始上传</button>
			<button type="reset" class="layui-btn layui-btn-warm">重置</button>
		</div>
	</form>
	<script type="text/javascript" src="${ctx }/resources/layui/layui.js"></script>
</body>
<script type="text/javascript">
    layui.use(['form','jquery','layer','upload'],function(){
        var form=layui.form;
        var $=layui.jquery;
        var upload=layui.upload;
        //如果父页面有layer就使用父页的  没有就自己导入
        var layer = parent.layer === undefined ? layui.layer : top.layer;
        upload.render({
            elem: '#chouseZip'
            ,url: '${ctx}/workFlow/addDeployment.action'
            ,auto: false
            ,exts: 'zip' //只允许上传压缩文件  帮我们在提交之前去验文件的后缀名
            ,acceptMime:'application/zip'//设置默认选择的文件类型
            ,field:'mf'
            ,data: {
            	deploymentName: function(){
            	 return $('#deploymentName').val();
            	}
             }
            ,bindAction: '#addDeployment'
            ,done: function(res){
            	//关闭添加的弹出层
                var addIndex = parent.layer.getFrameIndex(window.name);
                window.parent.layer.close(addIndex);
                layer.msg(res.msg);
                //刷新父页面的table
                window.parent.tableInsDeployment.reload();
                window.parent.tableInsProcessDef.reload();
            }
    	});
    });
</script>
</html>