package red.black.tree.dictionary;


public class RedBlackTreeDictionary {

    public static void main(String[] args)  {
        
//        RBTree tree = new RBTree(50);
//        
//        tree.insert(30);
//        
//        tree.insert(50);
//        
//        tree.insert(70);
//        
//        tree.insert(20);
//        
//        tree.insert(40);
//        
//        tree.insert(-90);
//        
//        tree.insert(90);
//        tree.insert(60);
//        tree.insert(25);
//        tree.insert(23);
        
        
        int arr[] = {87,57,29,43,5,17,14,99,16,37,59,54,58,23,25,26,3,2,89,87};

        RBTree tree = new RBTree(1);
        for(int num:arr)
        {
            
            tree.insert(num);
        }

        tree.display();
        
        tree.rightRotate(tree.getRoot().getLeft());
        
        tree.display();
        
        tree.leftRotate(tree.getRoot().getLeft());
        
        tree.display();
    }

}
