package co.uk.app.commerce.catalog.security;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.FilterChain;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.bind.DatatypeConverter;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import co.uk.app.commerce.catalog.constant.CatalogConstants;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;

@Component
public class CatalogJWTAuthorizationFilter extends BasicAuthenticationFilter {

	private CatalogSecurityConfiguration securityConfiguration;

	public CatalogJWTAuthorizationFilter(AuthenticationManager authManager) {
		super(authManager);
	}

	@Override
	protected void doFilterInternal(HttpServletRequest req, HttpServletResponse res, FilterChain chain)
			throws IOException, ServletException {
		if (securityConfiguration == null) {
			ServletContext servletContext = req.getServletContext();
			WebApplicationContext webApplicationContext = WebApplicationContextUtils
					.getWebApplicationContext(servletContext);
			securityConfiguration = webApplicationContext.getBean(CatalogSecurityConfiguration.class);
		}
		String header = req.getHeader(securityConfiguration.getJwtHeader());

		if (header == null || !header.startsWith(securityConfiguration.getJwtTokenPrefix())) {
			chain.doFilter(req, res);
			return;
		}

		UsernamePasswordAuthenticationToken authentication = getAuthentication(req);

		SecurityContextHolder.getContext().setAuthentication(authentication);
		chain.doFilter(req, res);
	}

	private UsernamePasswordAuthenticationToken getAuthentication(HttpServletRequest request) {
		if (securityConfiguration == null) {
			ServletContext servletContext = request.getServletContext();
			WebApplicationContext webApplicationContext = WebApplicationContextUtils
					.getWebApplicationContext(servletContext);
			securityConfiguration = webApplicationContext.getBean(CatalogSecurityConfiguration.class);
		}
		String token = null;

		String authHeader = request.getHeader(securityConfiguration.getJwtHeader());
		if (authHeader != null && authHeader.startsWith(securityConfiguration.getJwtTokenPrefix())) {
			token = authHeader.replace(securityConfiguration.getJwtTokenPrefix(), "");
		}
		if (token != null) {

			Claims claims;
			try {
				claims = Jwts.parser()
						.setSigningKey(DatatypeConverter.parseBase64Binary(securityConfiguration.getJwtSecret()))
						.parseClaimsJws(token).getBody();
			} catch (Exception e) {
				claims = null;
			}

			if (null != claims) {
				String user = claims.getSubject();

				if (user != null) {
					request.setAttribute(CatalogConstants.REQUEST_HEADER_ROLE,
							claims.get(CatalogConstants.JWT_CLAIM_ROLE));
					return new UsernamePasswordAuthenticationToken(user, null, new ArrayList<>());
				}
			}
			return null;
		}
		return null;
	}
}
