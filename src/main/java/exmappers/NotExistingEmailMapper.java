package exmappers;

import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import com.scalefocus.edu.api.model.ExceptionHandlerAPI;
import exceptions.NotExistingEmailEx;

@Provider
public class NotExistingEmailMapper implements ExceptionMapper<NotExistingEmailEx> {

	@Override
	@Produces("application/json")
	public Response toResponse(NotExistingEmailEx message) {
		String moreInfo = "https://www.pctechguide.com/articles/404-not-found-error";
		ExceptionHandlerAPI errorMessage = new ExceptionHandlerAPI(Response.Status.NOT_FOUND.getStatusCode(), message.getMessage(), Response.Status.NOT_FOUND.getReasonPhrase(), moreInfo);
		return Response.status(Status.NOT_FOUND).entity(errorMessage).type("application/json").build();
	}

}