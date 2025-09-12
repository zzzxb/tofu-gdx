package cn.tofucat.gdx.alog.pfa;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;

/**
 *
 * @author zzzxb
 * 2025/9/11
 */
public class PathfindingContext {
    private Vector2 start;
    private Vector2 goal;
    private Array<Vector2> barriers;
    private boolean zigzag = true;

    public PathfindingContext(Vector2 start, Vector2 goal) {
        this(start, goal, true);
    }

    public PathfindingContext(Vector2 start, Vector2 goal, boolean zigzag) {
        this(start, goal, new Array<>(), zigzag);
    }

    public PathfindingContext(Vector2 start, Vector2 goal, Array<Vector2> barriers, boolean zigzag) {
        this.barriers = barriers;
        this.goal = goal;
        this.start = start;
        this.zigzag = zigzag;
    }

    public Array<Vector2> getBarriers() {
        return barriers;
    }

    public void setBarriers(Array<Vector2> barriers) {
        this.barriers = barriers;
    }

    public Vector2 getGoal() {
        return goal;
    }

    public void setGoal(Vector2 goal) {
        this.goal = goal;
    }

    public Vector2 getStart() {
        return start;
    }

    public void setStart(Vector2 start) {
        this.start = start;
    }

    public boolean isZigzag() {
        return zigzag;
    }

    public void setZigzag(boolean zigzag) {
        this.zigzag = zigzag;
    }
}
