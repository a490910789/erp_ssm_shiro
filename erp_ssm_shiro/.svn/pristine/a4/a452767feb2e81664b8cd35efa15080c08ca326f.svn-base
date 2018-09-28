<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>	
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>添加商品</title>
<meta name="renderer" content="webkit">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
<link rel="stylesheet" href="${ctx }/resources/layui/css/layui.css" media="all" />
<link rel="stylesheet" href="${ctx }/resources/css/public.css" media="all" />
<link rel="stylesheet" href="${ctx }/resources/zTree/css/metroStyle/metroStyle.css" type="text/css">
<link rel="stylesheet" type="text/css" ref="${ctx }/resources/plugin/css/index.css">
<!-- 注意：如果你直接复制所有代码到本地，上述css路径需要改成你本地的 -->
</head>
<body class="childrenBody">
	<form class="layui-form layui-form-pane" action="" id="fm">
		<div style="margin: 10px 20px">
			<div class="layui-form-item">
				<label class="layui-form-label">选择供应商</label>
				<div class="layui-input-inline">
			     <select name="providerid" lay-verify="">
		             <c:forEach var="p" items="${providers }">
		               <option value="${p.id }">${p.providername }</option>
		             </c:forEach>
		         </select>  
				</div>
			</div>
			<div class="layui-form-item">
				<label class="layui-form-label">商品名称</label>
				<div class="layui-input-inline">
					<input type="text" name="goodsname" autocomplete="off"
						class="layui-input">
				</div>
				<label class="layui-form-label">产地</label>
				<div class="layui-input-inline">
					<input type="text" name="produceplace" autocomplete="off"
						class="layui-input">
				</div>
			</div>
			<div class="layui-form-item">
				<label class="layui-form-label">规格</label>
				<div class="layui-input-inline">
					<input type="text" name="size" autocomplete="off"
						class="layui-input">
				</div>
				<label class="layui-form-label">包装</label>
				<div class="layui-input-inline">
					<input type="text" name="goodspackage" autocomplete="off"
						class="layui-input">
				</div>
			</div>
			<div class="layui-form-item">
				<label class="layui-form-label">生产批号</label>
				<div class="layui-input-inline">
					<input type="text" name="productcode" autocomplete="off"
						class="layui-input">
				</div>
				<label class="layui-form-label">批准文号</label>
				<div class="layui-input-inline">
					<input type="text" name="promitcode" autocomplete="off"
						class="layui-input">
				</div>
			</div>
			<div class="layui-form-item">
				<div class="layui-form-item">
					<label class="layui-form-label">商品描述</label>
					<div class="layui-input-block">
						<textarea name="description" placeholder="请输入"
							class="layui-textarea"></textarea>
					</div>
				</div>
			</div>
			<div class="layui-form-item">
				<label class="layui-form-label">销售价格</label>
				<div class="layui-input-inline">
					<input type="text" name="price" autocomplete="off"
						class="layui-input">
				</div>
				<label class="layui-form-label">是否可用</label>
				<div class="layui-input-inline">
					<input type="radio" name="available" title="可用" value="1" checked>
					<input type="radio" name="available" title="不可用" value="0">
				</div>
			</div>

			<div class="layui-form-item">
				<label class="layui-form-label">库存数量</label>
				<div class="layui-input-inline">
					<input type="text" name="number" autocomplete="off"
						class="layui-input">
				</div>
				<label class="layui-form-label">预警库存</label>
				<div class="layui-input-inline">
					<input type="text" name="dangernum" autocomplete="off"
						class="layui-input">
				</div>
			</div>
				<label class="layui-form-label">商品图片</label>
		 <div class="layui-upload">
			 <button type="button" class="layui-btn" id="goodspath">上传图片</button>
			 <div class="layui-upload-list">
			    <input type="hidden" name="goodsimg" id="goodsimg">
			    <img class="layui-upload-img" width="200px" height="200px" id="goodsimg_img">
			    <p id="goodsText"></p>
            </div>
         </div>   
		</div>
		<div class="layui-form-item">
			<div class="layui-input-block" style="text-align: center;">
				<a href="javascript:void(0)" class="layui-btn" lay-submit=""
					lay-filter="formSubmit">提交</a>
				<button type="reset" class="layui-btn layui-btn-warm reset">重置</button>
			</div>
		</div>
	</form>
	<script type="text/javascript" src="${ctx }/resources/layui/layui.js"></script>
	<script type="text/javascript"
		src="${ctx }/resources/zTree/js/jquery-1.4.4.min.js"></script>
	<script type="text/javascript"
		src="${ctx }/resources/zTree/js/jquery.ztree.core.js"></script>
	<script type="text/javascript"
		src="${ctx }/resources/plugin/js/selectTree.js"></script>
	<script type="text/javascript">
	
		layui.use([ 'form', 'layer', 'jquery','upload' ], function() {
			var form = layui.form, layer = layui.layer, $ = layui.jquery,upload=layui.upload;
			form.render();
			var uploadInst = upload.render({
			    elem: '#goodspath'
			    ,url: '${ctx}/upload/goodsImgUpload.action'
			    ,field:'mf'
			    ,accept: ['jpg','png','jpeg']
			    ,before: function(obj){
			      //预读本地文件示例，不支持ie8
			      obj.preview(function(index, file, result){
			        $('#goodsimg_img').attr('src', result); //图片链接（base64）
			      });
			    } 
			    ,done: function(res){
			      //如果上传失败
			      if(res.code > 0){
			        return layer.msg('上传失败');
			      }
			      //上传成功
			    //  $('#goodsimg_img').attr('src', res.path); //图片链接（base64）
			      $('#goodsimg').val(res.path);
			    }
			    ,error: function(){
			      //演示失败状态，并实现重传
			      var demoText = $('#goodsText');
			      goodsText.html('<span style="color: #FF5722;">上传失败</span> <a class="layui-btn layui-btn-xs demo-reload">重试</a>');
			      goodsText.find('.demo-reload').on('click', function(){
			        uploadInst.upload();
			      });
			    }
			  });
			form.on("submit(formSubmit)", function(data) {
				var params = $('#fm').serialize();
				$.post("${ctx}/goods/addGoods.action?" + params, function(
						result) {
					layer.msg(result.msg);//弹出添加成功或失败
				     //关闭窗口
					var index = parent.layer.getFrameIndex(window.name);
					parent.layer.close(index);
					parent.tableIns.reload();//刷新表格	
				});
			});
		});
	</script>
</body>
</html>