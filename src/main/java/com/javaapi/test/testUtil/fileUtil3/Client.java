package com.javaapi.test.testUtil.fileUtil3;

import org.junit.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

/**
 * 过滤emoji表情   filterEmoji 是有问题的,会过滤掉非emoji的一些符号
 */
public class Client {

    @Test
    public void test() throws IOException {
        String path = Client.class.getClassLoader().getResource("emoji.txt").getPath();
        List<String> strings = Files.readAllLines(Paths.get(path));
        strings.stream().forEach((s) -> {
            System.out.println("s = " + s);
        });
        String s = EmojiFilter.filterEmoji(strings.toString());
        System.out.println("s = " + s);

    }

    @Test
    public void testName() {
        String s = EmojiFilter.filterEmoji("糖♂分");
        System.out.println("s = " + s);
    }



}
