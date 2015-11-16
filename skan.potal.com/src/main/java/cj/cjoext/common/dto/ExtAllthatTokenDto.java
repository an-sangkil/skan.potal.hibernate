package cj.cjoext.common.dto;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;

import com.skan.potal.hibernate.application.model.test.ExtAllthatCommonDto;


/**
 *  
 * @author Kang
 *
 * CJ오쇼핑 -> 올댓 외부 API 전문 전송용 외부클래스
 */
@JsonIgnoreProperties(ignoreUnknown=true)
public class ExtAllthatTokenDto extends ExtAllthatCommonDto{
	
	private String access_token;	// 이후의 API 호출에 사용할 보안 토큰 문자열
	
	
	
	public String getAccess_token() {
		return access_token;
	}
	
	public void setAccess_token(String access_token) {
		this.access_token = access_token;
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
	}
}
