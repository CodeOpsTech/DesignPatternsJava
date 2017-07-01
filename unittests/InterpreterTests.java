import static org.mockito.Mockito.*;
import static org.junit.Assert.*;
import java.util.List;
import org.junit.*;
import java.util.*; 

public class InterpreterTests { 
	Stack<Integer> execStack; 
	@Before
	public void init() {
		execStack = new Stack<>(); 
	} 
	@Test 
	public void BUPUSH10() {
		BIPUSH bipush10 = new BIPUSH(10);
		int result = bipush10.exec(execStack);
		assertEquals(10, result);
	}
	@Test 
	public void IADD() {
		IADD iadd = new IADD(); 
		BIPUSH bipush10 = new BIPUSH(10);
		BIPUSH bipush20 = new BIPUSH(20); 
		bipush10.exec(execStack);
		bipush20.exec(execStack); 
		int result = iadd.exec(execStack);
		assertEquals(result, 30);
	}
	@Test 
	public void IMUL() {
		IMUL imul = new IMUL(); 
		BIPUSH bipush10 = new BIPUSH(10);
		BIPUSH bipush20 = new BIPUSH(20); 
		bipush10.exec(execStack);
		bipush20.exec(execStack); 
		int result = imul.exec(execStack);
		assertEquals(result, 200);
	}
	@Test 
	public void IDIV() {
		IDIV idiv = new IDIV(); 
		BIPUSH bipush10 = new BIPUSH(10);
		BIPUSH bipush20 = new BIPUSH(20); 
		bipush10.exec(execStack);
		bipush20.exec(execStack); 
		int result = idiv.exec(execStack);
		assertEquals(result, 0);
	}
	@Test(expected = DivByZeroException.class)  
	public void IDIVByZero() {
		IDIV idiv = new IDIV(); 
		BIPUSH bipush10 = new BIPUSH(10);
		BIPUSH bipush0 = new BIPUSH(0); 
		bipush10.exec(execStack);
		bipush0.exec(execStack); 
		idiv.exec(execStack);
	}
	@Test
	public void InterpreterBytecodeSequence1() {
		List<ByteCode> byteCodes = Arrays.asList(new BIPUSH(10), new BIPUSH(20), new IADD()); 
		Interpreter interpret = new Interpreter(execStack);
		int result = interpret.interpret(byteCodes); 
		assertEquals(30, result); 
	}
	@Test
	public void InterpreterBytecodeSequence2() {
		List<ByteCode> byteCodes = Arrays.asList(new BIPUSH(10), new BIPUSH(20), new IMUL(), new BIPUSH(30), new IADD()); 
		Interpreter interpret = new Interpreter(execStack);
		int result = interpret.interpret(byteCodes); 
		assertEquals(230, result); 
	} 
}
