package com.devevolve.answerservice;

import java.util.HashMap;
import java.util.Map;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.annotation.RegisteredOAuth2AuthorizedClient;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {
	
	    @GetMapping("/public/hello")
	    public String publicHello() {
	        return "Hello public";
	    }

	    @GetMapping("/private/hello")
	    public String privateHello(@AuthenticationPrincipal OAuth2User principal) {
	        return "Hello " + principal.getAttribute("login");
	    }

	    // Returns server-side info about the GitHub access token received for the logged-in user
	    @GetMapping("/tokeninfo")
	    public Map<String,Object> tokenInfo(
	        @RegisteredOAuth2AuthorizedClient("github") OAuth2AuthorizedClient client,
	        @AuthenticationPrincipal OAuth2User principal) {

	        Map<String,Object> m = new HashMap<>();
	        if (client == null) {
	            m.put("error", "not authenticated");
	            return m;
	        }
	        m.put("username", principal.getAttribute("login"));
	        m.put("accessToken", client.getAccessToken().getTokenValue());
	        m.put("expiresAt", client.getAccessToken().getExpiresAt());
	        return m;
	    }
}
