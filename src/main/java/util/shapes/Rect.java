package util.shapes;

public class Rect {

    private final int x;
    private final int y;
    private final int l;
    private final int w;

    public Rect(int x, int y, int l, int w) {
        this.x = x;
        this.y = y;
        this.l = l;
        this.w = w;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getL() {
        return l;
    }

    public int getW() {
        return w;
    }
}
