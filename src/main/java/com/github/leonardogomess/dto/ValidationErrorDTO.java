package com.github.leonardogomess.dto;


import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class ValidationErrorDTO  extends CustomErrorDTO{

    private List<FieldMessageDTO> errors = new ArrayList<>();

    public ValidationErrorDTO(String timestamp,Integer status,String error ,String path){
        super(timestamp,status,error,path);
    }

    public void addError(String fieldName , String message){
        errors.removeIf(x -> x.getFiedlName().equals(fieldName));
        errors.add(new FieldMessageDTO(fieldName,message));
    }

}
