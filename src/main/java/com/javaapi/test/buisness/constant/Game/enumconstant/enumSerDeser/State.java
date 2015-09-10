package com.javaapi.test.buisness.constant.Game.enumconstant.enumSerDeser;

public enum State {
    COMMON(1);
    private int value;

    private State(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }
}