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
				<li class="active">用户修改</li>
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
						<h1>用户修改
						<small>
							<i class="icon-double-angle-right">
							请输入用户详细资料
							</i>
						</small>
						</h1>
					</div>
					<div class="row">
						<div class="col-xs-12">
							<form action="${contextPath}/admin/user/update" role="form" class="form-horizontal" method="post" id="form_post" >
								<div class="form-group">
									<label class="col-sm-3 control-label no-padding-right" for="form-field-1"> 请选择部门 </label>
									<div class="col-sm-9">
										<input type="hidden" name="id" value="${us.id}" />
										<select name="deptId">
											<c:forEach items="${groups}" var="g">
												<c:if test="${us.groupId == g.id}">
													<option value="${g.id}" selected="selected">${g.name}</option>
												</c:if>
												<c:if test="${us.groupId != g.id}">
													<option value="${g.id}">${g.name}</option>
												</c:if>
												
											</c:forEach>
										</select>
									</div>
								</div>
								<div class="space-4"></div>
							
								<div class="form-group">
									<label class="col-sm-3 control-label no-padding-right" for="form-field-1"> 请输入用户账号 </label>
									<div class="col-sm-9">
										<input type="text" id="form-field-1" placeholder="用户账号" class="col-xs-10 col-sm-5" readonly="readonly" name="loginName" value="${us.loginName}" />
									</div>
								</div>
								<div class="space-4"></div>
							
								<div class="form-group">
									<label class="col-sm-3 control-label no-padding-right" for="form-field-1"> 请输入用户姓名 </label>
									<div class="col-sm-9">
										<input type="text" id="form-field-1" placeholder="用户姓名 " class="col-xs-10 col-sm-5" name="userName" value="${us.userName}" />
									</div>
								</div>
								<div class="space-4"></div>
								
								<div class="form-group">
									<label class="col-sm-3 control-label no-padding-right" for="form-field-1">密码初始化</label>
									<div class="col-sm-9">
										<button class="btn btn-purple btn-sm" type="button" onclick="init_pass()">
											提交请求
										</button>
									</div>
								</div>
								<div class="space-4"></div>
					
								<div class="clearfix form-actions">
									<div class="col-md-offset-3 col-md-9">
										<button class="btn btn-info" type="button" onclick="form_submit('form_post')">
											<i class="icon-ok bigger-110"></i>
											提交
										</button>
										&nbsp; &nbsp; &nbsp;
										<button class="btn" type="reset" onclick="go_back()">
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
		<script type="text/javascript">
			function init_pass(){
				var id = '${us.id}';
				bootbox.confirm("确认初始化密码?",function(result){
					if(result){
						$.ajax({
							url:'${contextPath}/admin/user/initpass',
							type:"POST",
							data:{id:id},
							dataType:'json',
							success:function(data){
								if(data.code == 0){
									ace_msg.success(data.msg);
								}else{
									ace_msg.danger(data.msg);
								}
							},
							error:function(data){
								//console.log(data);
							}
						});
					}
				});
			}
		
			function form_submit(id){
				bootbox.confirm("确认修改?",function(result){
					if(result){
						var form = $("#"+id);
						$.ajax({
							url:form.attr('action'),
							type:"POST",
							data:form.serialize(),
							dataType:'json',
							success:function(data){
								if(data.code == 0){
									ace_msg.success(data.msg);
								}else{
									ace_msg.danger(data.msg);
								}
							},
							error:function(data){
								//console.log(data);
							}
						});
					}
				});
			}
		</script>
	
	</fms:Content>
</fms:ContentPage>