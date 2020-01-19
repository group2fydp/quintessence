package com.cove.safetyplan.model.enums;

public enum StrategyType {
    IMAGE(1),
    VIDEO(2),
    TEXT(3),
    EXTERNAL(4),
    PERSONAL_CONTACT(5),
    PROFESSIONAL_CONTACT(6);

    private int id;

    StrategyType(int id){
        this.id = id;
    }

    public int getId(){
        return this.id;
    }
}
