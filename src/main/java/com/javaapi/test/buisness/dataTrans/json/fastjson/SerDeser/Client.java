package com.javaapi.test.buisness.dataTrans.json.fastjson.SerDeser;

import com.javaapi.test.application.cache.redis.User;
import com.javaapi.test.application.cache.redis.jedis.SeriUtil.FastJsonSerializeUtil;
import org.junit.Test;

import java.io.UnsupportedEncodingException;
import java.util.Date;

/**
 * Created by user on 15/8/24.
 */
public class Client {
    @Test
    public void testSerDeSer() throws UnsupportedEncodingException {
        User user =new User();
        user.setName("kk");
        user.setCreateTime(new Date());
        byte[] serialize = FastJsonSerializeUtil.serialize(user);
        System.out.println("new String(serialize) = " + new String(serialize, "UTF-8"));
        User unserialize = (User) FastJsonSerializeUtil.deserialize(serialize, User.class);
        System.out.println("deserialize = " + unserialize);

    }
}
