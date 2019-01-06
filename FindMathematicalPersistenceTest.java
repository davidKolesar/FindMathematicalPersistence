import static org.junit.Assert.*;
import org.junit.Test;
import java.util.Random;

public class PersistTest {

	//----------------
	private static int randint(Random rnd, int min, int max) {
		int randomNumber = rnd.nextInt(max - min) + min;
		return randomNumber;
    }
	
	private static int sol(long n) {
		String[] digits = Long.toString(n).split("");
		if (digits.length == 1) return 0;
        int dprod = 1;
        for (String d : digits) {
            int dgt = Integer.parseInt(d);
            dprod *= dgt;
        }
        return 1 + sol(dprod);
	}
	//----------------
    
	@Test
	public void BasicTests() {
		System.out.println("****** Basic Tests ******");
		assertEquals(3, Persist.persistence(39));
		assertEquals(0, Persist.persistence(4));
	  assertEquals(2, Persist.persistence(25));
	  assertEquals(4, Persist.persistence(999));
	  assertEquals(3, Persist.persistence(444));	
	}
	@Test
	public void RandomTests() {
		System.out.println("****** Random tests ******");
		Random rnd = new Random();
		for (int i = 0; i < 100; i++) {		
			int  x = PersistTest.randint(rnd, 1, 500000);
			System.out.println("Number n tested: " + x);
	    assertEquals(PersistTest.sol(x), Persist.persistence(x));
		}
	}	
}
