// This code works, but follows procedural approach
// Task: Convert it to Object Oriented approach and apply Composite pattern

class Expr {
	Expr left;
	String value;
	Expr right;
	public Expr(Expr left, String value, Expr right) {
		this.left = left;
		this.value = value;
		this.right = right; 
	} 

	void genCode() {
		if(this == null) 
			return;
		if((left == null) && (right == null)) {
			System.out.println("iload " + value); 
		} 
		else { 	// its an intermediate node 
			left.genCode();
			right.genCode(); 
			switch(value) {
			case "+": System.out.println("iadd"); break; 
			case "-": System.out.println("isub"); break; 
			case "*": System.out.println("imul"); break; 
			case "/": System.out.println("idiv"); break; 
			default: 
				System.out.println("Not implemented yet!"); 
			}
		} 
	}
}

class Compiler { 
	public static void main(String []args) {
		// ((10 * 20) + 30)  
		Expr expr = new Expr(
				new Expr(
					new Expr(null, "10", null), "*", new Expr(null, "20", null)), 
				     	"+", 
				     	new Expr(null, "30", null)); 
		expr.genCode(); 
	} 
}

