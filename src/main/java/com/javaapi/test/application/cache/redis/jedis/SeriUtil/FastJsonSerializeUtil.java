package com.javaapi.test.application.cache.redis.jedis.SeriUtil;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.parser.deserializer.JSONObjectDeserializer;
import com.alibaba.fastjson.serializer.JSONSerializer;
import com.alibaba.fastjson.serializer.SerializeWriter;
import com.alibaba.fastjson.serializer.SerializerFeature;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 * Created by user on 15/8/23.
 */
public class FastJsonSerializeUtil {
    public static byte[] serialize(Object object) {
        if(object == null){
            return null;
        }
        SerializerFeature[] features = new SerializerFeature[0];
        SerializeWriter out = new SerializeWriter();

        try {
            JSONSerializer serializer = new JSONSerializer(out);
            for (com.alibaba.fastjson.serializer.SerializerFeature feature : features) {
                serializer.config(feature, true);
            }

            serializer.write(object);
            return out.toBytes("UTF-8");
        } finally {
            out.close();
        }
    }

    public static Object unserialize(byte[] bytes, Class<Person> personClass) {
        Object parse = JSON.parseObject(bytes, personClass);
        return parse;
    }
}
