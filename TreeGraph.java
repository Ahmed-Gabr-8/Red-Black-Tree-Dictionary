package red.black.tree.dictionary;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
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
    public static final int DEFAULT_NODE_SIZE = 50;
    private LinkedList<RBNode> nodes;
    private LinkedList<Edge> edges;
    private int nodeSize;
    private final int edgeThickness = 1;
    private Point offset;
    private JFrame frame;

    public TreeGraph() {
        super();
        nodes = new LinkedList<>();
        edges = new LinkedList<>();
        offset = new Point(0, 0);
        this.nodeSize = DEFAULT_NODE_SIZE;

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

            if(!e.checkBounds()){
                continue;
            }

            g2d.draw(e.getLine());
        }

        for (RBNode n : nodes) {

            if (n.getGraphPoint().x < 0 || n.getGraphPoint().x > WINDOW_WIDTH) {
                continue;
            }

            if (n.getGraphPoint().y < 0 || n.getGraphPoint().y > WINDOW_HEIGHT) {
                continue;
            }

            if (n.isRed()) {
                g2d.setColor(Color.RED);
            } else {
                g2d.setColor(Color.BLACK);
            }

            g.fillOval(n.getGraphPoint().x, n.getGraphPoint().y, nodeSize, nodeSize);
            g.setColor(Color.WHITE);
            String nodeText = n.getKey();
            int textSize = getTextSize(nodeText);
            Font textFont = new Font("Arial", Font.BOLD, textSize);
            Point textPosition = this.getTextCoordinates(g.getFontMetrics(textFont), nodeText, n.getGraphPoint());
            g.setFont(textFont);
            g.drawString(nodeText, textPosition.x, textPosition.y);

        }

    }

    public void showGraph() {

        this.addKeyListener(this);
        this.setFocusable(true);
        this.frame = new JFrame("Tree Graph");
        this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.frame.add(this);
        this.frame.setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
        this.frame.setVisible(true);
        this.frame.setResizable(false);

    }

    private Point getTextCoordinates(FontMetrics fontMetrics, String text, Point nodePosition) {
        return new Point(nodePosition.x + nodeSize / 2 - fontMetrics.stringWidth(text) / 2, nodePosition.y + nodeSize / 2 + fontMetrics.getHeight() / 2);
    }

    private int getTextSize(String text) {
        return Math.min(nodeSize / 2, nodeSize / text.length());

    }

    class Edge {

        private RBNode node1;
        private RBNode node2;

        public Edge(RBNode node1, RBNode node2) {
            this.node1 = node1;
            this.node2 = node2;
        }

        public boolean between(int x, int min, int max) {
            return x >= min && x <= max;
        }

        public boolean checkBounds() {
            int x1 = node1.getGraphPoint().x + nodeSize / 2;
            int x2 = node2.getGraphPoint().x + nodeSize / 2;
            int y1 = node1.getGraphPoint().y + nodeSize / 2;
            int y2 = node2.getGraphPoint().y + nodeSize / 2;

            if (between(x1, 0, WINDOW_WIDTH) && between(y1, 0, WINDOW_HEIGHT)) {
                return true;
            }

            if (between(x2, 0, WINDOW_WIDTH) && between(y2, 0, WINDOW_HEIGHT)) {
                return true;
            }
            return false;
        }

        public Line2D getLine() {
            return new Line2D.Double(node1.getGraphPoint().x + nodeSize / 2, node1.getGraphPoint().y + nodeSize / 2, node2.getGraphPoint().x + nodeSize / 2, node2.getGraphPoint().y + nodeSize / 2);
        }
    }

    public void updateGraph() {
        for (RBNode n : nodes) {
            n.updateGraphPoint(nodeSize, offset);

        }
        this.repaint();
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int keyCode = e.getKeyCode();
        int translateSpeed = nodeSize;
        int scaleSpeed = 4;
        switch (keyCode) {
            case KeyEvent.VK_UP -> {
                this.offset.translate(0, translateSpeed);
                this.updateGraph();
            }
            case KeyEvent.VK_DOWN -> {
                this.offset.translate(0, -translateSpeed);
                this.updateGraph();
            }
            case KeyEvent.VK_LEFT -> {
                this.offset.translate(translateSpeed, 0);
                this.updateGraph();
            }
            case KeyEvent.VK_RIGHT -> {
                this.offset.translate(-translateSpeed, 0);
                this.updateGraph();
            }
            case KeyEvent.VK_EQUALS -> {
                double scaleKeyX = ((1.0 / nodeSize) * (WINDOW_WIDTH / 2 - nodeSize / 2 - offset.x) + 0.5) * scaleSpeed;
                double scaleKeyY = ((1.0 / nodeSize) * (WINDOW_HEIGHT / 2 - nodeSize / 2 - offset.y) + 0.5) * scaleSpeed;
                this.offset.translate((int) -scaleKeyX, (int) -scaleKeyY);
                this.nodeSize += scaleSpeed;
                this.updateGraph();
            }
            case KeyEvent.VK_MINUS -> {
                if (nodeSize <= scaleSpeed) {
                    return;
                }
                double scaleKeyX = ((1.0 / nodeSize) * (WINDOW_WIDTH / 2 - nodeSize / 2 - offset.x) + 0.5) * scaleSpeed;
                double scaleKeyY = ((1.0 / nodeSize) * (WINDOW_HEIGHT / 2 - nodeSize / 2 - offset.y) + 0.5) * scaleSpeed;
                this.offset.translate((int) scaleKeyX, (int) scaleKeyY);
                this.nodeSize = this.nodeSize - scaleSpeed;
                this.updateGraph();
            }

        }

    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    public void reset() {
        nodes = new LinkedList<>();
        edges = new LinkedList<>();
    }

    public void centerAroundNode(RBNode n) {
        this.offset.x += WINDOW_WIDTH / 2 - n.getGraphPoint().x + nodeSize / 2;
        this.offset.y += WINDOW_HEIGHT / 2 - n.getGraphPoint().y + nodeSize / 2;
        this.updateGraph();
    }

    public void centerAroundPoint(Point p) {
        this.offset.x = WINDOW_WIDTH / 2 - p.x;
        this.offset.y = WINDOW_HEIGHT / 2 - p.y;
        this.updateGraph();
    }
}
