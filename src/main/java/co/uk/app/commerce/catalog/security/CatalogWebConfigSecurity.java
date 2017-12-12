package co.uk.app.commerce.catalog.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;

@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true)
public class CatalogWebConfigSecurity extends WebSecurityConfigurerAdapter {

	@Autowired
	private CatalogSecurityConfiguration securityConfiguration;

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.cors().and().csrf().disable().authorizeRequests()
				.antMatchers(HttpMethod.GET).permitAll()
				.antMatchers(HttpMethod.GET, securityConfiguration.getJwtCatentryUrlGetMethod()).permitAll()
//				.antMatchers("/api/catentry/**").permitAll()
//				.antMatchers("/api/category/**").permitAll()
				.anyRequest().authenticated().and()
				.addFilter(new CatalogJWTAuthorizationFilter(authenticationManager()))
				// this disables session creation on Spring Security
				.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
	}

}
