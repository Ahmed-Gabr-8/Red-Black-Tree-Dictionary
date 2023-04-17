package red.black.tree.dictionary;

import java.util.Scanner;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class RedBlackTreeDictionary {

    public static JFrame tempFrame = new JFrame("Paint Frame");

    public static void main(String[] args) {

        RBTree tree = new RBTree();

        Scanner s = new Scanner(System.in);
        System.out.println("Welcome to the red-black tree menu:");
        byte x = 8;
        boolean flag = true;

        while (flag) {
            while (x > 7 || x < 1) {
                System.out.println("Choose from the options below:");

                System.out.println("1)Load");
                System.out.println("2)Insert");
                System.out.println("3)Search");
                System.out.println("4)print Tree height");
                System.out.println("5)print Tree size");
                System.out.println("6)display Tree");
                System.out.println("7)exit");

                x = s.nextByte();
                System.out.println("x = " + x);
            }

            switch (x) {

                //load
                case 1:
                    tree = new RBTree();
                    System.out.println("Loading.....");
                    tree.load("EN-US-Dictionary.txt");
                    System.out.println("Loaded.");
                    break;

                //insert
                case 2:
                    System.out.println("Please enter a string to be inserted.");
                    String line = s.next();
                    System.out.println(line);
                    if (tree == null) {
                        tree = new RBTree();
                    }
                    tree.insert(line);
                    break;

                //Search
                case 3:

                    if (tree.getRoot().isNIL()) {
                        System.out.println("you should load the file firstly.");
                    } else {
                        System.out.println("Enter the word to be searched:");
                        String word = s.next();
                        System.out.println("Word = " + word);
                        tree.search(word, tree.getRoot());
                    }
                    break;

                //print tree height    
                case 4:

                    System.out.println("The tree height is: " + tree.getHeight());
                    break;

                //print Tree size   
                case 5:

                    System.out.println("The tree size is: " + tree.getCount());
                    break;

                case 6:

                    if (tree != null) {
                        tree.display();
                    } else {
                        JOptionPane.showMessageDialog(null, "Tree is Empty.");
                    }
                    break;

                default:
                    System.exit(0);

            }

            System.out.println("Do you want to do another operation ?Y or N");
            String k = s.next();
            char c = k.charAt(0);
            if (c == 'N' || c == 'n') {
                x = 8;
                
            }

        }

    }

}
