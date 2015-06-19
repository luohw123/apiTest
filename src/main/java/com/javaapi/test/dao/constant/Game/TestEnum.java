package com.javaapi.test.dao.constant.Game;

import org.junit.Test;

public class TestEnum {

    @Test
    public void testEnum() {
        System.out.println(EnumGame.CYHX == EnumGame.CYHX);

    }

    @Test
    public  void testEnum2() {
        String name = EnumGame.CYHX.name();
        
        //
        EnumGame game = EnumGame.valueOf(EnumGame.class, name);
        System.out.println(game.getDescription());
    }


}
