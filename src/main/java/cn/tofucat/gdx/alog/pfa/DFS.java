package cn.tofucat.gdx.alog.pfa;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;

import java.util.Optional;

/**
 * 深度优先遍历，时间复杂度很可怕的一个遍历方式，图越大遍历时间越久
 * @author zzzxb
 * 2025/9/10
 */
public class DFS extends PathfindingAbstract {

    @Override
    public Optional<Array<Node>> find(PathfindingContext context) {
        if (context.getStart().equals(context.getGoal())) return Optional.empty();

        Array<Node> nodes = new Array<>();
        Node nowNode = new Node(context.getStart(), DEFAULT_FOUR_DIRECTION);
        do {
            if(nowNode.getDirections().isEmpty()) {
                nowNode = rollback(nodes);
                continue;
            }
            Node node = nextNode(nowNode, context.getGoal(), context.isZigzag());
            boolean collide = context.getBarriers().contains(node.getPosition(), false);
            boolean footprint = nodes.contains(node, false);
            boolean eqStart = node.getPosition().equals(context.getStart());
            if (!collide && !footprint && !eqStart) {
                nowNode = node;
                nodes.add(node);
            }
        } while (!context.getGoal().equals(nodes.peek().getPosition()));

        return Optional.of(nodes);
    }

    private Node nextNode(Node node, Vector2 goal, boolean zigzag) {
        Vector2 start = node.getPosition().cpy();
        Direction direction = node.getDirections().removeIndex(0);
        Vector2 v2 = move(start, goal, direction);
        return new Node(v2, DEFAULT_EIGHT_DIRECTION);
    }

    /**
     * 回滚到上个节点
     * @param nodes 已访问的节点组
     */
    private Node rollback(Array<Node> nodes) {
        Node pop = nodes.pop();
        return nodes.peek();
    }

    private Vector2 move(Vector2 start, Vector2 goal, Direction direction) {
        if (direction != Direction.UP && direction != Direction.DOWN) {
            moveX(start, goal, direction.horizontal);
        }
        if (direction != Direction.LEFT && direction != Direction.RIGHT) {
            moveY(start, goal, direction.vertical);
        }
        return start.cpy();
    }
}
