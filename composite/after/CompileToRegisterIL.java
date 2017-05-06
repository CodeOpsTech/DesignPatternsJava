// This code uses composite pattern, but targets a register-based IL 
// In the opcodes, the source values or registers come first 
// and the target registers are towards the right  

class Register {
	private int index; 
	private Register(int index) {
		this.index = index;
	} 
	public static Register getRegister(int index) {
		return new Register(index); 
	} 
	public int getIndex() {
		return index;
	} 
} 

class RegisterAllocator {
	private static int registerIndex = 0; 
	public static Register getNextRegister() {
		return Register.getRegister(registerIndex++); 
	}
}

abstract class Expr {
	public abstract Register genCode(); 
}

class Constant extends Expr {
	int val; 
	public Constant(int arg) {
		val = arg; 
	}
	public Register genCode() {
		Register targetRegister = RegisterAllocator.getNextRegister(); 
		System.out.printf("mov %d, r%d\n", val, targetRegister.getIndex());
		return targetRegister; 
	}
}

class Addition extends Expr {
	private Expr left, right; 
	public Addition(Expr arg1, Expr arg2) {
		left = arg1; 
		right = arg2; 
	}
	public Register genCode() {
		Register firstRegister = left.genCode();
                Register secondRegister = right.genCode();
                Register targetRegister = RegisterAllocator.getNextRegister();
                System.out.printf("add r%d, r%d, r%d\n", firstRegister.getIndex(), secondRegister.getIndex(), targetRegister.getIndex());
                return targetRegister;
	}
}

class Multiplication extends Expr {
	private Expr left, right; 
	public Multiplication(Expr arg1, Expr arg2) {
		left = arg1; 
		right = arg2; 
	}
	public Register genCode() {
		Register firstRegister = left.genCode();
                Register secondRegister = right.genCode();
                Register targetRegister = RegisterAllocator.getNextRegister();
                System.out.printf("mul r%d, r%d, r%d\n", firstRegister.getIndex(), secondRegister.getIndex(), targetRegister.getIndex());
                return targetRegister;
	}
}

class CompileToRegisterIL {
	public static void main(String []args) {
		// ((10 * 20) + 30)  
		Expr expr = new Addition(
				new Multiplication(
					new Constant(10), 
					new Constant(20)), 
				new Constant(30)); 	
		expr.genCode(); 
	}
}
