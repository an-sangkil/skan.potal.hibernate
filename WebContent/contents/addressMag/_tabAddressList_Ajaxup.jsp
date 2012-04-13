<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/include/taglib.inc"%>
<!--
	* fileName   : _tabAddressList_Ajax.jsp
	* createDate : 2011. 4. 5. 오후 6:01:42
	* CreateUser : skan
	* Document   : 주소록에서 텝이동할때 List 페이지임
	*
-->
				
				<div class="insert_table" style="float: left;width: 300px; height: 700px ;">
					<div style="width:100%; margin-right: 17px">
						<table style="width: 100%;">
							<tr>
								<td colspan="4"><input type="text" id="_user_name" name="_user_name" value="${user_name}"/>
												<button type="button" onclick="_searchAddress()">검색</button></td>
							</tr>
							<tr>
								<th width="33%">이름</th>
								<th width="33%">직책</th>
								<th width="33%" style="padding-right: 17px ;">전화번호</th>
							</tr>
						</table>
					</div>
					
					<button type="button" onclick="addSellAnniversary();">추가</button>
					
					<table width="100%">
						<tr>
							<td colspan="3">
								<div style="overflow-x:hidden;overflow-y: scroll;height: 300px ">		
									<table width="100%">
										<c:choose>
											<c:when test="${!empty addressList}">
												<c:forEach var="items" items="${addressList}" varStatus="status" >
													<tr onclick="detailAddress('${items.ads_mgt_no}' , null)" style="cursor: pointer;" onmouseover="onmouse('over')" onmouseout="onmouse()" onmousedown="onmouse('down')">
														<td width="33%">${items.user_name}</td>
														<td width="33%">${items.position_name}</td>
														<td width="33%">${items.phone}(휴대전화 : ${items.cell_phone})</td>
													</tr>		
												</c:forEach>
												<!-- 
												<c:forEach varStatus="status" begin="1" end="1000" >
													<tr>
														<td width="33%">${status.count}</td>
														<td width="33%">${status.index}</td>
														<td width="33%"></td>
													</tr>		
												</c:forEach>
												 -->
											</c:when>
											<c:otherwise>
											</c:otherwise>
										</c:choose>
									</table>
								</div> 
							</td>
						</tr>
					</table>
					
				</div>