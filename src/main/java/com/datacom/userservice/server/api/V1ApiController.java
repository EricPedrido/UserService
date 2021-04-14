package com.datacom.userservice.server.api;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Optional;

@javax.annotation.Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2021-04-13T17:49:44.674+12:00[Pacific/Auckland]")
@Controller
@RequestMapping("${openapi.userService.base-path:}")
public class V1ApiController implements V1Api {
    private final V1ApiDelegate delegate;

    public V1ApiController(@org.springframework.beans.factory.annotation.Autowired(required = false) V1ApiDelegate delegate) {
        this.delegate = Optional.ofNullable(delegate).orElse(new V1ApiDelegate() {});
    }

    @Override
    public V1ApiDelegate getDelegate() {
        return delegate;
    }

}
