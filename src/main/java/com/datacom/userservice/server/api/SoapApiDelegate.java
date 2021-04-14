package com.datacom.userservice.server.api;

import io.swagger.annotations.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * A delegate to be called by the {@link SoapApiController}}.
 * Implement this interface with a {@link org.springframework.stereotype.Service} annotated class.
 */
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2021-04-13T17:49:44.674+12:00[Pacific/Auckland]")
public interface SoapApiDelegate {

    default Optional<NativeWebRequest> getRequest() {
        return Optional.empty();
    }

    /**
     * GET /soap/v1/users : The SOAP WSDL.
     *
     * @param wsdl  (required)
     * @return A SOAP response. (status code 200)
     * @see SoapApi#soapV1UsersGet
     */
    default ResponseEntity<Object> soapV1UsersGet(String wsdl) {
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);

    }

    /**
     * POST /soap/v1/users : A SOAP service for managing users.
     *
     * @param body A SOAP request. (required)
     * @param target  (optional)
     * @return A SOAP response. (status code 200)
     * @see SoapApi#soapV1UsersPost
     */
    default ResponseEntity<Object> soapV1UsersPost(Object body,
        String target) {
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);

    }

}
