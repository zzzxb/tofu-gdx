package cn.tofucat.gdx;

import cn.tofucat.gdx.alog.pfa.DFS;
import cn.tofucat.gdx.alog.pfa.PathfindingAbstract;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import org.junit.jupiter.api.Test;

/**
 *
 * @author zzzxb
 * 2025/9/10
 */
public class PathfindingTests {

    @Test
    public void freePath() {
        PathfindingAbstract pa = new DFS();
        Array<Vector2> up = pa.freePath(new Vector2(5, 5), new Vector2(5, 10));
        System.out.println("up: " + up);

        Array<Vector2> down = pa.freePath(new Vector2(5, 5), new Vector2(5, 0));
        System.out.println("down: " + down);

        Array<Vector2> left = pa.freePath(new Vector2(5, 5), new Vector2(0, 5));
        System.out.println("left: " + left);

        Array<Vector2> right = pa.freePath(new Vector2(5, 5), new Vector2(10, 5));
        System.out.println("right:" + right);

        Array<Vector2> lu = pa.freePath(new Vector2(5, 5), new Vector2(0, 10));
        System.out.println("left up:" + lu);

        Array<Vector2> ld = pa.freePath(new Vector2(5, 5), new Vector2(0, 0));
        System.out.println("left down:" + ld);

        Array<Vector2> ru = pa.freePath(new Vector2(5, 5), new Vector2(10, 10));
        System.out.println("right up:" + ru);

        Array<Vector2> rd = pa.freePath(new Vector2(5, 5), new Vector2(10, 0));
        System.out.println("right down:" + rd);
    }
}
