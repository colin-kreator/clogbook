package com.pilotair.clogbook;

import java.util.Locale;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

import com.fasterxml.jackson.datatype.hibernate5.Hibernate5Module;
import com.pilotair.clogbook.webapp.file.FileStorageProperties;

@SpringBootApplication
@EnableConfigurationProperties( { FileStorageProperties.class } )
public class ClogbookWebappApplication implements WebMvcConfigurer {

	public static void main( String[] args ) {
		// Locale.setDefault( Locale.ENGLISH );
		SpringApplication.run( ClogbookWebappApplication.class, args );
	}

	/**
	 * Utilisé pour nullifier les proxys hibernate avant que les entities soient
	 * transmises à Jackson pour conversion en JSon
	 * 
	 * @return une nouvelle instance de hibernate5Module
	 */
	@Bean
	public Hibernate5Module dataTypeHibernateModule() {
		return new Hibernate5Module();
	}

	// Instanciation pour permettre de faire des requetes ajax dans l'appli
	@Bean
	public RestTemplate restTemplate( RestTemplateBuilder builder ) {
		return builder.build();
	}

	@Bean
	public LocaleResolver localeResolver() {
		SessionLocaleResolver slr = new SessionLocaleResolver();
		slr.setDefaultLocale( Locale.UK );
		return slr;
	}

	@Bean
	public LocaleChangeInterceptor localeChangeInterceptor() {
		LocaleChangeInterceptor lci = new LocaleChangeInterceptor();
		lci.setParamName( "lang" );
		return lci;
	}

	@Override
	public void addInterceptors( InterceptorRegistry registry ) {
		registry.addInterceptor( localeChangeInterceptor() );
	}

}
