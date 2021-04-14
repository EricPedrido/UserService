package com.datacom.userservice.server.api;

import com.datacom.userservice.server.api.repository.SoapUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;

@Endpoint
public class SoapUserEndpoint {

    //TODO figure out how to return "GetUserResponse" from "GetUserRequest"

    private SoapUserRepository repository;

    @Autowired
    public SoapUserEndpoint(SoapUserRepository repository) {
        this.repository = repository;
    }


}
