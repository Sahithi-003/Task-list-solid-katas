package com.codurance.training.tasks;

record TaskId (String id){
    @Override
    public String toString(){
        return id;
    }
}
