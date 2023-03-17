package org.leslie.elm.dto;

import lombok.Data;

/**
 * @author zhang
 * date created in 2023/3/17 02:12
 */
@Data
public class AmapIPResponse {

    private String status;
    private String info;
    private String infocode;
    private String province;
    private String city;
    private String adcode;
    private String rectangle;
}
