package nl.laurens7734.AoC2023.Day10;

public class Pipe {
    private int x;
    private int y;
    private char direction;

    public Pipe(int x, int y, char direction){
        this.x = x;
        this.y = y;
        this.direction = direction;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public char getDirection() {
        return direction;
    }

    @Override
    public boolean equals(Object o){
        if(o == null)
            return false;
        if (o.getClass() != this.getClass()) {
            return false;
        }

        final Pipe other = (Pipe) o;
        return other.x == x && other.y == y;
    }
}
