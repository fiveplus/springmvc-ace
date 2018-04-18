<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib uri="/master-tag" prefix="fms" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="/date-tag" prefix="date" %>
<fms:ContentPage masterPageId="master">
	<fms:Content contentPlaceHolderId="title">
		Customer Relationship Management
	</fms:Content>
	<fms:Content contentPlaceHolderId="main">
		<div class="breadcrumbs" id="breadcrumbs">
			<script type="text/javascript">
				try{ace.settings.check('breadcrumbs' , 'fixed')}catch(e){}
			</script>

			<ul class="breadcrumb">
				<li>
					<i class="icon-home home-icon"></i>
					<a href="../system/index.htm">Home</a>
				</li>

				<li class="active">Error 404</li>
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

					<div class="error-container">
						<div class="well">
							<h1 class="grey lighter smaller">
								<span class="blue bigger-125">
									<i class="icon-sitemap"></i>
									404
								</span>
								Page Not Found
							</h1>

							<hr />
							<h3 class="lighter smaller">We looked everywhere but we couldn't find it!</h3>

							<div>
								<form class="form-search">
									<span class="input-icon align-middle">
										<i class="icon-search"></i>

										<input type="text" class="search-query" placeholder="Give it a search..." />
									</span>
									<button class="btn btn-sm" onclick="return false;">Go!</button>
								</form>

								<div class="space"></div>
								<h4 class="smaller">Try one of the following:</h4>

								<ul class="list-unstyled spaced inline bigger-110 margin-15">
									<li>
										<i class="icon-hand-right blue"></i>
										Re-check the url for typos
									</li>

									<li>
										<i class="icon-hand-right blue"></i>
										Read the faq
									</li>

									<li>
										<i class="icon-hand-right blue"></i>
										Tell us about it
									</li>
								</ul>
							</div>

							<hr />
							<div class="space"></div>

							<div class="center">
								<a href="javascript:go_back()" class="btn btn-grey">
									<i class="icon-arrow-left"></i>
									Go Back
								</a>

								<a href="../system/index.htm" class="btn btn-primary">
									<i class="icon-dashboard"></i>
									Home
								</a>
							</div>
						</div>
					</div><!-- PAGE CONTENT ENDS -->
				</div><!-- /.col -->
			</div><!-- /.row -->
		</div><!-- /.page-content -->
	</fms:Content>
</fms:ContentPage>
		
				