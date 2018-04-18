<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib uri="/master-tag" prefix="fms" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="/date-tag" prefix="date" %>
<fms:ContentPage masterPageId="master">
	<fms:Content contentPlaceHolderId="title">
		Ace Admin
	</fms:Content>
		
	<fms:Content contentPlaceHolderId="source">
		<!-- 按需加载模块 -->
	</fms:Content>
	
	<fms:Content contentPlaceHolderId="main">
		<div class="breadcrumbs" id="breadcrumbs">
			<script type="text/javascript">
				try{ace.settings.check('breadcrumbs' , 'fixed')}catch(e){}
			</script>

			<ul class="breadcrumb">
				<li>
					<i class="icon-home home-icon"></i>
					<a href="${contextPath}/admin/index">Home</a>
				</li>
				<li>
					<a href="${contextPath}/admin/user/list/1">用户管理</a>
				</li>
				<li class="active">用户查看</li>
			</ul><!-- .breadcrumb -->

			<div class="nav-search" id="nav-search">
				<form class="form-search">
					<span class="input-icon">
						<input type="text" placeholder="Search ..." class="nav-search-input" id="nav-search-input" autocomplete="off" />
						<i class="icon-search nav-search-icon"></i>
					</span>
				</form>
			</div><!-- #nav-search -->
		</div>

		<div class="page-content">
			<div class="row">
				<div class="col-xs-12">
					<!-- PAGE CONTENT BEGINS -->
					<div class="page-header">
						<h1>用户查看
						<small>
							<i class="icon-double-angle-right">
							用户详细资料
							</i>
						</small>
						</h1>
					</div>
					<div class="row">
						<div class="col-xs-12">
							<div class="form-group">
								<label class="col-sm-3 control-label no-padding-right"> 所属组 </label>
								<div class="col-sm-9" style="padding:5px;">
									${us.groupName}
								</div>
							</div>&nbsp;
							<div class="space-4"></div>
							
							<div class="form-group">
								<label class="col-sm-3 control-label no-padding-right"> 用户账号 </label>
								<div class="col-sm-9" style="padding:5px;">
									${us.loginName}
								</div>
							</div>&nbsp;
							<div class="space-4"></div>
							
							<div class="form-group">
								<label class="col-sm-3 control-label no-padding-right"> 用户姓名 </label>
								<div class="col-sm-9" style="padding:5px;">
									${us.userName}
								</div>
							</div>&nbsp;
							<div class="space-4"></div>
					
							<div class="clearfix form-actions">
								<div class="col-md-offset-3 col-md-9">
									<button class="btn btn-info" type="button" onclick="go_url('${contextPath}/admin/user/perlist/${us.id}')">
										<i class="icon-ok bigger-110"></i>
										修改权限
									</button>
									&nbsp;&nbsp;&nbsp;
									<button class="btn" type="reset" onclick="history.go(-1)">
										<i class="icon-undo bigger-110"></i>
										返回
									</button>
								</div>
							</div>
								
						</div>
					</div>
					<!-- PAGE CONTENT ENDS -->
				</div><!-- /.col -->
			</div><!-- /.row -->
		</div><!-- /.page-content -->
	
	</fms:Content>
</fms:ContentPage>