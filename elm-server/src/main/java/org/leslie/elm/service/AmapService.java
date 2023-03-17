package org.leslie.elm.service;

import lombok.extern.slf4j.Slf4j;
import org.leslie.elm.config.ElmConfig;
import org.leslie.elm.dto.AmapIPResponse;
import org.leslie.elm.dto.AmapPOIResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Optional;

/**
 * @author zhang
 * date created in 2023/3/17 02:08
 */
@Service
@Slf4j
public class AmapService {

    private final ElmConfig elmConfig;
    private final RestTemplate restTemplate;

    public AmapService(ElmConfig elmConfig, RestTemplate restTemplate) {
        this.elmConfig = elmConfig;
        this.restTemplate = restTemplate;
    }

    public String getCityName(String ip) {
        try {
            String url = elmConfig.getAmapUrl() + "/ip?ip=" + ip + "&key=" + elmConfig.getAmapKey();
            log.info("amap ip url: {}", url);
            ResponseEntity<AmapIPResponse> response = this.restTemplate.getForEntity(url, AmapIPResponse.class);
            if (response.getStatusCode().is2xxSuccessful()) {
                AmapIPResponse body = response.getBody();
                String city = Optional.ofNullable(body).map(AmapIPResponse::getCity).orElse(null);
                log.info("ip - city: {} - {}", ip, city);
                return city;
            }
        } catch (Exception e) {
            log.error("get city through ip error >>>", e);
        }
        return null;
    }

    public AmapPOIResponse poiSearch(String cityName, String keyword) {
        try {
            String url = elmConfig.getAmapUrl() + "/place/text?key=" + elmConfig.getAmapKey() + "&keywords=" + keyword + "&city=" + cityName + "&children=1&offset=20&page=1&extensions=base";
            log.info("amap poi url: {}", url);
            ResponseEntity<AmapPOIResponse> response = this.restTemplate.getForEntity(url, AmapPOIResponse.class);
            if (response.getStatusCode().is2xxSuccessful()) {
                AmapPOIResponse body = response.getBody();
                if (body != null && "1".equals(body.getStatus())) {
                    return body;
                }
                if (body == null) {
                    log.error("get poi error, body null");
                    return null;
                }
                log.error("get poi error, info: {}, infoCode: {}", body.getInfo(), body.getInfocode());
                return null;
            }
        } catch (Exception e) {
            log.error("get poi error >>>", e);
        }
        return null;
    }
}
