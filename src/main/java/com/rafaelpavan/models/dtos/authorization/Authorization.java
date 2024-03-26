package com.rafaelpavan.models.dtos.authorization;

public record Authorization(String message) {
    public boolean isAuthorized(){
        return message.equals("Autorizado");
    };
}
