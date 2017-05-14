package edu.iis.mto.blog.configuration;

import java.util.Map;

import org.springframework.boot.autoconfigure.web.DefaultErrorAttributes;
import org.springframework.boot.autoconfigure.web.ErrorAttributes;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.request.RequestAttributes;

import edu.iis.mto.blog.domain.errors.DomainError;

@Configuration
public class ErrorResponseConfiguration {

    @Bean
    public ErrorAttributes errorAttributes() {
        return new DefaultErrorAttributes() {

            @Override
            public Map<String, Object> getErrorAttributes(RequestAttributes requestAttributes,
                    boolean includeStackTrace) {
                Map<String, Object> errorAttributes = super.getErrorAttributes(requestAttributes, includeStackTrace);
                Throwable error = getError(requestAttributes);
                if (error instanceof DomainError) {
                    DomainError domainException = (DomainError) error;
                    errorAttributes.put("details", domainException.getMessage());
                }
                return errorAttributes;
            }
        };
    }

}
