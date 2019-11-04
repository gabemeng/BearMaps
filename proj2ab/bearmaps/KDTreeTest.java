package bearmaps;

import org.junit.Test;
import java.util.List;
import static org.junit.Assert.*;
import java.util.ArrayList;
import java.util.Random;
import edu.princeton.cs.algs4.Stopwatch;

/*
import org.junit.Test;
import static org.junit.Assert.assertEquals;
*/
//@HugVideo
public class KDTreeTest {
    private static Random rand = new Random(500);
    @Test
    public void constructorTest() {
        Point p1 = new Point(2, 3);
        Point p2 = new Point(4, 2);
        Point p3 = new Point(4, 5);
        Point p4 = new Point(3, 3);
        Point p5 = new Point(1, 5);
        Point p6 = new Point(4, 4);
        KDTree kdt = new KDTree(List.of(p1, p2, p3, p4, p5, p6));
    }
    @Test
    public void nearestTest() {
        Point p1 = new Point(2, 3);
        Point p2 = new Point(4, 2);
        Point p3 = new Point(4, 5);
        Point p4 = new Point(3, 3);
        Point p5 = new Point(1, 5);
        Point p6 = new Point(4, 4);
        KDTree kdt = new KDTree(List.of(p1, p2, p3, p4, p5, p6));
        Point near = kdt.nearest(0, 7);
        assertEquals(new Point(1, 5), near);
    }
    @Test
    public void randomizedNearestTest() {
        ArrayList<Point> points = new ArrayList<>();
        for (int z = 0; z < 100000; z++) {
            double x = rand.nextDouble();
            double y = rand.nextDouble();
            points.add(new Point(x, y));
        }
        NaivePointSet n = new NaivePointSet(points);
        KDTree kdt = new KDTree(points);
        ArrayList<Point> testPoints = new ArrayList<>();
        for (int g = 0; g < 2000; g++) {
            double x = rand.nextDouble();
            double y = rand.nextDouble();
            testPoints.add(new Point(x, y));
        }
        for (Point p: testPoints) {
            Point expected = n.nearest(p.getX(), p.getY());
            Point actual = kdt.nearest(p.getX(), p.getY());
            assertEquals(expected, actual);
        }
    }
    @Test
    public void timeTest() {
        ArrayList<Point> points = new ArrayList<>();
        for (int z = 0; z < 100000; z++) {
            double x = rand.nextDouble();
            double y = rand.nextDouble();
            points.add(new Point(x, y));
        }
        NaivePointSet n = new NaivePointSet(points);
        KDTree kdt = new KDTree(points);
        ArrayList<Point> testPoints = new ArrayList<>();
        for (int g = 0; g < 10000; g++) {
            double x = rand.nextDouble();
            double y = rand.nextDouble();
            testPoints.add(new Point(x, y));
        }
        Stopwatch sw = new Stopwatch();
        for (Point p: testPoints) {
            Point actual = kdt.nearest(p.getX(), p.getY());
        }
        System.out.println("Total time elapsed: " + sw.elapsedTime() +  " seconds.");
        Stopwatch sw2 = new Stopwatch();
        for (Point p: testPoints) {
            Point actual = n.nearest(p.getX(), p.getY());
        }
        System.out.println("Total time elapsed: " + sw2.elapsedTime() +  " seconds.");
    }
}
