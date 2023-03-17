package org.leslie.elm.entity;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Field;

/**
 * @author zhang
 * date created in 2023/3/17 00:37
 */
@Data
public class City {

    @Field("id")
    private Integer id;
    private String pinyin;
    private Boolean isMap;
    private Double longitude;
    private Double latitude;
    private Integer sort;
    private String areaCode;
    private String abbr;
    private String name;
}
