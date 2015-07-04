package com.javaapi.test.application.test.testmockito;

public interface UserService {

	User findUserByUserName(String string);

	boolean hasMatchUser(String string, String string2);

	void registerUser(User u);

}
