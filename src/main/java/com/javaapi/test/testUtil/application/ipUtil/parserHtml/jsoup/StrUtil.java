package com.javaapi.test.testUtil.application.ipUtil.parserHtml.jsoup;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.junit.Test;

import java.util.Arrays;
import java.util.stream.Stream;

/**
 * Created by user on 16/11/22.
 */
public class StrUtil {

    private static final String[] tagRemove = {"style","link"};
    private static final String[] attrRemove = {"width","min-width","max-width"};
    /**
     *
     * @return
     */
    public static String removeIlleaglString(String txt) {
        try {
            final Document parse = Jsoup.parse(txt);
            Stream<String> stream = Arrays.stream(tagRemove);
            stream.forEach((s)-> {
                parse.getElementsByTag(s).remove();
            });
            Stream<String> streamAttr = Arrays.stream(attrRemove);
            streamAttr.forEach((s)->{
                parse.getElementsByAttribute(s).removeAttr(s);
            });
            return parse.body().html();
        } catch (Exception e) {
            return txt;
        }
    }
    @Test
    public void testRemove() {
        String nihao = "<p>哈哈哈哈</p><a>aa</a>";
        String s = removeIlleaglString(nihao);
        System.out.println("s = " + s);
    }

    @Test
    public void testRemove2() {
        String nihao = "<p>哈哈哈哈<a>aa</a>";
        String s = removeIlleaglString(nihao);
        System.out.println("s = " + s);
    }

    @Test
    public void testRemoveTag() {
        String nihao = "<style> aaaaaaa </style><div>bbbbbbb</div>";
        String s = removeIlleaglString(nihao);
        System.out.println("s = " + s);
    }
    @Test
    public void testRemoveAttr() {
        String nihao = "<style> aaaaaaa </style><div width='12' >bbbbbb</div>";
        String s = removeIlleaglString(nihao);
        System.out.println("s = " + s);
    }
}
