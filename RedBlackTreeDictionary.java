package red.black.tree.dictionary;

public class RedBlackTreeDictionary {

    public static void main(String[] args) {

        RBTree tree = new RBTree(50);

        tree.insert(30);
        tree.insert(70);

        tree.insert(20);

        tree.insert(40);

        tree.insert(0);

        tree.insert(90);
        tree.insert(60);
        tree.insert(25);

        tree.insert(1);
        tree.insert(2);
        tree.insert(3);
        tree.insert(4);
        tree.insert(5);
        tree.insert(6);
        tree.insert(7);
        tree.insert(8);
        tree.insert(9);

        tree.insert(17);
        tree.insert(12);
//        tree.insert(34);
//        tree.insert(27);
//        tree.insert(21);
//        tree.insert(100);

        tree.display();

    }

}
