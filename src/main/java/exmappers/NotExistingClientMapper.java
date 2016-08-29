package exmappers;

import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import com.scalefocus.edu.api.model.ExceptionHandlerAPI;

import exceptions.NotExistingClientEx;

@Provider
public class NotExistingClientMapper implements ExceptionMapper<NotExistingClientEx> {

	@Override
	@Produces("application/json")
	public Response toResponse(NotExistingClientEx message) {
		String moreInfo = "https://www.pctechguide.com/articles/404-not-found-error";
		ExceptionHandlerAPI errorMessage = new ExceptionHandlerAPI(Response.Status.NOT_FOUND.getStatusCode(), message.getMessage(), Response.Status.NOT_FOUND.getReasonPhrase(), moreInfo);
		return Response.status(Status.NOT_FOUND).entity(errorMessage).type("application/json").build();
	}

}
