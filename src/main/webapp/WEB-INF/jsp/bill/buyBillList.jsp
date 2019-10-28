<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false" %>
<%@ taglib prefix="s" uri="/struts-tags"%>
<link href="${pageContext.request.contextPath}/css/index.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-1.12.4.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/My97DatePicker/WdatePicker.js"></script>
<script type="text/javascript">
    $(function () {
        $("#query").click(function () {
            $("[name=pageNum]").val(1);
            $("form:first").submit();
        });
        $(".info").click(function () {
            /*
            * empty() 方法移除被选元素的所有子节点和内容。
			* 注意：该方法不会移除元素本身，或它的属性。
			* 提示：如需移除元素，但保留数据和事件，请使用 detach() 方法。
			* 提示：如需移除元素及它的数据和事件，请使用 remove() 方法。
            */
            $('.ajaxAddRow').empty();
            var $nowTr = $(this).parent().parent();
            var jsonParam = {};
            jsonParam["billQueryModel.productId"] = $(this).attr("param");
            jsonParam["billQueryModel.orderStatus"] = $("[name='billQueryModel.orderStatus']").val();
            jsonParam["billQueryModel.startTimeView"] = $("[name='billQueryModel.startTimeView']").val();
            jsonParam["billQueryModel.endTimeView"] = $("[name='billQueryModel.endTimeView']").val();
            $.post("bill_ajaxListBuyBillByProduct.action", jsonParam, function (data) {
				var orderDetailList = data.orderDetailList;
				var $headTr = $('<tr class="ajaxAddRow" align="center" bgcolor="#FFFFFF"></tr>');
				var $td1 = $('<td height="30">订单号</td>');
				$headTr.append($td1);
				var $td2 =$('<td>申请时间</td>');
                $headTr.append($td2);
				var $td3 =$('<td>商品数量</td>');
                $headTr.append($td3);
                var $td4 =$('<td>进价</td>');
                $headTr.append($td4);
                var $td5 =$('<td>小计</td>');
                $headTr.append($td5);
                $nowTr.after($headTr);
                $nowTr = $headTr;
				var sum = 0;
				for (var i=0;i<orderDetailList.length;i++) {
				    var orderDetail = orderDetailList[i];
                    var $tr = $('<tr class="ajaxAddRow" align="center"></tr>');
				    var $td1 = $('<td height="30">'+orderDetail.order.sn+'</td>');
                    $tr.append($td1);
                    var $td2 =$('<td>'+orderDetail.order.gmtCreateView+'</td>');
                    $tr.append($td2);
                    var $td3 =$('<td>'+orderDetail.quantity+'</td>');
                    $tr.append($td3);
                    var $td4 =$('<td align="right">'+orderDetail.priceView+'&nbsp;元</td>');
                    $tr.append($td4);
                    var $td5 =$('<td align="right">'+orderDetail.subtotalView+'&nbsp;元</td>');
                    $tr.append($td5);
                    $nowTr.after($tr);
                    $nowTr = $tr;
                    sum = sum + 1*orderDetail.subtotalView;
				}
				var $footTr = $('<tr class="ajaxAddRow" align="center" bgcolor="#FFFFFF"></tr>');
				var $td1 = $('<td align="right" colspan="4" height="30">总计</td>');
				$footTr.append($td1);
				var $td2 = $('<td align="right">'+changeTwoDecimal(sum)+'&nbsp;元</td>');
                $footTr.append($td2);
				$nowTr.after($footTr);
            });
        });
        function changeTwoDecimal(val) {
            return new Number(val).toFixed(2);
        }
    });
</script>
<div class="content-right">
	<div class="content-right-pic_w">
		<div style="margin:8px auto auto 12px;margin-top:6px">
			<span class="page_title">进货报表</span>
		</div>
	</div>
	<div class="content-text">
		<s:form action="bill_buyBillList" method="post">
			<div class="square-order-top">
				<table width="100%" border="0" cellpadding="0" cellspacing="0" style="font-size:14px; font-weight:bold; font-family:'黑体';">
					<tr>
						<td width="70" height="30">报表类别:</td>
						<td width="140">
							<input type="radio" name="groupBy" checked="checked">商品名称
						</td>
						<td width="70">订单状态:</td>
						<td width="190">
							<s:select name="billQueryModel.orderStatus" list="@cn.com.zv2.invoice.bill.entity.BillQueryModel@buyStatusMap" headerKey="-1" headerValue="----请-选-择----" cssClass="width"></s:select>
						</td>
						<td width="70">开始日期:</td>
						<td width="190">
							<input name="billQueryModel.startTimeView" id="d4311" class="Wdate" type="text" size="14" onclick="WdatePicker({maxDate:'#F{$dp.$D(\'d4312\')||\'2020-10-01\'}'})" value="${billQueryModel.startTimeView}" readonly="readonly" />
						<td >
							<a id="query"><img src="/image/can_b_01.gif" border="0" /></a>
						</td>
					</tr>
					<tr>
						<td height="30">&nbsp;</td>
						<td>
							<input type="radio" name="groupBy">采购人员
						</td>
						<td>供应商:</td>
						<td>
							<s:select name="billQueryModel.supplierId" list="supplierList" listKey="id" listValue="name" headerKey="-1" headerValue="----请-选-择----" cssClass="width"></s:select>
						</td>
						<td>结束日期:</td>
						<td width="190">
							<input name="billQueryModel.endTimeView" id="d4312" class="Wdate" type="text" size="14" onclick="WdatePicker({minDate:'#F{$dp.$D(\'d4311\')}',maxDate:'2020-10-01'})" value="${billQueryModel.endTimeView}" readonly="readonly" />
						<td>
							<a href="bill_downloadBuyBill.action">
								<img src="/image/can_b_03.gif" border="0" />
							</a>
					</tr>
				</table>
			</div>
		</s:form>
		<!--"square-order-top"end-->
		<div class="square-order">
			<table width="70%" border="1" cellpadding="0" cellspacing="0" style="float:left;">
				<tr align="center" style="background:url(/image/table_bg.gif) repeat-x;">
					<td colspan="2" width="49%" height="30">商品名称</td>
					<td colspan="2" width="28%">商品数量</td>
					<td width="23%">详情</td>
				</tr>
				<s:iterator var="objects" value="buyBillList">
					<tr align="center" bgcolor="#FFFFFF">
						<td colspan="2" width="30%" height="30">${objects[0].name}</td>
						<td colspan="2">${objects[1]}</td>
						<td>
							<a href="javascript:void(0)" class="edit info" param="${objects[0].id}">
								详情
							</a>
						</td>
					</tr>
				</s:iterator>
			</table>
			<div style="float:right;">
				<img id="pie" src="bill_jfreechartPie.action?
					billQueryModel.orderStatus=${param['billQueryModel.orderStatus']}&
					billQueryModel.startTimeView=${param['billQueryModel.startTimeView']}&
					billQueryModel.endTimeView=${param['billQueryModel.endTimeView']}&
					billQueryModel.supplierId=${param['billQueryModel.supplierId']}
					" width="240px" height="180px">
			</div>
		</div>
	</div>
	<div class="content-bbg"></div>
</div>
