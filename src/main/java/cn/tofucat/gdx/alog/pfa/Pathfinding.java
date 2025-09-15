package cn.tofucat.gdx.alog.pfa;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;

import java.util.Optional;

/**
 *
 * @author zzzxb
 * 2025/9/10
 */
public interface Pathfinding {

    Optional<Array<Node>> find(PathfindingContext context);

    /**
     * @param horizontal left -1 right 1 eq 0
     */
    void moveX(Vector2 start, Vector2 goal, int horizontal);

    /**
     * @param vertical up 1 down -1 eq 0
     */
    void moveY(Vector2 start, Vector2 goal, int vertical);
}
