package com.javaapi.test.dao.constant.Game;

import org.junit.Test;

public class TestEnum {

    @Test
    public void testEnum() {
        System.out.println(Game.CYHX == Game.CYHX);

    }

    @Test
    public static void testEnum2() {
        String chuanyuehuoxian = Game.CYHX.name();
        Game game = Game.valueOf(Game.class, chuanyuehuoxian);
        System.out.println(game.getDescription());
    }

    public static void main(String[] args) {
        testEnum2();
    }

}
