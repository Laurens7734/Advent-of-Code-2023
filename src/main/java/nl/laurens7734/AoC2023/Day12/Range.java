package nl.laurens7734.AoC2023.Day12;

public class Range {
    private int leftMost;
    private int rightMost;
    private int size;

    public Range(int left, int right, int size){
        leftMost = left;
        rightMost = right;
        this.size = size;
    }

    public boolean hasKnownFields(){
        //size minus 1 because left and right are indexes
        return rightMost-(size-1) < leftMost+(size-1);
    }

    public int startOfKnown(){
        return rightMost-(size-1);
    }

    public int endOfKnown(){
        return leftMost+(size-1);
    }

    public int getLeftMost() {
        return leftMost;
    }

    public boolean setLeftMost(int leftMost) {
        if(leftMost > this.leftMost){
            this.leftMost = leftMost;
            return true;
        }
            return false;
    }

    public int getRightMost() {
        return rightMost;
    }

    public boolean setRightMost(int rightMost) {
        if(rightMost < this.rightMost){
            this.rightMost = rightMost;
            return true;
        }
        return false;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public boolean checkGap(int gap){
        if(gap < leftMost || gap > rightMost)
            return false;
        if(leftMost+size-1 >= gap){
            leftMost = gap+1;
            return true;
        }
        if(rightMost-(size-1) <= gap){
            rightMost = gap-1;
            return true;
        }
        return false;
    }
}
