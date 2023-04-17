package red.black.tree.dictionary;

public class RedBlackTreeDictionary {

    public static void main(String[] args) {

        RBTree tree = new RBTree();
       tree.load("EN-US-Dictionary.txt");
        //tree.load("smalldict.txt");

        tree.display();

        //tree.graphicalSearch("math", tree.getRoot());

//        System.out.println(tree.getHeight());
    }

}
