package cn.tofucat.gdx.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author zzzxb
 * 2025/9/2
 */
public final class StringUtils {
    public static final String STR_PLACEHOLDER_REGEX = "\\{}";
    public static final Pattern PATTERN_STR_FORMAT = Pattern.compile(STR_PLACEHOLDER_REGEX);

    /**
     * <blockquote><pre>
     * ""      -> true
     * " "     -> true
     * " abc " -> false
     * "abc"   -> false
     * </pre></blockquote>
     */
    public static boolean isBlank(String str) {
        return str == null || str.isEmpty() || str.trim().isEmpty();
    }

    /**
     * <blockquote><pre>
     * ""      -> false
     * " "     -> false
     * " abc " -> true
     * "abc"   -> true
     * </pre></blockquote>
     */
    public static boolean isNotBlank(String str) {
        return !isBlank(str);
    }

    /**
     * <blockquote><pre>
     * "", ""       -> ""
     * "abc", ""    -> "abc"
     * "", "efg"    -> "efg"
     * "abc", "efg" -> "abc"
     * </pre></blockquote>
     */
    public static String spare(String nowStr, String spareStr) {
        return isBlank(nowStr) ? spareStr : nowStr;
    }

    /**
     * <blockquote><pre>
     * "", "" -> ""
     * "abc", "" -> "abc"
     * "{}abc", "" -> "abc"
     * "{}abc", "123" -> "123abc"
     * "{}{}abc", "123" -> "123abc"
     * "{}{}abc", "123", "-" -> "123-abc"
     * </pre></blockquote>
     */
    public static String format(String str, Object... params) {
        if (isBlank(str) || CollectionUtils.isBlack(params) ||
                Pattern.matches(STR_PLACEHOLDER_REGEX, str)) return str;

        int num = 0;
        int count = params.length;
        String newStr = str;
        Matcher matcher = PATTERN_STR_FORMAT.matcher(newStr);
        while (matcher.find()) {
            newStr = matcher.replaceFirst(num < count ? String.valueOf(params[num++]) : "");
            matcher.reset(newStr);
        }
        return newStr;
    }
}
