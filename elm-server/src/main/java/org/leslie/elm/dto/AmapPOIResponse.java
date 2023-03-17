package org.leslie.elm.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import org.springframework.util.ObjectUtils;

import java.util.List;

/**
 * @author zhang
 * date created in 2023/3/18 00:43
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class AmapPOIResponse {
    private String status;
    private String info;
    private String infocode;
    private String count;
    private List<POI> pois;

    @Data
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class POI {
        private String id;
        private String name;
        private String address;
        private String location;
        private String pname;
        private String cityname;
        private String adname;

        public String getFullAddress() {
            String fullAddress = "";
            if (ObjectUtils.nullSafeEquals(pname, cityname)) {
                fullAddress = pname;
            } else {
                fullAddress = pname + cityname;
            }

            fullAddress += (adname + address);
            return fullAddress;
        }

        public double[] getSplitLocation() {
            String[] temp = this.location.split(",");
            double[] result = new double[2];
            result[0] = Double.parseDouble(temp[0]);
            result[1] = Double.parseDouble(temp[1]);
            return result;
        }
    }
}
