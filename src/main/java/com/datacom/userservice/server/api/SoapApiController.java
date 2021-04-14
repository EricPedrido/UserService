package com.datacom.userservice.server.api;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import java.util.Optional;
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2021-04-13T17:49:44.674+12:00[Pacific/Auckland]")
@Controller
@RequestMapping("${openapi.userService.base-path:}")
public class SoapApiController implements SoapApi {

    private final SoapApiDelegate delegate;

    public SoapApiController(@org.springframework.beans.factory.annotation.Autowired(required = false) SoapApiDelegate delegate) {
        this.delegate = Optional.ofNullable(delegate).orElse(new SoapApiDelegate() {});
    }

    @Override
    public SoapApiDelegate getDelegate() {
        return delegate;
    }

}
