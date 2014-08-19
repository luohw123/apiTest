package com.javaapi.test.dataTrans.json.jackson;


/**
 * @author <a href="mailto:l.weinan@gmail.com">Weinan Li</a>
 */

/**
 * pom报错，暂时注释掉,以后要打开JsonProperty,JsonIgnore
 *
 */
public class Apple {

//    @JsonProperty("_color")
    private String color;

//    @JsonIgnore
    private String id;

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
