<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title></title>
<meta name="renderer" content="webkit">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
<meta name="apple-mobile-web-app-status-bar-style" content="black">
<meta name="apple-mobile-web-app-capable" content="yes">
<meta name="format-detection" content="telephone=no">
<link rel="stylesheet" href="${ctx}/resources/layui/css/layui.css" media="all" />
<link rel="stylesheet" href="${ctx }/resources/zTree/css/metroStyle/metroStyle.css" type="text/css">
<script type="text/javascript" src="${ctx }/resources/zTree/js/jquery-1.4.4.min.js"></script>
<script type="text/javascript" src="${ctx }/resources/zTree/js/jquery.ztree.core.js"></script>
<script type="text/javascript" src="${ctx }/resources/zTree/js/jquery.ztree.excheck.min.js"></script>
</head>
<body class="childrenBody">
	<ul id="permissionTree" class="ztree"></ul>
	<div style="text-align: center;">
	   <a href="javascript:void(0)" class="layui-btn"  onclick="save()" style="margin-top: 20px">确认分配</a>
	</div>
</body>
<script type="text/javascript" src="${ctx }/resources/layui/layui.js"></script>
<script type="text/javascript">
var layer;
  layui.use('layer',function(){
	  layer = layui.layer;
  })
	 //保存分配
	function save(){
	  var treeObj = $.fn.zTree.getZTreeObj("permissionTree");
		var nodes = treeObj.getCheckedNodes(true);
		var params="?id=${roleVo.id }";
		for (var i = 0; i < nodes.length; i++) {
			params+="&ids="+nodes[i].id;
		}
		$.post("${ctx}/role/saveSetPermissions.action"+params,function(result){
			layer.msg(result.msg);//弹出添加成功或失败
			//parent.tableIns.reload();//刷新表格
		});
	}
	$(document).ready(function() {
		initzTree();
	});
	var setting = {
		data : {
			simpleData : {
				enable : true
			}
		},
		check: {
			enable : true
		}
	};
	function initzTree() {
		$.post("${ctx}/role/setPermissions.action?id=${roleVo.id }", function(zNodes) {
			$.fn.zTree.init($("#permissionTree"), setting, zNodes);
		});
	}
</script>
</html>