package nuricanozturk.dev.service.order.util;

import java.text.Normalizer;

public final class ConvertUtil
{
    private ConvertUtil()
    {
    }

    public static String convert(String str)
    {
        str = Normalizer.normalize(str, Normalizer.Form.NFD);
        return str.replaceAll("[^\\p{ASCII}]", "").trim().toUpperCase().replaceAll("\\s+", "_");
    }

    public static String convertInverse(String str)
    {
        return str.replaceAll("_", " ");
    }
}