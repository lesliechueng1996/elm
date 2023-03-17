package org.leslie.elm.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.leslie.elm.config.ElmConfig;
import org.leslie.elm.dto.AmapPOIResponse;
import org.leslie.elm.dto.CityDto;
import org.leslie.elm.dto.SearchResultDto;
import org.leslie.elm.entity.Cities;
import org.leslie.elm.entity.City;
import org.leslie.elm.exception.ElmServerException;
import org.leslie.elm.repository.CitiesRepository;
import org.leslie.elm.service.CitiesService;
import org.leslie.elm.service.AmapService;
import org.leslie.elm.util.PinYinUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.*;

/**
 * @author zhang
 * date created in 2023/3/17 00:42
 */
@Service
@Slf4j
public class CitiesServiceImpl implements CitiesService {

    private final CitiesRepository citiesRepository;
    private final AmapService amapService;
    private final ElmConfig elmConfig;
    private final String HOT_CITIES = "hotCities";

    public CitiesServiceImpl(CitiesRepository citiesRepository, AmapService amapService, ElmConfig elmConfig) {
        this.citiesRepository = citiesRepository;
        this.amapService = amapService;
        this.elmConfig = elmConfig;
    }

    @Override
    public Map<String, List<CityDto>> retrieveAllCitiesByGroup() {
        List<Cities> citiesList = citiesRepository.findAll();
        if (CollectionUtils.isEmpty(citiesList)) {
            throw new ElmServerException("城市列表为空");
        }
        Cities cities = citiesList.get(0);

        Map<String, List<CityDto>> result = new LinkedHashMap<>();

        result.put(HOT_CITIES, modifyCityDtoList(cities.getData().get(HOT_CITIES)));
        for (char c = 'A'; c <= 'Z'; c++) {
            if (cities.getData().containsKey(String.valueOf(c))) {
                result.put(String.valueOf(c), modifyCityDtoList(cities.getData().get(String.valueOf(c))));
            }
        }
        return result;
    }

    @Override
    public List<CityDto> retrieveHotCities() {
        List<Cities> citiesList = citiesRepository.findAll();
        if (CollectionUtils.isEmpty(citiesList)) {
            throw new ElmServerException("城市列表为空");
        }
        Cities cities = citiesList.get(0);
        return modifyCityDtoList(cities.getData().get(HOT_CITIES));
    }

    @Override
    public CityDto retrieveGuessCity(String ip) {
        String cityName = amapService.getCityName(ip);
        if (cityName == null) {
            cityName = elmConfig.getDefaultCityName();
        }
        Set<String> firstPinyin = PinYinUtil.getFirstCharOfCityName(cityName);
        log.info("cityName: {}, firstPinyin: {}", cityName, firstPinyin);
        if (firstPinyin == null) {
            Set<String> temp = new HashSet<>();
            for (char c = 'A'; c <= 'Z'; c++) {
                temp.add(String.valueOf(c));
            }
            firstPinyin = temp;
        }

        List<Cities> citiesList = citiesRepository.findAll();
        if (CollectionUtils.isEmpty(citiesList)) {
            throw new ElmServerException("城市列表为空");
        }
        Cities cities = citiesList.get(0);

        for (String key : cities.getData().keySet()) {
            if (firstPinyin.contains(key)) {
                List<City> cityList = cities.getData().get(key);
                for (City city : cityList) {
                    if (city.getName().equals(cityName)) {
                        CityDto dto = new CityDto();
                        BeanUtils.copyProperties(city, dto);
                        return dto;
                    }
                }
            }
        }

        throw new ElmServerException("暂不支持该城市");
    }

    @Override
    public CityDto retrieveCityById(Integer id) {
        List<Cities> citiesList = citiesRepository.findAll();
        if (CollectionUtils.isEmpty(citiesList)) {
            throw new ElmServerException("城市列表为空");
        }
        Cities cities = citiesList.get(0);

        for (List<City> cityList : cities.getData().values()) {
            for (City city : cityList) {
                if (Objects.equals(id, city.getId())) {
                    CityDto dto = new CityDto();
                    BeanUtils.copyProperties(city, dto);
                    return dto;
                }
            }
        }
        throw new ElmServerException("城市ID错误");
    }

    @Override
    public List<SearchResultDto> retrieveSearchResult(Integer cityId, String keyword) {
        CityDto city = this.retrieveCityById(cityId);
        String name = city.getName();

        AmapPOIResponse response = this.amapService.poiSearch(name, keyword);
        return response.getPois().stream().map(poi -> {
            double[] pos = poi.getSplitLocation();
            return new SearchResultDto(poi.getName(), poi.getFullAddress(), pos[0], pos[1], poi.getLocation());
        }).toList();
    }

    private List<CityDto> modifyCityDtoList(List<City> entities) {
        return entities.stream()
                .map(city -> {
                    CityDto cityDto = new CityDto();
                    BeanUtils.copyProperties(city, cityDto);
                    return cityDto;
                }).toList();
    }
}
