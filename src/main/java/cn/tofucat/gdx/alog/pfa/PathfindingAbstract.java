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
    protected int totalSteps = 0;
    protected final static Array<Direction> DEFAULT_FOUR_DIRECTION;
    protected final static Array<Direction> DEFAULT_EIGHT_DIRECTION;

    static {
        DEFAULT_FOUR_DIRECTION = Array.with(Direction.UP, Direction.DOWN, Direction.LEFT, Direction.RIGHT);
        DEFAULT_EIGHT_DIRECTION = Array.with(DEFAULT_FOUR_DIRECTION.toArray());
        DEFAULT_EIGHT_DIRECTION.add(Direction.LEFT_UP, Direction.LEFT_DOWN, Direction.RIGHT_UP, Direction.RIGHT_DOWN);
    }

    @Override
    public abstract Optional<Array<Vector2>> find(PathfindingContext context);

    /**
     * vertical: up 1 down -1 eq 0
     * horizontal: left -1 right 1 eq 0
     */
    public Optional<Array<Vector2>> freePath(PathfindingContext c) {
        if (c.getStart().equals(c.getGoal())) return Optional.empty();
        int h = Float.compare(c.getGoal().x, c.getStart().x);
        int v = Float.compare(c.getGoal().y, c.getStart().y);
        Vector2 cpStart = c.getStart().cpy();
        Array<Vector2> path = new Array<>();
        do {
            totalSteps++;
            Vector2 v2 = c.isZigzag() ? zigzag(cpStart, c.getGoal(), h, v) : straight(cpStart, c.getGoal(), h, v);
            path.add(v2);
        } while (!c.getGoal().equals(path.peek()));
        return Optional.of(path);
    }

    protected Vector2 move(Vector2 start, Vector2 goal, Direction direction) {
        if (direction != Direction.UP && direction != Direction.DOWN) {
            moveX(start, goal, direction.horizontal);
        }
        if (direction != Direction.LEFT && direction != Direction.RIGHT) {
            moveY(start, goal, direction.vertical);
        }
        return start.cpy();
    }

    protected void moveY(Vector2 start, Vector2 goal, int vertical) {
        start.y += vertical * step;
    }

    protected void moveX(Vector2 start, Vector2 goal, int horizontal) {
        start.x += horizontal * step;
    }

    /**
     * 计算两点之间距离
     * -1 -> -2  = 1
     * 1 -> 2 = 1
     * -2 -> 2 = 4
     */
    protected float between(float a, float b) {
        return Math.abs(a < 0 & b < 0 || a > 0 && b > 0 ? a - b : a + b);
    }

    private Vector2 zigzag(Vector2 start, Vector2 goal, int horizontal, int vertical) {
        boolean sk = totalSteps % 2 != 0;
        if ((sk || start.x == goal.x) && start.y != goal.y) {
            start.y = start.y != goal.y ? between(goal.y, start.y) < step ? goal.y : start.y + (vertical * step) : start.y;
        } else {
            start.x = start.x != goal.x ? between(goal.x, start.x) < step ? goal.x : start.x + (horizontal * step) : start.x;
        }
        return start.cpy();
    }

    private Vector2 straight(Vector2 start, Vector2 goal, int horizontal, int vertical) {
        start.y = start.y != goal.y ? between(goal.y, start.y) < step ? goal.y : start.y + (vertical * step) : start.y;
        start.x = start.x != goal.x ? between(goal.x, start.x) < step ? goal.x : start.x + (horizontal * step) : start.x;
        return start.cpy();
    }
}