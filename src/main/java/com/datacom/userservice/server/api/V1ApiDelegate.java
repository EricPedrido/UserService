package com.datacom.userservice.server.api;

import com.datacom.userservice.server.model.Error;
import com.datacom.userservice.server.model.User;
import com.datacom.userservice.server.model.User;
import io.swagger.annotations.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * A delegate to be called by the {@link V1ApiController}}.
 * Implement this interface with a {@link org.springframework.stereotype.Service} annotated class.
 */
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2021-04-13T17:49:44.674+12:00[Pacific/Auckland]")
public interface V1ApiDelegate {

    default Optional<NativeWebRequest> getRequest() {
        return Optional.empty();
    }

    /**
     * POST /v1/users : Creates a new user.
     *
     * @param user A user request. (required)
     * @param target  (optional)
     * @return The user was created successfully. (status code 201)
     *         or An unexpected error occurred. (status code 400)
     * @see V1Api#create
     */
    default ResponseEntity<Void> create(@Valid User user,
                                        String target) {
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);

    }

    /**
     * DELETE /v1/users/{email} : Delete an existing user.
     *
     * @param email User email. (required)
     * @param target  (optional)
     * @return The user was deleted successfully. (status code 204)
     *         or An unexpected error occurred. (status code 400)
     * @see V1Api#delete
     */
    default ResponseEntity<Void> delete(String email,
        String target) {
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);

    }

    /**
     * GET /v1/users/{email} : Retrieve an existing user.
     *
     * @param email User email. (required)
     * @param target  (optional)
     * @return A user response. (status code 200)
     *         or An unexpected error occurred. (status code 400)
     *         or An unexpected error occurred. (status code 404)
     * @see V1Api#retrieve
     */
    default ResponseEntity<User> retrieve(String email,
        String target) {
        getRequest().ifPresent(request -> {
            for (MediaType mediaType: MediaType.parseMediaTypes(request.getHeader("Accept"))) {
                if (mediaType.isCompatibleWith(MediaType.valueOf("application/json"))) {
                    String exampleString = "{ \"firstName\" : \"firstName\", \"lastName\" : \"lastName\", \"password\" : \"password\", \"email\" : \"email\" }";
                    ApiUtil.setExampleResponse(request, "application/json", exampleString);
                    break;
                }
            }
        });
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);

    }

    /**
     * PUT /v1/users/{email} : Update an existing user.
     *
     * @param email User email. (required)
     * @param user A user request. (required)
     * @param target  (optional)
     * @return The user was updated successfully. (status code 204)
     *         or An unexpected error occurred. (status code 400)
     *         or An unexpected error occurred. (status code 404)
     * @see V1Api#update
     */
    default ResponseEntity<Void> update(String email,
        User user,
        String target) {
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);

    }

}
