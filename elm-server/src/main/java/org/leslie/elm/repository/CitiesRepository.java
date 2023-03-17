package org.leslie.elm.repository;

import org.leslie.elm.entity.Cities;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * @author zhang
 * date created in 2023/3/17 00:41
 */
public interface CitiesRepository extends MongoRepository<Cities, String> {
}
