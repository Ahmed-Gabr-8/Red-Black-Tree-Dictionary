package red.black.tree.dictionary;

import java.awt.Point;

class RBNode {

    private static RBNode NIL = null;
    private String key;
    private boolean red;
    private RBNode left;
    private RBNode right;
    private RBNode parent;
    private Point graphPoint;

    private static void createNIL() {

        NIL = new RBNode();
        NIL.makeBlack();

    }

    public static RBNode getNil() {
        if (NIL == null) {
            createNIL();
        }

        return NIL;
    }

    private RBNode() {

    }

    public RBNode(String key) {
        this.key = key;
        this.red = true;
        this.left = getNil();
        this.right = getNil();
    } 
    
    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public boolean isRed() {
        return red;
    }

    public boolean isBlack() {
        return !red;
    }

    public void switchColor() {
        this.red = !this.red;

    }

    public void makeRed() {
        this.red = true;
    }

    public void makeBlack() {
        this.red = false;
    }

    public RBNode getLeft() {
        return left;
    }

    public void setLeft(RBNode left) {
        this.left = left;
    }

    public RBNode getRight() {
        return right;
    }

    public void setRight(RBNode right) {
        this.right = right;
    }

    public boolean isNIL() {
        return this == getNil();

    }

    public void setParent(RBNode parent) {
        this.parent = parent;

    }

    public RBNode getParent() {
        return this.parent;
    }

    public Point getGraphPoint() {
        return graphPoint;
    }

    public void setGraphPoint(Point graphPoint) {
        this.graphPoint = graphPoint;
    }

    public void addToGraph(TreeGraph graph, int upperHeight) {
        if (this.isNIL()) {
            return;
        }

        this.left.addToGraph(graph, upperHeight + 1);
        graph.addNode(this, upperHeight);
        this.right.addToGraph(graph, upperHeight + 1);

        graph.addEdge(this, this.right);
        graph.addEdge(this, this.left);

    }

    public int getNodeCount() {
        if (this.isNIL()) {
            return 0;
        }

        return 1 + this.right.getNodeCount() + this.left.getNodeCount();
    }

    public int getNodeHeight() {
        if (this.isNIL()) {
            return -1;
        }

        return 1 + Math.max(this.right.getNodeHeight(), this.left.getNodeHeight());
    }

}
