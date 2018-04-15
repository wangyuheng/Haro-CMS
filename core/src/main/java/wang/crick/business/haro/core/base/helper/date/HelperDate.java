package wang.crick.business.haro.core.base.helper.date;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class HelperDate {
    private static final SimpleDateFormat DEFAULT_DATE_SDF = new SimpleDateFormat("yyyy-MM-dd");
    private static final SimpleDateFormat DEFAULT_DATE_TIME_SDF = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    public static Date format(String format, String datetime)
    {
        try
        {
            return new SimpleDateFormat(format).parse(datetime);
        }
        catch (ParseException e)
        {
//            throw new TypeConvertException("Error parsing default null value date.  Format must be '" + format + "'. Cause: " + e);
        }
        return null;
    }

    public static String getDefaultDate(Date date)
    {
        return DEFAULT_DATE_SDF.format(date);
    }

    public static Date parseDefaultDate(String date)
    {
        try
        {
            return DEFAULT_DATE_SDF.parse(date);
        }
        catch (ParseException e)
        {
//            throw new Exception("Error parsing default null value date.  Format must be yyyy-MM-dd");
        }
        return null;
    }

    public static String getDefaultDateTime(Date date)
    {
        return DEFAULT_DATE_TIME_SDF.format(date);
    }

    public static Date parseDefaultDateTime(String date)
    {
        try
        {
            return DEFAULT_DATE_TIME_SDF.parse(date);
        }
        catch (ParseException e)
        {
//            throw new TypeConvertException("Error parsing default null value date.  Format must be yyyy-MM-dd HH:mm:ss");
        }
        return null;
    }
}