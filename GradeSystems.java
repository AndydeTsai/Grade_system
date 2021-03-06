import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;


public class GradeSystems {
	double[] weights={0.1, 0.1, 0.1, 0.3, 0.4};
	String[][] alist = new String[100][8];
	int index=0;
	
	public GradeSystems() throws FileNotFoundException {
		File file = new File("gradeInput.txt");
		Scanner sc=new Scanner(file);
		//{0.1,0.1,0.1,0.3,0.4}
		
		while(sc.hasNextLine()){
			String line = sc.nextLine();
			Scanner sc1=new Scanner(line);
			alist[index][0]=sc1.next();
			alist[index][1]=sc1.next();
			alist[index][2]=sc1.next();
			alist[index][3]=sc1.next();
			alist[index][4]=sc1.next();
			alist[index][5]=sc1.next();
			alist[index][6]=sc1.next();
			alist[index][7]=String.valueOf(calculateTotalGrade(weights, alist[index][2], alist[index][3], alist[index][4], alist[index][5], alist[index][6]));
			index=index+1;
		}
	}
	
	private double calculateTotalGrade(double[] weights, String lab1, String lab2, String lab3, String midTerm, String finalExam) {
		double result = (Integer.valueOf(lab1)*weights[0])+(Integer.parseInt(lab2)*weights[1])+(Integer.valueOf(lab3)*weights[2])+(Integer.valueOf(midTerm)*weights[3])+(Integer.valueOf(finalExam)*weights[4]);
		return result;
	}

	public String getcurrentname(String ID){
		String ret = "Jack", tmp = ID;
		
		for(int i=0; i<index; i++){
			String icom = alist[i][0];
//			System.out.println(i + " " + tmp + " " + icom + ". ");
			if(ID.equals(icom)){
				ret = alist[i][1];
				break;
			}
		}
		return ret;
	}
	
	public void showGrade(String ID){
		for(int i=0; i<index; i++){
			String tmpID = alist[i][0];
			if(ID.equals(tmpID)){		
				System.out.println(alist[i][1]+"成績:");
				System.out.println("lab1:       "+alist[i][2]);
				System.out.println("lab2:       "+alist[i][3]);
				System.out.println("lab3:       "+alist[i][4]);
				System.out.println("mid-term:   "+alist[i][5]);
				System.out.println("final exam: "+alist[i][6]);
				System.out.println("total grade:"+alist[i][7]);
			}
		}
	}
	
	public void showRank(String ID){
		int rank = 1;
		String crank = "";
		
		for(int i=0; i<index; i++){			//找出使用者的成績
			String tmpID = alist[i][0];
			if(ID.equals(tmpID)){		
				crank = alist[i][7];
			}
		}
			
		for(int j=0; j<index; j++){			//逐一比較成績
			String crank2 = alist[j][7];
			if(crank.compareTo(crank2) < 0){
				//System.out.println(crank + ">" + crank2);
				rank++; //預設為第一名，分數小於一人就後退一個名次。
			 }
		 }
		
		 System.out.println(getcurrentname(ID)+"排名第"+rank);
		 return ;
	}
	
	public void updateWeights (){
		 showOldWeights() ;
		 getNewWeights();
//		 System.out.println(weights[0]+" "+weights[1]+" "+weights[2]+" "+weights[3]+" "+weights[4]);
		 for(int i=0; i<index; i++){
//			String oldScore = alist[i][7];
			alist[i][7]=String.valueOf(calculateTotalGrade(weights, alist[i][2], alist[i][3], alist[i][4], alist[i][5], alist[i][6]));
//			System.out.println("Old:" + oldScore + " -> New:" + alist[i][7]);
		 }
	}

	public void getNewWeights() {
		double[] tmpweights=new double[5];
		
		boolean RIGHT=false;
		Scanner sc=new Scanner(System.in);
		while(!RIGHT){
			System.out.print("\t\tlab1\t\t");
			tmpweights[0]=sc.nextDouble()/100;
			System.out.print("\t\tlab2\t\t");
			tmpweights[1]=sc.nextDouble()/100;
			System.out.print("\t\tlab3\t\t");
			tmpweights[2]=sc.nextDouble()/100;
			System.out.print("\t\ttmid-term\t");
			tmpweights[3]=sc.nextDouble()/100;
			System.out.print("\t\tfinal exam\t");
			tmpweights[4]=sc.nextDouble()/100;
			System.out.println("\t請確認新配分");
			System.out.println("\t\tlab1\t\t"+(int)(tmpweights[0]*100)+"%");
			System.out.println("\t\tlab2\t\t"+(int)(tmpweights[1]*100)+"%");
			System.out.println("\t\tlab3\t\t"+(int)(tmpweights[2]*100)+"%");
			System.out.println("\t\tmid-term\t"+(int)(tmpweights[3]*100)+"%");
			System.out.println("\t\tfinal exam\t"+(int)(tmpweights[4]*100)+"%");
			System.out.println("以上正確嗎? Y (Yes) 或 N (No) ");
			String cmd=sc.next();
			if(cmd.equalsIgnoreCase("y")){
				weights[0]=tmpweights[0];
				weights[1]=tmpweights[1];
				weights[2]=tmpweights[2];
				weights[3]=tmpweights[3];
				weights[4]=tmpweights[4];
				break;
			}
		}
		return ;		
	}


	private void showOldWeights() {
		System.out.println("\t舊配分");		
		System.out.println("\t\tlab1\t\t"+(int)(weights[0]*100)+"%");
		System.out.println("\t\tlab2\t\t"+(int)(weights[1]*100)+"%");
		System.out.println("\t\tlab3\t\t"+(int)(weights[2]*100)+"%");
		System.out.println("\t\tmid-term\t"+(int)(weights[3]*100)+"%");
		System.out.println("\t\tfinal exam\t"+(int)(weights[4]*100)+"%");
	}


	public boolean checkID(String ID) {
		boolean result=false;
		for(int i=0; i<index; i++){
			String icom=alist[i][0];
			if(ID.equals(icom)){				
				result = true;
				break;
			}
		}
		return result;
	}
}
