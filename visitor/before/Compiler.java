// This code targets two platforms: JVM and DOTNET
// Use visitor pattern to refactor the conditional checks 

enum Target {
	JVM, DOTNET 
}

abstract class Expr {
	public static Target t  = Target.JVM; 
	public static void setTarget(Target target) {
		t = target; 	
	}
	public abstract void genCode(); 
}

class Constant extends Expr {
	int val; 
	public Constant(int arg) {
		val = arg; 
	}
	public void genCode() {
		if(t == Target.JVM) {
			System.out.println("bipush " + val); 
		}
		else { // DOTNET 
			System.out.println("ldarg " + val);
		}
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
		if(t == Target.JVM) { 
			System.out.println("iadd"); 
		}
		else { // DOTNET 
			System.out.println("add"); 
		}
	}
}

class Sub extends Expr {
	private Expr left, right; 
	public Sub(Expr arg1, Expr arg2) {
		left = arg1;
		right = arg2; 	
	}
	public void genCode() {
		left.genCode();
		right.genCode(); 
		if(t == Target.JVM) { 
			System.out.println("isub"); 
		}
		else { // DOTNET 
			System.out.println("sub"); 
		}	
	}
}

class Compiler {
	public static void main(String []args) {
		Expr.setTarget(Target.JVM); 
		// (10 + (20 - 30))  
		Expr expr = new Plus(
				new Constant(10), 
				new Sub(
					new Constant(20), 
					new Constant(30))); 	
		expr.genCode(); 
	}
}
