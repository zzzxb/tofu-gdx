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
    /**
     * 步距, 每走一次的长度
     */
    protected float step = 1;
    protected final static Array<Direction> DEFAULT_FOUR_DIRECTION;
    protected final static Array<Direction> DEFAULT_EIGHT_DIRECTION;

    static {
        DEFAULT_FOUR_DIRECTION = Array.with(Direction.UP, Direction.DOWN, Direction.LEFT, Direction.RIGHT);
        DEFAULT_EIGHT_DIRECTION = Array.with(DEFAULT_FOUR_DIRECTION.toArray());
        DEFAULT_EIGHT_DIRECTION.add(Direction.LEFT_UP, Direction.LEFT_DOWN, Direction.RIGHT_UP, Direction.RIGHT_DOWN);
    }

    @Override
    public abstract Optional<Array<Node>> find(PathfindingContext context);

    public void moveY(Vector2 start, Vector2 goal, int vertical) {
        start.y += vertical * step;
    }

    public void moveX(Vector2 start, Vector2 goal, int horizontal) {
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
}