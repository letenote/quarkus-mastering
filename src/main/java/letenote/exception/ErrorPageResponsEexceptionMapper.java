package letenote.exception;


import letenote.dto.ResponseErrorDto;
import org.jboss.logging.Logger;
import javax.inject.Inject;
import javax.ws.rs.BadRequestException;
import javax.ws.rs.NotAllowedException;
import javax.ws.rs.NotSupportedException;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;
import java.util.Date;

@Provider
public class ErrorPageResponsEexceptionMapper implements ExceptionMapper<Exception> {
    @Context
    HttpHeaders headers;
    @Inject
    Logger logger;
    @Override
    public Response toResponse(Exception exception) {
        System.out.println("toResponse : " + exception.getClass() + " detail: " + exception );
        // Modify error response...
        Response errorResponse = mapExceptionToResponse(exception);

        return Response.fromResponse(errorResponse)
                .type(headers.getMediaType())
                .entity(errorResponse.getEntity())
                .build();
    }

    private Response mapExceptionToResponse(Exception exception) {
        // Special mappings
        if (exception instanceof IllegalArgumentException) {
            var statusCode = Response.Status.BAD_REQUEST.getStatusCode();
            var exceptionResponse = new ResponseErrorDto()
                    .setStatus(statusCode)
                    .setShortCode(Response.Status.BAD_REQUEST.name())
                    .setTimestamp(new Date())
                    .setMessage(exception.getMessage());

            return Response.status(statusCode).entity(exceptionResponse).build();
        }
        else if(exception instanceof BadRequestException){
            var statusCode = Response.Status.BAD_REQUEST.getStatusCode();
            var exceptionResponse = new ResponseErrorDto()
                    .setStatus(statusCode)
                    .setShortCode(Response.Status.BAD_REQUEST.name())
                    .setTimestamp(new Date())
                    .setMessage(exception.getMessage());

            return Response.status(statusCode).entity(exceptionResponse).build();
        }
        else if(exception instanceof NotAllowedException){
            var statusCode = Response.Status.METHOD_NOT_ALLOWED.getStatusCode();
            var exceptionResponse = new ResponseErrorDto()
                    .setStatus(statusCode)
                    .setShortCode(Response.Status.METHOD_NOT_ALLOWED.name())
                    .setTimestamp(new Date())
                    .setMessage(exception.getMessage());

            return Response.status(statusCode).entity(exceptionResponse).build();
        }
        else if (exception instanceof NotSupportedException) {
            var statusCode = Response.Status.UNSUPPORTED_MEDIA_TYPE.getStatusCode();
            var exceptionResponse = new ResponseErrorDto()
                    .setStatus(statusCode)
                    .setShortCode(Response.Status.UNSUPPORTED_MEDIA_TYPE.name())
                    .setTimestamp(new Date())
                    .setMessage(exception.getMessage());

            return Response.status(statusCode).entity(exceptionResponse).build();
        }
        // Use response from WebApplicationException as they are
        else if (exception instanceof WebApplicationException) {
                // Overwrite error message
                System.out.println("WebApplicationException: " + ((WebApplicationException) exception));
                Response originalErrorResponse = ((WebApplicationException) exception).getResponse();
                return Response.fromResponse(originalErrorResponse)
                        .entity(originalErrorResponse.getStatusInfo().getReasonPhrase())
                        .build();
            }
        // Use 500 (Internal Server Error) for all other
        else {
            logger.fatalf(exception,
                    "FAILED_TO_PROCESS_REQUEST_TO: {}"
                    // "containerRequestContext.get().getUriInfo()"
            );
            return Response.serverError().entity("Internal Server Error").build();
        }
    }
}
