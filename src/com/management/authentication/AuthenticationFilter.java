package com.management.authentication;

import java.io.IOException;
import java.util.StringTokenizer;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.Provider;
import org.glassfish.jersey.internal.util.Base64;
import com.management.dao.LoginDao;
import com.management.exceptions.ErrorMessage;

@Provider
public class AuthenticationFilter implements ContainerRequestFilter {
	private static final String AUTHORIZATION_HEADER = "Authorization";
	private static final String AUTHORIZATION_HEADER_PREFIX = "Basic ";
	private LoginDao login=new LoginDao();

	@Override
	public void filter(ContainerRequestContext requestContext) throws IOException {
		MediaType mediaType = requestContext.getAcceptableMediaTypes().size() > 0 ? requestContext.getAcceptableMediaTypes().get(0) : null;
		if(requestContext.getHeaderString(AUTHORIZATION_HEADER)!=null) {
			String credentials = requestContext.getHeaderString(AUTHORIZATION_HEADER);
			credentials = credentials.replaceFirst(AUTHORIZATION_HEADER_PREFIX, "");
			String decodedString = Base64.decodeAsString(credentials);
			StringTokenizer userPass = new StringTokenizer(decodedString, ":");
			String user = userPass.nextToken();
			String pass = userPass.nextToken();
			String token=login.AuthenticateLogin(user, pass);
			if(token!=null) {
				return;
			} else {
				Response accessDenied = Response.status(Response.Status.UNAUTHORIZED).entity(new ErrorMessage(401,"Access Denied/Invalid User")).type(mediaType).build();
				requestContext.abortWith(accessDenied);
			}
		} else {
			Response unauthStatus = Response.status(Response.Status.UNAUTHORIZED).entity(new ErrorMessage(401,"Unauthorized Access/Login to Continue")).type(MediaType.APPLICATION_JSON).build();
			requestContext.abortWith(unauthStatus);
		}
	}
}
