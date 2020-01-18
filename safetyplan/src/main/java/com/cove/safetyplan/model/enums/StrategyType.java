package com.cove.safetyplan.model.enums;

public enum StrategyType {
    SOCIAL(1)
    ;

    private int id;

    StrategyType(int id){
        this.id = id;
    }

    public int getId(){
        return this.id;
    }
}
