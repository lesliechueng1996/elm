package org.leslie.elm.enums;

/**
 * @author zhang
 * date created in 2023/3/17 00:53
 */
public enum CityType {
    GUESS("guess"),
    HOT("hot"),
    GROUP("group");


    public final String type;

    CityType(String type) {
        this.type = type;
    }

    public static boolean isValidType(String type) {
        for (CityType cityType : CityType.values()) {
            if (cityType.type.equals(type)) {
                return true;
            }
        }
        return false;
    }
}
