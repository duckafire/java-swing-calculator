public enum Operations{
	ADD_SUB{
		public Double calculate(Double n0, Double n1, boolean isFirst){
			if(isFirst)
				return n0 + n1;
			else
				return n0 - n1;
		}
	},
	
	MUL_DIV{
		public Double calculate(Double n0, Double n1, boolean isFirst){
			if(isFirst)
				return n0 * n1;
			else
				return n0 / n1;
		}
	},

	POR_POW{
		public Double calculate(Double n0, Double n1, boolean isFirst){
			if(isFirst)
				return n0 * ((double)(n1 / 100));
			else
				return Math.pow(n0, n1);
		}
	};

	Operations(){
	}

	public abstract Double calculate(Double n0, Double n1, boolean isFirst);
}

