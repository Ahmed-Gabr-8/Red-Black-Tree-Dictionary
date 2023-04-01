package red.black.tree.dictionary;


public class RedBlackTreeDictionary {

    public static void main(String[] args) {
        
        RBTree tree = new RBTree(20);
        tree.insert(10);
        tree.insert(5);
        tree.insert(7);
        tree.insert(3);
        tree.insert(8);
               
        tree.display();
    }

}
