package com.javaapi.test.buisness.exception.bussiness.exception;

/**
 * Created by user on 16/2/28.
 */
public class Client {
    public static void main(String[] args) {
        try {
            throw new BusinessException(ErrorCode.commonError);
        } catch (Exception e) {
            e.printStackTrace();
            if (e instanceof BusinessException) {
                ErrorCode error = ((BusinessException) e).getError();
                System.out.println("error = " + error.getMsg());
            }
        }
        System.out.println("done");

    }
}
