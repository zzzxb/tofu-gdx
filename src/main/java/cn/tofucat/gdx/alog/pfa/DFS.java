package cn.tofucat.gdx.alog.pfa;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import org.w3c.dom.ls.LSOutput;

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
        Node nowNode = new Node(context.getStart(), DEFAULT_EIGHT_DIRECTION);
        do {
            System.out.println(nodes.size + " - " + nowNode.getPosition());
            Node node = nextNode(nowNode, context.getGoal(), context.isZigzag());
            if (context.getBarriers().contains(node.getPosition(), false) ||
                    nodes.contains(node, false) || node.getPosition().equals(context.getStart())) {
                if (nowNode.getDirections().isEmpty()) {
                    nowNode = rollback(nodes);
                    System.out.println("滚了吧");
                }
                continue;
            }
            nowNode = node;
            nodes.add(node);
        } while (!context.getGoal().equals(nodes.peek().getPosition()));

        return Optional.empty();
    }

    private Node nextNode(Node node, Vector2 goal, boolean zigzag) {
        Vector2 start = node.getPosition().cpy();
        Direction direction = node.getDirections().removeIndex(0);
        Vector2 v2 = move(start, goal, direction);
        return new Node(v2, DEFAULT_EIGHT_DIRECTION);
    }

    private Node rollback(Array<Node> nodes) {
        // 丢弃这个当前节点, 回滚到当前节点的上个节点
        nodes.pop();
        return nodes.peek();
    }
}
