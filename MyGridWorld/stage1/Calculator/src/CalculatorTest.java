import org.junit.Test;
import org.junit.Assert;

public class CalculatorTest {
	Calculator calculator = new Calculator();
	double a;
	double b;
	
        @Test
        public void testAdd() {
            System.out.println("testadd");
            Assert.assertEquals("3.0", calculator.add(1, 2) + "");
        }
        
        @Test
        public void testSub() {
            System.out.println("testsub");
            Assert.assertEquals("3.0", calculator.sub(5, 2) + "");
        }
        
        @Test
        public void testMul() {
            System.out.println("testmul");
            Assert.assertEquals("2.0", calculator.mul(1, 2) + "");
        }
        
        @Test
        public void testDiv() {
            System.out.println("testdiv");
            Assert.assertEquals("0.5", calculator.div(1, 2) + "");
        }
}
