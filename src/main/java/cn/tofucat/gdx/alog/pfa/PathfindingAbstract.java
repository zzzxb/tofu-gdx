package cn.tofucat.gdx.alog.pfa;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;

import java.util.Optional;

/**
 *
 * @author zzzxb
 * 2025/9/10
 */
public abstract class PathfindingAbstract implements Pathfinding {
    protected float step = 1;

    @Override
    public abstract Optional<Array<Vector2>> find(PathfindingContext context);

    /**
     * vertical: up 1 down -1 eq 0
     * horizontal: left -1 right 1 eq 0
     */
    public Optional<Array<Vector2>> freePath(PathfindingContext c) {
        if (c.getStart().equals(c.getGoal())) return Optional.empty();
        int v = Float.compare(c.getGoal().y, c.getStart().y);
        int h = Float.compare(c.getGoal().x, c.getStart().x);
        return Optional.of(c.isZigzag() ? zigzag(c, v, h) : straight(c, v, h));
    }

    protected Array<Vector2> zigzag(PathfindingContext context, int vertical, int horizontal) {
        Array<Vector2> path = new Array<>();
        float x = context.getStart().x, y = context.getStart().y;
        int count = 0;
        do {
            boolean sk = ++count % 2 != 0;
            if ((sk || x == context.getGoal().x) && y != context.getGoal().y)
                y = y != context.getGoal().y ? y + (vertical * step) : y;
            else
                x = x != context.getGoal().x ? x + (horizontal * step) : x;
            path.add(new Vector2(x, y));
        } while (!context.getGoal().equals(path.peek()));
        return path;
    }

    protected Array<Vector2> straight(PathfindingContext context, int vertical, int horizontal) {
        Array<Vector2> path = new Array<>();
        float x = context.getStart().x, y = context.getStart().y;
        do {
            y = y != context.getGoal().y ? y + (vertical * step) : y;
            x = x != context.getGoal().x ? x + (horizontal * step) : x;
            path.add(new Vector2(x, y));
        } while (!context.getGoal().equals(path.peek()));
        return path;
    }

    /**
     * 之字线 生成4个方向
     * 斜直线 生成8个方向
     * @param zigzag true 走之字线, false 走斜直线
     */
    protected Array<Direction> getAdjacentPoints(boolean zigzag) {
        Array<Direction> directions = Array.with(Direction.UP, Direction.DOWN, cn.tofucat.gdx.alog.pfa.Direction.LEFT, cn.tofucat.gdx.alog.pfa.Direction.RIGHT);
        if(zigzag) directions.add(Direction.LEFT_UP, Direction.LEFT_DOWN, Direction.RIGHT_UP, Direction.RIGHT_DOWN);
        return directions;

    }

}