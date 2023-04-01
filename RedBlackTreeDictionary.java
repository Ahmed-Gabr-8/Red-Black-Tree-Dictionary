
package red.black.tree.dictionary;
public class RedBlackTreeDictionary {

    public static void main(String[] args) {
        System.out.println("Test");
        
        RedBlackTree tree = new RedBlackTree(50);
        System.out.println(tree.getRoot().toString());
        
        RedBlackNode n = new RedBlackNode(30);
        System.out.println(n.toString());
        n.changeColor();
        System.out.println(n.toString());
    }
    
}
