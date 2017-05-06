// this code uses builder pattern along with flyweight pattern 

import java.util.Map;
import java.util.HashMap; 

abstract class Expr {
	public abstract void genCode(); 
}

class ExprBuilder {
	private Expr expr = null; 
	public ExprBuilder() {} 
	public ExprBuilder Const(int arg) { 
		expr = Constant.make(arg); 
		return this; 
	}   
	public ExprBuilder Plus(int arg) { 
		expr = new Addition(expr, Constant.make(arg)); 
		return this; 
	}   
	public ExprBuilder Mult(int arg) { 
		expr = new Multiplication(expr, Constant.make(arg)); 
		return this; 
	}   
	public Expr Build() {
		return expr; 
	} 	
} 

class Constant extends Expr {
	private int val; 
	private static Map<Integer, Constant> pool = new HashMap<>(); 
	private Constant(int arg) {
		val = arg; 
	}
	public static Expr make(int val) { 
		if(!pool.containsKey(val)) {
			// System.out.println("inserting value " + val); 
			pool.put(val, new Constant(val)); 
		} 
		return pool.get(val); 
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
		Expr expr = new ExprBuilder().Const(10).Mult(20).Plus(30).Build(); 
		expr.genCode(); 
	}
}
