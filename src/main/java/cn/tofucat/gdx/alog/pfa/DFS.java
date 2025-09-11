package cn.tofucat.gdx.alog.pfa;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;

import java.util.Optional;

/**
 *
 * @author zzzxb
 * 2025/9/10
 */
public class DFS extends PathfindingAbstract {

    @Override
    public Optional<Array<Vector2>> find(PathfindingContext context) {
        if (context.getStart().equals(context.getGoal())) return Optional.empty();

        Array<Node> nodes = new Array<>();
        Node nowPoint = new Node(context.getStart(), getAdjacentPoints(context.isZigzag()));
        do {
            Node node = nextNode(nowPoint, context.getGoal());
            if (context.getBarriers().contains(node.getPosition(), false)) {
                nowPoint.getDirections().removeIndex(0);
                if (nowPoint.getDirections().isEmpty()) {
                    nowPoint = rollback(nodes);
                }
                continue;
            }
            nodes.add(nowPoint = node);
        } while (!context.getGoal().equals(nodes.peek().getPosition()));

        return Optional.empty();
    }

    private Node nextNode(Node node, Vector2 goal) {
        // TODO
        return null;
    }

    private Node rollback(Array<Node> nodes) {
        return nodes.removeIndex(nodes.size - 1);
    }
}
