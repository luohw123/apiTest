package com.javaapi.test.dao.constant.Game.classconstant;

import java.io.Serializable;

import com.javaapi.test.testReflect.serializable.ser1.CustomTest;

/**
 * 
 * @project apiTest
 * @author kk
 * @date 2014年8月4日
 * @see CustomTest
 */
public class ConstantGame implements Serializable {
    private static final long serialVersionUID = 1L;
    public static final ConstantGame SJ = new ConstantGame(1, "射击游戏", null);
    public static final ConstantGame CYHX = new ConstantGame(2, "穿越火线", SJ);
    public static final ConstantGame NZ = new ConstantGame(3, "逆战", SJ);
    public static final ConstantGame SMZH = new ConstantGame(4, "使命召唤", SJ);

    private Integer index;

    private String description;

    private ConstantGame parent;

    private ConstantGame(Integer index, String description, ConstantGame parent) {
        this.index = index;
        this.description = description;
        this.parent = parent;
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

    public ConstantGame getParent() {
        return parent;
    }

    public void setParent(ConstantGame parent) {
        this.parent = parent;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((index == null) ? 0 : index.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        ConstantGame other = (ConstantGame) obj;
        if (index == null) {
            if (other.index != null)
                return false;
        } else if (!index.equals(other.index))
            return false;
        return true;
    }

    private Object readResolve() {
        if (this.getIndex().intValue() == ConstantGame.SJ.getIndex().intValue()) {
            return ConstantGame.SJ;
        }
        if (this.getIndex().intValue() == ConstantGame.CYHX.getIndex().intValue()) {
            return ConstantGame.CYHX;
        }
        if (this.getIndex().intValue() == ConstantGame.NZ.getIndex().intValue()) {
            return ConstantGame.NZ;
        }
        if (this.getIndex().intValue() == ConstantGame.SMZH.getIndex().intValue()) {
            return ConstantGame.SMZH;
        }
        return null;
    }
    //
    //    @Override
    //    public String toString() {
    //        return "ConstantGame [index=" + index + ", description=" + description + ", parent=" + parent + "]";
    //    }

}
