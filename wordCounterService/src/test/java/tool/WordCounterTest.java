/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tool;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Jeffrey
 */
public class WordCounterTest {
    
    public WordCounterTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of hello method, of class WordCounter.
     */
    @Test
    public void testHello() {
        System.out.println("hello");
        String txt = "you";
        WordCounter instance = new WordCounter();
        String expResult = "Hello you !";
        String result = instance.hello(txt);
        assertEquals(expResult, result);
    }

    /**
     * Test of WordCount method, of class WordCounter.
     */
    @Test
    public void testWordCount() {
        System.out.println("WordCount");
        String txt = "Hey man how is it going";
        WordCounter instance = new WordCounter();
        int expResult = 6;
        int result = instance.WordCount(txt);
        assertEquals(expResult, result);
    }
    
}
