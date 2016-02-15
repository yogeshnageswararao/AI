
public class Node1 {
	 public int OwnIndex,ParentIndex,BlankXcoordinate,BlankYcoordinate,ManhattanDistance;
	 public char Direction='\0';
	public int State[][]=new int[6][6];
	 public Node1()
	 {
	 }
	 
	public Node1(int[][] values,int X, int Y,int Index, int Parent,char Dir) 
	{
		 for(int i=0;i<6;i++)
		 {
			 for(int j=0;j<6;j++)
		 
			 {
				 State[i][j]=values[i][j];
			 }
		 }
			
		 BlankXcoordinate=X;
		 BlankYcoordinate=Y;
		 ParentIndex=Parent;
	     Direction=Dir;		
 }
}
