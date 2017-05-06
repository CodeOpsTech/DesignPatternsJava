import java.util.Stack;
import java.util.List;
import java.util.ArrayList;

class Interpreter { 
	private Stack<Integer> evalStack = new Stack<Integer>(); 
	public int interpret(ByteCode[] byteCodes) {
		for(ByteCode byteCode : byteCodes) {
			byteCode.exec(evalStack); 
		}
		return evalStack.pop(); 
	} 
} 

abstract class ByteCode {
	abstract public void exec(Stack<Integer> execStack); 
}

class ILOAD extends ByteCode {
	int val; 
	public ILOAD(int arg) {
		val = arg; 
	}
	public void exec(Stack<Integer> execStack) {
		execStack.push(val); 
	}
} 

class IADD extends ByteCode {
	public void exec(Stack<Integer> execStack) {
		execStack.push(execStack.pop() + execStack.pop()); 
	}
} 

class IMUL extends ByteCode {
	public void exec(Stack<Integer> execStack) {
		execStack.push(execStack.pop() * execStack.pop()); 
	}
} 

class ISUB extends ByteCode {
	public void exec(Stack<Integer> execStack) {
		int rval = execStack.pop(); 
		int lval = execStack.pop();
		execStack.push(lval - rval); 
	}
} 

class IDIV extends ByteCode {
	public void exec(Stack<Integer> execStack) {
		int rval = execStack.pop(); 
		int lval = execStack.pop();
		execStack.push(lval / rval); 
	}
} 

class Interpret {
	public static void main(String []args) {
		// ((10 * 20) + 30)
		ByteCode []byteCodes = { new ILOAD(0x0A), new ILOAD(0x14), new IMUL(), new ILOAD(0x1E), new IADD() }; 

		Interpreter interpreter = new Interpreter(); 
		System.out.println(interpreter.interpret(byteCodes)); 
	}
}
