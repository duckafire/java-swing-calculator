package calculator.algorithm;

public enum Operator{
	ADD('+', OperatorsIcon.ADD.getChar()){
		public Double calculate(Double n0, Double n1){
			return n0 + n1;
		}
	},
	SUB('-', OperatorsIcon.SUB.getChar()){
		public Double calculate(Double n0, Double n1){
			return n0 - n1;
		}
	},
	MUL('*', OperatorsIcon.MUL.getChar()){
		public Double calculate(Double n0, Double n1){
			return n0 * n1;
		}
	},
	DIV('/', OperatorsIcon.DIV.getChar()){
		public Double calculate(Double n0, Double n1){
			if(n1.equals(0.0))
				return 0.0;

			return n0 / n1;
		}
	},
	POR('%', OperatorsIcon.POR.getChar()){
		public Double calculate(Double n0, Double n1){
			return n0 * (n1 / 100);
		}
	};

	private char id;
	private char visualId;

	Operator(char _id, char _visualId){
		this.id = _id;
		this.visualId = _visualId;
	}

	public char getId(){
		return this.id;
	}

	public char getVisualId(){
		return this.visualId;
	}

	public abstract Double calculate(Double n0, Double n1);
}
