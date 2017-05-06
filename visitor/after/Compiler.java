// This code employs visitor pattern 

abstract class Expr {
	public abstract void accept(Visitor v); 
}

class Constant extends Expr {
	int val; 
	public Constant(int arg) {
		val = arg; 
	}
	public int getVal() {
		return val; 
	}
	public void accept(Visitor v) {
		v.visit(this); 
	}
}

class Plus extends Expr {
	private Expr left, right; 
	public Plus(Expr arg1, Expr arg2) {
		left = arg1;
		right = arg2; 	
	}
	public Expr getLeft() {
		return left; 
	}
	public Expr getRight() {
		return right; 
	}
	public void accept(Visitor v) {
		v.visit(this); 
	}
} 

class Sub extends Expr {
	private Expr left, right; 
	public Sub(Expr arg1, Expr arg2) {
		left = arg1;
		right = arg2; 	
	}
	public Expr getLeft() {
		return left; 
	}
	public Expr getRight() {
		return right; 
	}
	public void accept(Visitor v) {
		v.visit(this); 
	}
}

abstract class Visitor {
	public abstract void visit(Constant constant);
	public abstract void visit(Plus plus);
	public abstract void visit(Sub sub); 
	public abstract void genCode(Expr expr); 
}

class JVMVisitor extends Visitor {
	public void visit(Constant arg) {
		System.out.println("iload " + arg.getVal());  
	}
	public void visit(Plus plus) {
		genCode(plus.getLeft()); 
		genCode(plus.getRight()); 
		System.out.println("iadd"); 
	}
	public void visit(Sub sub) {
		genCode(sub.getLeft()); 
		genCode(sub.getRight()); 
		System.out.println("isub"); 
	}
	public void genCode(Expr expr) {
    		expr.accept(this);
  	}
}

class DOTNETVisitor extends Visitor {
	public void visit(Constant arg) {
		System.out.println("ldarg " + arg.getVal());  
	}
	public void visit(Plus plus) {
		genCode(plus.getLeft()); 
		genCode(plus.getRight()); 
		System.out.println("add"); 
	}
	public void visit(Sub sub) {
		genCode(sub.getLeft()); 
		genCode(sub.getRight()); 
		System.out.println("sub"); 
	}
	public void genCode(Expr expr) {
    		expr.accept(this);
  	}
}

class Compiler {
	public static void main(String []args) {
		// (10 + (20 - 30))  
		Expr expr = new Plus(
				new Constant(10), 
				new Sub(
					new Constant(20), 
					new Constant(30))); 	
		Visitor v = new DOTNETVisitor();
		v.genCode(expr); 
	}
}
