package cn.tofucat.gdx.util;

import java.util.Collection;

/**
 *
 * @author zzzxb
 * 2025/9/2
 */
public final class CollectionUtils {

    /**
     * <blockquote><pre>
     * null     -> true
     * []       -> true
     * ["abc"]  -> false
     * ["", ""] -> false
     * </pre></blockquote><p>
     */
    public static boolean isBlack(Object[] objects) {
        return objects == null || objects.length  == 0;
    }

    /**
     * <blockquote><pre>
     * null     -> true
     * []       -> true
     * ["abc"]  -> false
     * ["", ""] -> false
     * </pre></blockquote><p>
     */
    public static boolean isBlack(Collection<?> collection) {
        return collection == null || collection.isEmpty();
    }
}
