package cn.tofucat.gdx.alog.pfa;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;

import java.util.Optional;

/**
 * 无障碍搜索， 起点到终点的距离
 * @author zzzxb
 * 2025/9/15
 */
public class FreePath extends PathfindingAbstract {
    private int count = 0;

    @Override
    public Optional<Array<Node>> find(PathfindingContext c) {
        if (c.getStart().equals(c.getGoal())) return Optional.empty();
        int h = Float.compare(c.getGoal().x, c.getStart().x);
        int v = Float.compare(c.getGoal().y, c.getStart().y);
        Vector2 cpStart = c.getStart().cpy();
        Array<Node> path = new Array<>();
        do {
            count++;
            Vector2 v2 = c.isZigzag() ? zigzag(cpStart, c.getGoal(), h, v) : straight(cpStart, c.getGoal(), h, v);
            path.add(new Node(v2));
        } while (!c.getGoal().equals(path.peek().getPosition()));
        return Optional.of(path);
    }

    private Vector2 zigzag(Vector2 start, Vector2 goal, int horizontal, int vertical) {
        boolean sk = count % 2 != 0;
        if ((sk || start.x == goal.x) && start.y != goal.y) {
            moveY(start, goal, vertical);
        } else {
            moveX(start, goal, horizontal);
        }
        return start.cpy();
    }

    private Vector2 straight(Vector2 start, Vector2 goal, int horizontal, int vertical) {
        moveX(start, goal, horizontal);
        moveY(start, goal, vertical);
        return start.cpy();
    }

    @Override
    public void moveX(Vector2 start, Vector2 goal, int horizontal) {
        start.x = start.x != goal.x ? between(goal.x, start.x) < step ? goal.x : start.x + (horizontal * step) : start.x;
    }

    @Override
    public void moveY(Vector2 start, Vector2 goal, int vertical) {
        start.y = start.y != goal.y ? between(goal.y, start.y) < step ? goal.y : start.y + (vertical * step) : start.y;
    }
}
