package letenote.dto;

import letenote.dto.utils.CatchDto;

import javax.enterprise.context.SessionScoped;
import javax.inject.Singleton;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

@Singleton
public class CatchFactoryDto {
    @Produces
    @SessionScoped
    public CatchDto badRequestResponse(String message){
        var response = new CatchDto()
                .setStatus(Response.Status.BAD_REQUEST.getStatusCode())
                .setShortCode(Response.Status.BAD_REQUEST.name())
                .setMessage(message);

        return response;
    }
    @Produces
    @SessionScoped
    public CatchDto methodNotAllowedResponse(String message){
        var response = new CatchDto()
                .setStatus(Response.Status.METHOD_NOT_ALLOWED.getStatusCode())
                .setShortCode(Response.Status.METHOD_NOT_ALLOWED.name())
                .setMessage(message);

        return response;
    }
    @Produces
    @SessionScoped
    public CatchDto unsupportedMediaTypeResponse(String message){
        var response = new CatchDto()
                .setStatus(Response.Status.UNSUPPORTED_MEDIA_TYPE.getStatusCode())
                .setShortCode(Response.Status.UNSUPPORTED_MEDIA_TYPE.name())
                .setMessage(message);

        return response;
    }
    @Produces
    @SessionScoped
    public CatchDto internalServerErrorResponse(String message){
        var response = new CatchDto()
                .setStatus(Response.Status.INTERNAL_SERVER_ERROR.getStatusCode())
                .setShortCode(Response.Status.INTERNAL_SERVER_ERROR.name())
                .setMessage(message);

        return response;
    }
}
