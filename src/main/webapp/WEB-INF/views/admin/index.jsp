<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="/master-tag" prefix="fms" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="/date-tag" prefix="date" %>
<fms:ContentPage masterPageId="master">
	<fms:Content contentPlaceHolderId="title">
		Ace Admin
	</fms:Content>
		
	<fms:Content contentPlaceHolderId="source">
		<!-- 消息统计框架 -->
		<!-- <script src="../js/ichart.1.2.min.js"></script>  -->
		<script src="${contextPath}/js/echarts.min.js"></script>	
	</fms:Content>
	
	<fms:Content contentPlaceHolderId="main">
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
		</div>
		<script type="text/javascript">
		$(document).ready(function(){

		});
		</script>
	</fms:Content>
</fms:ContentPage>