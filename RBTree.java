package red.black.tree.dictionary;

public class RBTree {

    private RBNode root;

    public RBTree(int root_key) {
        this.root = new RBNode(root_key);
        root.makeBlack();
    }

    public void insert(int key) {
        RBNode newNode = new RBNode(key);
        RBNode prevParent = this.root;
        RBNode currentParent = this.root;

        while (!currentParent.isNIL()) {
            if (newNode.getKey() > currentParent.getKey()) {
                prevParent = currentParent;
                currentParent = currentParent.getRight();
            } else {
                prevParent = currentParent;
                currentParent = currentParent.getLeft();

            }

        }
        
        if(newNode.getKey() > prevParent.getKey()){
            prevParent.setRight(newNode);
        }
        
        else {
            prevParent.setLeft(newNode);
        }
        
        newNode.setParent(prevParent);

    }

    public RBNode getRoot() {
        return root;
    }
    
    public void display(){
        TreeGraph graph = new TreeGraph();
        this.root.addToGraph(graph, 0);
        TreeGraph.showTree(graph);
    }          

}
