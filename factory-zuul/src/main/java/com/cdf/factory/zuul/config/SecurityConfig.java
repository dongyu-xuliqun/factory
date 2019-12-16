package com.cdf.factory.zuul.config;

import org.springframework.boot.autoconfigure.security.oauth2.client.EnableOAuth2Sso;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.oauth2.provider.authentication.OAuth2AuthenticationManager;
import org.springframework.security.oauth2.provider.authentication.OAuth2AuthenticationProcessingFilter;
import org.springframework.security.web.authentication.preauth.AbstractPreAuthenticatedProcessingFilter;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

/**
 * 安全配置
 * @ EnableWebSecurity 启用web安全
 */
@Configuration
@EnableOAuth2Sso
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    /**
     * http安全配置
     * @param http http安全对象
     * @throws Exception http安全异常信息
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
    	http
		.authorizeRequests()
		.antMatchers("/login", "/client/**")
		.permitAll()
		.anyRequest()
		.authenticated()		
		.and()
		.csrf()
		.disable(); //.addFilterBefore(oAuth2AuthenticationProcessingFilter(), AbstractPreAuthenticatedProcessingFilter.class);
    }
    
//	private OAuth2AuthenticationProcessingFilter oAuth2AuthenticationProcessingFilter() {
//
//		OAuth2AuthenticationProcessingFilter oAuth2AuthenticationProcessingFilter = new OAuth2AuthenticationProcessingFilter();
//
//		oAuth2AuthenticationProcessingFilter.setAuthenticationManager(oauthAuthenticationManager());
//
//		oAuth2AuthenticationProcessingFilter.setStateless(false);
//
//		return oAuth2AuthenticationProcessingFilter;
//	}
//	
//	private AuthenticationManager oauthAuthenticationManager() { 
//
//		  OAuth2AuthenticationManager oAuth2AuthenticationManager = new OAuth2AuthenticationManager(); 
//
//		  oAuth2AuthenticationManager.setResourceId("zuul"); 
//
//		  oAuth2AuthenticationManager.setTokenServices(resourceServerTokenServices); 
//
//		  oAuth2AuthenticationManager.setClientDetailsService(null); 	 
//
//		  return oAuth2AuthenticationManager; 
//
//		  } 
}
