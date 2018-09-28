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
			<div class="layui-form-item layui-inline">
					<label class="layui-form-label">所属部门</label>
					<div class="layui-input-inline">
						<div id="deptid"  name="deptid" class="layui-form-select select-tree"></div>
					</div>

					<label class="layui-form-label">排序码</label>
					<div class="layui-input-inline">
						<input type="number" name="ordernum" lay-verify="required"
							autocomplete="off" class="layui-input">
				   </div>
			</div>
			<div class="layui-form-item">
				<label class="layui-form-label">领导部门</label>
				<div class="layui-input-inline">
					<div id="leaderdeptid"  name="leaderdeptid" class="layui-form-select select-tree"></div>
				</div>
				<label class="layui-form-label">领导姓名</label>
				<div class="layui-input-inline">
					<select id="mgr" name="mgr"  autocomplete="off" class="layui-input">
					<option value=""></option>
					</select>
				</div>
			</div>
			<div class="layui-form-item">
				<label class="layui-form-label">用户姓名</label>
				<div class="layui-input-inline">
					<input type="text" name="name" autocomplete="off" class="layui-input">
				</div>
				<label class="layui-form-label">登入名</label>
				<div class="layui-input-inline">
					<input type="text" name="loginname" autocomplete="off" class="layui-input">
				</div>
			</div>
			<div class="layui-form-item">
				<label class="layui-form-label">用户地址</label>
				<div class="layui-input-block">
					<input type="text" name="address" autocomplete="off" class="layui-input">
				</div>
			</div>
			<div class="layui-form-item">
				<label class="layui-form-label">用户备注</label>
				<div class="layui-input-block">
					<textarea name="remark" placeholder="请输入" class="layui-textarea"></textarea>
				</div>
			</div>
			<div class="layui-form-item layui-inline">
			        <label class="layui-form-label">性别</label>
					<div class="layui-input-inline">
						<input type="radio" name="sex" value="1" title="男" checked>
						<input type="radio" name="sex" value="0" title="女">
					</div>
					<label class="layui-form-label">是否有效</label>
					<div class="layui-input-inline">
						<input type="radio" name="available" value="1" title="是" checked>
						<input type="radio" name="available" value="0" title="否">
					</div>
			</div>
					<div class="layui-input-inline">
				     <label class="layui-form-label">入职日期</label>
				    <div class="layui-input-inline">
					   <input type="text" name="hiredate" id="hiredate" autocomplete="off" class="layui-input">
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
var form;
	 layui.use(['form','jquery','layer','laydate'], function () {
		 form=layui.form;
		 var $=layui.jquery,
		 laydate=layui.laydate;
		 layer = parent.layer === undefined ? layui.layer : parent.layer;
		 
		 
		 laydate.render({
			elem:"#hiredate" 
		 });
		 
		 form.on("submit(formSubmit)",function(data){
		   var params=$('#fm').serialize();
	       $.post("${ctx}/user/addUser.action?"+params,function(result){
			    layer.msg(result.msg);//弹出添加成功或失败
				//关闭窗口
				var index=layer.getFrameIndex(window.name);
				layer.close(index);
		        parent.tableIns.reload();//刷新表格
			   });
		 });
	  });
		$(document).ready(function() {
			initDeptTree();
		});
		function initDeptTree(){
			$.post("${ctx}/dept/loadTreeForDept.action",function(zNodes){
				initSelectTree("deptid", zNodes,false);
				initSelectTree("leaderdeptid", zNodes,false);
		   });
		}
		 //选中领导部门时 点击事件 查询该部门下所有员工 生成下拉列表数据
		 function initLeaderName(id){
				$.post("${ctx}/user/queryUsersByDeptId.action?deptid="+id,function(users){
				 if(users.length>0){
					for (var i = 0; i < users.length; i++) {
						  var html="<option value="+users[i].id+" >"+users[i].name+"</option>";
						  $("#mgr").html(html);
						  form.render("select");
					 }
				 }else{
					 $("#mgr").html("");
					 form.render("select");
				 }
			 },'json');
	    }
		
		
</script>
</html>