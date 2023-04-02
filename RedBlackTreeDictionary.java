package red.black.tree.dictionary;


public class RedBlackTreeDictionary {

    public static void main(String[] args)  {

        RBTree  tree = new RBTree(50);
        
        tree.insert(30);
        tree.insert(70);
        tree.insert(20);
        tree.insert(3);
        tree.insert(90);
        tree.insert(95);
        tree.insert(97);
        tree.display();

    }

}
