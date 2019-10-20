<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<link href="/css/index.css" rel="stylesheet" type="text/css"/>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-1.12.4.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.min.js"></script>
<script type="text/javascript">
    $(function () {
        $("#commit").click(function () {
            $("form:first").submit();
        });
        $("#supplier").change(function () {
            var supplierId = $(this).val();
            $.post("order_ajaxListCategoryAndProduct.action", {"supplierId":supplierId}, function (data) {
                var categoryList = data.categoryList;
                $(".category").empty();
                for (var i=0;i<categoryList.length;i++) {
                    var $category_option = $("<option value='"+categoryList[i].id+"'>"+categoryList[i].name+"</option>");
                    $(".category").append($category_option);
                }
                var productList = data.productList;
                $(".product").empty();
                for (var j=0;j<productList.length;j++) {
                    var $product_option = $("<option value='"+productList[j].id+"'>"+productList[j].name+"</option>");
                    $(".product").append($product_option);
                }
                var price = data.product.bidView;
                // 修改数量为1
                $(".quantity").val(1);
                // 修改单价
                $(".price").val(price);
                // 修改小计
                $(".subtotal").html(price+" 元");
                $(".total").html(price+" 元");
                // 计算总计
                calculateTotal();
            });
        });
        // 修改类别
        $(".category").live("change",function () {
//            var $product_select = $(this).parent().next().children("select");
//            var $quantity_input = $(this).parent().next().next().children("input");
//            var $price_input = $(this).parent().next().next().next().children("input");
//            var $subtotal = $(this).parent().next().next().next().next();
            var $nowTr = $(this).parent().parent();
            var $product_select = $nowTr.children("td:eq(1)").children("select");
            var $quantity_input = $nowTr.children("td:eq(2)").children("input");
            var $price_input = $nowTr.children("td:eq(3)").children("input");
            var $subtotal = $nowTr.children("td:eq(4)");
            // 过滤页面上已存在的商品
            var productArr = $(".product");
            var usedProductIds = "";
            for (var i=0;i<productArr.length;i++) {
                usedProductIds = usedProductIds+"'"+productArr[i].value+"',";
            }
            var categoryId = $(this).val();
            $.post("order_ajaxListProductByCategory.action", {"categoryId":categoryId,"usedProductIds":usedProductIds}, function (data) {
                var productList = data.productList;
                $product_select.empty();
                for (var j=0;j<productList.length;j++) {
                    var $product_option = $("<option value='"+productList[j].id+"'>"+productList[j].name+"</option>");
                    $product_select.append($product_option);
                }
                var price = data.product.bidView;
                // 修改数量为1
                $quantity_input.val(1);
                // 修改单价
                $price_input.val(price);
                // 修改小计
                $subtotal.html(price+" 元");
                // 计算总计
                calculateTotal();
            });
        });
        // 修改商品
        $(".product").live("change",function () {
//            var $quantity_input = $(this).parent().next().children("input");
//            var $price_input = $(this).parent().next().next().children("input");
//            var $subtotal = $(this).parent().next().next().next();
            var $nowTr = $(this).parent().parent();
            var $quantity_input = $nowTr.children("td:eq(2)").children("input");
            var $price_input = $nowTr.children("td:eq(3)").children("input");
            var $subtotal = $nowTr.children("td:eq(4)");
            var productId = $(this).val();
            $.post("order_ajaxGetProductPrice.action", {"productId":productId}, function (data) {
                var price = data.bidView;
                // 修改数量为1
                $quantity_input.val(1);
                // 修改单价
                $price_input.val(price);
                // 修改小计
                $subtotal.html(price+" 元");
                // 计算总计
                calculateTotal();
            });
        });
        var addBtnAccessible = true;
        $("#add").click(function () {
            // 锁定供应商
            $("#supplier").attr("disabled",true);
            $(".category").attr("disabled",true);
            $(".product").attr("disabled",true);
            // 判断ajax是否取得响应结果
            if (!addBtnAccessible) {
                return;
            }
            addBtnAccessible = false;
            // 动态生成行数据，添加到指定位置
            // AJAX获取数据
            // 过滤页面上已存在的商品
            var productArr = $(".product");
            var usedProductIds = "";
            for (var i=0;i<productArr.length;i++) {
                usedProductIds = usedProductIds+"'"+productArr[i].value+"',";
            }
            var supplierId = $("#supplier").val();
            $.post("order_ajaxListNewCategoryAndProduct.action", {"supplierId":supplierId,"usedProductIds":usedProductIds}, function (data) {

                var $tr = $('<tr align="center" bgcolor="#FFFFFF"></tr>');

                var $td1 = $('<td height="30"></td>');
                var $category_select = $('<select class="category" style="width:200px"></select>');
                var categoryList = data.categoryList;
                for (var i=0;i<categoryList.length;i++) {
                    var $category_option = $('<option value="'+categoryList[i].id+'">'+categoryList[i].name+'</option>');
                    $category_select.append($category_option);
                }
                $td1.append($category_select);
                $tr.append($td1);

                var $td2 = $('<td></td>');
                var $product_select = $('<select class="product" style="width:200px"></select>');
                var productList = data.productList;
                for (var j=0;j<productList.length;j++) {
                    var $product_option = $('<option value="'+productList[j].id+'">'+productList[j].name+'</option>');
                    $product_select.append($product_option);
                }
                $td2.append($product_select);
                $tr.append($td2);

                var $td3 = $('<td></td>');
                var $quantity_input = $('<input name="quantity" class="quantity" style="width:67px;border:1px solid black;text-align:right;padding:2px" type="text" value="1">');
                $td3.append($quantity_input);
                $tr.append($td3);

                var price = data.product.bidView;
                var $td4 = $('<td><input name="price" class="price" style="width:93px;border:1px solid black;text-align:right;padding:2px" type="text" value="'+price+'">&nbsp;元</td>');
                $tr.append($td4);

                var $td5 = $('<td class="subtotal" align="right">'+price+' 元</td>');
                $tr.append($td5);

                var $td6 = $('<td><a class="deleteBtn delete edit"><img src="/image/icon_04.gif">删除</a></td>');
                $tr.append($td6);

                $("#finalTr").before($tr);

                // 控制响应之前不可见
                addBtnAccessible = true;

                // 判断添加按钮是否显示
                // 当只有1个类别和1个商品时,隐藏添加按钮
                if (categoryList.length == 1 && productList.length == 1) {
                    // 样式切换,$("#add").toggleClass("");
                    $("#add").hide();
                }
                // 计算总计
                calculateTotal();
            });
        });

        // 删除行
        $(".deleteBtn").live("click",function () {
            // 当只有1行数据时无法删除
            if ($(".deleteBtn").length == 1) {
                return;
            }
            // 删除当前行对象
            var $nowTr = $(this).parent().parent();
            $nowTr.remove();
            // 显示添加按钮
            $("#add").show();
            if ($(".deleteBtn").length == 1) {
                $(".deleteBtn").parent().empty();
            }
            calculateTotal();
        });
        $(".quantity").live("keyup",function () {
            // 保证只有数字
            $(this).val($(this).val().replace(/[^\d]/g,""));
            calculateSubtotal($(this));
            calculateTotal();
        });
        $(".price").live("keyup",function () {
            // 把除了数字和.以外的都剔除
            $(this).val($(this).val().replace(/[^\d.]/g,""));
            // 保证第一个为数字而不是.
            $(this).val($(this).val().replace(/^\./g,"0."));
            // 保证只有一个.
            $(this).val($(this).val().replace(/\.{2,}/g,"."));
            // 保证只有一个.
            $(this).val($(this).val().replace(".","$#$").replace(/\./g,"").replace("$#$","."));
            calculateSubtotal($(this));
            calculateTotal();
        });
        function calculateSubtotal(obj) {
            var $nowTr = obj.parent().parent();
            var $quantity_input = $nowTr.children("td:eq(2)").children("input");
            var $price_input = $nowTr.children("td:eq(3)").children("input");
            var $subtotal = $nowTr.children("td:eq(4)");
            var subtotal = $quantity_input.val()*$price_input.val();
            $subtotal.html(changeTwoDecimal(subtotal)+" 元");
        }
        function calculateTotal() {
            var $total = $(".total");
            var $quantityArr = $(".quantity");
            var $priceArr = $(".price");
            var total = 0.00;
            for (var i=0;i<$quantityArr.length;i++) {
                total = total + $quantityArr[i].value*$priceArr[i].value;
            }
            $total.html(changeTwoDecimal(total)+" 元");
        }
        function changeTwoDecimal(val) {
            return new Number(val).toFixed(2);
        }
    });
</script>
<div class="content-right">
    <div class="content-right-pic_w">
        <div style="margin:8px auto auto 12px;margin-top:6px">
            <span class="page_title">采购订单管理</span>
        </div>
    </div>
    <div class="content-text">
        <s:form action="order_buySave" method="POST">
            <div class="square-order-top">
                <table width="100%" border="0" cellpadding="0" cellspacing="0" style="font-size:14px; font-weight:bold; font-family:'黑体';">
                    <tr>
                        <td width="68px" height="30">供应商：</td>
                        <td width="648px">
                            <s:select id="supplier" list="supplierList" listKey="id" listValue="name" cssStyle="width:190px"></s:select>
                        </td>
                        <td height="30">
                            <a id="add"><img src="/image/can_b_02.gif" border="0" /></a>
                        </td>
                    </tr>
                </table>
            </div>
            <!--"square-order-top"end-->
            <div class="square-order">
                <table width="100%" border="0" cellpadding="0" cellspacing="0">
                    <tr align="center" style="background:url(/image/table_bg.gif) repeat-x;">
                        <td width="25%" height="30">商品类别</td>
                        <td width="25%">商品名称</td>
                        <td width="10%">采购数量</td>
                        <td width="15%">单价</td>
                        <td width="15%">合计</td>
                        <td width="10%">操作</td>
                    </tr>
                    <tr align="center" bgcolor="#FFFFFF">
                        <td height="30">
                            <s:select cssClass="category" list="categoryList" listKey="id" listValue="name" cssStyle="width:200px"></s:select>
                        </td>
                        <td>
                            <s:select cssClass="product" list="productList" listKey="id" listValue="name" cssStyle="width:200px"></s:select>
                        </td>
                        <td>
                            <input name="quantity" class="quantity" style="width:67px;border:1px solid black;text-align:right;padding:2px" type="text" value="1"/>
                        </td>
                        <td>
                            <input name="price" class="price" style="width:93px;border:1px solid black;text-align:right;padding:2px" type="text" value="${productList[0].bidView}"/>&nbsp;元
                        </td>
                        <td class="subtotal" align="right">${productList[0].bidView}&nbsp;元</td>
                        <td>
                            <a class="deleteBtn delete edit" value="4"><img src="/image/icon_04.gif" />删除</a>
                        </td>
                    </tr>
                    <tr id="finalTr" align="center" style="background:url(/image/table_bg.gif) repeat-x;">
                        <td height="30" colspan="4" align="right">总&nbsp;计:&nbsp;</td>
                        <td class="total" width="16%" align="right">${productList[0].bidView}&nbsp;元</td>
                        <td>&nbsp;</td>
                    </tr>
                </table>
                <div class="order-bottom">
                    <div style="margin:1px auto auto 1px;">
                        <table width="100%" border="0" cellpadding="0" cellspacing="0">
                            <tr>
                                <td>
                                    <a href="javascript:void(0)" id="commit"><img src="/image/order_tuo.gif" border="0"/></a>
                                </td>
                                <td>&nbsp;</td>
                                <td><a href="#"><img src="/image/order_tuo.gif" border="0"/></a></td>
                                <td>&nbsp;</td>
                                <td><a href="#"><img src="/image/order_tuo.gif" border="0"/></a></td>
                            </tr>
                        </table>
                    </div>
                </div>
            </div><!--"square-order"end-->
        </s:form>
    </div><!--"content-text"end-->
    <div class="content-bbg"><img src="/image/content_bbg.jpg"/></div>
</div>
