package cn.tofucat.gdx;

import cn.tofucat.gdx.alog.pfa.DFS;
import cn.tofucat.gdx.alog.pfa.PathfindingAbstract;
import cn.tofucat.gdx.alog.pfa.PathfindingContext;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
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
    public void dfs() {
        PathfindingContext context = new PathfindingContext(new Vector2(5, 5), new Vector2(10, 10));
        context.setBarriers(toV2S(
                "11,11", "11,10", "11,9", "11,8", "11,7", "11,6", "11,5", "11,4", "11,3", "11,2", "11,1", "11,0",
                "0,11", "1,11", "2,11", "3,11", "4,11", "5,11", "6,11", "7,11", "8,11", "9,11", "10,11",
                "0,0", "0,1", "0,2", "0,3", "0,4", "0,5", "0,6", "0,7", "0,8", "0,9", "0,10",
                "1,0", "2,0", "3,0", "4,0", "5,0", "6,0", "7,0", "8,0", "9,0", "10,0"
        ));
        Optional<Array<Vector2>> optional = pa.find(context);
        System.out.println(optional.orElseThrow());
    }

    @Test
    public void freePath() {
        boolean zigzag = false;
        do {
            zigzag = !zigzag;
            System.out.format("---------------------------- zigzag: %s ------------------------------------\n", zigzag);
            freePath("UP", new Vector2(1, 1), new Vector2(1, 2), zigzag);
            freePath("DOWN", new Vector2(1, 1), new Vector2(1, 0), zigzag);
            freePath("LEFT", new Vector2(1, 1), new Vector2(0, 1), zigzag);
            freePath("RIGHT", new Vector2(1, 1), new Vector2(2, 1), zigzag);
            freePath("LEFT UP", new Vector2(1, 1), new Vector2(0, 2), zigzag);
            freePath("LEFT DOWN", new Vector2(1, 1), new Vector2(0, 0), zigzag);
            freePath("RIGHT UP", new Vector2(1, 1), new Vector2(2, 2), zigzag);
            freePath("RIGHT DOWN", new Vector2(1, 1), new Vector2(2, 0), zigzag);
        } while (zigzag);
    }

    private Array<Vector2> toV2S(String... args) {
        Array<Vector2> v2s = new Array<>();
        for (String arg : args) {
            String[] split = arg.split(",");
            v2s.add(new Vector2(Float.parseFloat(split[0]), Float.parseFloat(split[1])));
        }
        return v2s;
    }

    private void freePath(String desc, Vector2 s, Vector2 g, boolean zigzag) {
        PathfindingContext context = new PathfindingContext(s, g, zigzag);
        Optional<Array<Vector2>> optional = pa.freePath(context);
        System.out.format("[%s] start: %s goal: %s path: %s\n", desc, s, g, optional.orElseThrow());
    }
}
