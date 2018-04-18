<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="date" uri="/date-tag" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<div class="breadcrumbs" id="breadcrumbs">
	<script type="text/javascript">
		try{ace.settings.check('breadcrumbs' , 'fixed')}catch(e){}
	</script>

	<ul class="breadcrumb">
		<li class="active">
			<i class="icon-home home-icon"></i>
			Home
			<!-- <a href="javascript:void(0)">Home</a> -->
		</li>
		<!--
		<li>
			<a href="#">Other Pages</a>
		</li>
		<li class="active">Blank Page</li> -->
	</ul><!-- .breadcrumb -->

	<div class="nav-search" id="nav-search">
		<form class="form-search" action="" method="post" onsubmit="return false;" >
			<span class="input-icon">
				<input type="text" placeholder="Search ..." class="nav-search-input" id="nav-search-input" autocomplete="off" />
				<i class="icon-search nav-search-icon"></i>
			</span>
		</form>
	</div><!-- #nav-search -->
</div>

<div class="page-content">
	<!-- PAGE CONTENT BEGINS -->

	<!-- PAGE CONTENT ENDS -->
</div><!-- /.page-content -->
					
<script type="text/javascript">
	$(function(){
	/**
	 *利用自定义组件构造左侧说明文本。
	 */

	//	chart.plugin(new iChart.Custom({
	//			drawFn:function(){
	//				/**
	//				 *计算位置
	//				 */
	//				var y = chart.get('originy');
	//				/**
	//				 *在左侧的位置，设置竖排模式渲染文字。
	//				 */
	//				chart.target.textAlign('center')
	//				.textBaseline('middle')
	//				.textFont('600 24px 微软雅黑')
	//				.fillText('攻城师需要掌握的核心技能',100,y,false,'#6d869f', 'tb',26,false,0,'middle');
	//
	//			}
	//	}));



	});
</script>
