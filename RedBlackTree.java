package red.black.tree.dictionary;

import javax.swing.JOptionPane;

public class RedBlackTree {
    private RedBlackNode root;
    
    
    public RedBlackTree(int key)
    {
        System.out.println("Forming tree.");
        root = new RedBlackNode(key);
        root.setColor(false);//false means black.
    }
    
    
    public RedBlackNode insertNode(int key)
    {
        //Initial Tree not RedBlack.
        
        //It is not important as there is only one constructor but just to make sure.
        if(root == null)
            root = new RedBlackNode(key);
        else if(key > root.getKey()){
            System.out.println("go right");
            root.left = insertNode(key);
        }
        else if(key < root.getKey()){
            System.out.println("go left");
            root.right = insertNode(key);
        }
        else
            JOptionPane.showMessageDialog(null, "This key is already inserted,BST cannot have equal numbers.");
        return root;
    }
    
    public RedBlackNode getRoot()
    {
        return root;
    }
}
