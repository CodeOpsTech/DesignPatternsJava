import static org.mockito.Mockito.*;
import static org.junit.Assert.*;
import java.util.List;
import org.junit.*;
import java.util.*; 

public class OperatorTests { 
	Expr const10; 
	Expr const20; 

	@Before
	public void init() {
		const10 = new Constant(10);  
		const20 = new Constant(20);  
	}  
	@Test 
	public void testAddOperator1() {
		Plus tenPlusTwenty = new Plus(const10, const20); 
		int result = tenPlusTwenty.eval(); 
		assertEquals(30, result); 
	} 
	@Test 
	public void testAddOperator2() {
		Expr tenPlusTwenty = new Plus(const10, const20); 
		int result = tenPlusTwenty.eval(); 
		assertEquals(30, result); 
	} 
	@Test 
	public void testConstant() {
		Expr ten = const10; 
		int result = ten.eval(); 
		assertEquals(10, result); 
	} 
	@Test(expected = IllegalArgumentException.class)
	public void testNullArgsForPlusOperator() {
		Expr invalidPlus = new Plus(null, null); 
	} 
	@Test 
	public void testMultOperator1() {
		Mult tenMultTwenty = new Mult(const10, const20); 
		int result = tenMultTwenty.eval(); 
		assertEquals(200, result); 
	} 
	@Test 
	public void testMultOperator2() {
		Expr tenMultTwenty = new Mult(const10, const20); 
		int result = tenMultTwenty.eval(); 
		assertEquals(200, result); 
	}  
	@Test 
	public void testDivOperator1() {
		Expr tenDivTwenty = new Div(const10, const20); 
		int result = tenDivTwenty.eval(); 
		assertEquals(0, result); 
	} 
	@Test 
	public void testDivOperator2() {
		Expr twentyDivTen = new Div(const20, const10); 
		int result = twentyDivTen.eval(); 
		assertEquals(2, result); 
	} 
	@Test(expected = ArithmeticException.class) 
	public void testDivByZero() {
		Expr divByZero = new Div(const20, new Constant(0)); 
		divByZero.eval(); 
	} 
} 
