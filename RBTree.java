package red.black.tree.dictionary;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import javax.swing.JOptionPane;

public class RBTree {

    private RBNode root;

    public RBTree() {
        this.root = RBNode.getNil();
    }

    public RBTree(String root_key) {
        this.root = new RBNode(root_key);
        //to make the root's parent NIL.
        this.root.setParent(RBNode.getNil());
        root.makeBlack();
    }


    private static boolean validateBlank(String s)
    {
        return s.isBlank();
    }
    
    public void load(String fileName) {
        try {
            File file = new File(fileName);
            Scanner scan = new Scanner(file);
            
            while (scan.hasNextLine()) {
                String line = scan.nextLine();
                if(RBTree.validateBlank(line)){
                    JOptionPane.showMessageDialog(RedBlackTreeDictionary.tempFrame, "There is an empty string.");
                    continue;
                }
                else
                    this.insert(line);
                
                
            }
        } catch (FileNotFoundException e) {
            JOptionPane.showMessageDialog(RedBlackTreeDictionary.tempFrame, "Error: File Not Found");
        }

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


    public void insertFixUp(RBNode newNode) {
        if (newNode == root) {
            return;
        }

        RBNode parent = newNode.getParent();
        RBNode grandParent = parent.getParent();
        RBNode uncle;

        if (parent.isRed()) {

            if (grandParent.getLeft() == parent) {
                uncle = grandParent.getRight();
            } else {
                uncle = grandParent.getLeft();
            }

            if (uncle.isRed()) {
                uncle.switchColor();
                parent.switchColor();
                if (grandParent != root) {
                    grandParent.switchColor();
                } else {
                    grandParent.makeBlack();
                }
                //this.display();
                //System.out.println("Case 1: Uncle Red.");
                insertFixUp(grandParent);

            } else {

                //right left.
                if (grandParent.getRight().getLeft() == newNode) {
                    this.rightRotate(parent);
                    insertFixUp(parent);
//                    this.leftRotate(grandParent);
//                    grandParent.switchColor();
//                    parent.switchColor();
                    //System.out.println("Case 2: right left");
                    // also instead of leftRotate we could call insertFixUp(parent);
                } //right right.
                else if (grandParent.getRight().getRight() == newNode) {
                    this.leftRotate(grandParent);
                    grandParent.switchColor();
                    parent.switchColor();

                    //System.out.println("Case 3: right right.");
                    //check for root and color it.
                } //left right
                else if (grandParent.getLeft().getRight() == newNode) {
                    this.leftRotate(parent);
                    insertFixUp(parent);
//                        this.rightRotate(grandParent);
//                        grandParent.switchColor();
//                        parent.switchColor();
                    //System.out.println("Case 2: left right.");
                } //left left    
                else {
                    this.rightRotate(grandParent);
                    grandParent.switchColor();
                    parent.switchColor();

                    //System.out.println("Case 3: left left.");
                }

            }

        }

    }

    public void insert(String key) {

        RBNode newNode = new RBNode(key);
        if (this.root == null) {
            this.root = newNode;
            this.root.makeBlack();
        }
        RBNode prevParent = this.root;
        RBNode currentParent = this.root;

        if (prevParent.isNIL()) {
            this.root = newNode;
            newNode.setParent(RBNode.getNil());
            newNode.makeBlack();
            return;
        }

        while (!currentParent.isNIL()) {
            if (newNode.getKey().compareToIgnoreCase(currentParent.getKey()) > 0) {
                prevParent = currentParent;
                currentParent = currentParent.getRight();
            } else if (newNode.getKey().compareToIgnoreCase(currentParent.getKey()) < 0) {
                prevParent = currentParent;
                currentParent = currentParent.getLeft();

            } else {
                
                JOptionPane.showMessageDialog(RedBlackTreeDictionary.tempFrame, "ERROR: Word already in the dictionary!");
                return;

            }

        }


        if (newNode.getKey().compareToIgnoreCase(prevParent.getKey()) > 0) {

            prevParent.setRight(newNode);
        } else {
            prevParent.setLeft(newNode);
        }


        newNode.setParent(prevParent);
        insertFixUp(newNode);
        //System.out.println("key = " + key);
    }

    public void search(String target, RBNode n) {
        if (n.getKey() != null) {
            if (target.equalsIgnoreCase(n.getKey())) {
                JOptionPane.showMessageDialog(null, "Found");
                return;
            } else if (target.compareToIgnoreCase(n.getKey()) < 0) {
                n = n.getLeft();
                search(target, n);
            } else {
                n = n.getRight();
                search(target, n);
            }
        } else {
            JOptionPane.showMessageDialog(null, "Not Found");
        }

    }

    public RBNode getRoot() {
        return root;
    }

    public void display() {
        TreeGraph graph = new TreeGraph(this.getCount(), this.getHeight());
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

    public int getHeight() {
        if (this.root == null) {
            return -1;
        }

        return this.root.getNodeHeight();
    }


}
