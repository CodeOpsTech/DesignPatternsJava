import static org.mockito.Mockito.*;
import static org.junit.Assert.*;
import java.util.List;
import org.junit.*;
import java.util.*; 

public class MockByteCodeTest { 
	private ByteCode byteCode = mock(ByteCode.class);
	private Stack<Integer> execStack = new Stack<>(); 

	@Test 
	public void checkByteCode() {
		assertTrue(byteCode instanceof ByteCode);
	}
	@Test 
	public void checkByteCodeDefaultInit() {
		assertEquals(0, byteCode.exec(execStack));
	}
	@Test 
	public void checkByteCodeMockExecMethod() {
		when(byteCode.exec(execStack)).thenReturn(10); 
		assertEquals(10, byteCode.exec(execStack));
	}
	@Test 
	public void mockBIPUSHByteCode() {
		BIPUSH bipush10 = new BIPUSH(10); 
		when(bipush10.exec(execStack)).thenReturn(10); 
		assertEquals(10, byteCode.exec(execStack));
	}
	@Test 
	public void mockIADDByteCode() {
		BIPUSH bipush10 = new BIPUSH(10); 
		BIPUSH bipush20 = new BIPUSH(20); 
		IADD iadd = new IADD(); 
		assertEquals(10, byteCode.exec(execStack));
	}
}
