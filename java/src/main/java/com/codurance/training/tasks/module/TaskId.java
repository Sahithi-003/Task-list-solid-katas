package com.codurance.training.tasks.module;

public record TaskId (String id){
    @Override
    public String toString(){
        return id;
    }
}
