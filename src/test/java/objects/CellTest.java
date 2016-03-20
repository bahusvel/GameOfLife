package objects;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
 * Cell Tester.
 *
 * @author <Authors name>
 * @version 1.0
 * @since <pre>03/10/2016</pre>
 */
public class CellTest extends TestCase {
    public CellTest(String name) {
        super(name);
    }

    public static Test suite() {
        return new TestSuite(CellTest.class);
    }

    public void setUp() throws Exception {
        super.setUp();
    }

    public void tearDown() throws Exception {
        super.tearDown();
    }

    public void testSetIsAlive() throws Exception {
        //TODO: Test goes here...
    }

    public void testSetGetNextGenState() throws Exception {
        //TODO: Test goes here...
    }
}
