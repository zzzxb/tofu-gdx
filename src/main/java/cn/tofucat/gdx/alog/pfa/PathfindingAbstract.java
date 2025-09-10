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
        // up 1 down -1 eq 0
        int vertical = Float.compare(goal.y, start.y);
        // left -1 right 1 eq 0
        int horizontal = Float.compare(goal.x, start.x);
        float x = start.x, y = start.y;
        do {
            y = y != goal.y ? y + (vertical * step) : y;
            x = x != goal.x ? x + (horizontal * step) : x;
            path.add(new Vector2(x, y));
        } while (!path.get(path.size - 1).equals(goal));
        return path;
    }
}