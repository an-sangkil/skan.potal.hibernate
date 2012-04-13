<!--
	* fileName   : _anniversartDetailAjaxUp.jsp
	* createDate : 2011. 4. 5. 오후 6:01:42
	* CreateUser : skan
	* Document   : 주소록에서 상세 보기 (이름클릭) 을 한경우 Ajax.Update 로 "this" 페이지가 화면 타켓으로 들어간다.
	*
-->

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/include/taglib.inc"%>

				<div class="insert_table" style="float: left;width: 480px">
					<form name="_address_form" method="post" action="" >
						<div id="_addressDetail_div">
							<table width="100%">
								<tbody>
									<tr>
										<th>이름</th>
										<td> 
											<input id="user_name" name="user_name" value="${address.user_name }"/>
											<input type="hidden" id="ads_mgt_no" name="ads_mgt_no" value="${address.ads_mgt_no}"/>
										</td>
									</tr>
									<tr>
										<th>그룹</th>
										<td>
											<input id="group_code" name="group_code" alt="그룹코드" value="${address.group_code}"/>
											<input id="group_code_name" name="group_code_name" alt="그룹코드이름" value="${address.group_code_name}"/>
											<button type="button" onclick="searchAddressGroup_popup('00000','upperCodeCallback');">검색</button>
										</td>
									</tr>
									<tr>
										<th>직책코드</th>
										<td>
											<code:codelist codeGroup="00029" isSelectBox="Y" attribute="id='position_code' name='position_code'" selectedInfo="${address.position_code}"></code:codelist>	
										</td>
									</tr>
									<tr>
										<th>회사명</th>
										<td><input id="company_name" name="company_name" value="${address.company_name }"/></td>
									</tr>
									<tr>
										<th>집전화</th>
										<td><input id="phone" name="phone" value="${address.phone }"/></td>
									</tr>
									<tr>
										<th>휴대전화</th>
										<td><input id="cell_phone" name="cell_phone" value="${address.cell_phone }"/></td>
									</tr>
									<tr>
										<th>우편번호</th>
										<td><input id="postcode" name="postcode" value="${address.postcode }"/></td>
									</tr>
									<tr>
										<th>지역주소</th>
										<td> <input id="district_address" name="district_address" value="${address.district_address }"/></td>
									</tr>
									<tr>
										<th>상세주소</th>
										<td><input id="detail_address" name="detail_address" value="${address.detail_address }"/></td>
									</tr>
									<tr>
										<th>이메일</th>
										<td> <input id="email" name="email" value="${address.email }"/></td>
									</tr>
									<tr>
										<th>메모</th>
										<td><textarea rows="" cols="" id="memo" name="memo" alt="메모">${address.memo }</textarea></td>
									</tr>
								</tbody>
							</table>
							<button type="button" onclick="addSellAnniversary();">추가</button>
							<table id="_anniversary_table" width="100%">
								<thead>
									<tr>
										<th>기념일 종류</th>
										<th>기념일 내용</th>
										<th>기념일 날짜</th>
									</tr>
								</thead>
								<tbody id="_anniversary_table_tbody">
									<c:choose>
										<c:when test="${!empty address.anniversaryList}">
											<c:forEach var="items" items="${address.anniversaryList}" varStatus="status" >
												<tr>
													<td>
														<code:codelist codeGroup="00038" isSelectBox="Y" attribute="id='anver_type_code' name='anver_type_code'" selectedInfo="${items.anver_type_code}"></code:codelist><!-- 기념일 종류-->
														<!-- <input type="hidden" id="ads_mgt_no" name="ads_mgt_no" value="${items.ads_mgt_no}"/> -->
														<input type="hidden" id="anver_seq"  name="anver_seq"  value="${items.anver_seq}" alt="번호"/>
													</td>
													<td><input id="anver_content"   name="anver_content"   value="${items.anver_content}" alt="기념일 내용" /></td>
													<td>
														<input id="anver_date" name="anver_date" value="${items.anver_date}" alt="기념일날짜" onclick="new Calendar(this)" readonly="readonly"/>
														<button type="button" onclick="removeSellAnniversary(this);">삭제</button>
													</td>
												</tr>		
											</c:forEach>
										</c:when>
										<c:otherwise></c:otherwise>
									</c:choose>
								</tbody>
							</table>
							
							<div>
								<button type="button" onclick="_newSaveAddress();" id="_newSaveButton" style="display: none;">새연락처추가</button>
								<button type="button" onclick="saveAddress();"     id="_saveButton">저장</button>
							</div>

						</div>
					</form>
				</div>	
