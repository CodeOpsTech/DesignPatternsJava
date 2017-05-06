// This is the refactored implementation that exploits the power provided 
// by Composite pattern. The genCode method creates Java-like bytecode that is stack-based 

abstract class Expr {
	public abstract void genCode(); 
}

class Constant extends Expr {
	int val; 
	public Constant(int arg) {
		val = arg; 
	}
	public void genCode() {
		System.out.println("iload " + val);
	}
}

class BinaryExpr extends Expr {
	private Expr left, right; 
	public BinaryExpr(Expr arg1, Expr arg2) {
		left = arg1;
		right = arg2; 	
	}
	public void genCode() {
		left.genCode();
		right.genCode(); 
	}
}

class Addition extends BinaryExpr {
	public Addition(Expr arg1, Expr arg2) {
		super(arg1, arg2); 
	}
	public void genCode() {
		super.genCode(); 
		System.out.println("iadd"); 
	}
}

class Multiplication extends BinaryExpr {
	public Multiplication(Expr arg1, Expr arg2) {
		super(arg1, arg2); 
	}
	public void genCode() {
		super.genCode(); 
		System.out.println("imul"); 
	}
}

class CompilerRefactored {
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
