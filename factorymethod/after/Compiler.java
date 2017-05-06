// This refactored implementation uses factory method named "make" to create the nodes 

abstract class Expr {
	public abstract void genCode(); 
}

class Constant extends Expr {
	int val; 
	public Constant(int arg) {
		val = arg; 
	}
	public static Expr make(int val) { 
		return new Constant(val);
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
	public static Expr make(Expr left, Expr right) {
		return new Addition(left, right); 
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
	public static Expr make(Expr left, Expr right) {
		return new Multiplication(left, right);
	} 
	public void genCode() {
		super.genCode(); 
		System.out.println("imul"); 
	}
}

class Compiler {
	public static void main(String []args) {
		// ((10 * 20) + 30)  
		Expr expr = Addition.make(
				Multiplication.make(
					Constant.make(10), 
					Constant.make(20)), 
				Constant.make(30)); 	
		expr.genCode(); 
	}
}
