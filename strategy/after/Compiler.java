// This code targets two platforms: JVM and DOTNET
// This code uses strategy pattern 

abstract class Target { 
	public abstract void genCode(Constant constant); 
	public abstract void genCode(Plus plus); 
	public abstract void genCode(Mult mult); 
}

class JVMTarget extends Target { 
	public void genCode(Constant constant) { 
		System.out.println("bipush " + constant.getValue()); 
	}
	public void genCode(Plus plus) { 
		System.out.println("iadd"); 
	}
	public void genCode(Mult mult) { 
		System.out.println("imul"); 
	}
} 

class DotNetTarget extends Target { 
	public void genCode(Constant constant) {
		System.out.println("ldarg " + constant.getValue());
	}
	public void genCode(Plus plus) { 
		System.out.println("add"); 
	}
	public void genCode(Mult mult) { 
		System.out.println("mul"); 
	}
} 

abstract class Expr {
	protected static Target target  = new JVMTarget(); 
	public static void setTarget(Target newTarget) {
		target = newTarget; 	
	}
	public abstract void genCode(); 
}

class Constant extends Expr {
	int val; 
	public Constant(int arg) {
		val = arg; 
	}
	public int getValue() { 
		return val; 
	} 
	public void genCode() {
		target.genCode(this); 
	}
}

class Plus extends Expr {
	private Expr left, right; 
	public Plus(Expr arg1, Expr arg2) {
		left = arg1;
		right = arg2; 	
	}
	public void genCode() {
		left.genCode();
		right.genCode(); 
		target.genCode(this); 
	}
}

class Mult extends Expr {
	private Expr left, right; 
	public Mult(Expr arg1, Expr arg2) {
		left = arg1;
		right = arg2; 	
	}
	public void genCode() {
		left.genCode();
		right.genCode(); 
		target.genCode(this); 
	}
}

class Compiler {
	public static void main(String []args) {
		Expr.setTarget(new JVMTarget()); 
		// ((10 * 20) + 30))  
		Expr expr = new Plus(
				new Mult(
					new Constant(10), 
					new Constant(20)), 
				new Constant(30)); 	
		expr.genCode(); 
	}
}
