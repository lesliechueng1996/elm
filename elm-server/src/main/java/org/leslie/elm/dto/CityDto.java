package org.leslie.elm.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 * @author zhang
 * date created in 2023/3/17 00:48
 */
@Data
public class CityDto {

    private Integer id;
    private String pinyin;
    @JsonProperty("is_map")
    private Boolean isMap;
    private Double longitude;
    private Double latitude;
    private Integer sort;
    @JsonProperty("area_code")
    private String areaCode;
    private String abbr;
    private String name;
}
