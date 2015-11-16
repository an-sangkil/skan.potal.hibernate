package com.skan.potal.hibernate.test.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import cj.cjoext.common.dto.ExtAllthatTokenDto;

import com.skan.potal.hibernate.application.model.test.ExtAllthatErrorDto;
import com.skan.potal.hibernate.application.model.test.ExtAllthatMallDto;

@Controller
public class AllthatTestController {
	
	/**
	 * getMall ErrorDTO Test
	 * @param json
	 * @return
	 */
	@RequestMapping("/apis/malls.json")
	public @ResponseBody List<ExtAllthatMallDto>  getMalls(@RequestParam(required=false) String json) {
		
		List<ExtAllthatMallDto> extAllthatMallDtoList = new ArrayList<ExtAllthatMallDto>(); 
		ExtAllthatMallDto extAllthatMallDto = new ExtAllthatMallDto();
		for(int i=0 ; i < 10 ; i ++ ){
			extAllthatMallDto.setAlth_cag_id("가나다라마바사" + i);
			extAllthatMallDto.setAlth_cag_ccd("cagCCD");
			extAllthatMallDtoList.add(extAllthatMallDto);

		}
		
		return  extAllthatMallDtoList;
	}
	
	
	@RequestMapping("/apis/token.json")
	public @ResponseBody ExtAllthatTokenDto  getToken(@RequestParam(required=false) String partnerId) {
		ExtAllthatTokenDto  extAllthatTokenDto   = new ExtAllthatTokenDto  ();
		extAllthatTokenDto.setAccess_token("testToken");
		
		ExtAllthatErrorDto extAllthatErrorDto = new ExtAllthatErrorDto();
		extAllthatErrorDto.setCode("1000");
		extAllthatErrorDto.setMessage("error test");
		extAllthatTokenDto.setError(extAllthatErrorDto);
		
		return  extAllthatTokenDto;
	}
	
	
}
