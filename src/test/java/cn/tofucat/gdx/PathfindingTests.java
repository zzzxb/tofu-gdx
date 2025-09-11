package cn.tofucat.gdx;

import cn.tofucat.gdx.alog.pfa.DFS;
import cn.tofucat.gdx.alog.pfa.PathfindingAbstract;
import cn.tofucat.gdx.alog.pfa.PathfindingContext;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Optional;

/**
 *
 * @author zzzxb
 * 2025/9/10
 */
public class PathfindingTests {
    PathfindingAbstract pa = new DFS();

    @Test
    public void freePath() {
        boolean zigzag = false;
        freePath(new Vector2(1, 1), new Vector2(1, 2), zigzag, "[(1.0,2.0)]");
        freePath(new Vector2(1, 1), new Vector2(1, 0), zigzag, "[(1.0,0.0)]");
        freePath(new Vector2(1, 1), new Vector2(0, 1), zigzag, "[(0.0,1.0)]");
        freePath(new Vector2(1, 1), new Vector2(2, 1), zigzag, "[(2.0,1.0)]");
        freePath(new Vector2(1, 1), new Vector2(0, 2), zigzag, "[(0.0,2.0)]");
        freePath(new Vector2(1, 1), new Vector2(0, 0), zigzag, "[(0.0,0.0)]");
        freePath(new Vector2(1, 1), new Vector2(2, 2), zigzag, "[(2.0,2.0)]");
        freePath(new Vector2(1, 1), new Vector2(2, 0), zigzag, "[(2.0,0.0)]");
    }

    private void freePath(Vector2 s, Vector2 g, boolean zigzag, String result) {
        PathfindingContext context = new PathfindingContext(s, g, zigzag);
        Optional<Array<Vector2>> optional = pa.freePath(context);
        System.out.format("> start: %s goal: %s path: %s\n", s, g, optional.orElseThrow());
        Assertions.assertEquals(result, optional.get().toString());
    }
}
