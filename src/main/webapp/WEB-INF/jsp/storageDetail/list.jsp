<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<link href="${pageContext.request.contextPath}/css/index.css" rel="stylesheet" type="text/css">
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-1.12.4.js"></script>
<script type="text/javascript">
    $(function () {
        $("#query").click(function () {
            $("[name=pageNum]").val(1);
            $("form:first").submit();
        });
    });
</script>
<div class="content-right">
    <div class="content-right-pic_w">
        <div style="margin:8px auto auto 12px;margin-top:6px">
            <span class="page_title">库存明细</span>
        </div>
    </div>
    <div class="content-text">
        <s:form action="storageDetail_list.action" method="post">
            <div class="square-order-top">
                <table width="100%" border="0" cellpadding="0" cellspacing="0" style="font-size:14px; font-weight:bold; font-family:'黑体';">
                    <tr>
                        <td width="12%">仓库名称:</td>
                        <td width="18%"><s:textfield name="warehouseName" size="18"/></td>
                        <td width="12%">仓库管理员:</td>
                        <td width="18%"><s:textfield name="keeperName" size="18"/></td>
                        <td width="12%">商品名称:</td>
                        <td width="18%"><s:textfield name="productName" size="18"/></td>
                        <td width="70">
                            <a id="query"><img src="${pageContext.request.contextPath}/image/can_b_01.gif" border="0"/></a>
                        </td>
                    </tr>
                </table>
            </div>
            <!--"square-order-top"end-->
            <div class="square-order">
                <s:if test="#storageDetailList.size() == 0">
                    <center>
                        <span style="font-size:20px;color:#96D34A;font-weight:bold">没有查找到满足条件的数据！</span>
                    </center>
                </s:if>
                <s:else>
                    <table width="100%" border="1" cellpadding="0" cellspacing="0">
                        <tr align="center" style="background:url(${pageContext.request.contextPath}/image/table_bg.gif) repeat-x;">
                            <td width="25%" height="30">仓库名称</td>
                            <td width="25%">仓库管理员</td>
                            <td width="25%">商品名称</td>
                            <td width="25%">当前库存数量</td>
                        </tr>
                        <s:iterator value="storageDetailList">
                            <tr align="center" bgcolor="#FFFFFF">
                                <td><s:property value="warehouse.name"/></td>
                                <td><s:property value="warehouse.keeper.name"/></td>
                                <td><s:property value="product.name"/></td>
                                <td><s:property value="quantity"/> <s:property value="product.unit"/></td>
                            </tr>
                        </s:iterator>
                    </table>
                    <%@ include file="/WEB-INF/jsp/tools/paging.jsp" %>
                </s:else>
            </div>
        </s:form>
    </div>
    <div class="content-bbg"></div>
</div>
