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
					<a href="${contextath}/admin/index">Home</a>
				</li>
				<li>
					<a href="${contextath}/admin/permission/list/1">权限列表</a>
				</li>
				<li class="active">权限查看</li>
			</ul><!-- .breadcrumb -->
			
			<div class="nav-search" id="nav-search">
				<form action="" method="post" onsubmit="return false;" class="form-search">
					<span class="input-icon">
						<input type="text" placeholder="Search ..." class="nav-search-input" id="nav-search-input" autocomplete="off" />
						<i class="icon-search nav-search-icon"></i>
					</span>
				</form>
			</div><!-- #nav-search -->
			
			<div class="page-content">
				<div class="row">
					<div class="col-xs-12">
						<!-- PAGE CONTENT BEGINS -->
						<div class="page-header">
							<h1>权限查看
							<small>
								<i class="icon-double-angle-right">
								权限详细资料
								</i>
							</small>
							</h1>
						</div>
						<div class="row">
							<div class="col-xs-12">
								<form action="" role="form" class="form-horizontal" method="post">
								<div class="form-group">
									<label class="col-sm-3 control-label no-padding-right" for="form-field-1"> 父菜单ID </label>
									<div class="col-sm-9">
										<label class="col-xs-10 col-sm-5" style="margin-top:3px;">${permission.parentId}</label>
									</div>
								</div>
								<div class="space-4"></div>
							
								<div class="form-group">
									<label class="col-sm-3 control-label no-padding-right" for="form-field-1"> 权限ID </label>
									<div class="col-sm-9">
										<label class="col-xs-10 col-sm-5" style="margin-top:3px;">${permission.id}</label>
									</div>
								</div>
								<div class="space-4"></div>
										
								<div class="form-group">
									<label class="col-sm-3 control-label no-padding-right" for="form-field-1"> 权限名称 </label>
									<div class="col-sm-9">
										<label class="col-xs-10 col-sm-5" style="margin-top:3px;">${permission.name}</label>
									</div>
								</div>
								<div class="space-4"></div>
											
								<div class="form-group">
									<label class="col-sm-3 control-label no-padding-right" for="form-field-1"> ClassName</label>
									<div class="col-sm-9">
										<label class="col-xs-10 col-sm-5" style="margin-top:3px;">${permission.className}</label>
									</div>
								</div>
								<div class="space-4"></div>

								<div class="form-group">
									<label class="col-sm-3 control-label no-padding-right" for="form-field-1"> 权限坐标</label>
									<div class="col-sm-9">
										<label class="col-xs-10 col-sm-5" style="margin-top:3px;">${permission.menuIndex}</label>
									</div>
								</div>
								<div class="space-4"></div>
												
								<div class="form-group">
									<label class="col-sm-3 control-label no-padding-right" for="form-field-1"> URL</label>
									<div class="col-sm-9">
										<label class="col-xs-10 col-sm-5" style="margin-top:3px;">${permission.url}</label>
									</div>
								</div>
								<div class="space-4"></div>
											
								<div class="form-group">
									<label class="col-sm-3 control-label no-padding-right" for="form-field-1"> 是否为菜单</label>
									<div class="col-sm-9">
									<label class="col-xs-10 col-sm-5" style="margin-top:3px;">	
										<c:if test="${permission.isMenu == 'Y'}">
											是
										</c:if>
										<c:if test="${permission.isMenu != 'Y'}">
											否
										</c:if>
									</label>
									</div>
								</div>
								<div class="space-4"></div>
								<div class="clearfix form-actions">
									<div class="col-md-offset-3 col-md-9">
										&nbsp; &nbsp; &nbsp;
										<button class="btn" type="reset" onclick="history.go(-1)">
											<i class="icon-undo bigger-110"></i>
											返回
										</button>
									</div>
								</div>
								</form>
							</div>
						</div>
						<!-- PAGE CONTENT ENDS -->
					</div><!-- /.col -->
				</div><!-- /.row -->
			</div><!-- /.page-content -->
			
			
		</div>
	
	</fms:Content>
</fms:ContentPage>