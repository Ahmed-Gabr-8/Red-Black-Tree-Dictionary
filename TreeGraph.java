package red.black.tree.dictionary;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.LinkedList;
import java.awt.Point;
import java.awt.geom.Line2D;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class TreeGraph extends JPanel {

    public static final int NODE_SIZE = 50;
    public static final int EDGE_THICKNESS = 1;
    public static final int WINDOW_WIDTH = 300;
    public static final int WINDOW_HEIGHT = 250;
    private LinkedList<RBNode> nodes;
    private LinkedList<Edge> edges;

    public TreeGraph() {
        super();
        nodes = new LinkedList<>();
        edges = new LinkedList<>();
    
    }
    
    public void addNode(RBNode node, int upperHeight) {
        if (node.isNIL()) {

            return;
        }
        nodes.add(node);
        int x = (nodes.size() - 1) * NODE_SIZE;
        int y = upperHeight * NODE_SIZE * 2;
        node.setGraphPoint(new Point(x, y));

    }

    public void addEdge(RBNode parent, RBNode child) {
        if (child.isNIL()) {
            return;
        }
        Point parentPoint = new Point(parent.getGraphPoint().x+NODE_SIZE/2, parent.getGraphPoint().y+NODE_SIZE/2);
        Point childPoint = new Point(child.getGraphPoint().x+NODE_SIZE/2, child.getGraphPoint().y+NODE_SIZE/2);
        edges.add(new Edge(parentPoint, childPoint));
        
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(Color.BLUE);
        g2d.setStroke(new BasicStroke(EDGE_THICKNESS));
        for (Edge e : edges) {
            g2d.draw(e.getLine());
        }
        
        
        for (RBNode n : nodes) {
            if (n.isRed()) {
                g2d.setColor(Color.RED);
            } else {
                g2d.setColor(Color.BLACK);
            }
            
            g.fillOval(n.getGraphPoint().x, n.getGraphPoint().y, NODE_SIZE, NODE_SIZE);
            g.setColor(Color.WHITE);
            String nodeText = String.valueOf(n.getKey());
            Point textPosition = this.getTextCoordinates(nodeText, n.getGraphPoint());
            g.setFont(new Font("Arial", Font.BOLD, this.getTextSize(nodeText))); 
            g.drawString(nodeText, textPosition.x, textPosition.y);

        }

    }

    public static void showTree(TreeGraph treeGraph) {
        JFrame frame = new JFrame("Tree Graph");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(treeGraph);
        frame.setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
        frame.setVisible(true);
    }

    private static Point getTextCoordinates(String text, Point nodePosition) {
        return new Point(nodePosition.x + NODE_SIZE / 3, nodePosition.y + 3 * NODE_SIZE / 4);
    }

    private static int getTextSize(String text) {
        return NODE_SIZE / 2;

    }

    class Edge {

        private Point point1;
        private Point point2;

        public Edge(Point point1, Point point2) {
            this.point1 = point1;
            this.point2 = point2;
        }

        public Line2D getLine() {
            return new Line2D.Double(point1.x, point1.y, point2.x, point2.y);

        }
    }

}
