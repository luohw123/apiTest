package com.javaapi.test.dao.constant.Game;

import org.junit.Test;

public class TestEnum {

    @Test
    public void testEnum() {
        System.out.println(EnumGame.CYHX == EnumGame.CYHX);

    }

    @Test
    public static void testEnum2() {
        String chuanyuehuoxian = EnumGame.CYHX.name();
        EnumGame game = EnumGame.valueOf(EnumGame.class, chuanyuehuoxian);
        System.out.println(game.getDescription());
    }

    public static void main(String[] args) {
        testEnum2();
    }

}
