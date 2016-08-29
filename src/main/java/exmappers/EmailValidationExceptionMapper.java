package exmappers;

import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import com.scalefocus.edu.api.model.ExceptionHandlerAPI;

@Provider
public class EmailValidationExceptionMapper implements ExceptionMapper<org.hibernate.JDBCException> {

	@Override
	@Produces("application/json")
	public Response toResponse(org.hibernate.JDBCException message){
		String moreInfo = "https://www.pctechguide.com/articles/correct-400-bad-request-ewrror";
		ExceptionHandlerAPI errorMessage = new ExceptionHandlerAPI(Response.Status.BAD_REQUEST.getStatusCode(), message.getMessage(), Response.Status.BAD_REQUEST.getReasonPhrase(), moreInfo);
		return Response.status(Status.BAD_REQUEST).entity(errorMessage).type("application/json").build();
}}