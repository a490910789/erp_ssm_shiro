/*
 * Generated by the Jasper component of Apache Tomcat
 * Version: Apache Tomcat/7.0.47
 * Generated at: 2018-09-28 12:06:06 UTC
 * Note: The last modified time of this file was set to
 *       the last modified time of the source file after
 *       generation to assist with modification tracking.
 */
package org.apache.jsp.WEB_002dINF.view.system.notice;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;

public final class noticeManager_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

  private static final javax.servlet.jsp.JspFactory _jspxFactory =
          javax.servlet.jsp.JspFactory.getDefaultFactory();

  private static java.util.Map<java.lang.String,java.lang.Long> _jspx_dependants;

  private javax.el.ExpressionFactory _el_expressionfactory;
  private org.apache.tomcat.InstanceManager _jsp_instancemanager;

  public java.util.Map<java.lang.String,java.lang.Long> getDependants() {
    return _jspx_dependants;
  }

  public void _jspInit() {
    _el_expressionfactory = _jspxFactory.getJspApplicationContext(getServletConfig().getServletContext()).getExpressionFactory();
    _jsp_instancemanager = org.apache.jasper.runtime.InstanceManagerFactory.getInstanceManager(getServletConfig());
  }

  public void _jspDestroy() {
  }

  public void _jspService(final javax.servlet.http.HttpServletRequest request, final javax.servlet.http.HttpServletResponse response)
        throws java.io.IOException, javax.servlet.ServletException {

    final javax.servlet.jsp.PageContext pageContext;
    javax.servlet.http.HttpSession session = null;
    final javax.servlet.ServletContext application;
    final javax.servlet.ServletConfig config;
    javax.servlet.jsp.JspWriter out = null;
    final java.lang.Object page = this;
    javax.servlet.jsp.JspWriter _jspx_out = null;
    javax.servlet.jsp.PageContext _jspx_page_context = null;


    try {
      response.setContentType("text/html; charset=UTF-8");
      pageContext = _jspxFactory.getPageContext(this, request, response,
      			null, true, 8192, true);
      _jspx_page_context = pageContext;
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      session = pageContext.getSession();
      out = pageContext.getOut();
      _jspx_out = out;

      out.write("\r\n");
      out.write("<!DOCTYPE html>\r\n");
      out.write("<html>\r\n");
      out.write("<head>\r\n");
      out.write("\t<meta charset=\"utf-8\">\r\n");
      out.write("\t<title>其他管理--登入公告</title>\r\n");
      out.write("\t<link rel=\"stylesheet\" href=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${ctx}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("/resources/layui/css/layui.css\" media=\"all\" />\r\n");
      out.write("\t<link rel=\"stylesheet\" href=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${ctx}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("/resources/css/public.css\" media=\"all\" />\r\n");
      out.write("</head>\r\n");
      out.write("<body class=\"childrenBody\">\r\n");
      out.write("<fieldset class=\"layui-elem-field layui-field-title\" style=\"margin-top: 5px;\">\r\n");
      out.write("  <legend>查询条件</legend>\r\n");
      out.write("</fieldset>\r\n");
      out.write("<form class=\"layui-form\" id=\"fm\" lay-filter=\"fm\">\r\n");
      out.write("       <div class=\"layui-form-item\" style=\"text-align: center;\">\r\n");
      out.write("    <div class=\"layui-inline\">\r\n");
      out.write("      <label class=\"layui-form-label\">公告标题</label>\r\n");
      out.write("      <div class=\"layui-input-inline\">\r\n");
      out.write("        <input type=\"text\" name=\"title\" id=\"title\"   autocomplete=\"off\" class=\"layui-input\">\r\n");
      out.write("      </div>\r\n");
      out.write("    </div>\r\n");
      out.write("    <div class=\"layui-inline\">\r\n");
      out.write("      <label class=\"layui-form-label\">发布时间</label>\r\n");
      out.write("      <div class=\"layui-input-inline\">\r\n");
      out.write("        <input type=\"text\" name=\"startDate\" id=\"startDate\" lay-verify=\"date\" placeholder=\"yyyy-MM-dd HH:mm:ss\" autocomplete=\"off\" class=\"layui-input\">\r\n");
      out.write("      </div>\r\n");
      out.write("    </div>\r\n");
      out.write("    \r\n");
      out.write("     <div class=\"layui-inline\">\r\n");
      out.write("      <label class=\"layui-form-label\">结束时间</label>\r\n");
      out.write("      <div class=\"layui-input-inline\">\r\n");
      out.write("        <input type=\"text\" name=\"endDate\" id=\"endDate\" lay-verify=\"date\" placeholder=\"yyyy-MM-dd HH:mm:ss\" autocomplete=\"off\" class=\"layui-input\">\r\n");
      out.write("      </div>\r\n");
      out.write("    </div>\r\n");
      out.write("  </div>\r\n");
      out.write("   <div class=\"layui-form-item\" lay-filter=\"tableToolBar\" style=\"text-align: center;\">\r\n");
      out.write("       <a href=\"javascript:void(0)\" class=\"search_btn layui-btn\">查询</a>\r\n");
      out.write("       <button type=\"reset\" class=\"layui-btn layui-btn-warm\" >清空</button>\r\n");
      out.write("   </div>\r\n");
      out.write(" </form>\r\n");
      out.write("<script type=\"text/javascript\" src=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${ctx}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("/resources/layui/layui.js\"></script>\r\n");
      out.write("\t<table id=\"noticeList\" lay-filter=\"noticeList\" ></table>\r\n");
      out.write("\t<!-- 表头操作按钮 -->\r\n");
      out.write("<script type=\"text/html\" id=\"tableToolBar\">\r\n");
      out.write("       <a class=\"layui-btn\" lay-event=\"add\">添加</a>\r\n");
      out.write("       <a class=\"layui-btn layui-btn-danger\" lay-event=\"batchDel\">批量删除</a>\r\n");
      out.write("</script>\r\n");
      out.write("\t<!-- 操作 -->\r\n");
      out.write("\t<script type=\"text/html\" id=\"noticeListBar\">\r\n");
      out.write("\t\t<a class=\"layui-btn layui-btn-xs layui-btn\" lay-event=\"update\">编辑</a>\r\n");
      out.write("\t\t<a class=\"layui-btn layui-btn-xs layui-btn-danger\" lay-event=\"delete\">删除</a>\r\n");
      out.write("\t\t<a class=\"layui-btn layui-btn-xs layui-btn-normal\" lay-event=\"find\">查看</a>\r\n");
      out.write("\t</script>\t\r\n");
      out.write("<script type=\"text/javascript\">\r\n");
      out.write("  var tableIns;\r\n");
      out.write("layui.use(['form','layer','laydate','table','laytpl'],function(){\r\n");
      out.write("        var form = layui.form,\r\n");
      out.write("        layer = parent.layer === undefined ? layui.layer : top.layer,\r\n");
      out.write("        $ = layui.jquery,\r\n");
      out.write("        laydate = layui.laydate,\r\n");
      out.write("        laytpl = layui.laytpl,\r\n");
      out.write("        table = layui.table;\r\n");
      out.write("    //登入公告列表\r\n");
      out.write("        tableIns = table.render({\r\n");
      out.write("        elem: '#noticeList',\r\n");
      out.write("        url : '");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${ctx}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("/notice/loadAllNotice.action',\r\n");
      out.write("        cellMinWidth : 95,\r\n");
      out.write("        toolbar: \"#tableToolBar\",\r\n");
      out.write("        page : true,\r\n");
      out.write("        height : \"full-180\",\r\n");
      out.write("        limit : 10,\r\n");
      out.write("        limits : [10,15,20,25],\r\n");
      out.write("        defaultToolbar: ['filter'],\r\n");
      out.write("        id : \"noticeListTable\",\r\n");
      out.write("        cols : [[\r\n");
      out.write("            {type: \"checkbox\", fixed:\"left\", width:50},\r\n");
      out.write("            {field: 'id', title: '公告ID',align:\"center\"},\r\n");
      out.write("            {field: 'title', title: '公告标题',align:\"center\"},\r\n");
      out.write("            {field: 'createtime', title: '发布时间', align:'center'},\r\n");
      out.write("            {field: 'opername', title: '发布人',  align:'center'},\r\n");
      out.write("            {title: '操作', width:180, templet:'#noticeListBar',fixed:\"right\",align:\"center\"}\r\n");
      out.write("        ]]\r\n");
      out.write("    });\r\n");
      out.write("    \r\n");
      out.write("    laydate.render({\r\n");
      out.write("    \telem:\"#startDate\",\r\n");
      out.write("    \ttype:\"datetime\"\r\n");
      out.write("    });\r\n");
      out.write("    laydate.render({\r\n");
      out.write("    \telem:\"#endDate\",\r\n");
      out.write("    \ttype:\"datetime\"\r\n");
      out.write("    });\r\n");
      out.write("   \r\n");
      out.write("    //搜索【此功能需要后台配合，所以暂时没有动态效果演示】\r\n");
      out.write("    $(\".search_btn\").on(\"click\",function(){\r\n");
      out.write("            table.reload(\"noticeListTable\",{\r\n");
      out.write("                page: {\r\n");
      out.write("                    curr: 1 //重新从第 1 页开始\r\n");
      out.write("                },\r\n");
      out.write("                where: {\r\n");
      out.write("                    title: $(\"#title\").val(),  //搜索的关键字\r\n");
      out.write("                    startDate: $(\"#startDate\").val(),  //搜索的关键字\r\n");
      out.write("                    endDate: $(\"#endDate\").val(),  //搜索的关键字\r\n");
      out.write("                }\r\n");
      out.write("          })\r\n");
      out.write("    });\r\n");
      out.write("    \r\n");
      out.write("    //批量删除\r\n");
      out.write("    function batchDel(){\r\n");
      out.write("        var checkStatus = table.checkStatus('noticeListTable'),\r\n");
      out.write("            data = checkStatus.data,\r\n");
      out.write("            url=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${ctx}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("/notice/delNotices.action?1=1\";\r\n");
      out.write("        if(data.length > 0) {\r\n");
      out.write("            for (var i in data) {\r\n");
      out.write("                url+=\"&ids=\"+data[i].id;\r\n");
      out.write("            }\r\n");
      out.write("            layer.confirm('确定删除选中的公告？', {icon: 3, title: '提示信息'}, function (index) {\r\n");
      out.write("                $.post(url,function(result){\r\n");
      out.write("                \tlayer.msg(result.msg);\r\n");
      out.write("\t\t            tableIns.reload();\r\n");
      out.write("\t\t            layer.close(index);\r\n");
      out.write("                });\r\n");
      out.write("            });\r\n");
      out.write("        }else{\r\n");
      out.write("            layer.msg(\"请选择需要删除的公告\");\r\n");
      out.write("        }\r\n");
      out.write("    }\r\n");
      out.write("    \r\n");
      out.write("  //查看内容\r\n");
      out.write("    function see(){\r\n");
      out.write("    \tvar checkStatus = table.checkStatus('noticeListTable');\r\n");
      out.write("        data = checkStatus.data;\r\n");
      out.write("        layer.open({\r\n");
      out.write("    \t\ttitle:'公告内容',  \r\n");
      out.write("    \t\ttype:2,\r\n");
      out.write("    \t\tcontent:data.content,\r\n");
      out.write("    \t\tarea:['800px','500px'],\r\n");
      out.write("    \t\tmaxmin:true\r\n");
      out.write("    \t});\r\n");
      out.write("    }\r\n");
      out.write("\r\n");
      out.write("    //监听单元格按钮\r\n");
      out.write("    table.on('tool(noticeList)', function(obj){\r\n");
      out.write("       var id=obj.data.id;\r\n");
      out.write("    \tswitch (obj.event) {\r\n");
      out.write("\t\tcase \"update\":\r\n");
      out.write("\t\t\ttoAddOrUpdate(id,\"修改公告\");\r\n");
      out.write("\t\t\tbreak;\r\n");
      out.write("\t\tcase \"delete\":\r\n");
      out.write("\t\t\ttoDelete(id);\r\n");
      out.write("\t\t\tbreak;\r\n");
      out.write("\t\tcase \"find\":\r\n");
      out.write("\t\t\tfind(id);\r\n");
      out.write("\t\t\tbreak;\r\n");
      out.write("\t\t}\r\n");
      out.write("    });\r\n");
      out.write("    //监听表格按钮\r\n");
      out.write("    table.on('toolbar(noticeList)',function(obj){\r\n");
      out.write("     \tswitch (obj.event) {\r\n");
      out.write(" \t\tcase \"add\":\r\n");
      out.write(" \t\t\ttoAddOrUpdate(\"\",\"添加公告\");\r\n");
      out.write(" \t\t\tbreak;\r\n");
      out.write(" \t\tcase \"batchDel\":\r\n");
      out.write(" \t\t\tbatchDel();\r\n");
      out.write(" \t\t\tbreak;\r\n");
      out.write(" \t\t}\r\n");
      out.write("    });\r\n");
      out.write("  //删除公告\r\n");
      out.write("    function toDelete(id){\r\n");
      out.write("    \t  layer.confirm('确定删除选中的公告？', {icon: 3, title: '提示信息'}, function (index) {\r\n");
      out.write("    \t\t  $.post(\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${ctx}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("/notice/delNotice.action?id=\"+id,function(result){\r\n");
      out.write("    \t          \tlayer.msg(result.msg);\r\n");
      out.write("\t\t            layer.close(index);\r\n");
      out.write("\t\t            tableIns.reload();\r\n");
      out.write("    \t          });\r\n");
      out.write("        });\r\n");
      out.write("    }\r\n");
      out.write("})\r\n");
      out.write("//添加公告\r\n");
      out.write("function toAddOrUpdate(id,title){\r\n");
      out.write("\tvar title=title;\r\n");
      out.write("\t\tlayer.open({\r\n");
      out.write("\t\t\ttitle:title,  \r\n");
      out.write("\t\t\ttype:2,\r\n");
      out.write("\t\t\tcontent:'");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${ctx}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("/notice/toAddOrUpdateNotice.action?id='+id,\r\n");
      out.write("\t\t\tarea:['800px','500px'],\r\n");
      out.write("\t\t\tmaxmin:true,\r\n");
      out.write("\t\t\tsuccess : function(layero, index){\r\n");
      out.write("                var body = layui.layer.getChildFrame('body', index);\r\n");
      out.write("                setTimeout(function(){\r\n");
      out.write("                    layui.layer.tips('点击此处返回公告列表', '.layui-layer-setwin .layui-layer-close', {\r\n");
      out.write("                        tips: 3\r\n");
      out.write("                    });\r\n");
      out.write("                },500)\r\n");
      out.write("            }\r\n");
      out.write("\t\t});\r\n");
      out.write("   }\r\n");
      out.write("   \r\n");
      out.write("//查看公告详细内容\r\n");
      out.write("function find(id){\r\n");
      out.write("\t\tlayer.open({\r\n");
      out.write("\t\t\ttitle:['公告内容'],  \r\n");
      out.write("\t\t\ttype:2,\r\n");
      out.write("\t\t\tcontent:'");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${ctx}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("/notice/findContent.action?id='+id,\r\n");
      out.write("\t\t\tarea:['800px','500px'],\r\n");
      out.write("\t\t\tmaxmin:true,\r\n");
      out.write("\t\t\tsuccess : function(layero, index){\r\n");
      out.write("                var body = layui.layer.getChildFrame('body', index);\r\n");
      out.write("                setTimeout(function(){\r\n");
      out.write("                    layui.layer.tips('点击此处返回公告列表', '.layui-layer-setwin .layui-layer-close', {\r\n");
      out.write("                        tips: 3\r\n");
      out.write("                    });\r\n");
      out.write("                },500)\r\n");
      out.write("            }\r\n");
      out.write("\t\t});\r\n");
      out.write("   }\r\n");
      out.write("\r\n");
      out.write("</script>\r\n");
      out.write("</body>\r\n");
      out.write("</html>");
    } catch (java.lang.Throwable t) {
      if (!(t instanceof javax.servlet.jsp.SkipPageException)){
        out = _jspx_out;
        if (out != null && out.getBufferSize() != 0)
          try { out.clearBuffer(); } catch (java.io.IOException e) {}
        if (_jspx_page_context != null) _jspx_page_context.handlePageException(t);
        else throw new ServletException(t);
      }
    } finally {
      _jspxFactory.releasePageContext(_jspx_page_context);
    }
  }
}
