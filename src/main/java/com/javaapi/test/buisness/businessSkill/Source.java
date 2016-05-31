package com.javaapi.test.buisness.businessSkill;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by user on 16/5/30.
 */
public class Source {

    private Map<Integer, SourceStrategy> mapStrategy = new HashMap<>();


    private int status;

    public Source(int status) {
        this.status = status;
    }

    public Source() {
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    {
        mapStrategy.put(1, new SuccessStrategy());
        mapStrategy.put(0, new FailStrategy());
    }


    public interface SourceStrategy {
        public boolean getStrategy(int status);
    }

    public class SuccessStrategy implements SourceStrategy {

        @Override
        public boolean getStrategy(int status) {
            if (status == SourceStatus.SUCCESS) {
                return true;
            }
            return false;
        }
    }

    public class FailStrategy implements SourceStrategy {

        @Override
        public boolean getStrategy(int status) {
            if (status == SourceStatus.FAIL) {
                return true;
            }
            return false;
        }
    }

    public boolean check(int status) {
        SourceStrategy sourceStrategy = mapStrategy.get(status);
        return sourceStrategy.getStrategy(status);
    }


    public static final class SourceStatus {
        public static final int SUCCESS = 1;
        public static final int FAIL = 0;
    }
}
