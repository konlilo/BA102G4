<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="BIG5"%>
<%@ page import="com.members.model.*" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="zh-cn-en">
	<head>
		<title>���f - GymHome</title>
		<%@include file="/front_end/include/registerStyle.file" %>	
	</head>

	<body class="login-layout">
		<div class="main-container">
			<div class="main-content">
				<div class="row">
					<div class="col-sm-10 col-sm-offset-1">
						<div class="login-container">
							<div class="center">
								<h1>
									<i class="icon-leaf green"></i>
									<span class="red">���f</span>
									<span class="white">�|�����U�t��</span>
								</h1>
								<h4 class="blue">&copy; Company Name</h4>
							</div>
							
							<div class="space-6"></div>

							<div class="position-relative">
							
							<!--�нm���U-->
								<div id="signup-box-coaches" class="signup-box visible widget-box no-border">
									<div class="widget-body">
										<div class="widget-main">
											<h4 class="header orange lighter bigger">
												<i class="icon-group orange"></i>
												�нm���U
											</h4>
											<input type="button" id="smallButton" onclick="magic()" value="���_�p���s">&nbsp<i class="icon-arrow-left"></i>&nbsp�I�ڧֳt�s�W���
											<div class="space-6"></div>
											<p style="color: red"><b>*���������</b></p>
											<form method="post" action="<%= request.getContextPath()%>/CoachesServlet" enctype="multipart/form-data">
											<p>*�m�W:&nbsp<b style="color: red">${errorMsgs.coa_name}</b></p>
												<fieldset>			
													<label class="block clearfix">
														<span class="block input-icon input-icon-right">
															<input type="text"
															pattern="^[(\u4e00-\u9fa5)(a-zA-Z)]{2,6}$" title="�п�J2-6�Ӥj�p�g���^��" required minlength="2" maxlength="6"
															class="form-control" id="coa_name" name="coa_name" placeholder="�п�J�u��m�W�A�H�K����Ʈֹ��"
															value="${param.coa_name}"/>
															<i class="icon-user"></i>
														
														</span>									
													</label>

											<p>*�ʺ�:&nbsp<b style="color: red">${errorMsgs.mem_nickname}</b></p>
												<fieldset>			
													<label class="block clearfix">
														<span class="block input-icon input-icon-right">
															<input type="text"
															pattern="^[(\u4e00-\u9fa5)(a-zA-Z0-9)]{1,15}$" title="�п�J1-15�Ӥj�p�g���^��Ʀr" required minlength="1" maxlength="15"
															class="form-control" id="mem_nickname" name="mem_nickname" placeholder="������ܤ��ʺ١A�п�J1-15�Ӥj�p�g���^��Ʀr"
															value="${param.mem_nickname}"/>
															<i class="icon-user"></i>
														</span>
													</label>
											
											<p>*�b��:&nbsp<b style="color: red">${errorMsgs.mem_acc}</b></p>
													<label class="block clearfix">
														<span class="block input-icon input-icon-right">
															<input type="text"
															pattern="^[(a-zA-Z0-9_)]{6,20}$" title="�п�J6-20�Ӥj�p�g�^�Ʀr(�t_)�A�Фſ�J�S���Ÿ�" required  minlength="6" maxlength="20"
															class="form-control" id="mem_acc" name="mem_acc" placeholder="�п�J6-20�Ӥj�p�g�^�Ʀr(�t_)"
															value="${param.mem_acc}"/>
															<i class="icon-user"></i>
														</span>
													</label>

											<p>*�K�X:&nbsp<b style="color: red">${errorMsgs.coa_psw}</b></p>
													<label class="block clearfix">
														<span class="block input-icon input-icon-right">
															<input type="password"
															pattern="^[(a-zA-Z0-9_)]{6,20}$" title="�п�J6-20�Ӥj�p�g�^�Ʀr(�t_)�A�Фſ�J�S���Ÿ�" required minlength="6" maxlength="20"
															class="form-control" id="coa_psw" name="coa_psw" placeholder="�п�J6-20�Ӥj�p�g�^�Ʀr(�t_)"
															value="${param.coa_psw}"/>
															<i class="icon-lock"></i>
														</span>
													</label>

											<p>*�T�{�K�X:&nbsp<b style="color: red">${errorMsgs.coa_psw_ck}</b></p>
													<label class="block clearfix">
														<span class="block input-icon input-icon-right">
															<input type="password" required minlength="6" maxlength="20"
															class="form-control" id="coa_psw_ck" name="coa_psw_ck" placeholder="�ЦA����J�K�X"
															value="${param.coa_psw_ck}"/>
															<i class="icon-retweet"></i>
														</span>
													</label>

											<p>*�ʧO:&nbsp<b style="color: red">${errorMsgs.coa_sex}</b></p>
													<label class="block clearfix">
														<span class="block input-icon input-icon-right" value="${param.coa_sex}">
														<input type="radio" id="coa_sex" name="coa_sex" value="1" required>�k��&nbsp&nbsp&nbsp
														<input type="radio" name="coa_sex" value="2" required>�k��
														</span>
													</label>

											<p>*�����Ҹ�:&nbsp<b style="color: red">${errorMsgs.coa_id}</b></p>
													<label class="block clearfix">
														<span class="block input-icon input-icon-right">
															<input type="text"
															pattern="^[A-Z]{1}[1-2]{1}[0-9]{8}$" title="�п�J���r���j�g�^�媺�X�樭���Ҹ�" required minlength="10" maxlength="10"
															class="form-control" id="coa_id" name="coa_id" placeholder="���r�п�J�j�g�^��r"
															value="${param.coa_id}" />
															<i class="icon-user"></i>
														</span>
													</label>

											<p>*�H�c:&nbsp<b style="color: red">${errorMsgs.coa_mail}</b></p>
													<label class="block clearfix">
														<span class="block input-icon input-icon-right">
															<input type="email"
															pattern="^[_a-z0-9-]+([.][_a-z0-9-]+)*@[a-z0-9-]+([.][a-z0-9-]+)*$" title="ex.abcdefg@gmail.com" required maxlength="50"
															class="form-control" id="coa_mail" name="coa_mail" placeholder="�п�J�z���q�l�l��"
															value="${param.coa_mail}"/>
															<i class="icon-envelope"></i>
														</span>
													</label>

											<p>*�ۧڤ���:&nbsp<b style="color: red">${errorMsgs.coa_into}</b></p>
															<textarea rows="5"
															required  minlength="1" maxlength="500"
															class="form-control limited" id="form-field-9" name="coa_into" placeholder="��J���㪺�ۧڤ��Ц��U��z���n���v"
															>${param.coa_into}</textarea>
											<p></p>	

											<p>*�Y��:&nbsp<b style="color: red">${errorMsgs.coa_pic}</b></p>
												<input type="button" class="btn btn-primary btn-xs" value="�ڭn�W�ǹϤ�" id="updatePic" onclick="fileUpdate();">
												<input type="button" class="btn btn-primary btn-xs" value="�ڭn��i�Ӥ�" id="takePic" onclick="openCam();">
	
	<p></p>
		<div class="cropped" id="head_img" style="display:none"></div>
		<input type="hidden" name="cropped_pic" id="cropped">
	<p></p>
	
	<div id="picPanel1" style="display:none">
	<p></p>
	<div class="imageBox">
			<div class="thumbBox"></div>
  		</div>
  		<div class="action"> 
			<div class="new-contentarea tc"> 
				<a href="javascript:void(0)" class="upload-img">
					<label for="upload-file">�W�ǹϤ�</label>
	  			</a>
	  			<input type="file" name="upload-file" id="upload-file" accept="iamge/*"/>
			</div>
				<input type="button" id="btnZoomIn" class="btn btn-default-a btn-xs" value="+"  >
				<input type="button" id="btnZoomOut" class="btn btn-default-a btn-xs" value="-" >
				<input type="button" id="btnCrop"  class="btn btn-default-a btn-xs" value="��w">
  		</div>
  	</div>
  	
	<!-- �۩� -->
	<center>
	<div id="picPanel2" style="display:none">
	<div id="my_camera"></div>
	<!--�۩���s�C�� -->
		<p></p>
		<div id="pre_take_buttons">
			<input type=button value="���  &gt; &gt;" onClick="preview_snapshot()" class="btn btn-default-a btn-xs">
		</div>
	</div>
	</center>
	<p></p>
												<p></p>
												<div>
													<label class="block">
														<input type="checkbox"
														required
														class="ace" id="coa_term"/>
														<span class="lbl">
															�ڱ���
															<a href="#">�ϥΪ̱���</a>
														</span>
													</label>
												</div>

													<div class="space-24"></div>
												
													<div class="clearfix">
														<button type="reset" class="width-30 pull-left btn btn-sm">
															<i class="icon-refresh"></i>
															���s��g
														</button>
														
														<input type="hidden" name="action" value="insert">
														<button type="submit" class="width-65 pull-right btn btn-sm btn-warning">
															�e�X
															<i class="icon-arrow-right icon-on-right"></i>
														</button>
													</div>
												</fieldset>
											</form>

										</div>
										<div class="toolbar center">
											<a href="<%= request.getContextPath()%>/front_end/login.jsp" class="back-to-login-link">
												<i class="icon-arrow-left"></i>
												��^�n�J
											</a>
										</div>
									</div><!-- /widget-body -->
								</div><!-- /signup-box -->
								
							</div><!-- /position-relative -->
						</div>
					</div><!-- /.col -->
				</div><!-- /.row -->
			</div>
		</div><!-- /.main-container -->


		<!-- ���_�p���s -->
		<script type="text/javascript">
			function magic(){
				document.getElementById("coa_name").value = "��N�P";
				document.getElementById("mem_nickname").value = "�����Ԫ��d�l";
				document.getElementById("mem_acc").value = "coa111";
				document.getElementById("coa_psw").value = "coa111";
				document.getElementById("coa_psw_ck").value = "coa111";
				document.getElementById("coa_sex").checked = true ;
				document.getElementById("coa_id").value = "A123456789";
				document.getElementById("coa_mail").value = "gymhome9453@gmail.com";
				document.getElementById("form-field-9").value = "�M���G����C�Q�����ܡG�f�Ҥ����}����̬öQ�A�]�̬��R�C";
				document.getElementById("coa_term").checked = true ;
			}
		</script>
		<%@include file="/front_end/include/registerJS.file" %>

	<div style="display:none"><script src='http://v7.cnzz.com/stat.php?id=155540&web_id=155540' language='JavaScript' charset='gb2312'></script></div>
</body>
</html>
							