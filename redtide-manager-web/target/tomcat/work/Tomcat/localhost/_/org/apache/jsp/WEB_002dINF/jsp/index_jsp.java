/*
 * Generated by the Jasper component of Apache Tomcat
 * Version: Apache Tomcat/7.0.47
 * Generated at: 2018-12-29 01:32:17 UTC
 * Note: The last modified time of this file was set to
 *       the last modified time of the source file after
 *       generation to assist with modification tracking.
 */
package org.apache.jsp.WEB_002dINF.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;

public final class index_jsp extends org.apache.jasper.runtime.HttpJspBase
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
      out.write("<!DOCTYPE html PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\" \"http://www.w3.org/TR/html4/loose.dtd\">\r\n");
      out.write("<html>\r\n");
      out.write("<head>\r\n");
      out.write("<meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\">\r\n");
      out.write("<title>赤潮监测系统后台管理</title>\r\n");
      out.write("<link rel=\"stylesheet\" type=\"text/css\" href=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${pageContext.request.contextPath}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("/js/easyui/themes/default/easyui.css\" />\r\n");
      out.write("<link rel=\"stylesheet\" type=\"text/css\" href=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${pageContext.request.contextPath}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("/js/easyui/themes/icon.css\" />\r\n");
      out.write("<script type=\"text/javascript\" src=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${pageContext.request.contextPath}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("/js/easyui/jquery.min.js\"></script>\r\n");
      out.write("<script type=\"text/javascript\" src=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${pageContext.request.contextPath}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("/js/easyui/jquery.easyui.min.js\"></script>\r\n");
      out.write("<script type=\"text/javascript\" src=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${pageContext.request.contextPath}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("/js/easyui/locale/easyui-lang-zh_CN.js\"></script>\r\n");
      out.write("<link rel=\"stylesheet\" type=\"text/css\" href=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${pageContext.request.contextPath}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("/css/e3.css\" />\r\n");
      out.write("<link rel=\"stylesheet\" type=\"text/css\" href=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${pageContext.request.contextPath}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("/css/default.css\" />\r\n");
      out.write("<!--  -->\r\n");
      out.write("<style>\r\n");
      out.write("\t/* 更换datagrid首行的颜色 */\r\n");
      out.write("    .datagrid-header-row td{background-color:white;color:#white}\r\n");
      out.write("    /* 后面有个小灰空格用覆盖样式来 覆盖掉*/\r\n");
      out.write("    .datagrid-header-inner{background-color:white}\r\n");
      out.write("    /* 设置后台标题长度不然会有滚动条 */\r\n");
      out.write("    .northTitle{height: 40px}\r\n");
      out.write("</style>\r\n");
      out.write("<!-- 一些前段的script -->\r\n");
      out.write("<script type=\"text/javascript\" src=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${pageContext.request.contextPath}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("/js/common.js\"></script>\r\n");
      out.write("<style type=\"text/css\">\r\n");
      out.write("\t.content {\r\n");
      out.write("\t\tpadding: 10px 10px 10px 10px;\r\n");
      out.write("\t}\r\n");
      out.write("</style>\r\n");
      out.write("\r\n");
      out.write("</head>\r\n");
      out.write("<body class=\"easyui-layout\">\r\n");
      out.write("    <!-- 头部标题 -->\r\n");
      out.write("\t<div data-options=\"region:'north',border:false\" style=\"height:50px; padding:5px; background:#FFF\"> \r\n");
      out.write("\t\t<span class=\"northTitle\" style=\"display:block;\">&nbsp&nbsp赤潮监测系统后台</span>\r\n");
      out.write("\t    <span style=\"font-size:16px\" class=\"loginInfo\">&nbsp姓名：");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${user.name}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("&nbsp;&nbsp;&nbsp角色：系统管理员&nbsp;&nbsp;\r\n");
      out.write("\t    <!-- <button id=\"userQuery\" class=\"easyui-linkbutton\">查看用户信息</button>&nbsp&nbsp<button id=\"userUpdate\" class=\"easyui-linkbutton\">修改用户信息</button> -->\r\n");
      out.write("\t    </span>\r\n");
      out.write("\t</div>\r\n");
      out.write("\t\r\n");
      out.write("    <div data-options=\"region:'west',title:'菜单',split:true\" style=\"width:180px;\">\r\n");
      out.write("    \t<ul id=\"menu\" class=\"easyui-tree\" style=\"margin-top: 10px;margin-left: 5px;\">\r\n");
      out.write("         \t<li>\r\n");
      out.write("         \t\t<span>影像管理</span>\r\n");
      out.write("         \t\t<ul>\r\n");
      out.write("\t         \t\t<li data-options=\"attributes:{'url':'image-add'}\">新增影像</li>\r\n");
      out.write("\t         \t\t<li data-options=\"attributes:{'url':'image-list'}\">查询影像</li>\r\n");
      out.write("\t         \t</ul>\r\n");
      out.write("         \t</li>\r\n");
      out.write("          \t<li>\r\n");
      out.write("         \t\t<span>航次管理</span>\r\n");
      out.write("         \t\t<ul>\r\n");
      out.write("\t         \t\t<li data-options=\"attributes:{'url':'voyage-add'}\">新增航次</li>\r\n");
      out.write("\t         \t\t<li data-options=\"attributes:{'url':'voyage-list'}\">查询航次</li>\r\n");
      out.write("\t         \t\t<li data-options=\"attributes:{'url':'voyage-myVoyage'}\">我的航次</li>\r\n");
      out.write("\t         \t</ul>\r\n");
      out.write("         \t</li>\r\n");
      out.write("         \t<li>\r\n");
      out.write("         \t\t<span>记录管理</span>\r\n");
      out.write("         \t\t<ul>\r\n");
      out.write("\t         \t\t<li data-options=\"attributes:{'url':'record-add'}\">新增记录</li>\r\n");
      out.write("\t         \t\t<li data-options=\"attributes:{'url':'record-list'}\">查询记录</li>\r\n");
      out.write("\t         \t</ul>\r\n");
      out.write("         \t</li>  \r\n");
      out.write("         </ul>\r\n");
      out.write("    </div>\r\n");
      out.write("    <div data-options=\"region:'center',title:''\">\r\n");
      out.write("    \t<div id=\"tabs\" class=\"easyui-tabs\">\r\n");
      out.write("\t\t    <div title=\"首页\" style=\"padding:20px;\">\r\n");
      out.write("\t\t        \t<h1><a href=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${pageContext.request.contextPath}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("/pic/portal\" style=\"font-size: 20px\"><button id=\"back\" class=\"easyui-linkbutton\">返回赤潮页面</button></a></h1>\r\n");
      out.write("\t\t        \t\r\n");
      out.write("\t\t    </div>\r\n");
      out.write("\t\t</div>\r\n");
      out.write("    </div>\r\n");
      out.write("    <!-- 页脚信息 -->\r\n");
      out.write("\t<div data-options=\"region:'south',border:false\" style=\"height:20px; background:#F3F3F3; padding:2px; vertical-align:middle;\">\r\n");
      out.write("\t\t<span id=\"sysVersion\">系统版本：V3.0</span>\r\n");
      out.write("\t    <span id=\"nowTime\"></span>\r\n");
      out.write("\t</div>\r\n");
      out.write("\t<div id=\"userEditWindow\" class=\"easyui-window\" title=\"修改个人信息\" data-options=\"modal:true,closed:true,iconCls:'icon-save',href:'");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${pageContext.request.contextPath}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("/user-edit'\" style=\"width:80%;height:80%;padding:10px;\">\r\n");
      out.write("\t</div>\r\n");
      out.write("<script type=\"text/javascript\">\r\n");
      out.write("$(function(){\r\n");
      out.write("\t$('#menu').tree({\r\n");
      out.write("\t\tonClick: function(node){\r\n");
      out.write("\t\t\tif($('#menu').tree(\"isLeaf\",node.target)){\r\n");
      out.write("\t\t\t\tvar tabs = $(\"#tabs\");\r\n");
      out.write("\t\t\t\tvar tab = tabs.tabs(\"getTab\",node.text);\r\n");
      out.write("\t\t\t\tif(tab){\r\n");
      out.write("\t\t\t\t\ttabs.tabs(\"select\",node.text);\r\n");
      out.write("\t\t\t\t}else{\r\n");
      out.write("\t\t\t\t\ttabs.tabs('add',{\r\n");
      out.write("\t\t\t\t\t    title:node.text,\r\n");
      out.write("\t\t\t\t\t    href: node.attributes.url,\r\n");
      out.write("\t\t\t\t\t    closable:true,\r\n");
      out.write("\t\t\t\t\t    bodyCls:\"content\"\r\n");
      out.write("\t\t\t\t\t});\r\n");
      out.write("\t\t\t\t}\r\n");
      out.write("\t\t\t}\r\n");
      out.write("\t\t}\r\n");
      out.write("\t});\r\n");
      out.write("\t\r\n");
      out.write("\t$(\"#userUpdate\").click(function(){\r\n");
      out.write("\t\t$(\"#userEditWindow\").window({\r\n");
      out.write("    \t\tonLoad :function(){\r\n");
      out.write("    \t\t\t/* //回显数据\r\n");
      out.write("    \t\t\tvar data = $(\"#imageList\").datagrid(\"getSelections\")[0];\r\n");
      out.write("    \t\t\tdata.priceView = E3.formatPrice(data.price);\r\n");
      out.write("    \t\t\t$(\"#imageEditForm\").form(\"load\",data); */\r\n");
      out.write("    \t\t}\r\n");
      out.write("    \t}).window(\"open\");\r\n");
      out.write("\t});\r\n");
      out.write("});\r\n");
      out.write("setInterval(\"document.getElementById('nowTime').innerHTML=new Date().toLocaleString()+' 星期'+'日一二三四五六'.charAt(new Date().getDay());\",1000);\r\n");
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
