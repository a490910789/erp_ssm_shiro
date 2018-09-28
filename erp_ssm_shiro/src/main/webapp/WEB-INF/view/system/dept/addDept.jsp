<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>添加部门</title>
<link rel="stylesheet" href="${ctx }/resources/layui/css/layui.css" media="all" />
<link rel="stylesheet" href="${ctx }/resources/css/public.css" media="all" />
<link rel="stylesheet" href="${ctx }/resources/zTree/css/metroStyle/metroStyle.css" type="text/css">
<link rel="stylesheet" type="text/css" href="${ctx }/resources/plugin/css/index.css">
</head>
<body class="childrenBody">
		<form lay-filter="fm" class="layui-form" action="#" method="post" id="fm">
			<div class="layui-form-item">
				<div class="layui-inline">
					<label class="layui-form-label">父级部门</label>
					<div class="layui-input-inline">
						<div id="pid"  name="pid" class="layui-form-select select-tree"></div>
					</div>
				</div>

				<div class="layui-inline">
					<label class="layui-form-label">排序码</label>
					<div class="layui-input-inline">
						<input type="number" name="ordernum" lay-verify="required"
							autocomplete="off" class="layui-input">
					</div>
				</div>
			</div>
			<div class="layui-form-item">
				<label class="layui-form-label">部门名称</label>
				<div class="layui-input-block">
					<input type="text" name="name" autocomplete="off"
						class="layui-input">
				</div>
			</div>
			<div class="layui-form-item">
				<label class="layui-form-label">部门地址</label>
				<div class="layui-input-block">
					<input type="text" name="loc" autocomplete="off"
						class="layui-input">
				</div>
			</div>

			<div class="layui-form-item">
				<label class="layui-form-label">部门备注</label>
				<div class="layui-input-block">
					<textarea name="remark" placeholder="请输入" class="layui-textarea"></textarea>
				</div>
			</div>
			<div class="layui-form-item">
				<div class="layui-inline">
					<label class="layui-form-label">是否展开</label>
					<div class="layui-input-inline">
						<input type="radio" name="spread" value="1" title="是"> <input
							type="radio" name="spread" value="0" title="否" checked>
					</div>
				</div>

				<div class="layui-inline">
					<label class="layui-form-label">是否父节点</label>
					<div class="layui-input-inline">
						<input type="radio" name="parent" value="1" title="是"> <input
							type="radio" name="parent" value="0" title="否" checked>
					</div>
				</div>
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
<script type="text/javascript" src="${ctx }/resources/zTree/js/jquery-1.4.4.min.js"></script>
<script type="text/javascript" src="${ctx }/resources/zTree/js/jquery.ztree.core.js"></script>
<script type="text/javascript" src="${ctx }/resources/plugin/js/selectTree.js"></script>

<script type="text/javascript">
	 layui.use(['form','jquery','layer'], function () {
		 var form=layui.form,
		 $=layui.jquery,
		 layer = parent.layer === undefined ? layui.layer : parent.layer;
		 
		 form.on("submit(formSubmit)",function(data){
		   var params=$('#fm').serialize();
	       $.post("${ctx}/dept/addDept.action?"+params,function(result){
			    layer.msg(result.msg);//弹出添加成功或失败
			//关闭窗口
			var index=layer.getFrameIndex(window.name);
			layer.close(index);
	        parent.tableIns.reload();//刷新表格
	        parent.parent.deptLeft.initzTree();//刷新左边的树
			//parent.parent.left.initzTree();//刷新左边的树
			   });
		 });
	 });
		$(document).ready(function() {
			initDeptTree();
		});
		function initDeptTree(){
			$.post("${ctx}/dept/loadTreeForDept.action",function(zNodes){
				initSelectTree("pid", zNodes,false);
		   });
		}
		
</script>
</html>