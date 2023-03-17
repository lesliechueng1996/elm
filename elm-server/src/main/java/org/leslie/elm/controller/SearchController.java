package org.leslie.elm.controller;

import org.leslie.elm.dto.SearchResultDto;
import org.leslie.elm.service.CitiesService;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author zhang
 * date created in 2023/3/18 00:20
 */
@RestController
@RequestMapping("/v1")
public class SearchController {

    private final CitiesService citiesService;

    public SearchController(CitiesService citiesService) {
        this.citiesService = citiesService;
    }

    @GetMapping("/pois")
    public List<SearchResultDto> searchAddress(
            @RequestParam("city_id") Integer cityId,
            @RequestParam("keyword") String keyword,
            @RequestParam(value = "type", required = false) String type) {
        if (!StringUtils.hasLength(type)) {
            type = "search";
        }

        return this.citiesService.retrieveSearchResult(cityId, keyword);
    }
}
