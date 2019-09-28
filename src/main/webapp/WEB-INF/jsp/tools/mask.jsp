<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false" %>
<%@ taglib prefix="s" uri="/struts-tags"%>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/index.css" type="text/css">
<style type="text/css">
#pageOverlay {
	visibility: hidden;
	position: fixed;
	top: 0;
	left: 0;
	z-index: 1987;
	width: 100%;
	height: 100%;
	background: #ccc;
	filter: alpha(opacity = 70);
	opacity: 0.7;
}

* html body {
	margin: 0;
	height: 100%;
}

* html #pageOverlay {
	position: absolute;
	left: expression(documentElement.scrollLeft + documentElement.clientWidth - this.offsetWidth);
	top: expression(documentElement.scrollTop + documentElement.clientHeight - this.offsetHeight);
}
.content-size {
	width: 300px;
}
.content-size-height {
	height: 180px;
}
.content-top{
	background:url("/image/content_top.jpg") no-repeat scroll 0 0 rgba(0, 0, 0, 0);
}
.content-body{
	width:298px;
}
.content-bottom{
	background:url("/image/content_bottom.jpg") no-repeat scroll 0 0 rgba(0, 0, 0, 0);
}

.context {
	width: 300px;
	height: 200px;
	position: absolute;
	left: 40%;
	top: 30%;
	z-index: 2000;
	display: none;
}
.context-txt {
	width: 262px;
	height: 122px;
	margin-left: 18px;
	margin-right: 18px;
	margin-top: 15px;
	font-size: 18px;
	font-family: '黑体';
	color: #202677;
}
.context-btn {
	width: 262px;
	height: 40px;
	margin-left: 18px;
	margin-right: 18px;
}
</style>
<script type="text/javascript">
    // 获取对象
    var $ = function (id) {
        return document.getElementById(id);
    };
    // 遍历
    var each = function(a, b) {
        for (var i = 0, len = a.length; i < len; i++) {
            b(a[i], i);
        }
    };
    // 事件绑定
    var bind = function (obj, type, fn) {
        if (obj.attachEvent) {
            obj['e' + type + fn] = fn;
            obj[type + fn] = function() {
                obj['e' + type + fn](window.event);
            };
            obj.attachEvent('on' + type, obj[type + fn]);
        } else {
            obj.addEventListener(type, fn, false);
        }
    };
    // 移除事件
    var unbind = function (obj, type, fn) {
        if (obj.detachEvent) {
            try {
                obj.detachEvent('on' + type, obj[type + fn]);
                obj[type + fn] = null;
            } catch(_) {

            }
        } else {
            obj.removeEventListener(type, fn, false);
        }
    };

    // 阻止浏览器默认行为
    var stopDefault = function(e){
        e.preventDefault ? e.preventDefault() : e.returnValue = false;
    };
    // 获取页面滚动条位置
    var getPage = function(){
        var dd = document.documentElement,
            db = document.body;
        return {
            left: Math.max(dd.scrollLeft, db.scrollLeft),
            top: Math.max(dd.scrollTop, db.scrollTop)
        };
    };

    // 锁屏
    var lock = {
        show: function() {
            $('pageOverlay').style.visibility = 'visible';
            var p = getPage(),
                left = p.left,
                top = p.top;

            // 页面鼠标操作限制
            this.mouse = function(evt){
                var e = evt || window.event;
                stopDefault(e);
                scroll(left, top);
            };
            each(['DOMMouseScroll', 'mousewheel', 'scroll', 'contextmenu'], function(o, i) {
                bind(document, o, lock.mouse);
            });
            // 屏蔽特定按键: F5, Ctrl + R, Ctrl + A, Tab, Up, Down
            this.key = function(evt){
                var e = evt || window.event,
                    key = e.keyCode;
                if((key == 116) || (e.ctrlKey && key == 82) || (e.ctrlKey && key == 65) || (key == 9) || (key == 38) || (key == 40)) {
                    try{
                        e.keyCode = 0;
                    }catch(_) {

                    }
                    stopDefault(e);
                }
            };
            bind(document, 'keydown', lock.key);
        },
        close: function(){
            $('pageOverlay').style.visibility = 'hidden';
            each(['DOMMouseScroll', 'mousewheel', 'scroll', 'contextmenu'], function(o, i) {
                unbind(document, o, lock.mouse);
            });
            unbind(document, 'keydown', lock.key);
        }
    };
    bind(window, 'load', function(){

        $('btn_ack').onclick = function(){
            //显示遮罩的方法调用
            lock.close();
            $('frame-content').src = $('hide-action').value;
            $('context').style.display="none";
        };
        $('btn_cancel').onclick = function(){
            //删除遮罩的方法调用
            lock.close();
            $('context').style.display="none";
        };
    });
</script>
<div id="pageOverlay"></div>
<div id="context" class="context">
	<div class="content-right-pic content-size content-top">
	</div>
	<div class="content-text content-size content-size-height content-body">
		<div id="context-text" class="context-txt">
			临时消息文字内容，测试专用，看看宽度，测试专用
		</div>
		<input id="hide-action" type="hidden" value=""/>
		<div class="context-btn">
			<center>
			<input id="btn_ack" type="image" src="/image/content_btn_ack.jpg"/>
			<input id="btn_cancel" type="image" src="/image/content_btn_cancel.jpg"/>
			</center>
		</div>
	</div>
	<div class="content-bbg content-size content-bottom"></div>
</div>
