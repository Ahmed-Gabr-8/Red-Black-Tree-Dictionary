package red.black.tree.dictionary;

import javax.swing.JOptionPane;

public class RBTree {

    private RBNode root;

    public RBTree(int root_key) {
        this.root = new RBNode(root_key);
        root.makeBlack();
    }

    public void leftRotate(RBNode n) {
        RBNode rightChild = n.getRight();
        if (rightChild.isNIL()) {
            return;
        }
        n.setRight(rightChild.getLeft());

        if (rightChild.getLeft().isNIL() == false) {
            rightChild.getLeft().setParent(n);
        }
        rightChild.setParent(n.getParent());

        if (n == root) {
            root = rightChild;
        } else if (n.getParent().getLeft() == n) {
            n.getParent().setLeft(rightChild);
        } else {
            n.getParent().setRight(rightChild);
        }

        rightChild.setLeft(n);
        n.setParent(rightChild);
    }

    public void rightRotate(RBNode n) {
        RBNode leftChild = n.getLeft();
        if (leftChild.isNIL()) {
            return;
        }
        n.setLeft(leftChild.getRight());

        if (leftChild.getRight().isNIL() == false) {
            leftChild.getRight().setParent(n);
        }
        leftChild.setParent(n.getParent());

        if (n == root) {
            root = leftChild;
        } else if (n.getParent().getLeft() == n) {
            n.getParent().setLeft(leftChild);
        } else {
            n.getParent().setRight(leftChild);
        }

        leftChild.setRight(n);
        n.setParent(leftChild);
    }

    public void insert(int key) {
        RBNode newNode = new RBNode(key);
        RBNode prevParent = this.root;
        RBNode currentParent = this.root;

        while (!currentParent.isNIL()) {
            if (newNode.getKey() > currentParent.getKey()) {
                prevParent = currentParent;
                currentParent = currentParent.getRight();
            } else if (newNode.getKey() < currentParent.getKey()) {
                prevParent = currentParent;
                currentParent = currentParent.getLeft();

            } else {
                JOptionPane.showMessageDialog(null, "ERROR: Word already in the dictionary!");
                return;

            }

        }

        if (newNode.getKey() > prevParent.getKey()) {
            prevParent.setRight(newNode);
        } else {
            prevParent.setLeft(newNode);
        }

        newNode.setParent(prevParent);

    }

    public RBNode getRoot() {
        return root;
    }

    public void display() {
        TreeGraph graph = new TreeGraph();
        this.root.addToGraph(graph, 0);
        TreeGraph.showTree(graph);
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            System.out.println("Interruped Exception occured.");
        }
    }

    public int getCount() {
        if (this.root == null) {
            return 0;
        }
        return this.root.getNodeCount();

    }
    
    
    public int getHeight(){
        if(this.root == null){
            return -1;
        }
        
        return this.root.getNodeHeight();
    }
    
    }
