package com.codurance.training.tasks;

record TaskId (long id){
    @Override
    public String toString(){
        return String.valueOf(id);
    }
}
