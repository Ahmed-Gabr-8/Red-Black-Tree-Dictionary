
package red.black.tree.dictionary;
public class RedBlackTreeDictionary {

    public static void main(String[] args) {

        RBTree tree = new RBTree(5);
        tree.insert(3);        
        tree.insert(7);
        tree.insert(1);
        tree.insert(4);
        tree.insert(6);
        tree.insert(9);
        tree.insert(4);
        tree.insert(8);
        
        tree.display();
    }
    
}
