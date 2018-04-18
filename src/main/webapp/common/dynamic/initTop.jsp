<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="date" uri="/date-tag" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"  %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>


<div class="navbar-container" id="navbar-container">
	<div class="navbar-header pull-left">
		<a href="javascript:void(0)" class="navbar-brand">
		<small><i class="icon-leaf"></i>Ace Admin</small>
		</a><!-- /.brand -->
	</div><!-- /.navbar-header -->
	
<div class="navbar-header pull-right" role="navigation">
	<ul class="nav ace-nav">
	<!--
	<li class="grey">
		<a data-toggle="dropdown" class="dropdown-toggle" href="#">
			<i class="icon-tasks"></i>
			<span class="badge badge-grey">4</span>
		</a>

		<ul class="pull-right dropdown-navbar dropdown-menu dropdown-caret dropdown-close">
			<li class="dropdown-header">
				<i class="icon-ok"></i>
				4 Tasks to complete
			</li>

			<li>
				<a href="#">
					<div class="clearfix">
						<span class="pull-left">Software Update</span>
						<span class="pull-right">65%</span>
					</div>

					<div class="progress progress-mini ">
						<div style="width:65%" class="progress-bar "></div>
					</div>
				</a>
			</li>

			<li>
				<a href="#">
					<div class="clearfix">
						<span class="pull-left">Hardware Upgrade</span>
						<span class="pull-right">35%</span>
					</div>

					<div class="progress progress-mini ">
						<div style="width:35%" class="progress-bar progress-bar-danger"></div>
					</div>
				</a>
			</li>

			<li>
				<a href="#">
					<div class="clearfix">
						<span class="pull-left">Unit Testing</span>
						<span class="pull-right">15%</span>
					</div>

					<div class="progress progress-mini ">
						<div style="width:15%" class="progress-bar progress-bar-warning"></div>
					</div>
				</a>
			</li>

			<li>
				<a href="#">
					<div class="clearfix">
						<span class="pull-left">Bug Fixes</span>
						<span class="pull-right">90%</span>
					</div>

					<div class="progress progress-mini progress-striped active">
						<div style="width:90%" class="progress-bar progress-bar-success"></div>
					</div>
				</a>
			</li>

			<li>
				<a href="#">
					See tasks with details
					<i class="icon-arrow-right"></i>
				</a>
			</li>
		</ul>
	</li>


	<li class="purple">
		<a data-toggle="dropdown" class="dropdown-toggle" href="#">
			<i class="icon-bell-alt icon-animated-bell"></i>
			<span class="badge badge-important">8</span>
		</a>

		<ul class="pull-right dropdown-navbar navbar-pink dropdown-menu dropdown-caret dropdown-close">
			<li class="dropdown-header">
				<i class="icon-warning-sign"></i>
				8 Notifications
			</li>

			<li>
				<a href="#">
					<div class="clearfix">
						<span class="pull-left">
							<i class="btn btn-xs no-hover btn-pink icon-comment"></i>
							New Comments
						</span>
						<span class="pull-right badge badge-info">+12</span>
					</div>
				</a>
			</li>

			<li>
				<a href="#">
					<i class="btn btn-xs btn-primary icon-user"></i>
					Bob just signed up as an editor ...
				</a>
			</li>

			<li>
				<a href="#">
					<div class="clearfix">
						<span class="pull-left">
							<i class="btn btn-xs no-hover btn-success icon-shopping-cart"></i>
							New Orders
						</span>
						<span class="pull-right badge badge-success">+8</span>
					</div>
				</a>
			</li>

			<li>
				<a href="#">
					<div class="clearfix">
						<span class="pull-left">
							<i class="btn btn-xs no-hover btn-info icon-twitter"></i>
							Followers
						</span>
						<span class="pull-right badge badge-info">+11</span>
					</div>
				</a>
			</li>

			<li>
				<a href="#">
					See all notifications
					<i class="icon-arrow-right"></i>
				</a>
			</li>
		</ul>
	</li>
	-->

	<li class="light-blue">
		<a data-toggle="dropdown" href="#" class="dropdown-toggle">
			<img class="nav-user-photo" src="${contextPath}/<c:if test="${user.portrait == ''}" >assets/avatars/user.jpg</c:if><c:if test="${user.portrait != ''}" >${user.portrait}</c:if>" alt="" />
			<span class="user-info">
				<small>Welcome,</small>
				${user.userName}
			</span>
			<i class="icon-caret-down"></i>
		</a>
		<ul class="user-menu pull-right dropdown-menu dropdown-yellow dropdown-caret dropdown-close">
			<li>
				<a  href="${contextPath}/admin/user/uptuser"  >
					<i class="icon-cog"></i>
					Settings
				</a>
			</li>
			<li class="divider"></li>
			<li>
				<a href="javascript:logout()">
					<i class="icon-off"></i>
					Logout
				</a>
			</li>
		</ul>
	</li>
</ul><!-- /.ace-nav -->
</div><!-- /.navbar-header -->
</div><!-- /.container -->
<div id="help-hide" style="display: none;"></div>
			
<script type="text/javascript">
	$(document).ready(function(){
		//init();
		/*$("#help-hide").load("help/project-domain.html",function(){
			var html = $("#help-hide").html();
			$('#gritter-regular').on(ace.click_event, function(){
		$.gritter.add({
			title: 'Chinese-English Help',
			text: html,
			image: 'assets/avatars/avatar1.png',
			sticky: false,
			time: '',
			class_name: ('')
			});
			return false;
		});
		});*/
	});
        		
	/* 
	* 消息监听初始化 
	**/
	function init(){
		var logsize_span = document.getElementById('logsize_span');
		var temp_message = document.getElementById('temp_message');
		JS.Engine.stop();
		JS.Engine.on({
			msg_time : function(data){//侦听一个channel
			var res = eval("("+data+")");
			var size = res.count == 0 ? "" : ""+res.count;
			logsize_span.innerHTML = size;
							
			var top = "<li class='dropdown-header'><i class='icon-envelope-alt'></i>"+res.count+" Messages</li>";
			var bottom = "<li><a href='javascript:void(0)'>See all messages<i class='icon-arrow-right'></i></a></li>";
							
			temp_message.innerHTML = top + getlilist(res.logs) + bottom;
		},
			test2 : function(bb){
				//kbDom2.innerHTML = bb;
			}
		});
		JS.Engine.start('comet'); 
	}

	function logout(){
		//JS.Engine.stop();
		window.location.href = "${contextPath}/admin/logout";
	}
</script>