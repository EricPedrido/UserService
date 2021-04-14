package com.datacom.userservice.server.api;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class SoapApiDelegateImpl implements SoapApiDelegate {

    //TODO Take the SOAP requests and Produce SOAP responses

    @Override
    public ResponseEntity<Object> soapV1UsersGet(String wsdl) {
        return SoapApiDelegate.super.soapV1UsersGet(wsdl);
    }

    @Override
    public ResponseEntity<Object> soapV1UsersPost(Object body, String target) {
        return SoapApiDelegate.super.soapV1UsersPost(body, target);
    }
}
