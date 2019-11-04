package bearmaps;

import org.junit.Test;
import static org.junit.Assert.*;
import edu.princeton.cs.algs4.Stopwatch;

public class ArrayHeapMinPQTest {

    @Test
    public void testBasics() {
        ArrayHeapMinPQ<Integer> apq = new ArrayHeapMinPQ<>();
        apq.add(25, 25);
        apq.add(32, 32);
        apq.add(60, 60);
        assertTrue(apq.contains(60));
        assertTrue(apq.contains(25));
        assertFalse(apq.contains(3));
        for (int x = 20; x > 10; x--) {
            apq.add(x, x);
        }
        assertTrue(apq.contains(15));
        assertEquals(11, (long) apq.removeSmallest());

    }
    @Test
    public void testRemove() {
        ArrayHeapMinPQ<Integer> apq = new ArrayHeapMinPQ<>();
        for (int x = 1000; x >= 0; x--) {
            apq.add(x, x);
        }
        int oldSmall = apq.removeSmallest();
        assertEquals(0, oldSmall);
        assertTrue(apq.contains(500));
        assertTrue(apq.contains(231));
        assertFalse(apq.contains(1200));
        assertFalse(apq.contains(0));
        int smallest = apq.getSmallest();
        assertEquals(1, smallest);
        for (int s = 300; s < 601; s++) {
            apq.removeSmallest();
        }
        assertFalse(apq.contains(300));
        smallest = apq.getSmallest();
        assertEquals(302, smallest);
        smallest = apq.removeSmallest();
        assertEquals(302, smallest);
        assertFalse(apq.contains(302));
    }
    @Test
    public void testChangePriority() {
        ArrayHeapMinPQ<Integer> apq = new ArrayHeapMinPQ<>();
        for (int x = 100; x > 10; x--) {
            apq.add(x, x);
        }
        apq.changePriority(55, 10);
        assertEquals(55, (long) apq.removeSmallest());
        apq.add(9, 9);
        apq.add(8, 9);
        assertEquals(9, (long) apq.removeSmallest());
        assertEquals(8, (long) apq.removeSmallest());
        assertEquals(11, (long) apq.removeSmallest());
        apq.changePriority(12, 14);
        assertEquals(13, (long) apq.removeSmallest());
        assertEquals(12, (long) apq.removeSmallest());
    }
    @Test
    public void compareTest() {
        ArrayHeapMinPQ<Integer> apq = new ArrayHeapMinPQ<>();
        NaiveMinPQ<Integer> npq = new NaiveMinPQ<>();
        for (int x = 10000; x > 0; x--) {
            apq.add(x, x);
            npq.add(x, x);
        }

    }
    @Test
    public void test() {
        ArrayHeapMinPQ<Integer> apq = new ArrayHeapMinPQ<>();
        apq.add(1, 1);
        apq.changePriority(1, 2);
        apq.add(2, 1);
        assertEquals(2, (long) apq.removeSmallest());
        assertEquals(1, (long) apq.removeSmallest());
    }
    @Test
    public void randomTest() {
        ArrayHeapMinPQ<String> apq = new ArrayHeapMinPQ<>();
        /*for (int x = 15; x > 10; x--) {
            apq.add(x, x);
        } */
        apq.add("five", 5);
        apq.add("four", 4);
        apq.add("three", 3);
        apq.add("two", 2);
        apq.add("one", 1);
        /*
        apq.changePriority(15, 9);
        assertEquals(15, (long) apq.removeSmallest());
        assertEquals(11, (long) apq.removeSmallest()); */
        apq.add("negativeOne", -1);
        apq.add("negativeTwo", -2);
        apq.changePriority("four", -4);
        apq.removeSmallest();
        apq.add("ten", 10);
        assertEquals("negativeTwo",  apq.removeSmallest());

    }
    @Test
    public void printTest() {
        ArrayHeapMinPQ<Integer> apq = new ArrayHeapMinPQ<>();
        for (int x = 10; x > 0; x++) {
            apq.add(x, x);
        }
        int size = apq.size();
        Integer[] heapItems = (Integer[]) new Object[size];
        heapItems[0] = 0;
        for (int i = 1; i <= size; i++) {
            heapItems[i] = apq.removeSmallest();
        }
        PrintHeapDemo.printSimpleHeapDrawing(heapItems);

    }
    @Test
    public void timeChangePriority() {
        ArrayHeapMinPQ<Integer> apq = new ArrayHeapMinPQ<>();
        NaiveMinPQ<Integer> npq = new NaiveMinPQ<>();
        for (int x = 0; x < 100000; x++) {
            apq.add(x, x);
            npq.add(x, x);
        }
        Stopwatch sw = new Stopwatch();
        for (int x = 0; x < 100000; x++) {
            apq.changePriority(x, x + 2);
        }
        System.out.println("Total time elapsed: " + sw.elapsedTime() +  " seconds.");
        Stopwatch sw2 = new Stopwatch();
        for (int x = 0; x < 100000; x++) {
            npq.changePriority(x, x + 2);
        }
        System.out.println("Total time elapsed: " + sw2.elapsedTime() +  " seconds.");
    }
    @Test
    public void timeTest() {
        //add
        Stopwatch sw = new Stopwatch();
        ArrayHeapMinPQ<Integer> apq = new ArrayHeapMinPQ<>();
        for (int x = 0; x < 100000; x++) {
            apq.add(x, x);
        }

        //
        Stopwatch sw2 = new Stopwatch();
        NaiveMinPQ<Integer> npq = new NaiveMinPQ<>();
        for (int x = 0; x < 100000; x++) {
            npq.add(x, (double) x);
        }
        System.out.println("Total time elapsed: " + sw2.elapsedTime() +  " seconds.");
        //contains
        Stopwatch watch = new Stopwatch();
        for (int x = 0; x < 100000; x++) {
            apq.contains(x);
        }
        System.out.println("Total time elapsed: " + watch.elapsedTime() +  " seconds.");
        //
        Stopwatch watch2 = new Stopwatch();
        for (int x = 0; x < 100000; x++) {
            npq.contains(x);
        }
        System.out.println("Total time elapsed: " + watch2.elapsedTime() +  " seconds.");
        //remove
        Stopwatch stopwatch = new Stopwatch();
        for (int i = 0; i < 100000; i++) {
            apq.removeSmallest();
        }
        System.out.println("Total time elapsed: " + stopwatch.elapsedTime() +  " seconds.");
        //
        Stopwatch stopwatch2 = new Stopwatch();
        for (int x = 0; x < 100000; x++) {
            npq.removeSmallest();
        }
        System.out.println("Total time elapsed: " + stopwatch2.elapsedTime() +  " seconds.");
    }
    @Test
    public void timeTest2() {
        ArrayHeapMinPQ<Integer> apq = new ArrayHeapMinPQ<>();
        for (int x = 0; x < 1000000; x++) {
            apq.add(x, x);
        }
        Stopwatch sw = new Stopwatch();
        for (int x = 0; x < 1000; x++) {
            apq.removeSmallest();
        }
        System.out.println("Total time elapsed: " + sw.elapsedTime() +  " seconds.");
        //
        NaiveMinPQ<Integer> npq = new NaiveMinPQ<>();
        for (int x = 0; x < 1000000; x++) {
            npq.add(x, (double) x);
        }
        Stopwatch sw2 = new Stopwatch();
        for (int x = 0; x < 1000; x++) {
            npq.removeSmallest();
        }
        System.out.println("Total time elapsed: " + sw2.elapsedTime() +  " seconds.");
    }
    @Test
    public void testStructure() {
        ArrayHeapMinPQ<Integer> apq = new ArrayHeapMinPQ<>();
        apq.add(6, 6);
        apq.add(12, 12);
        apq.add(1, 1);
        apq.add(3, 3);
        apq.add(15, 15);
        apq.add(9, 9);
        apq.add(13, 13);
        apq.add(8, 8);
        apq.add(14, 14);
        apq.add(7, 7);
        apq.add(11, 11);
        apq.add(2, 2);
        apq.add(4, 4);
        apq.add(5, 5);
        apq.add(10, 10);
        assertEquals(1, (long) apq.removeSmallest());
        assertEquals(2, (long) apq.removeSmallest());
        assertEquals(3, (long) apq.removeSmallest());
        assertEquals(4, (long) apq.removeSmallest());
        assertEquals(5, (long) apq.removeSmallest());
        assertEquals(10, (long) apq.size());
        assertEquals(6, (long) apq.removeSmallest());
        assertEquals(7, (long) apq.removeSmallest());
        assertEquals(8, (long) apq.removeSmallest());
        assertEquals(9, (long) apq.removeSmallest());
        assertEquals(10, (long) apq.removeSmallest());
        assertFalse(apq.contains(3));
        assertEquals(11, (long) apq.removeSmallest());
        assertEquals(12, (long) apq.removeSmallest());
        assertEquals(13, (long) apq.removeSmallest());
        assertEquals(14, (long) apq.removeSmallest());
        assertEquals(15, (long) apq.removeSmallest());
        assertEquals(0, apq.size());
    }
}
