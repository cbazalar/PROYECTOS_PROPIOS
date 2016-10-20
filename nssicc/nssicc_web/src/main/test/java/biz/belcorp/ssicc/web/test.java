package biz.belcorp.ssicc.web;


public class test {

	int i= 0;
	int j= 0;
	int k = 0;
	int n= 20;
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		test t = new test();
		t.abc();

	}
	
	public void abc(){
		
		
		for(i=0;i<n;i++){
			for(j=i; j<n;j++){
				
				k++;
				//System.out.print(k+", ");
			}
				
			System.out.print("k: "+ k + "\n");
		}
		
	}

}
