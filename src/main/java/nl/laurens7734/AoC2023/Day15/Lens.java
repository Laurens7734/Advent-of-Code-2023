package nl.laurens7734.AoC2023.Day15;

public class Lens {
    private final String label;
    private int focalLength;

    public Lens(String label, int focalLength){
        this.focalLength = focalLength;
        this.label = label;
    }

    public String getLabel() {
        return label;
    }

    public int getFocalLength() {
        return focalLength;
    }

    public void setFocalLength(int focalLength) {
        this.focalLength = focalLength;
    }
}
