package filmclub.application;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;


public class RestExceptionsMapper implements ExceptionMapper<Exception> {
    private static final Logger logger = LoggerFactory.getLogger(RestExceptionsMapper.class);
    @Override
    public Response toResponse(Exception e) {
        Response defaultResponse = Response
                .serverError()
                .entity(new ExceptionResponse(500, e.getMessage()))
                .build();

        logger.error(e.getMessage(), e);

        if(e instanceof WebApplicationException) {
            WebApplicationException ex = (WebApplicationException) e;
            int status = ex.getResponse().getStatus();
            return Response
                .status(status)
                .entity(new ExceptionResponse(status, ex.getMessage()))
                .build();
        }

        if(e instanceof HandledException) {
            return Response
                    .status(400)
                    .entity(new ExceptionResponse(400, e.getMessage()))
                    .build();
        }

        return defaultResponse;
    }
}
