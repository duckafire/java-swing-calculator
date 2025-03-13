package calculator.algorithm;

public enum Operator{
	ADD('+'){
		public Double calculate(Double n0, Double n1){
			return n0 + n1;
		}
	},
	SUB('-'){
		public Double calculate(Double n0, Double n1){
			return n0 - n1;
		}
	},
	MUL('*'){
		public Double calculate(Double n0, Double n1){
			return n0 * n1;
		}
	},
	DIV('/'){
		public Double calculate(Double n0, Double n1){
			return n0 / n1;
		}
	},
	POR('%'){
		public Double calculate(Double n0, Double n1){
			return n0 * (n1 / 100);
		}
	};

	private char id;

	Operator(char signal){
		this.id = signal;
	}

	public char getId(){
		return this.id;
	}

	public abstract Double calculate(Double n0, Double n1);
}
