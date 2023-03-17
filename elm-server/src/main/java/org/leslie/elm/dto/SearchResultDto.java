package org.leslie.elm.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author zhang
 * date created in 2023/3/18 00:30
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SearchResultDto {

    private String name;
    private String address;
    private Double latitude;
    private Double longitude;
    private String geohash;

}
