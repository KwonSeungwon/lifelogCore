package lifelogcollector.structure;

public class Step {
		private String stdatetime;
		private int step;
		
		public Step(){
			this.stdatetime = "";
			this.step = 0;		
		}
		
		public Step(String dt2, int st ){
			this.stdatetime = dt2;
			this.step = st;
		}
		
		public void setDatetimest(String dt2){
			this.stdatetime = dt2;
		}
		public void setstep(int st){
			this.step = st;
		}
		
		public String getDatetimest(){	
			return this.stdatetime;
		}
		
		public int getstep(){	
			return this.step;
		}
		
		}


