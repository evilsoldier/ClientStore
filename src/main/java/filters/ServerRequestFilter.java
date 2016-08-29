package filters;

import java.io.IOException;
import javax.ws.rs.Path;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.ext.Provider;

import org.springframework.stereotype.Component;

@Component
@Provider
@Path("/*")
public class ServerRequestFilter implements ContainerRequestFilter {

	public ServerRequestFilter() {
		System.out.println("ServerRequestFilter initialization");
	}

	@Override
	public void filter(ContainerRequestContext requestContext) throws IOException {
//		requestContext.abortWith(Response.ok()
//				.type(MediaType.APPLICATION_JSON)
//				.entity("you have the same version").build());
		
	}

}
