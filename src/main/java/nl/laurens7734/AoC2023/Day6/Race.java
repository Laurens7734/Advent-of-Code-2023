package nl.laurens7734.AoC2023.Day6;

public class Race {
    private long time;
    private long distance;

    public Race(long time, long distance){
        this.time = time;
        this.distance = distance;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public long getDistance() {
        return distance;
    }

    public void setDistance(long distance) {
        this.distance = distance;
    }
}
