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

    public void insert(int key, boolean fix) {

        RBNode newNode = new RBNode(key);
        if (this.root == null) {
            this.root = newNode;
            this.root.makeBlack();
        }
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

        if (fix) {
            this.insertFix(newNode);

        }

    }

    public void insert(int key) {

        this.insert(key, true);

    }

    public void insertFix(RBNode insertedNode) {

        RBNode parent = insertedNode.getParent();
        RBNode grandParent = parent.getParent();
        RBNode uncle = parent.getSibling();

        if (parent.isBlack()) {

            //Do nothing
        } else if (uncle.isRed()) {
            grandParent.makeRed();
            parent.makeBlack();
            uncle.makeBlack();

            if (grandParent == this.root) {
                grandParent.makeBlack();

            } else {

                this.insertFix(grandParent);

            }

        } else {
            if (parent == grandParent.getLeft()) {

                if (insertedNode == parent.getRight()) {
                    this.leftRotate(parent);
                    parent.switchColor();
                    insertedNode.switchColor();
                }
                
                parent.switchColor();
                this.rightRotate(grandParent);                

            } else {

                if (insertedNode == parent.getLeft()) {
                    this.rightRotate(parent);
                    parent.switchColor();
                    insertedNode.switchColor();
                }
                
                parent.switchColor();
                this.leftRotate(grandParent);                

            }

            
            grandParent.makeRed();

        }

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

    public RBNode search(int key) {
        RBNode node = this.root;

        while (!node.isNIL()) {
            if (key > node.getKey()) {
                node = node.getRight();
            } else if (key < node.getKey()) {
                node = node.getLeft();
            }
            
            else break;
        }
        
        return node;

    }

}
