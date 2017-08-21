<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%-- �U�νƦX�d��-�i�ѫȤ��select_page.jsp�H�N�W�����Q�d�ߪ���� --%>
<%-- �����u�@���ƦX�d�߮ɤ����G�m�ߡA�i���ݭn�A�W�[�����B�e�X�ק�B�R�����\��--%>

<jsp:useBean id="listMgr_ByCompositeQuery" scope="request" type="java.util.List" />
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html lang="zh-cn-en">
<head>
<title>�ƦX�d�� - listMgr_ByCompositeQuery</title>
</head>
<body>
	<div class="page-content">
		<div class="page-header">
			<h1>
				���u�b���޲z <small> <i class="icon-double-angle-right"></i>
					�ƦX�d�߭��u  - listMgr_ByCompositeQuery
				</small>
			</h1>
		</div>

	
		<div class="row">
			<div class="col-xs-12 col-sm-10 col-sm-offset-1">
				<div class="table-responsive">
					<table id="sample-table-1" class="table table-striped">
						<thead class="aaa">
							<tr>
								<th class="center">�޲z���s��</th>
								<th class="center">�Ӥ�</th>
								<th class="center">�b��</th>
								<th class="center">¾��</th>
								<th class="center">�m�W</th>
								<th class="center">�H�c</th>
								<th class="center">���A</th>
								<th class="center">�ק�</th>
							</tr>
						</thead>	
						
						<tbody>
					<%@ include file="pages/page1_ByCompositeQuery.file"%>
					<c:forEach var="managerVO" items="${listMgr_ByCompositeQuery}" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">
							<tr align='center' valign='middle' ${(managerVO.mgr_no==param.mgr_no) ? 'bgcolor=#CCCCFF':''}>
									<!--�N�ק諸���@���[�J����Ӥw-->
								<td>${managerVO.mgr_no}</td>
								<td><img src="<%=request.getContextPath()%>/tools/DBGifReader?mgr_no=${managerVO.mgr_no}"
										style="width: 100px; height: 100px;"></td>
								<td>${managerVO.mgr_id}</td>
									<%-- 			<td>${managerVO.mgr_pwd}</td> --%>
								<td>${(managerVO.mgr_job==0)?'�޲z��':'�t�κ޲z��'}</td>
								<td>${managerVO.mgr_name}</td>
								<td>${managerVO.mgr_email}</td>
								<td>${(managerVO.mgr_status==0)?'�b¾��':'��¾'}</td>
								<td>
										<!-- �e�L�h�������O��PART�B�z�A�ҥH�o��FORM�O�o��enctype='multipart/form-data' -->
									<FORM METHOD="post"	ACTION="<%=request.getContextPath()%>/mgr/MgrCtrl"
										enctype='multipart/form-data'>
										<input type="submit" value="�ק�" class="btn btn-inverse"> 
										<input type="hidden" name="mem_no" value="${managerVO.mem_no}"> 
										<input type="hidden" name="mgr_no" value="${managerVO.mgr_no}">
										<input type="hidden" name="action" value="getOne_For_Update">
									</FORM>
								</td>
								</tr>
							</c:forEach>
	<%@ include file="pages/page2_ByCompositeQuery.file"%>
							</tbody>
					</table>


	</body>
</html>