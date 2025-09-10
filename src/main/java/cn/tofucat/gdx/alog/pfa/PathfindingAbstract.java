package cn.tofucat.gdx.alog.pfa;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;

/**
 *
 * @author zzzxb
 * 2025/9/10
 */
public abstract class PathfindingAbstract implements Pathfinding {
    protected float step = 1;

    @Override
    public abstract Array<Vector2> find(Vector2 start, Vector2 goal, Array<Vector2> barriers);

    /**
     */
    public Array<Vector2> freePath(Vector2 start, Vector2 goal) {
        Array<Vector2> path = new Array<>();
        float sx = start.x, sy = start.y, gx = goal.x, gy = goal.y;
        // up 1 down -1 eq 0
        int v = Float.compare(gy, sy);
        // left -1 right 1 eq 0
        int h = Float.compare(gx, sx);
        do {
            sy = sy != gy ? sy + (v * step) : gy;
            sx = sx != gx ? sx + (h * step) : sx;
            path.add(new Vector2(sx, sy));
        } while (sx != gx || sy != gy);
        return path;
    }
}
