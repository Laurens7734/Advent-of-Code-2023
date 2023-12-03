package nl.laurens7734.AoC2023.Day3;

public class PartNumber {
    private int x;
    private int y;
    private String digits;
    private boolean added;

    public PartNumber(int x, int y){
        this.x = x;
        this.y = y;
        digits = "";
        added = false;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public String getDigits() {
        return digits;
    }

    public void setDigits(String digits) {
        this.digits = digits;
    }

    public void addDigit(char digit){
        digits += digit;
    }

    public boolean isAdded() {
        return added;
    }

    public void setAdded(boolean added) {
        this.added = added;
    }

    public boolean isNextTo(Coordinate c){
        if(c.getY() < y-1 || c.getY() > y+1)
            return false;
        if(c.getX() < x-1 || c.getX() > x+digits.length())
            return false;
        return true;
    }

    public int getValue(){
        return Integer.parseInt(digits);
    }
}
