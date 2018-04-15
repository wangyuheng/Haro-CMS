package wang.crick.business.haro.core.base.converter;

import org.springframework.core.convert.TypeDescriptor;
import org.springframework.core.convert.converter.GenericConverter;
import wang.crick.business.haro.core.base.helper.date.HelperDate;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * 日期转换器
 */
public class StringToDateConverter implements GenericConverter {

    private static final Set<GenericConverter.ConvertiblePair> convertiblePair = new HashSet<>();

    static {
        GenericConverter.ConvertiblePair stringDatePair = new GenericConverter.ConvertiblePair(String.class, Date.class);
        convertiblePair.add(stringDatePair);
    }

    public Object convert(Object source, TypeDescriptor sourceType, TypeDescriptor targetType) {
        Date result = null;
        String realSource = (String) source;
        int len = realSource.trim().length();
        if (len == 10) {
            result = HelperDate.parseDefaultDate(realSource);
        } else if (len == 19) {
            result = HelperDate.parseDefaultDateTime(realSource);
        }
        return result;
    }

    public Set<GenericConverter.ConvertiblePair> getConvertibleTypes() {
        return convertiblePair;
    }
}
