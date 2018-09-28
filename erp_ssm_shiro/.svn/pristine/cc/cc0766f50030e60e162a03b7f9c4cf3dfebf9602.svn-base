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
<link rel="stylesheet" href="${ctx }/resources/zTree/css/metroStyle/metroStyle.css" type="text/css">
<script type="text/javascript" src="${ctx }/resources/zTree/js/jquery-1.4.4.min.js"></script>
<script type="text/javascript" src="${ctx }/resources/zTree/js/jquery.ztree.core.js"></script>
</head>
<body class="childrenBody">
	<ul id="menuTree" class="ztree"></ul>
</body>
<script type="text/javascript">

function zTreeOnClick(event, treeId, treeNode) {
    //调用deptRight页面的reloadTable  把treeNode.id传过来
    window.parent.right.reloadTable(treeNode.id); //right是deptManager里面的frame的name值
};
	$(document).ready(function() {
		initzTree();
	});
	var setting = {
		data : {
			simpleData : {
				enable : true
			}
		},
		callback: {
			onClick: zTreeOnClick
		}
	};
	function initzTree() {
		$.post("${ctx}/menu/loadTreeForMenu.action", function(zNodes) {
			$.fn.zTree.init($("#menuTree"), setting, zNodes);
		});
	}
</script>
</html>