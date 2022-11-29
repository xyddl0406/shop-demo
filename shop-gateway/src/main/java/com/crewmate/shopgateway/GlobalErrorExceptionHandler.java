package com.crewmate.shopgateway;

import java.net.URI;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.ObjectProvider;
import org.springframework.boot.autoconfigure.web.WebProperties;
import org.springframework.boot.autoconfigure.web.reactive.error.AbstractErrorWebExceptionHandler;
import org.springframework.boot.web.error.ErrorAttributeOptions;
import org.springframework.boot.web.reactive.error.ErrorAttributes;
import org.springframework.context.ApplicationContext;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.codec.ServerCodecConfigurer;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.RequestPredicates;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import org.springframework.web.reactive.result.view.ViewResolver;

import reactor.core.publisher.Mono;

@Component
@Order(-2)
public class GlobalErrorExceptionHandler extends AbstractErrorWebExceptionHandler {

    public GlobalErrorExceptionHandler(ErrorAttributes errorAttributes,
                    WebProperties webproperties,
                    ApplicationContext applicationContext,
                    ObjectProvider<List<ViewResolver>> viewResolversProvider,
                    ServerCodecConfigurer configurer) {
        super(errorAttributes, webproperties.getResources(), applicationContext);
        this.setMessageWriters(configurer.getWriters());
        this.setMessageReaders(configurer.getReaders());
        this.setViewResolvers(viewResolversProvider.getIfAvailable());
    }

    @Override
    protected RouterFunction<ServerResponse> getRoutingFunction(final ErrorAttributes errorAttributes) {
        return RouterFunctions.route(RequestPredicates.all(), this::renderErrorResponse); //
    }

    private Mono<ServerResponse> renderErrorResponse(ServerRequest request){

        Throwable error = getError(request);
        
        if (error instanceof AdminAuthException) {
            return ServerResponse.temporaryRedirect(URI.create("/login/form?from=" + request.path())).build();
        } else {
            Map<String, Object> errorPropertiesMap = getErrorAttributes(request, ErrorAttributeOptions.defaults());
            
            return ServerResponse
                    .status(HttpStatus.BAD_REQUEST)
                    .contentType(MediaType.APPLICATION_JSON)
                    //.body(BodyInserters.fromValue(errorPropertiesMap));
                    .body(BodyInserters.fromValue("[" + errorPropertiesMap.get("path") + "] BAD_REQUEST (요청된 서버의 응답이 없습니다.)"));
        }
    }
}