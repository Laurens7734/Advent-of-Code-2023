package nl.laurens7734.AoC2023.Day11;

public class Galaxy {
    private int x;
    private int y;

    public Galaxy(int x, int y){
        this.x = x;
        this.y = y;
    }

    public void expandX(int location, int amount){
        if(location < x)
            x += amount;
    }

    public void expandY(int location, int amount){
        if(location < y)
            y += amount;
    }

    public int distance(Galaxy other){
        int xDistance = Math.abs(other.x-x);
        int yDistance = Math.abs(other.y-y);
        return xDistance+yDistance;
    }
}
