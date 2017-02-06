package com.javaapi.test.testUtil.fileUtil4;

import org.apache.commons.io.FileUtils;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 */
public class TestClient {
    @Test
    public void testFile() throws IOException {
        List<Map<String, Object>> maps = new ArrayList<>();
        HashMap<String, Object> e = new HashMap<>();
        e.put("nihao", 2);
        maps.add(e);
        FileUtils.writeLines(new File("/Users/user/Downloads/test3333.txt"),maps);
    }
}
