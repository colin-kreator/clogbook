package com.pilotair.clogbook.webapp.security;

import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.rememberme.RememberMeAuthenticationFilter;

import com.pilotair.clogbook.webapp.filter.LoginPageFilter;

@EnableWebSecurity( debug = false )
@EnableGlobalMethodSecurity( prePostEnabled = true )
public class ApplicationSecurityConfig extends WebSecurityConfigurerAdapter {

	@Configuration
	@Order( 1 )
	public class ApiWebSecurityConfigurationAdapter extends WebSecurityConfigurerAdapter {

		@Override
		protected void configure( HttpSecurity http ) throws Exception {
			http
			        // Gestion du Cross Site Request Forgery
			        .csrf().disable()

			        // Right List
			        // .authorizeRequests()
			        .antMatcher( "/api/**" ).authorizeRequests( authorize -> authorize.anyRequest().authenticated() )
			        // LOGIN
			        .httpBasic();
		}
	}

	@Configuration
	@Order( 2 )
	public static class FormLoginWebSecurityConfigurerAdapter extends WebSecurityConfigurerAdapter {

		@Autowired
		private UserDetailsService userDetailsService;

		@Bean
		public AuthenticationManager customAuthenticationManager() throws Exception {
			return authenticationManager();
		}

		@Override
		protected void configure( HttpSecurity http ) throws Exception {
			http
			        // Gestion du Cross Site Request Forgery
			        .csrf().disable()

			        // Filtre qui redirige les users déjà logués
			        .addFilterAfter( new LoginPageFilter(), RememberMeAuthenticationFilter.class )

			        // Right List
			        .authorizeRequests()
			        .antMatchers( "/", "/css/**", "/libs/**", "/js/**", "/img/**", "/signup" )
			        .permitAll()
			        .antMatchers( "/app/*" ).authenticated()
			        .anyRequest().authenticated()
			        .and()

			        // LOGIN
			        .formLogin()
			        .loginPage( "/login" ).permitAll()
			        .loginProcessingUrl( "/login-process" )
			        .defaultSuccessUrl( "/app/dashboard", true )
			        .usernameParameter( "userlogin" )
			        .passwordParameter( "userpassword" )
			        .and()

			        // REMEMBER ME
			        .rememberMe().tokenValiditySeconds( (int) TimeUnit.DAYS.toSeconds( 21 ) )
			        .rememberMeParameter( "rememberme" )
			        .userDetailsService( userDetailsService )
			        .and()

			        // LOGOUT
			        .logout()
			        .logoutUrl( "/logout-process" )
			        .clearAuthentication( true )
			        .invalidateHttpSession( true )
			        .deleteCookies( "JSESSIONID", "remember-me" )
			        .logoutSuccessUrl( "/" )

			;

		}

	}

}
