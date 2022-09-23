package com.ms.microservice.services.exceptions;

public class ResourceAlreadyExistsException extends RuntimeException{

    public ResourceAlreadyExistsException(Long id) {

        super("Resource Already Exists Exception. Id: " + id);

    }

}
