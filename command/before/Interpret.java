// this is procedural implementation of an interpreter - 
// use object oriented approach using command pattern instead 

import java.util.Stack; 

class ByteCode { 
	private ByteCode() {} 
	// Bytecodes from JVM 8 Specification: 
	// https://docs.oracle.com/javase/specs/jvms/se8/html/jvms-6.html#jvms-6.5 
	public static final byte ILOAD = (byte)0x10; 	
	public static final byte IMUL = (byte)0x68; 
	public static final byte IDIV = (byte)0x6c; 
	public static final byte IADD = (byte)0x60; 
	public static final byte ISUB = (byte)0x64; 
}

class Interpret { 
	private static Stack<Integer> executionStack = new Stack<Integer>(); 
	private static int interpret(byte[] byteCodes) {
		int pc = 0;
		while(pc < byteCodes.length) { 
			switch(byteCodes[pc++]) {
			case ByteCode.ILOAD: 
				executionStack.push((int)byteCodes[pc++]); break;  
			case ByteCode.IMUL: 
				executionStack.push(executionStack.pop() * executionStack.pop());  break; 	
			case ByteCode.IDIV: 
			{ 
				int rval = executionStack.pop();
				int lval = executionStack.pop();
				executionStack.push(lval / rval);  break; 	
			} 
			case ByteCode.IADD: 
				executionStack.push(executionStack.pop() + executionStack.pop());  break; 	
			case ByteCode.ISUB: 
			{ 
				int rval = executionStack.pop();
				int lval = executionStack.pop();
				executionStack.push(lval - rval);  break; 	
			} 
			} 
		}	  
		return executionStack.pop(); 
	} 

	public static void main(String []args) {
		// ((10 * 20) + 30)
		byte []byteCodes = { ByteCode.ILOAD, (byte)0x0A, ByteCode.ILOAD, (byte)0x14, ByteCode.IMUL, ByteCode.ILOAD, (byte)0x1E, ByteCode.IADD }; 
		int result = interpret(byteCodes); 
		System.out.printf("Execution result is: %d", result); 
	} 
}
