package com.javaapi.test.dao.constant.Game;


public enum Game {
    SJ(1, "射击游戏", null), CYHX(2, "穿越火线", SJ), NZ(3, "逆战", SJ), SMZH(4, "使命召唤", SJ);

    private Integer index;

    private String description;

    private Game parent;

    private Game(Integer index, String description, Game parent) {
        this.index = index;
        this.description = description;
        this.parent = parent;
    }

    public boolean equals(Game game) {
        if (game.getIndex() == this.getIndex()) {
            return true;
        }
        return false;
    }

    public Integer getIndex() {
        return index;
    }

    public void setIndex(Integer index) {
        this.index = index;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Game getParent() {
        return parent;
    }

    public void setParent(Game parent) {
        this.parent = parent;
    }

}
