package cn.tofucat.gdx.alog.pfa;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;

public class Node {
    private Vector2 position;
    private Array<Direction> directions;

    public Node(Vector2 position, Array<Direction> directions) {
        this.position = position.cpy();
        this.directions = new Array<>(directions);
    }

    public Vector2 getPosition() {
        return position;
    }

    public void setPosition(Vector2 position) {
        this.position = position;
    }

    public Array<Direction> getDirections() {
        return directions;
    }

    public void setDirections(Array<Direction> directions) {
        this.directions = directions;
    }

    public Node cpy() {
        return new Node(this.position.cpy(), new Array<>());
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Node node)) {
            return false;
        }
        return node.position != null && node.position.equals(position);
    }
}
