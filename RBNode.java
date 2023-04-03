package red.black.tree.dictionary;

import java.awt.Point;

class RBNode {

    private static RBNode NIL = null;
    private int key;
    private boolean red;
    private RBNode left;
    private RBNode right;
    private RBNode parent;
    private Point graphPoint;
    private Point graphIndex;
    private static void createNIL() {

        NIL = new RBNode();
        NIL.makeBlack();

    }

    private static RBNode getNil() {
        if (NIL == null) {
            createNIL();
        }

        return NIL;
    }

    private RBNode() {

    }

    public RBNode(int key) {
        this.key = key;
        this.red = true;
        this.left = getNil();
        this.right = getNil();
    }

    public int getKey() {
        return key;
    }

    public void setKey(int key) {
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

    public RBNode getSibling() {

        if (this.isNIL() || this.parent == null) {
            return RBNode.getNil();
        }

        if (this == this.parent.left) {

            return this.parent.right;
        } else {
            return this.parent.left;
        }

    }

    public RBNode getUncle() {
        if (this.isNIL() || this.parent == null) {
            return RBNode.getNil();
        }
        
        return this.parent.getSibling();
    }

    public Point getGraphPoint() {
        return graphPoint;
    }

    public void setGraphIndex(Point graphIndex, int nodeSize, Point offset) {
        this.graphIndex = graphIndex;      
        this.updateGraphPoint(nodeSize, offset);
    }
    
    
    public void updateGraphPoint(int nodeSize, Point offset){
        this.graphPoint = new Point(graphIndex.x*nodeSize+offset.x, graphIndex.y*nodeSize + offset.y);
    
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

}
