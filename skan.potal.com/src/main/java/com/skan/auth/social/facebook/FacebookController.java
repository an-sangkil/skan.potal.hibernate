package com.skan.auth.social.facebook;

import org.springframework.social.connect.ConnectionRepository;
import org.springframework.social.facebook.api.Facebook;
import org.springframework.social.facebook.api.PagedList;
import org.springframework.social.facebook.api.Post;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class FacebookController {

	private Facebook facebook;
	private ConnectionRepository connectionRepository;
	
//	@GetMapping
//	public void helloFacebook(Model model) {
//		if (connectionRepository.findPrimaryConnection(Facebook.class) == null) {
//			
//			//return "redirect:/connect/facebook";
//		}
//
//		model.addAttribute("facebookProfile", facebook.userOperations().getUserProfile());
//		facebook.userOperations().getUserProfile().getEmail();
//		
//		
//		PagedList<Post> feed = facebook.feedOperations().getFeed();
//		model.addAttribute("feed", feed);
//		
//	}

}
