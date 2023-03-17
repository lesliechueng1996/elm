package org.leslie.elm.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;
import java.util.Map;

/**
 * @author zhang
 * date created in 2023/3/17 00:27
 */

@Document("cities")
@Data
public class Cities {

    @Id
    private String id;
    private Map<String, List<City>> data;
}
