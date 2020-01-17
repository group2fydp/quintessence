package com.cove.user.model.enums;

public enum ContactType {

    PERSONAL(0), PROFESSIONAL(1);

    private int id;

    ContactType(int id){
        this.id = id;
    }

    public int getId(){
        return this.id;
    }

}
