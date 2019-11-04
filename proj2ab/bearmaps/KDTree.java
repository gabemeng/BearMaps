package bearmaps;
import java.util.List;
public class KDTree implements PointSet {
    private static final boolean HORI = false;
    private static final boolean VERT = true;
    private Node root;

    public KDTree(List<Point> points) {
        for (Point p: points) {
            root = constructor(p, root, HORI);
        }
    }
    private Node constructor(Point p, Node n, boolean ori) {
        if (n == null) {
            return new Node(p, ori);
        }
        if (p.equals(n.point)) {
            return n;
        }
        double compare = compare(ori, p, n.point);
        if (compare >= 0) {
            n.right = constructor(p, n.right, !ori);
        } else {
            n.left = constructor(p, n.left, !ori);
        }
        return n;
    }
    private int compare(boolean ori, Point one, Point two) {
        if (ori == HORI) {
            return Double.compare(one.getX(), two.getX());
        } else {
            return Double.compare(one.getY(), two.getY());
        }
    }
    @Override
    public Point nearest(double x, double y) {
        return nearestHelper(root, new Point(x, y), root).point;
    }
    private Node nearestHelper(Node n, Point goal, Node best) {
        Node goodSide;
        Node badSide;
        if (n == null) {
            return best;
        }
        if (Point.distance(n.point, goal) < Point.distance(best.point, goal)) {
            best = n;
        }
        if (chooseSide(goal, n.point, n.orientation)) {
            goodSide = n.right;
            badSide = n.left;
        } else {
            goodSide = n.left;
            badSide = n.right;
        }
        best = nearestHelper(goodSide, goal, best);
        if (isTherePotential(n, goal, best, n.orientation)) {
            best = nearestHelper(badSide, goal, best);
        }
        return best;

    }
    private boolean isTherePotential(Node n, Point goal, Node best, boolean ori) {
        Point bestPossible;
        if (ori == HORI) {
            bestPossible = new Point(n.point.getX(), goal.getY());
        } else {
            bestPossible = new Point(goal.getX(), n.point.getY());
        }
        return (Point.distance(bestPossible, goal) < Point.distance(best.point, goal));
    }
    private boolean chooseSide(Point goal, Point a, boolean ori) {
        //true = right, false = left
        if (ori == VERT) {
            return (goal.getY() > a.getY());
        } else {
            return (goal.getX() > a.getX());
        }
    }

    private class Node {
        private Point point;
        private Node left;
        private Node right;
        private boolean orientation;
        Node(Point p, boolean ori) {
            point = p;
            orientation = ori;
        }
    }
}
