package exmappers;

import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import com.scalefocus.edu.api.model.ExceptionHandlerAPI;

@Provider
public class ConstraintViolationExMapper implements ExceptionMapper<ConstraintViolationException> {

	@Override
	@Produces("application/json")
	public Response toResponse(ConstraintViolationException message){
		String moreInfo = "https://www.pctechguide.com/articles/correct-400-bad-request-ewrror";
		Set<ConstraintViolation<?>> set = message.getConstraintViolations();
		List<ExceptionHandlerAPI> list = new LinkedList<>();
		for (ConstraintViolation<?> constraintViolation : set) {
			new ExceptionHandlerAPI(Response.Status.BAD_REQUEST.getStatusCode(), message.getMessage() , Response.Status.BAD_REQUEST.getReasonPhrase(), moreInfo);
			list.add(new ExceptionHandlerAPI(Response.Status.BAD_REQUEST.getStatusCode(), constraintViolation.getMessage() , Response.Status.BAD_REQUEST.getReasonPhrase(), moreInfo));
		}		
//		ExceptionHandlerAPI errorMessage = new ExceptionHandlerAPI(Response.Status.BAD_REQUEST.getStatusCode(), message.getMessage() , Response.Status.BAD_REQUEST.getReasonPhrase(), moreInfo);
		return Response.status(Status.BAD_REQUEST).entity(list).type("application/json").build();
			}
}