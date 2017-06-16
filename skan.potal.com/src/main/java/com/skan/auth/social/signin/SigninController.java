package com.skan.auth.social.signin;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.skan.security.hash.salt.DigestUtils;
import com.skan.security.semmetric.CryptoStringUtils;
import com.skan.tms.mobile.web.service.ExecutorSendMailService;
import com.skan.tms.mobile.web.service.ExecutorSendMailService.ThreadSendEnum;
import com.skan.tms.web.jpa.dto.QUserDto;
import com.skan.tms.web.jpa.dto.UserDto;
import com.skan.tms.web.jpa.repository.UserJpaRepository;

@Controller
public class SigninController {
	
	@Value("${custom.server.domain}")
	private String hostName;
	
	@Autowired
	private UserJpaRepository userJpaRepository;
	
	@Autowired private ExecutorSendMailService emailSendService;
	/**
	 * 회원가입 스템 ONE
	 * 	회원 가입 폼으로 이동.
	 * 		 소셜 로그인 가입을 하지 않고 일반  회원가입으로 들어온 사용자. 
	 * @param request
	 * @param modelMap
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/sign/signupForm")
	public String signUpForm(HttpServletRequest request , ModelMap modelMap) throws Exception {
		
		
		return "/signup/signupForm";
	}
	
	/**
	 * 회원가입 스텝 TWO
	 * 		수동 회원 가입
	 * @param userDto
	 * @param bindingResult
	 * @param modelMap
	 * @return
	 * @throws Exception
	 */
	@Transactional(transactionManager="transactionManager")
	@RequestMapping("/sign/manual/signup")
	public String signUp(@Valid UserDto userDto, BindingResult bindingResult, ModelMap modelMap, RedirectAttributes redirectAttributes) throws Exception{
		
		//////////////////////////////////////////
		// 회원가입 validation 처리  
		//////////////////////////////////////////
		{
			if(bindingResult.hasErrors()){
				return "/signup/signupForm";
			}
			
			if (!userDto.getPassword().equals(userDto.getConfirmPassword())) {
				// 
				bindingResult.rejectValue("confirmPassword", "signup.password.notmatch","패스워드가 올바르지 않습니다.");
				return "/signup/signupForm";
				
			}
		}
		
		
		if(userJpaRepository.count(QUserDto.userDto.email.eq(userDto.getEmail())) > 0){
			bindingResult.rejectValue("email", "signup.email.duplication","이미 등록된 이메일 입니다.");
			return "/signup/signupForm";
		}
		if(userJpaRepository.count(QUserDto.userDto.userId.eq(userDto.getUserId())) > 0){
			bindingResult.rejectValue("userId", "signup.email.duplication","이미 등록된 아이디 입니다.");
			return "/signup/signupForm";
		}
		
		Date toDate = new Date();
		// 비밀번호 Digest (hash 160)
		userDto.setPassword(DigestUtils.encodePassword(userDto.getPassword()));
		userDto.setLastConnectedTime(toDate.getTime());
		userDto.setCreationTime(toDate);
		userDto = userJpaRepository.save(userDto);
		if(userDto == null) {
			throw new Exception("회원 가입에 실패 하였습니다.");
		}
		
		//EMAIL 발송  Thread 처리 , Template 생성
		String message = CryptoStringUtils.encrypt(userDto.getEmail());
		String template = "<a href='http://"+hostName+"/tms/outside/emailAuthentication?message="+ message +"'>여기</a>";
		emailSendService.sendEmail("knkcorp@knksoft.co.kr", userDto.getEmail(), "회원가입 인증 메일", template+ " 를 눌러 인증 하세요.", ThreadSendEnum.RUNNABLE);
		
		//emailSendService.emailSendShotdown();
		//return "redirect:/login";
		
		redirectAttributes.addFlashAttribute("email", userDto.getEmail());
		redirectAttributes.addAttribute("email", userDto.getEmail());
		
		return "redirect:/sign/signupAuthenticationEmailSendView";
	}
	
	/**
	 * 회원가입 스텝 THREE
	 * 		이메일 발송이 완료 되었습니다. 
	 * 
	 * @return
	 */
	@RequestMapping("/sign/signupAuthenticationEmailSendView")
	public String signupAuthenticationEmailSendView(HttpServletRequest request , HttpServletResponse response) throws Exception {
		
		String email=ServletRequestUtils.getRequiredStringParameter(request, "email");
		request.setAttribute("email", email);
		
		return "/signup/signupAuthenticationEmailSendView";
	}
	
}
