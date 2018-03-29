/*
 * Generated by the Jasper component of Apache Tomcat
 * Version: Apache Tomcat/7.0.47
 * Generated at: 2018-03-28 10:07:05 UTC
 * Note: The last modified time of this file was set to
 *       the last modified time of the source file after
 *       generation to assist with modification tracking.
 */
package org.apache.jsp.WEB_002dINF.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;

public final class record_002dlist_jsp extends org.apache.jasper.runtime.HttpJspBase
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
      out.write("<script type=\"text/javascript\" charset=\"utf-8\" src=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${pageContext.request.contextPath}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("/js/jquery.form.min.js\"></script>\r\n");
      out.write("<table class=\"easyui-datagrid\" id=\"recordList\" title=\"记录列表\" \r\n");
      out.write("       data-options=\"singleSelect:false,collapsible:true,pagination:true,url:'");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${pageContext.request.contextPath}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("/record/list',method:'post',pageSize:30,toolbar:toolbar\">\r\n");
      out.write("    <thead>\r\n");
      out.write("    <!-- ?voyageIdQuery='+ voyageIdQuery +'&pointNameQuery=' + pointNameQuery -->\r\n");
      out.write("        <tr>\r\n");
      out.write("        \t<!-- 除了图片不用显示其他都要显示 -->\r\n");
      out.write("        \t<th data-options=\"field:'ck',checkbox:true\"></th>\r\n");
      out.write("        \t<th data-options=\"field:'id',width:60\">记录ID</th> \r\n");
      out.write("            <th data-options=\"field:'recordNumber',width:60\">序号</th>\r\n");
      out.write("            <th data-options=\"field:'pointName',width:100\">点位名称</th>\r\n");
      out.write("            <th data-options=\"field:'voyageName',width:100\">所属航次</th>\r\n");
      out.write("            <th data-options=\"field:'weather',width:100\">天气</th>\r\n");
      out.write("            <th data-options=\"field:'arriveTime',width:130,align:'center',formatter:formatDatebox\">测量时间</th>\r\n");
      out.write("            <th data-options=\"field:'waterDemo',width:100\">水样编号</th>\r\n");
      out.write("            <th data-options=\"field:'chlDemo',width:100\">叶绿素浓度</th>\r\n");
      out.write("            <th data-options=\"field:'cdomDemo',width:100\">黄色物质浓度</th>\r\n");
      out.write("            <th data-options=\"field:'granuleDemo',width:100\">颗粒物浓度</th>\r\n");
      out.write("            <th data-options=\"field:'spectrumDemo',width:100\">光谱编号</th>\r\n");
      out.write("            <th data-options=\"field:'positionX',width:100\">经度</th>\r\n");
      out.write("            <th data-options=\"field:'positionY',width:100\">纬度</th>\r\n");
      out.write("        </tr>\r\n");
      out.write("    </thead>\r\n");
      out.write("</table>\r\n");
      out.write("<!-- <div id=\"queryBar\">\r\n");
      out.write("\t航次:<input type=\"text\" name=\"voyageName\" id=\"voyageName\"/>\r\n");
      out.write("</div> -->\r\n");
      out.write("<div id=\"recordEditWindow\" class=\"easyui-window\" title=\"编辑记录\" data-options=\"modal:true,closed:true,iconCls:'icon-save',href:'record-edit'\" style=\"width:80%;height:80%;padding:10px;\">\r\n");
      out.write("</div>\r\n");
      out.write("<div id=\"pictureUploadWindow\" class=\"easyui-window\" title=\"图片上传\" data-options=\"modal:true,closed:true\" style=\"width:200px;height:150px;padding:10px;margin-left:auto;margin-right:auto;\">\r\n");
      out.write("\t<form id=\"uploadForm\" method=\"post\" enctype=\"multipart/form-data\" action=\"/record/pictureUpload\">\r\n");
      out.write("\t\t图片上传:<input id=\"waterFile\" type=\"file\" name=\"waterFile\"><br><br>\r\n");
      out.write("\t\t<input type=\"hidden\" id=\"hiddenId\" name=\"recordId\">\r\n");
      out.write("\t\t<a id=\"submitPicture\" href=\"javascript:void(0)\" class=\"easyui-linkbutton\">提交</a>\r\n");
      out.write("\t</form>\r\n");
      out.write("\t<!-- <button id=\"bb\">提交</button> -->\r\n");
      out.write("</div>\r\n");
      out.write("<!-- <div id=\"recordSearchWindow\" class=\"easyui-window\" title=\"查询记录\" data-options=\"closed:true,iconCls:'icon-search'\" style=\"width:200px;height:200px;padding:10px;margin-left:auto;margin-right:auto;\">\r\n");
      out.write("\t<form id=\"recordSearchForm\" class=\"\" method=\"post\">\r\n");
      out.write("\t航次:<input id=\"voyageIdQuery\" name=\"voyageIdQuery\" class=\"easyui-combobox voyageSelect\"></input><br><br>\r\n");
      out.write("\t点位名称:<input type=\"text\" name=\"pointNameQuery\" id=\"pointNameQuery\"/><br><br>\r\n");
      out.write("\t\r\n");
      out.write("\t<a href=\"javascript:void(0)\" class=\"easyui-linkbutton\"  iconCls=\"icon-search\" onclick=\"submitRecordQueryForm()\">点击查询</a>\r\n");
      out.write("\t</form>\r\n");
      out.write("</div> -->\r\n");
      out.write("<div id=\"toolbar\">\r\n");
      out.write("    <a href=\"javascript:void(0)\" class=\"easyui-linkbutton\" data-options=\"iconCls:'icon-add',plain:true\" onclick=\"addRecord()\">新增</a>\r\n");
      out.write("    <a href=\"javascript:void(0)\" class=\"easyui-linkbutton\" data-options=\"iconCls:'icon-edit',plain:true\" onclick=\"updateRecord()\">修改</a>\r\n");
      out.write("    <a href=\"javascript:void(0)\" class=\"easyui-linkbutton\" data-options=\"iconCls:'icon-cancel',plain:true\" onclick=\"deleteRecord()\">删除</a>\r\n");
      out.write("    <!-- <a href=\"javascript:void(0)\" class=\"easyui-linkbutton\" data-options=\"iconCls:'icon-ok',plain:true\" onclick=\"addPicture()\">添加图片</a> -->\r\n");
      out.write("    <a href=\"javascript:void(0)\" id=\"searchbtn\" class=\"easyui-linkbutton\" data-options=\"iconCls:'icon-search',plain:true\" onclick=\"queryRecord()\">查询</a>\r\n");
      out.write("    \r\n");
      out.write("    <form id=\"searchForm\">\r\n");
      out.write("        <table>\r\n");
      out.write("            <tr>\r\n");
      out.write("                <th>航次:</th>\r\n");
      out.write("                <td>\r\n");
      out.write("                    <input id=\"voyageIdQuery\" name=\"voyageIdQuery\" class=\"easyui-combobox voyageSelect\" value=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${voyageNameBackShow}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("\"></input>\r\n");
      out.write("                </td>\r\n");
      out.write("                 <th>点位名称:</th>\r\n");
      out.write("                <td>\r\n");
      out.write("                    <input id=\"pointNameQuery\" name=\"pointNameQuery\" type=\"text\" value=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${pointNameBackShow}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("\" />\r\n");
      out.write("                </td>\r\n");
      out.write("            </tr>\r\n");
      out.write("        </table>\r\n");
      out.write("    </form>\r\n");
      out.write("</div>\r\n");
      out.write("<script>\r\n");
      out.write("\t  $(function(){\r\n");
      out.write("\t\t  //当关闭选项卡时，把参数都重置\r\n");
      out.write("\t\t   VoyageSelectInit();\r\n");
      out.write("\t\t  //并不需要\r\n");
      out.write("\t\t   /* $('#tabs').tabs({\r\n");
      out.write("\t\t\t\t  onBeforeClose: function(title,index){\r\n");
      out.write("\t\t\t\t    if(title == \"查询记录\") {\r\n");
      out.write("\t\t\t\t    \tpointNameQuery = \"\";\r\n");
      out.write("\t\t\t\t    \tvoyageIdQuery = null;\r\n");
      out.write("\t\t\t\t    }\r\n");
      out.write("\t\t\t\t  }\r\n");
      out.write("\t\t\t\t});  */\r\n");
      out.write("\t  });\r\n");
      out.write("\t\t \r\n");
      out.write("\t//关闭当前选项卡的函数\r\n");
      out.write("    function tabsClose(){  \r\n");
      out.write("        var tab=$('#tabs').tabs('getSelected');//获取当前选中tabs  \r\n");
      out.write("        var index = $('#tabs').tabs('getTabIndex',tab);//获取当前选中tabs的index  \r\n");
      out.write("        $('#tabs').tabs('close',index);//关闭对应index的tabs  \r\n");
      out.write("    } \r\n");
      out.write("\t\r\n");
      out.write("\tvar voyageIdQuery,pointNameQuery;\r\n");
      out.write("\t\r\n");
      out.write("    \r\n");
      out.write("\t//初始化航次选择\r\n");
      out.write("\tfunction VoyageSelectInit(){\r\n");
      out.write("\t\t$.ajax({\r\n");
      out.write("\t        url:\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${pageContext.request.contextPath}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("/voyage/selectIds\",  \r\n");
      out.write("\t        dataType:\"json\", \r\n");
      out.write("\t        type:\"POST\",\r\n");
      out.write("\t        success:function(data){\r\n");
      out.write("\t                    //绑定第一个下拉框\r\n");
      out.write("\t                    $(\".voyageSelect\").combobox({\r\n");
      out.write("\t                            data: data.data,\r\n");
      out.write("\t                            valueField: 'id',\r\n");
      out.write("\t                            textField: 'voyageName'\r\n");
      out.write("\t                    });   \r\n");
      out.write("\t                    \r\n");
      out.write("\t       },\r\n");
      out.write("\t       error:function(error){\r\n");
      out.write("\t           alert(\"初始化下拉控件失败\");\r\n");
      out.write("\t       }\r\n");
      out.write("\t    });\r\n");
      out.write("\t}\r\n");
      out.write("\t\r\n");
      out.write("    function getrecordSelectionsIds(){\r\n");
      out.write("    \tvar recordList = $(\"#recordList\");\r\n");
      out.write("    \tvar sels = recordList.datagrid(\"getSelections\");\r\n");
      out.write("    \tvar ids = [];\r\n");
      out.write("    \tfor(var i in sels){\r\n");
      out.write("    \t\tids.push(sels[i].id);\r\n");
      out.write("    \t}\r\n");
      out.write("    \tids = ids.join(\",\");\r\n");
      out.write("    \treturn ids;\r\n");
      out.write("    }\r\n");
      out.write("    \r\n");
      out.write("    \r\n");
      out.write("    //添加记录\r\n");
      out.write("   \r\n");
      out.write("    function addRecord() {\r\n");
      out.write("    \t$(\".tree-title:contains('新增记录')\").parent().click();\r\n");
      out.write("    }\r\n");
      out.write("    \r\n");
      out.write("    //编辑记录\r\n");
      out.write("    function updateRecord() {\r\n");
      out.write("    \tvar ids = getrecordSelectionsIds();\r\n");
      out.write("    \tif(ids.length == 0){\r\n");
      out.write("    \t\t$.messager.alert('提示','必须选择一个记录才能编辑!');\r\n");
      out.write("    \t\treturn ;\r\n");
      out.write("    \t}\r\n");
      out.write("    \tif(ids.indexOf(',') > 0){\r\n");
      out.write("    \t\t$.messager.alert('提示','只能选择一个记录!');\r\n");
      out.write("    \t\treturn ;\r\n");
      out.write("    \t}\r\n");
      out.write("    \t$(\"#recordEditWindow\").window({\r\n");
      out.write("    \t\tonLoad :function(){\r\n");
      out.write("    \t\t\t//回显数据\r\n");
      out.write("    \t\t\tvar data = $(\"#recordList\").datagrid(\"getSelections\")[0];\r\n");
      out.write("    \t\t\t//日期由于格式原因无法回显\r\n");
      out.write("    \t\t\t//由于form的load方法直接把全部的data添加时会无法回显，所以这里要分开添加\r\n");
      out.write("    \t\t\t$(\"#recordEditForm\").form(\"load\",{\"id\":data.id}); \r\n");
      out.write("    \t\t\t$(\"#recordEditForm\").form(\"load\",{\"voyageId\":data.voyageId}); \r\n");
      out.write("    \t\t\t$(\"#recordEditForm\").form(\"load\",{\"pointName\":data.pointName}); \r\n");
      out.write("    \t\t\t$(\"#recordEditForm\").form(\"load\",{\"recordNumber\":data.recordNumber}); \r\n");
      out.write("    \t\t\t$(\"#recordEditForm\").form(\"load\",{\"positionX\":data.positionX}); \r\n");
      out.write("    \t\t\t$(\"#recordEditForm\").form(\"load\",{\"positionY\":data.positionY}); \r\n");
      out.write("    \t\t\t$(\"#recordEditForm\").form(\"load\",{\"weather\":data.weather}); \r\n");
      out.write("    \t\t\t$(\"#recordEditForm\").form(\"load\",{\"waterDemo\":data.waterDemo}); \r\n");
      out.write("    \t\t\t$(\"#recordEditForm\").form(\"load\",{\"chlDemo\":data.chlDemo}); \r\n");
      out.write("    \t\t\t$(\"#recordEditForm\").form(\"load\",{\"cdomDemo\":data.cdomDemo}); \r\n");
      out.write("    \t\t\t$(\"#recordEditForm\").form(\"load\",{\"granuleDemo\":data.granuleDemo}); \r\n");
      out.write("    \t\t\t$(\"#recordEditForm\").form(\"load\",{\"spectrumDemo\":data.spectrumDemo}); \r\n");
      out.write("    \t\t\t\r\n");
      out.write("    \t\t}\r\n");
      out.write("    \t}).window(\"open\");\r\n");
      out.write("    }\r\n");
      out.write("    \r\n");
      out.write("    //删除记录\r\n");
      out.write("    function deleteRecord() {\r\n");
      out.write("    \tvar ids = getrecordSelectionsIds();\r\n");
      out.write("    \tif(ids.length == 0){\r\n");
      out.write("    \t\t$.messager.alert('提示','未选中记录!');\r\n");
      out.write("    \t\treturn ;\r\n");
      out.write("    \t}\r\n");
      out.write("    \t$.messager.confirm('确认','确定删除ID为 '+ids+' 的记录吗？',function(r){\r\n");
      out.write("    \t    if (r){\r\n");
      out.write("    \t    \tvar params = {\"ids\":ids};\r\n");
      out.write("            \t$.post(\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${pageContext.request.contextPath}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("/record/delete\",params, function(data){\r\n");
      out.write("        \t\t\tif(data.status == 200){\r\n");
      out.write("        \t\t\t\t$.messager.alert('提示','删除记录成功!',undefined,function(){\r\n");
      out.write("        \t\t\t\t\t//刷新记录列表\r\n");
      out.write("        \t\t\t\t\t$(\"#recordList\").datagrid(\"reload\");\r\n");
      out.write("        \t\t\t\t\t//刷新我的记录列表\r\n");
      out.write("        \t\t\t\t\t$(\"#recordUserList\").datagrid(\"reload\");\r\n");
      out.write("        \t\t\t\t\t//初始化选择记录combobox\r\n");
      out.write("        \t\t\t\t\trecordSelectInit();\r\n");
      out.write("        \t\t\t\t});\r\n");
      out.write("        \t\t\t}\r\n");
      out.write("        \t\t});\r\n");
      out.write("    \t    }\r\n");
      out.write("    \t});\r\n");
      out.write("    }\r\n");
      out.write("    \r\n");
      out.write("    //添加图片\r\n");
      out.write(" /*    function addPicture() {\r\n");
      out.write("    \tvar ids = getrecordSelectionsIds();\r\n");
      out.write("    \tif(ids.length == 0){\r\n");
      out.write("    \t\t$.messager.alert('提示','必须选择一个记录才能编辑!');\r\n");
      out.write("    \t\treturn ;\r\n");
      out.write("    \t}\r\n");
      out.write("    \tif(ids.indexOf(',') > 0){\r\n");
      out.write("    \t\t$.messager.alert('提示','只能选择一个记录!');\r\n");
      out.write("    \t\treturn ;\r\n");
      out.write("    \t}\r\n");
      out.write("    \t$(\"#hiddenId\").val(ids);\r\n");
      out.write("    \t$(\"#waterFile\").val(\"\");\r\n");
      out.write("    \t$(\"#pictureUploadWindow\").window(\"open\");\r\n");
      out.write("    } */\r\n");
      out.write("    \r\n");
      out.write("    //查询记录\r\n");
      out.write("    function queryRecord() {\r\n");
      out.write("    \t//赋值查询条件\r\n");
      out.write("\t\t//这里一定要用easyuicombobox内置的combobox方法才能取值！！！！！\r\n");
      out.write("\t\tvoyageIdQuery = $('#voyageIdQuery').combobox('getValue');\r\n");
      out.write("\t\tpointNameQuery = $(\"#pointNameQuery\").val();\r\n");
      out.write("\t\t//点击确定后重新发送分页查询请求\r\n");
      out.write("\t\t$('#recordList').datagrid('load',{          \r\n");
      out.write("            voyageIdQuery:voyageIdQuery,pointNameQuery:pointNameQuery          \r\n");
      out.write("        });\r\n");
      out.write("\t\t\r\n");
      out.write("\t//提交表格\r\n");
      out.write(" \r\n");
      out.write("\t\r\n");
      out.write("\t\r\n");
      out.write("\t\t/*  tabsClose();\r\n");
      out.write("\t\t$(\".tree-title:contains('查询记录')\").parent().click();  */\r\n");
      out.write("    }\r\n");
      out.write("/*     var toolbar =[ {\r\n");
      out.write("        text:'新增',\r\n");
      out.write("        iconCls:'icon-add',\r\n");
      out.write("        handler:function(){\r\n");
      out.write("        \t$(\".tree-title:contains('新增记录')\").parent().click();\r\n");
      out.write("        }\r\n");
      out.write("    },{\r\n");
      out.write("        text:'编辑',\r\n");
      out.write("        iconCls:'icon-edit',\r\n");
      out.write("        handler:function(){\r\n");
      out.write("        \tvar ids = getrecordSelectionsIds();\r\n");
      out.write("        \tif(ids.length == 0){\r\n");
      out.write("        \t\t$.messager.alert('提示','必须选择一个记录才能编辑!');\r\n");
      out.write("        \t\treturn ;\r\n");
      out.write("        \t}\r\n");
      out.write("        \tif(ids.indexOf(',') > 0){\r\n");
      out.write("        \t\t$.messager.alert('提示','只能选择一个记录!');\r\n");
      out.write("        \t\treturn ;\r\n");
      out.write("        \t}\r\n");
      out.write("        \t\r\n");
      out.write("        \t$(\"#recordEditWindow\").window({\r\n");
      out.write("        \t\tonLoad :function(){\r\n");
      out.write("        \t\t\t//回显数据\r\n");
      out.write("        \t\t\tvar data = $(\"#recordList\").datagrid(\"getSelections\")[0];\r\n");
      out.write("        \t\t\t//日期由于格式原因无法回显\r\n");
      out.write("        \t\t\t//由于form的load方法直接把全部的data添加时会无法回显，所以这里要分开添加\r\n");
      out.write("        \t\t\t$(\"#recordEditForm\").form(\"load\",{\"id\":data.id}); \r\n");
      out.write("        \t\t\t$(\"#recordEditForm\").form(\"load\",{\"recordName\":data.recordName}); \r\n");
      out.write("        \t\t\t$(\"#recordEditForm\").form(\"load\",{\"leader\":data.leader}); \r\n");
      out.write("        \t\t\t$(\"#recordEditForm\").form(\"load\",{\"position\":data.position}); \r\n");
      out.write("        \t\t\t$(\"#recordEditForm\").form(\"load\",{\"shipCode\":data.shipCode}); \r\n");
      out.write("        \t\t\t$(\"#recordEditForm\").form(\"load\",{\"mission\":data.mission}); \r\n");
      out.write("        \t\t}\r\n");
      out.write("        \t}).window(\"open\");\r\n");
      out.write("        }\r\n");
      out.write("    },{\r\n");
      out.write("        text:'删除',\r\n");
      out.write("        iconCls:'icon-cancel',\r\n");
      out.write("        handler:function(){\r\n");
      out.write("        \tvar ids = getrecordSelectionsIds();\r\n");
      out.write("        \tif(ids.length == 0){\r\n");
      out.write("        \t\t$.messager.alert('提示','未选中记录!');\r\n");
      out.write("        \t\treturn ;\r\n");
      out.write("        \t}\r\n");
      out.write("        \t$.messager.confirm('确认','确定删除ID为 '+ids+' 的记录吗？',function(r){\r\n");
      out.write("        \t    if (r){\r\n");
      out.write("        \t    \tvar params = {\"ids\":ids};\r\n");
      out.write("                \t$.post(\"/record/delete\",params, function(data){\r\n");
      out.write("            \t\t\tif(data.status == 200){\r\n");
      out.write("            \t\t\t\t$.messager.alert('提示','删除记录成功!',undefined,function(){\r\n");
      out.write("            \t\t\t\t\t//刷新记录列表\r\n");
      out.write("            \t\t\t\t\t$(\"#recordList\").datagrid(\"reload\");\r\n");
      out.write("            \t\t\t\t\t//刷新我的记录列表\r\n");
      out.write("            \t\t\t\t\t$(\"#recordUserList\").datagrid(\"reload\");\r\n");
      out.write("            \t\t\t\t\t//初始化选择记录combobox\r\n");
      out.write("            \t\t\t\t\trecordSelectInit();\r\n");
      out.write("            \t\t\t\t});\r\n");
      out.write("            \t\t\t}\r\n");
      out.write("            \t\t});\r\n");
      out.write("        \t    }\r\n");
      out.write("        \t});\r\n");
      out.write("        }\r\n");
      out.write("    },\r\n");
      out.write("    {\r\n");
      out.write("        text:'关联',\r\n");
      out.write("        iconCls:'icon-ok',\r\n");
      out.write("        handler:function(){\r\n");
      out.write("        \tvar ids = getrecordSelectionsIds();\r\n");
      out.write("        \tif(ids.length == 0){\r\n");
      out.write("        \t\t$.messager.alert('提示','未选中记录!');\r\n");
      out.write("        \t\treturn ;\r\n");
      out.write("        \t}\r\n");
      out.write("        \t$.messager.confirm('确认','确定本用户关联ID为 '+ids+' 的记录吗？',function(r){\r\n");
      out.write("        \t    if (r){\r\n");
      out.write("        \t    \tvar params = {\"ids\":ids};\r\n");
      out.write("                \t$.post(\"/record/relate/");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${user.id}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("\",params, function(data){\r\n");
      out.write("            \t\t\tif(data.status == 200){\r\n");
      out.write("            \t\t\t\t$.messager.alert('提示','关联记录成功!',undefined,function(){\r\n");
      out.write("            \t\t\t\t\t//打开我的记录\r\n");
      out.write("            \t\t\t\t\t$(\".tree-title:contains('我的记录')\").parent().click();\r\n");
      out.write("            \t\t\t\t\t//刷新我的记录列表\r\n");
      out.write("            \t\t\t\t\t$(\"#recordUserList\").datagrid(\"reload\");\r\n");
      out.write("            \t\t\t\t});\r\n");
      out.write("            \t\t\t}else if (data.status == 401){\r\n");
      out.write("            \t\t\t\t$.messager.alert('提示','关联记录重复，关联失败!');\r\n");
      out.write("            \t\t\t} else{\r\n");
      out.write("            \t\t\t\t$.messager.alert('提示','关联错误!');\r\n");
      out.write("            \t\t\t}\r\n");
      out.write("            \t\t});\r\n");
      out.write("        \t    }\r\n");
      out.write("        \t});\r\n");
      out.write("        }\r\n");
      out.write("    },\r\n");
      out.write("    {\r\n");
      out.write("        text:'搜索',\r\n");
      out.write("        iconCls:'icon-search',\r\n");
      out.write("        handler:function(){\r\n");
      out.write("        \t$(\"#recordSearchWindow\").window(\"open\");\r\n");
      out.write("        \t//初始化航次选择combobox\r\n");
      out.write("        \tVoyageSelectInit();\r\n");
      out.write("        }\r\n");
      out.write("    } \r\n");
      out.write("    ];\r\n");
      out.write("     */\r\n");
      out.write("     var toolbar = $(\"#toolbar\");\r\n");
      out.write("    //日期转换\r\n");
      out.write("    function formatDatebox(value) {  \r\n");
      out.write("        if (value == null || value == '') {  \r\n");
      out.write("            return '';  \r\n");
      out.write("        }  \r\n");
      out.write("        var dt;  \r\n");
      out.write("        if (value instanceof Date) {  \r\n");
      out.write("            dt = value;  \r\n");
      out.write("        } else {  \r\n");
      out.write("            dt = new Date(value);  \r\n");
      out.write("        }  \r\n");
      out.write("      \r\n");
      out.write("        return dt.format(\"yyyy-MM-dd\"); //扩展的Date的format方法(上述插件实现)  \r\n");
      out.write("    } \r\n");
      out.write("</script>");
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
