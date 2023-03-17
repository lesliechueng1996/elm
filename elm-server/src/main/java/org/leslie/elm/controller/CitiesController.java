package org.leslie.elm.controller;

import jakarta.servlet.http.HttpServletRequest;
import org.leslie.elm.dto.CityDto;
import org.leslie.elm.enums.CityType;
import org.leslie.elm.exception.ElmParamsException;
import org.leslie.elm.service.CitiesService;
import org.springframework.web.bind.annotation.*;

/**
 * @author zhang
 * date created in 2023/3/17 00:46
 */
@RestController
@RequestMapping("/v1/cities")
public class CitiesController {

    private final CitiesService citiesService;

    public CitiesController(CitiesService citiesService) {
        this.citiesService = citiesService;
    }

    @GetMapping
    public Object cities(@RequestParam(value = "type", required = true) String type, HttpServletRequest request) {
        if (CityType.GROUP.type.equals(type)) {
            return citiesService.retrieveAllCitiesByGroup();
        }
        if (CityType.HOT.type.equals(type)) {
            return citiesService.retrieveHotCities();
        }
        if (CityType.GUESS.type.equals(type)) {
            return citiesService.retrieveGuessCity(request.getRemoteAddr());
        }
        throw new ElmParamsException("type 参数异常");
    }

    @GetMapping("/{cityId}")
    public CityDto city(@PathVariable("cityId") Integer cityId) {
        return citiesService.retrieveCityById(cityId);
    }
}
