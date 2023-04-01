package red.black.tree.dictionary;

public class RedBlackNode {
    private int key;
    RedBlackNode left,right;
    private boolean color;
    
    private RedBlackNode()
    {
        //for NIL node as its color is black.
        this.color = false;
    }
    
    
    public RedBlackNode(int key)
    {
        this.key = key;
        RedBlackNode nil = new RedBlackNode();
        this.left = this.right = nil;
        color = true; //true means Red.
    }

    public int getKey() {
        return key;
    }

    public void setKey(int key) {
        this.key = key;
    }

    public boolean isColor() {
        return color;
    }

    public void setColor(boolean color) {
        this.color = color;
    }
    

    
    public void changeColor()
    {
        this.color = !(this.color);
    }

    @Override
    public String toString() {
        if(this.color == true)
            return "RedBlackNode{" + "key=" + key + ", left=" + left + ", right=" + right + ", color= Red " + color + '}';
        return "RedBlackNode{" + "key=" + key + ", left=" + left + ", right=" + right + ", color= Black " + color + '}';
    }
    
    
}
