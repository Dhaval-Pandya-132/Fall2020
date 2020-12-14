package org.example;

import org.junit.Test;

import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Unit test for simple App.
 */
public class AppTest {
    /**
     * Rigorous Test :-)
     */

    public static int LOCKUP_DETECT_TIMEOUT = 100;
    public static int CAPACITY=10;
    public static int THRESHOLD=1;

    @Test
    public void shouldAnswerWithTrue() {
        assertTrue(true);
    }

    @Test
    public void testIsEmptyWhenConstructed() {
        BoundedBuffer<Integer> bb = new BoundedBuffer<Integer>(10);
        assertTrue(bb.isEmpty());
        assertFalse(bb.isFull());
    }

    @Test
    public void testIsFullAfterPuts() throws InterruptedException {
        BoundedBuffer<Integer> bb = new BoundedBuffer<Integer>(10);
        for (int i = 0; i < 10; i++)
            bb.put(i);
        assertTrue(bb.isFull());
        assertFalse(bb.isEmpty());
    }

    @Test
    public void testTakeBlocksWhenEmpty() {
        final BoundedBuffer<Integer> bb = new BoundedBuffer<Integer>(10);
        Thread taker = new Thread() {
            public void run() {
                try {
                    bb.take();
                    fail();
                    // if we get here, it's an error
                } catch (InterruptedException success) {
                }
            }
        };
        try {
            taker.start();
            Thread.sleep(LOCKUP_DETECT_TIMEOUT);
            taker.interrupt();
            taker.join(LOCKUP_DETECT_TIMEOUT);
            assertFalse(taker.isAlive());
        } catch (Exception unexpected) {
            fail();
        }
    }

    @Test
    public void testLeak() throws InterruptedException {
        BoundedBuffer<Big> bb = new BoundedBuffer<Big>(CAPACITY);
        long heapSize1 = Runtime.getRuntime().totalMemory()  /* snapshot heap */ ;
        for (int i = 0; i < CAPACITY; i++)
            bb.put(new Big());
        for (int i = 0; i < CAPACITY; i++)
            bb.take();
        long heapSize2 = Runtime.getRuntime().totalMemory()/* snapshot heap */ ;
        assertTrue(Math.abs(heapSize1-heapSize2) < THRESHOLD);
    }


}
