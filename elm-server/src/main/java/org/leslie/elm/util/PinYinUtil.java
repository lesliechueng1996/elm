package org.leslie.elm.util;

import lombok.extern.slf4j.Slf4j;
import net.sourceforge.pinyin4j.PinyinHelper;
import net.sourceforge.pinyin4j.format.HanyuPinyinCaseType;
import net.sourceforge.pinyin4j.format.HanyuPinyinOutputFormat;
import net.sourceforge.pinyin4j.format.exception.BadHanyuPinyinOutputFormatCombination;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author zhang
 * date created in 2023/3/18 00:06
 */
@Slf4j
public class PinYinUtil {

    public static Set<String> getFirstCharOfCityName(String cityName) {
        HanyuPinyinOutputFormat format = new HanyuPinyinOutputFormat();
        format.setCaseType(HanyuPinyinCaseType.UPPERCASE);
        try {
            String[] pinyin = PinyinHelper.toHanyuPinyinStringArray(cityName.charAt(0), format);
            return Arrays.stream(pinyin).map(item -> String.valueOf(item.charAt(0))).collect(Collectors.toSet());
        } catch (BadHanyuPinyinOutputFormatCombination e) {
            log.error("{} to pin yin error >>>", cityName, e);
            return null;
        }
    }
}
