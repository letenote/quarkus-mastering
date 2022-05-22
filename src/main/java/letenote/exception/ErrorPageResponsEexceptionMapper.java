package letenote.exception;

import letenote.dto.CatchFactoryDto;
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

@Provider
public class ErrorPageResponsEexceptionMapper implements ExceptionMapper<Exception> {
    @Context
    HttpHeaders headers;
    @Inject
    Logger logger;
    @Inject
    CatchFactoryDto catchFactoryDto;
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
            return Response
                    .status(Response.Status.BAD_REQUEST.getStatusCode())
                    .entity(catchFactoryDto.badRequestResponse(exception.getMessage()))
                    .build();
        }
        else if(exception instanceof BadRequestException){
            return Response
                    .status(Response.Status.BAD_REQUEST.getStatusCode())
                    .entity(catchFactoryDto.badRequestResponse(exception.getMessage()))
                    .build();
        }
        else if(exception instanceof NotAllowedException){
            return Response
                    .status(Response.Status.METHOD_NOT_ALLOWED.getStatusCode())
                    .entity(catchFactoryDto.methodNotAllowedResponse(exception.getMessage()))
                    .build();
        }
        else if (exception instanceof NotSupportedException) {
            return Response
                    .status(Response.Status.UNSUPPORTED_MEDIA_TYPE.getStatusCode())
                    .entity(catchFactoryDto.unsupportedMediaTypeResponse(exception.getMessage()))
                    .build();
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
            return Response
                    .serverError()
                    .entity(catchFactoryDto.internalServerErrorResponse("Internal Server Error)"))
                    .build();
        }
    }
}
