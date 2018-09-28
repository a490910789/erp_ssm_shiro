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
		<input type="hidden" name="id" value="${dept.id }">
			<div class="layui-form-item">
				<div class="layui-inline">
					<label class="layui-form-label">父级部门</label>
					<div class="layui-input-inline">
						<div id="pid"  name="pid" value="${dept.pid }" class="layui-form-select select-tree"></div>
					</div>
				</div>

				<div class="layui-inline">
					<label class="layui-form-label">排序码</label>
					<div class="layui-input-inline">
						<input type="number" name="ordernum" value="${dept.ordernum }" lay-verify="required"
							autocomplete="off" class="layui-input">
					</div>
				</div>
			</div>
			<div class="layui-form-item">
				<label class="layui-form-label">部门名称</label>
				<div class="layui-input-block">
					<input type="text" name="name"  value="${dept.name }" autocomplete="off"
						class="layui-input">
				</div>
			</div>
			<div class="layui-form-item">
				<label class="layui-form-label">部门地址</label>
				<div class="layui-input-block">
					<input type="text" name="loc"  value="${dept.loc }" autocomplete="off"
						class="layui-input">
				</div>
			</div>

			<div class="layui-form-item">
				<label class="layui-form-label">部门备注</label>
				<div class="layui-input-block">
					<textarea name="remark" placeholder="请输入"  class="layui-textarea">${dept.remark }</textarea>
				</div>
			</div>
			<div class="layui-form-item">
				<div class="layui-inline">
					<label class="layui-form-label">是否展开</label>
					<div class="layui-input-inline">
						<input type="radio" name="spread" value="1" title="是" ${dept.spread eq 1 ?'checked':''}> <input
							type="radio" name="spread" value="0" title="否"  ${dept.spread eq 0 ?'checked':''}>
					</div>
				</div>

				<div class="layui-inline">
					<label class="layui-form-label">是否父节点</label>
					<div class="layui-input-inline">
						<input type="radio" name="parent" value="1" title="是" ${dept.parent eq 1?'checked':''}> <input
							type="radio" name="parent" value="0" title="否" ${dept.parent eq 0?'checked':''}>
					</div>
				</div>
				<div class="layui-inline">
					<label class="layui-form-label">是否可用</label>
					<div class="layui-input-inline">
						<input type="radio" name="available" value="1" title="是" ${dept.available eq 1?'checked':''}>
						<input type="radio" name="available" value="0" title="否" ${dept.available eq 0?'checked':''}>
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
		 var form=layui.form;
		 var $=layui.jquery;
		 var layer = parent.layer === undefined ? layui.layer : parent.layer;
		 form.on("submit(formSubmit)",function(data){
		   var params=$('#fm').serialize();
	        $.post("${ctx}/dept/updateDept.action?"+params,function(result){
			    layer.msg(result.msg);//弹出修改成功或失败
			    //关闭窗口
				var index=layer.getFrameIndex(window.name);
				layer.close(index);
		        parent.tableIns.reload();//刷新表格
				parent.parent.left.initzTree();//刷新左边的树 
			   });
			
		 });
	 });
		$(document).ready(function(){
			initDeptTree();
		});
		function initDeptTree(){
			$.post("${ctx}/dept/loadTreeForDept.action",function(zNodes){
				initSelectTree("pid", zNodes,false);
				var treeObj = $.fn.zTree.getZTreeObj("pidTree");
				var pid=${dept.pid};
				var node = treeObj.getNodeByParam("id",pid);
				treeObj.selectNode(node);
				//渲染
	     		onClick(event,"pidTree",node);
		   });
		}
		
</script>
</html>