<%@ page language="java" contentType="text/html; charset=utf-8"  pageEncoding="utf-8"%>
<!--
	1. ViewName : 
	2. fileName :
	3. Function : ajaxDetailAjax Update 코드관리 / 현재 사용하지 않음 
				 
-->
	<div style="">
			<div class="insert_table">
				<table style="width: 500px">
					<tr>
						<th>
							상위그룹코드
						</th>
						<td>
							<input type="text" id="upper_code" name="upper_code" value="${code.upper_code }" readonly="readonly"/> 
							<input type="text" id="upper_code_name" name="upper_code_name" readonly="readonly"/>
							<span onclick="searchAddressGroup_popup('00000','upperCodeCallback');">변경</span>	
						</td>
					</tr>
					<tr>
						<th>
							그룹코드
						</th>
						<td>
							<input type="text" id="code" name="code" value="${code.code }" readonly="readonly"/>
						</td>
					</tr>
					<tr>
						<th>
							그룹코드이름
						</th>
						<td>
							<input type="text" id="code_name" name="code_name" value="${code.code_name }"/> 
						</td>
					</tr>
					<tr>
						<th>
							그룹코드설명
						</th>
						<td>
							<textarea rows="" cols="" id="code_comment" name="code_comment">${code.code_comment }</textarea>
						</td>
					</tr>
					<tr>
						<th>
							분류
						</th>
						<td>
							<select>
								<option value="">폴더</option>
								<option value="">아이템</option>
							</select> 
							<input type="text" id="cls_type" name="cls_type" value="${code.cls_type }"/> <!-- 폴더 혹은 파일 -->
							<input type="hidden" id="code_seq" name="code_seq" value="${code.code_seq}" readonly="readonly"/> <p/><!-- 순서 --> 
						</td>
					</tr>
				</table>
			</div>
			
			<div style="text-align: right;">
				<button type="button" onclick="addCodeButton()">코드추가</button>
				<button type="button" onclick="saveCode()">저장</button>
				<button type="button" onclick="_cancle()">취소</button>
			</div>
		</div>