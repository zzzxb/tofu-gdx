package cn.tofucat.gdx;

import cn.tofucat.gdx.alog.pfa.*;
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
    PathfindingAbstract pathfinding;

    @Test
    public void dfs() {
        pathfinding = new DFS();
        PathfindingContext context = new PathfindingContext(new Vector2(2, 1), new Vector2(3, 3));
        context.setBarriers(toV2S("0,0", "1,0", "2,0", "3,0", "4,0",
                "0,4", "1,4", "2,4", "3,4", "4,4", "0,1", "0,2", "0,3", "4,1", "4,2", "4,3"));
        Optional<Array<Node>> nodes = pathfinding.find(context);
        System.out.println(nodes);
    }

    @Test
    public void freePath() {
        pathfinding = new FreePath();
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
        Optional<Array<Node>> nodes = pathfinding.find(context);
        System.out.format("[%s] start: %s goal: %s path: %s\n", desc, s, g, nodes.orElseThrow());
    }
}
