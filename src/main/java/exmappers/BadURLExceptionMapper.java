package exmappers;

import javax.ws.rs.ClientErrorException;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import com.scalefocus.edu.api.model.ExceptionHandlerAPI;

@Provider
public class BadURLExceptionMapper implements ExceptionMapper<ClientErrorException> {

	@Override
	@Produces("application/json")
	public Response toResponse(ClientErrorException message){
		String moreInfo = "https://www.pctechguide.com/articles/correct-400-bad-request-ewrror";
		ExceptionHandlerAPI errorMessage = new ExceptionHandlerAPI(Response.Status.NOT_FOUND.getStatusCode(), message.getMessage() , Response.Status.NOT_FOUND.getReasonPhrase(), moreInfo);
		return Response.status(Status.NOT_FOUND).entity(errorMessage).type("application/json").build();
		// TODO Auto-generated method stub
			}
}