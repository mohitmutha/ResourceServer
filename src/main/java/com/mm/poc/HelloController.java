package com.mm.poc;

import jdk.nashorn.internal.parser.TokenStream;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.authentication.OAuth2AuthenticationDetails;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/api")
public class HelloController {

  @Autowired
  TokenStore store;

  @RequestMapping(path = "/hello")
  public String hello(){
    Authentication auth = SecurityContextHolder.getContext().getAuthentication();
    OAuth2AuthenticationDetails details
            = (OAuth2AuthenticationDetails) auth.getDetails();
    OAuth2AccessToken accessToken = store.readAccessToken(details.getTokenValue());

    return "Hello " + accessToken.getAdditionalInformation().get("organization");
  }
}
