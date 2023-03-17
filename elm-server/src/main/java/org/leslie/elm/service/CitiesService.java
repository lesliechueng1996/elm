package org.leslie.elm.service;

import org.leslie.elm.dto.CityDto;
import org.leslie.elm.dto.SearchResultDto;

import java.util.List;
import java.util.Map;

/**
 * @author zhang
 * date created in 2023/3/17 00:42
 */
public interface CitiesService {

    Map<String, List<CityDto>> retrieveAllCitiesByGroup();

    List<CityDto> retrieveHotCities();

    CityDto retrieveGuessCity(String ip);

    CityDto retrieveCityById(Integer id);

    List<SearchResultDto> retrieveSearchResult(Integer cityId, String keyword);
}
