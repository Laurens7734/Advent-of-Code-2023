package nl.laurens7734.AoC2023.Day8;

public class Node {
    private Node left;
    private Node right;
    private final String name;
    private final String leftName;
    private final String rightName;

    public Node(String name, String leftName, String rightName){
        this.name = name;
        this.leftName = leftName;
        this.rightName = rightName;
    }

    public Node getLeft() {
        return left;
    }

    public Node getRight() {
        return right;
    }

    public String getName() {
        return name;
    }

    public String getLeftName() {
        return leftName;
    }

    public String getRightName() {
        return rightName;
    }

    public void setLeft(Node left) {
        this.left = left;
    }

    public void setRight(Node right) {
        this.right = right;
    }
}
