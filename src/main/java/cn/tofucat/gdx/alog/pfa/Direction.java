package cn.tofucat.gdx.alog.pfa;

public enum Direction {
    UP(0, 1),
    DOWN(0, -1),
    LEFT(-1, 0),
    RIGHT(1, 0),
    LEFT_UP(-1, 1),
    LEFT_DOWN(-1, -1),
    RIGHT_UP(1, 1),
    RIGHT_DOWN(1, -1),
    ;

    public final int horizontal;
    public final int vertical;

    Direction(int horizontal, int vertical) {
        this.vertical = vertical;
        this.horizontal = horizontal;
    }
}
