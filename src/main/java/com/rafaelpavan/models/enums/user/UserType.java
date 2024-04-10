package com.rafaelpavan.models.enums.user;

import lombok.Getter;

@Getter
public enum UserType {
    COMMON("COMMON"),
    MERCHANT("MERCHANT");

    private final String value;

    private UserType(String value){
        this.value = value;
    }

    @Override
    public String toString(){
        return value;
    }



//    public static String toValue(UserType userType){
//        return Optional.ofNullable(userType)
//                .map(UserType::getValue)
//                .orElseThrow(UserTypeException::new);
//    }
}
