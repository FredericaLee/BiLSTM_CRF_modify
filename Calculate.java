package prepocess;
#calculate Precision/recall rate/F
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
* write by Linwei Li
* 2018年7月13日 下午1:23:53
*/
public class Calculate {
	
	public static void main(String[] args) throws IOException{
		cal("F:/intern/test/answerword");
		
	}

	public static void cal(String src) throws IOException{//计算
		BufferedReader re1 = new BufferedReader(new InputStreamReader(new FileInputStream("F:/intern/test/answerwords"), "UTF-8"));
		BufferedReader re2 = new BufferedReader(new InputStreamReader(new FileInputStream(src), "UTF-8"));
		ArrayList<ArrayList<String[]>> an=new ArrayList<ArrayList<String[]>>();
		ArrayList<ArrayList<String[]>> self=new ArrayList<ArrayList<String[]>>();
		
		String temp=null;
		//re1.readLine();
		ArrayList<String[]>  tt=new ArrayList<String []>();
		while((temp=re1.readLine())!=null){
				if(temp.equals("")){
					an.add(tt);
					//System.out.println(tt.size());
					tt=new ArrayList<String []>();
				}
				else{
					String[] te=temp.split("=");
					tt.add(te);
				}
		}
		re1.close();
		temp=null;
		an.add(tt);
		//System.out.println(tt.size());
		tt=new ArrayList<String []>();
		//re2.readLine();
		while((temp=re2.readLine())!=null){
			if(temp.equals("")){
				self.add(tt);
				tt=new ArrayList<String []>();
			}
			else{
				String[] te=temp.split("=");
				tt.add(te);
			}
											}
		self.add(tt);
		re2.close();	
		double sum=ssum(an);//存在总数
		System.out.println("SUM："+sum);
		double sesum=ssum(self);//识别总数
		System.out.println("GET："+sesum);
		double right=0;//正确数
		
		
//		for(int i=0;i<an.get(i).size();i++){
//			System.out.println(an.get(i).get(0)[0]+"|"+an.get(i).get(0)[1]);
//		}
//		for(int i=0;i<self.get(i).size();i++){
//			System.out.println(self.get(i).get(0)[0]+"|"+self.get(i).get(0)[1]);
//		}
		
		//int c=10;
		//System.out.println(an.size());
		for(int i=0;i<an.size();i++){
			//System.out.println(an.size());
			//c--;
			//if(c==0)break;
			ArrayList<String[]> kotai=an.get(i);
			ArrayList<String[]> test=self.get(i);
		//	System.out.println(i+"句话");
			for(int j=0;j<test.size();j++){
		//	System.out.println("审查 "+test.get(j)[0]+"="+test.get(j)[1]);
				for(int k=0;k<kotai.size();k++){
					//System.out.print();
					if(test.get(j)[0].equals(kotai.get(k)[0])&&test.get(j)[1].equals(kotai.get(k)[1])){
						//System.out.println(kotai.get(k)[0]+"="+kotai.get(k)[1]+"合格");
						//System.out.println(test.get(j)[0]);
						kotai.remove(k);
						//System.out.println(kotai.size());
						k=-1;
						right++;
						break;
															}
				
				}
				
				test.remove(j);
				//System.out.println(test.size());
				//for(int k=0;k<test.size())
				j=-1;
				//System.out.println(i);
			}	
			//System.out.println(i);
		}
		System.out.println("Right:"+right);
		double pre=right/sesum;
		double recall=right/sum;
		System.out.println("Precision:"+pre);
		System.out.println("Recall:"+recall);
		System.out.println("F1:"+pre*recall*2/(pre+recall));
	}
	
	public static int ssum(ArrayList<ArrayList<String[]>> t){
		int size=0;
		for(int i=0;i<t.size();i++){
			size+=t.get(i).size();
		}
		return size;
	}
}
