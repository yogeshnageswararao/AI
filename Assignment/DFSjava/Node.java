class Node
	{
		 public int OwnIndex,ParentIndex,BlankXcoordinate,BlankYcoordinate,Child1Index,Child2Index,Child3Index,Child4Index;
		 public char Direction='\0';
		public int State[][]=new int[5][5];
		 public Node()
		 {
		 }
		 
		public Node(int[][] values,int X, int Y,int Index, int Parent,char Dir) 
		{
			 for(int i=0;i<5;i++)
			 {
				 for(int j=0;j<5;j++)
			 
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