<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false" %>
<%@ taglib prefix="s" uri="/struts-tags"%>
<link href="${pageContext.request.contextPath}/css/index.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-1.12.4.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.min.js"></script>
<script type="text/javascript">
	$(function () {
        var warehouseIdArr = new Array();
        var warehouseNameArr = new Array();
        var i = 0;
		<s:iterator value="warehouseList">
			warehouseIdArr[i] = ${id};
			warehouseNameArr[i] = "${name}";
			i++;
		</s:iterator>
        $(".selectWarehouse").click(function () {
            $(".newTr").remove();
            $nowTr = $(this).parent().parent();
            var orderDetailId=$nowTr.attr("param");
            $.post("orderDetail_ajaxGetSurplus.action",{"orderDetail.id":orderDetailId},function (data) {
                var surplus = data.surplus;
                $tr = $('<tr class="newTr" align="center" bgcolor="#FFFFFF"></tr>');
                $td1 = $('<td align="right">仓库</td>');
                $td2 = $('<td height="30"></td>');
                $select = $('<select id="warehouseId" style="width: 200px"></select>');
                for (var i=0;i<warehouseIdArr.length;i++) {
                    $option = $('<option value="'+warehouseIdArr[i]+'">'+warehouseNameArr[i]+'</option>');
                    $select.append($option);
                }
                $td2.append($select);
                $td3 = $('<td align="right">入库数量</td>');
                $td4 = $('<td><input type="text" id="inQuantity" value="'+surplus+'"></td>');
                $td5 = $('<td align="center"><a href="javascript:void(0)" class="ajaxIn edit"><img src="/image/icon_03.gif">确定</a></td>');
                $tr.append($td1);
                $tr.append($td2);
                $tr.append($td3);
                $tr.append($td4);
                $tr.append($td5);
                $nowTr.after($tr);
            });
        });
        $(".ajaxIn").live("click",function () {
			var jsonParam = {};
			jsonParam["warehouseId"] = $("#warehouseId").val();
			jsonParam["inQuantity"] = $("#inQuantity").val();
			$nowTr = $(this).parent().parent();
			$prevTr = $nowTr.prev();
            jsonParam["orderDetailId"] = $prevTr.attr("param");
			$.post("order_ajaxInProduct.action",jsonParam,function (data) {
			    var quantity = data.quantity;
			    var surplus = data.surplus;
			    if ($(".ins").length == 1 && surplus == 0) {
					$("#inOrderTitle").hide();
                    $("#inOrder").hide();
                    $("#allInTitle").show();
                    $("#return").show();
				}
                // 如果全部入库,删除对应行
                if (surplus == 0) {
					$prevTr.remove();
                    $nowTr.remove();
                }
				$prevTr.children("td:eq(2)").html(quantity-surplus);
                $prevTr.children("td:eq(3)").html(surplus);
                $nowTr.children("td:eq(3)").children("input").val(surplus);
            });
        });
    });
</script>
<div class="content-right">
	<div class="content-right-pic_w">
		<div style="margin:8px auto auto 12px;margin-top:6px">
			<span class="page_title">入库</span>
		</div>
	</div>
	<div class="content-text">
			<div class="square-order-top">
				<table width="100%" border="0" cellpadding="0" cellspacing="0" style="font-size:14px; font-weight:bold; font-family:'黑体';">
					<tr>
						<td>订单号:</td>
						<td class="order_show_msg" colspan="2"><s:property value="order.sn"/></td>
						<td>商品总量:</td>
						<td class="order_show_msg"><s:property value="order.amount"/></td>
					</tr>
				</table>
			</div>
			<!--"square-order-top"end-->
			<div class="square-order">
				<center id="inOrderTitle" style="text-decoration:underline;font-size:16px; font-weight:bold; font-family:'黑体';">&nbsp;&nbsp;&nbsp;&nbsp;单&nbsp;&nbsp;据&nbsp;&nbsp;明&nbsp;&nbsp;细&nbsp;&nbsp;&nbsp;&nbsp;</center>
				<br/>
				<table id="inOrder" width="100%" border="1" cellpadding="0" cellspacing="0">
					<tr align="center" style="background:url(/image/table_bg.gif) repeat-x;">
						<td width="20%">商品名称</td>
						<td width="15%">购买数量</td>
						<td width="15%">已入库数量</td>
						<td width="15%">剩余数量</td>
						<td width="20%">入库</td>
					</tr>
					<s:iterator value="order.orderDetails">
						<s:if test="surplus > 0">
							<tr class="ins" param="${id}" align="center" bgcolor="#FFFFFF">
								<td><s:property value="product.name"/></td>
								<td><s:property value="quantity"/></td>
								<td>${quantity - surplus}</td>
								<td>${surplus}</td>
								<td>
									<a href="javascript:void(0)" class="selectWarehouse edit"><img src="/image/icon_03.gif">入库</a>
								</td>
							</tr>
						</s:if>
					</s:iterator>
				</table>
				<center id="allInTitle" style="display: none; font-size:16px; font-weight:bold; font-family:'黑体';">&nbsp;&nbsp;&nbsp;&nbsp;全&nbsp;&nbsp;部&nbsp;&nbsp;入&nbsp;&nbsp;库&nbsp;&nbsp;&nbsp;&nbsp;</center>
				<table id="return" style="display: none">
					<tr>
						<td>&nbsp;</td>
						<td width="100%" align="center">
							<a href="order_warehousingList.action" style="color:#f00;font-size:20px;padding-top:2px;font-weight:bold;text-decoration:none;width:82px;height:28px;display:block;background:url(/image/btn_bg.jpg)">返回</a>
						</td>
						<td>&nbsp;</td>
					</tr>
				</table>
			</div>
	</div>
	<div class="content-bbg"></div>
</div>
