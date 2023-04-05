package red.black.tree.dictionary;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.LinkedList;
import java.awt.Point;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.geom.Line2D;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class TreeGraph extends JPanel implements KeyListener {

    public static final int WINDOW_WIDTH = 800;
    public static final int WINDOW_HEIGHT = 600;
    private LinkedList<RBNode> nodes;
    private LinkedList<Edge> edges;
    private int nodeSize = 50;
    private int edgeThickness = 1;
    private Point offset;

    public TreeGraph() {
        super();
        nodes = new LinkedList<>();
        edges = new LinkedList<>();
        offset = new Point(0, 0);

    }

    public void addNode(RBNode node, int upperHeight) {
        if (node.isNIL()) {

            return;
        }
        nodes.add(node);
        int x = (nodes.size() - 1);
        int y = upperHeight * 2;
        node.setGraphIndex(new Point(x, y), nodeSize, offset);

    }

    public void addEdge(RBNode parent, RBNode child) {
        if (child.isNIL()) {
            return;
        }

        edges.add(new Edge(parent, child));

    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(Color.BLUE);
        g2d.setStroke(new BasicStroke(edgeThickness));
        for (Edge e : edges) {
            g2d.draw(e.getLine());
        }

        for (RBNode n : nodes) {
            if (n.isRed()) {
                g2d.setColor(Color.RED);
            } else {
                g2d.setColor(Color.BLACK);
            }

            g.fillOval(n.getGraphPoint().x, n.getGraphPoint().y, nodeSize, nodeSize);
            g.setColor(Color.WHITE);
            String nodeText = String.valueOf(n.getKey());
            int textSize = getTextSize(nodeText);
            Point textPosition = this.getTextCoordinates(textSize, n.getGraphPoint());
            g.setFont(new Font("Arial", Font.BOLD, textSize));
            g.drawString(nodeText, textPosition.x, textPosition.y);

        }

    }

    public static void showTree(TreeGraph treeGraph) {

        treeGraph.addKeyListener(treeGraph);
        treeGraph.setFocusable(true);
        JFrame frame = new JFrame("Tree Graph");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(treeGraph);
        frame.setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
        frame.setVisible(true);

    }

    private Point getTextCoordinates(int textSize, Point nodePosition) {
        //return new Point(nodePosition.x + nodeSize / 3, nodePosition.y + 3 * nodeSize / 4);
        return new Point(nodePosition.x, nodePosition.y + nodeSize/2  + textSize/2);
    }

    private int getTextSize(String text) {
//        return nodeSize / 2;
        return nodeSize/text.length();

    }

    class Edge {

        private RBNode node1;
        private RBNode node2;

        public Edge(RBNode node1, RBNode node2) {
            this.node1 = node1;
            this.node2 = node2;
        }

        public Line2D getLine() {
            return new Line2D.Double(node1.getGraphPoint().x + nodeSize / 2, node1.getGraphPoint().y + nodeSize / 2, node2.getGraphPoint().x + nodeSize / 2, node2.getGraphPoint().y + nodeSize / 2);
        }
    }

    public void updateGraph() {
        for (RBNode n : nodes) {
            n.updateGraphPoint(nodeSize, offset);

        }
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        int keyCode = e.getKeyCode();        
        int translateSpeed = nodeSize/2;
        switch (keyCode) {
            case KeyEvent.VK_UP:
                this.offset.translate(0, -translateSpeed);
                this.updateGraph();
                break;
            case KeyEvent.VK_DOWN:
                this.offset.translate(0, translateSpeed);
                this.updateGraph();
                break;
            case KeyEvent.VK_LEFT:
                this.offset.translate(-translateSpeed, 0);
                this.updateGraph();
                break;
            case KeyEvent.VK_RIGHT:
                this.offset.translate(translateSpeed, 0);
                this.updateGraph();
                break;                
            case 61:
                this.nodeSize +=1;
                this.updateGraph();
                break;
            case KeyEvent.VK_MINUS:
                this.nodeSize -=1;
                this.updateGraph();                
                break;
             
        }
        
        

        this.repaint();
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

}
