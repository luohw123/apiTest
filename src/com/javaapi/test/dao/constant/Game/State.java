package com.javaapi.test.dao.constant.Game;

public enum State {
    COMMON(1), TEST(2);
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