package red.black.tree.dictionary;


public class RedBlackTreeDictionary {

    public static void main(String[] args)  {

        RBTree tree = new RBTree(50);

        tree.insert(30);

        tree.insert(50);

        tree.insert(70);

        tree.insert(20);

        tree.insert(40);

        tree.insert(-90);

        tree.insert(90);
        tree.insert(60);
        tree.insert(25);


        tree.display();

        tree.rightRotate(tree.getRoot().getLeft());

        tree.display();

        tree.leftRotate(tree.getRoot().getLeft());

        tree.display();

        tree.search(100,tree.getRoot());

    }

}
