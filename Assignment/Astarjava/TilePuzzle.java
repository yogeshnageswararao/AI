import java.util.*;

public class TilePuzzle {
	
int IndexOfNextNode=1;	

	
		
 static int CalculateManhattanDistance(Node Obj,Node Goal_Obj)//function to calculate manhattan distance for 3*3
	{
		
		    int manhattanCounter = 0;
		    for (int i=1; i<4; i++) {
		        for (int j=1; j<4; j++) 
		        {
		            if(Obj.State[i][j]!=Goal_Obj.State[i][j]) //if not equal 
		                         manhattanCounter++;            
		        }    
		    }
	   return manhattanCounter;	    
    }	
	
	static boolean CompareNodes(Node Obj1,Node Obj2) 
	{
		for (int i=1; i<4; i++) 
		{
	        for (int j=1; j<4; j++) 
	        {
	            if(Obj1.State[i][j]!=Obj2.State[i][j])
	            {
	                return true; //the two nodes are not same
	             }
	        }   
	
		}
	
	return false;//the two nodes are same
	}
	
	static boolean CheckUpDirection(Node Obj)
	{
		if (Obj.State[Obj.BlankXcoordinate-1][Obj.BlankYcoordinate]==-1) 
		{
	        return false;//cannot go up
	    }
		else
		{
	        return true;//can go up
	    }

	}
	
	static boolean CheckDownDirectionDirection(Node Obj)
	{
		if (Obj.State[Obj.BlankXcoordinate+1][Obj.BlankYcoordinate]==-1) 
		{
	        return false;//cannot go down
	    }
		else
		{
	        return true;//can go up
	    }
	
	}
	
	static boolean CheckRightDirection(Node Obj)
	{
		if (Obj.State[Obj.BlankXcoordinate][Obj.BlankYcoordinate+1]==-1) 
		{
	        return false;//cannot go right
	    }
		else
		{
	        return true;//can go right
	    }
	
	}
	
	static boolean CheckLeftDirection(Node Obj)
	{
		if (Obj.State[Obj.BlankXcoordinate][Obj.BlankYcoordinate-1]==-1) 
		{
	        return false;//cannot go left
	    }
		else
		{
	        return true;//can go left
	    }
	
	}
	
	static void ExchangeUp(Node Obj)//exchange the x and y coordinate for blank space
	{
	    Obj.State[Obj.BlankXcoordinate][Obj.BlankYcoordinate]=Obj.State[Obj.BlankXcoordinate-1][Obj.BlankYcoordinate];
	    Obj.State[Obj.BlankXcoordinate-1][Obj.BlankYcoordinate]=0;    

	}
	
	static void ExchangeDown(Node Obj)//exchange the x and y coordinate for blank space
	{
	    Obj.State[Obj.BlankXcoordinate][Obj.BlankYcoordinate]=Obj.State[Obj.BlankXcoordinate+1][Obj.BlankYcoordinate];
	    Obj.State[Obj.BlankXcoordinate+1][Obj.BlankYcoordinate]=0;    

	}
	
	static void ExchangeRight(Node Obj)//exchange the x and y coordinate for blank space
	{
	    Obj.State[Obj.BlankXcoordinate][Obj.BlankYcoordinate]=Obj.State[Obj.BlankXcoordinate][Obj.BlankYcoordinate+1];
	    Obj.State[Obj.BlankXcoordinate][Obj.BlankYcoordinate+1]=0;    

	}
	
	static void ExchangeLeft(Node Obj)//exchange the x and y coordinate for blank space
	{
	    Obj.State[Obj.BlankXcoordinate][Obj.BlankYcoordinate]=Obj.State[Obj.BlankXcoordinate][Obj.BlankYcoordinate-1];
	    Obj.State[Obj.BlankXcoordinate][Obj.BlankYcoordinate-1]=0;    

	}
	
	static void PrintNode(Node Obj)//print the 3*3 matrix of the node
	{
		System.out.println("\nNavigation of space: "+Obj.Direction);
		for(int i=1;i<4;i++)
		{
			for(int j=1;j<4;j++)
		
			{
				System.out.print(Obj.State[i][j]+" ");
			}
			System.out.print("\n");
		}
	}
	
	static int CreateChildNodes(Node[] Array,Node Parent,Node Goal,int NextIndex)//creation of child nodes for all the 4 directions
	{
		int Index=NextIndex;
		if(CheckUpDirection(Parent))//check for up direction
		{
	        Array[Index]=new Node(Parent.State,Parent.BlankXcoordinate,Parent.BlankYcoordinate,Index,Parent.OwnIndex,'U');	        
	        ExchangeUp(Array[Index]);//exchange the coordinates of blank space
	        Array[Index].BlankXcoordinate--;//change the x coordinates
	        Array[Index].ManhattanDistance=CalculateManhattanDistance(Array[Index],Goal);
	       
            
	        boolean cond=CompareNodes(Array[Index],Array[Parent.ParentIndex]);
	        
           if(cond==false)
	        {	
	        	Array[Index]=null;//--Index;
	        
	        }
	        else
	        {  
	        	PrintNode(Array[Index]);
	        	++Index;
		
	        }
		}
		
	if(CheckDownDirectionDirection(Parent))//check for down direction
	{
		Array[Index]=new Node(Parent.State,Parent.BlankXcoordinate,Parent.BlankYcoordinate,Index,Parent.OwnIndex,'D');	        
        ExchangeDown(Array[Index]);//exchange the coordinates of blank space
        Array[Index].BlankXcoordinate++;//change the x coordinates
        Array[Index].ManhattanDistance=CalculateManhattanDistance(Array[Index],Goal);
        
        
        boolean cond=CompareNodes(Array[Index],Array[Parent.ParentIndex]);
        
        if(cond==false)
        {	
        	Array[Index]=null;//--Index;
        
        }
        else
        {  
        	PrintNode(Array[Index]);
        	++Index;
	
        }
      }
 	
	if(CheckRightDirection(Parent))//check for right direction
	{
		Array[Index]=new Node(Parent.State,Parent.BlankXcoordinate,Parent.BlankYcoordinate,Index,Parent.OwnIndex,'R');	        
        ExchangeRight(Array[Index]);//exchange the coordinates of blank space
        Array[Index].BlankYcoordinate++;//change the y coordinates
        Array[Index].ManhattanDistance=CalculateManhattanDistance(Array[Index],Goal);
        
        boolean cond=CompareNodes(Array[Index],Array[Parent.ParentIndex]);
        
        if(cond==false)
        {	
        	Array[Index]=null;//--Index;
        
        }
        else
        {  
        	PrintNode(Array[Index]);
        	++Index;
	
        }
       }

	if(CheckLeftDirection(Parent))//check for left direction
	{
		Array[Index]=new Node(Parent.State,Parent.BlankXcoordinate,Parent.BlankYcoordinate,Index,Parent.OwnIndex,'L');	        
        ExchangeLeft(Array[Index]);//exchange the coordinates of blank space
        Array[Index].BlankYcoordinate--;//change the y coordinates
        Array[Index].ManhattanDistance=CalculateManhattanDistance(Array[Index],Goal);
      
        
        boolean cond=CompareNodes(Array[Index],Array[Parent.ParentIndex]);
        
        if(cond==false)
        {	
        	Array[Index]=null;//--Index;
        
        }
        else
        {  
        	PrintNode(Array[Index]);
        	++Index;
	
        }
	}
	
	return Index;
	}
	

	static int CalculateManhattanDistance1(Node1 Obj,Node1 Goal_Obj)
		{
			
			    int manhattanCounter = 0;
			    for (int i=1; i<5; i++) {
			        for (int j=1; j<5; j++) 
			        {
			            if(Obj.State[i][j]!=Goal_Obj.State[i][j])
			                         manhattanCounter++;            
			        }    
			    }
		   return manhattanCounter;	    
	    }	
		
		static boolean CompareNodes1(Node1 Obj1,Node1 Obj2)
		{
			for (int i=1; i<5; i++) 
			{
		        for (int j=1; j<5; j++) 
		        {
		            if(Obj1.State[i][j]!=Obj2.State[i][j])
		            {
		                return true; //the two nodes are not same
		             }
		        }   
		
			}
		
		return false;//the two nodes are same
		}
		
		static boolean CheckUpDirection1(Node1 Obj)
		{
			if (Obj.State[Obj.BlankXcoordinate-1][Obj.BlankYcoordinate]==-1) 
			{
		        return false;
		    }
			else
			{
		        return true;
		    }

		}
		
		static boolean CheckDownDirectionDirection1(Node1 Obj)
		{
			if (Obj.State[Obj.BlankXcoordinate+1][Obj.BlankYcoordinate]==-1) 
			{
		        return false;
		    }
			else
			{
		        return true;
		    }
		
		}
		
		static boolean CheckRightDirection1(Node1 Obj)
		{
			if (Obj.State[Obj.BlankXcoordinate][Obj.BlankYcoordinate+1]==-1) 
			{
		        return false;
		    }
			else
			{
		        return true;
		    }
		
		}
		
		static boolean CheckLeftDirection1(Node1 Obj)
		{
			if (Obj.State[Obj.BlankXcoordinate][Obj.BlankYcoordinate-1]==-1) 
			{
		        return false;
		    }
			else
			{
		        return true;
		    }
		
		}
		
		static void ApplyAstar(int c,Node []Array)//Display A star for 3*3
		{
			
			while(Array[c].ParentIndex!=0)
	          {
	        	  
	        	  PrintNode(Array[Array[c].ParentIndex]);//print the 3*3 matrix
	        	  c=Array[c].ParentIndex;
	          }	 
			
			
		}
		
		static void ApplyAstar1(int c,Node1 Array[])// Display A star for 4*4
		{
			while(Array[c].ParentIndex!=0)
	          {
	        	  
	        	  PrintNode1(Array[Array[c].ParentIndex]);//print the 4*4 matrix
	        	  c=Array[c].ParentIndex;
	          }	 
		}
		
		static void ExchangeUp1(Node1 Obj)
		{
		    Obj.State[Obj.BlankXcoordinate][Obj.BlankYcoordinate]=Obj.State[Obj.BlankXcoordinate-1][Obj.BlankYcoordinate];
		    Obj.State[Obj.BlankXcoordinate-1][Obj.BlankYcoordinate]=0;    

		}
		
		static void ExchangeDown1(Node1 Obj)
		{
		    Obj.State[Obj.BlankXcoordinate][Obj.BlankYcoordinate]=Obj.State[Obj.BlankXcoordinate+1][Obj.BlankYcoordinate];
		    Obj.State[Obj.BlankXcoordinate+1][Obj.BlankYcoordinate]=0;    

		}
		
		static void ExchangeRight1(Node1 Obj)
		{
		    Obj.State[Obj.BlankXcoordinate][Obj.BlankYcoordinate]=Obj.State[Obj.BlankXcoordinate][Obj.BlankYcoordinate+1];
		    Obj.State[Obj.BlankXcoordinate][Obj.BlankYcoordinate+1]=0;    

		}
		
		static void ExchangeLeft1(Node1 Obj)
		{
		    Obj.State[Obj.BlankXcoordinate][Obj.BlankYcoordinate]=Obj.State[Obj.BlankXcoordinate][Obj.BlankYcoordinate-1];
		    Obj.State[Obj.BlankXcoordinate][Obj.BlankYcoordinate-1]=0;    

		}
		
		static void PrintNode1(Node1 Obj)
		{
			System.out.println("\nNavigation of space: "+Obj.Direction);
			for(int i=1;i<5;i++)
			{
				for(int j=1;j<5;j++)
			
				{
					System.out.print(Obj.State[i][j]+" ");
				}
				System.out.print("\n");
			}
		}
		
		static int CreateChildNodes1(Node1[] Array,Node1 Parent,Node1 Goal,int NextIndex)
		{
			int Index=NextIndex;
			if(CheckUpDirection1(Parent))
			{
		        Array[Index]=new Node1(Parent.State,Parent.BlankXcoordinate,Parent.BlankYcoordinate,Index,Parent.OwnIndex,'U');	        
		        ExchangeUp1(Array[Index]);
		        Array[Index].BlankXcoordinate--;
		        Array[Index].ManhattanDistance=CalculateManhattanDistance1(Array[Index],Goal);
		       
	            
		        boolean cond=CompareNodes1(Array[Index],Array[Parent.ParentIndex]);
		        
	           if(cond==false)
		        {	
		        	Array[Index]=null;//--Index;
		        
		        }
		        else
		        {  
		        	PrintNode1(Array[Index]);
		        	++Index;
			
		        }
			}
			
		if(CheckDownDirectionDirection1(Parent))
		{
			Array[Index]=new Node1(Parent.State,Parent.BlankXcoordinate,Parent.BlankYcoordinate,Index,Parent.OwnIndex,'D');	        
	        ExchangeDown1(Array[Index]);
	        Array[Index].BlankXcoordinate++;
	        Array[Index].ManhattanDistance=CalculateManhattanDistance1(Array[Index],Goal);
	        
	        
	        boolean cond=CompareNodes1(Array[Index],Array[Parent.ParentIndex]);
	        
	        if(cond==false)
	        {	
	        	Array[Index]=null;//--Index;
	        
	        }
	        else
	        {  
	        	PrintNode1(Array[Index]);
	        	++Index;
		
	        }
	      }
	 	
		if(CheckRightDirection1(Parent))
		{
			Array[Index]=new Node1(Parent.State,Parent.BlankXcoordinate,Parent.BlankYcoordinate,Index,Parent.OwnIndex,'R');	        
	        ExchangeRight1(Array[Index]);
	        Array[Index].BlankYcoordinate++;
	        Array[Index].ManhattanDistance=CalculateManhattanDistance1(Array[Index],Goal);
	        
	        boolean cond=CompareNodes1(Array[Index],Array[Parent.ParentIndex]);
	        
	        if(cond==false)
	        {	
	        	Array[Index]=null;//--Index;
	        
	        }
	        else
	        {  
	        	PrintNode1(Array[Index]);
	        	++Index;
		
	        }
	       }

		if(CheckLeftDirection1(Parent))
		{
			Array[Index]=new Node1(Parent.State,Parent.BlankXcoordinate,Parent.BlankYcoordinate,Index,Parent.OwnIndex,'L');	        
	        ExchangeLeft1(Array[Index]);
	        Array[Index].BlankYcoordinate--;
	        Array[Index].ManhattanDistance=CalculateManhattanDistance1(Array[Index],Goal);
	      
	        
	        boolean cond=CompareNodes1(Array[Index],Array[Parent.ParentIndex]);
	        
	        if(cond==false)
	        {	
	        	Array[Index]=null;//--Index;
	        
	        }
	        else
	        {  
	        	PrintNode1(Array[Index]);
	        	++Index;
		
	        }
		}
		
		return Index;
		}
	
	
	
	
	
public static void main(String[] args)
	{
     
      int blankXstate=0;
      int blankYstate=0;
      
      
      System.out.println("Sliding tile puzzle for 8 and 16 tiles: A Star-----\n\n");
      
      System.out.println("Enter your option"+"\n 1.3*3 \n 2.4*4\n");
      Scanner scanner = new Scanner(System.in);
      int option=scanner.nextInt();
      if(option==1)
      {
    	  Node[] ObjectArray=new Node[300000];
    	  Node GoalNode=new Node();
    	  GoalNode.State[0][0]=GoalNode.State[0][1]=GoalNode.State[0][2]=GoalNode.State[0][3]=GoalNode.State[0][4]=GoalNode.State[1][0]=GoalNode.State[1][4]=GoalNode.State[2][0]=GoalNode.State[2][4]=GoalNode.State[3][0]=GoalNode.State[3][4]=GoalNode.State[4][0]=GoalNode.State[4][1]=GoalNode.State[4][2]=GoalNode.State[4][3]=GoalNode.State[4][4]=-1;
          GoalNode.State[1][1]=1;
          GoalNode.State[1][2]=2;
          GoalNode.State[1][3]=3;
          GoalNode.State[2][1]=4;
          GoalNode.State[2][2]=5;
          GoalNode.State[2][3]=6;
          GoalNode.State[3][1]=7;
          GoalNode.State[3][2]=8;
          GoalNode.State[3][3]=0;
          int InitialList[][]=new int[5][5];
          
          InitialList[0][0]=InitialList[0][1]=InitialList[0][2]=InitialList[0][3]=InitialList[0][4]=InitialList[1][0]=InitialList[1][4]=InitialList[2][0]=InitialList[2][4]=InitialList[3][0]=InitialList[3][4]=InitialList[4][0]=InitialList[4][1]=InitialList[4][2]=InitialList[4][3]=InitialList[4][4]=-1;
          
          System.out.println("Enter the Initial List");
          for(int i=1;i<4;i++)
        	  for(int j=1;j<4;j++)
                 InitialList[i][j]=scanner.nextInt();
          
          for (int i=1; i<4; i++)
          {
              for (int j=1; j<4; j++) 
              {
                  if(InitialList[i][j]==0)
                   {
                      blankXstate = i;
                      blankYstate = j;
                   }
              }
          }
          int count=0;
          ObjectArray[0]=new Node(InitialList,blankXstate,blankYstate,0,0,'\0');
          ObjectArray[0].ManhattanDistance=CalculateManhattanDistance(ObjectArray[0],GoalNode);
          System.out.println("The root node is:");
          PrintNode(ObjectArray[0]);
          
          System.out.println("The child nodes created are:"+"\n\n");
          
          int IndexOfNextNode=1;
          int i=0;
          for(i=0;CompareNodes(ObjectArray[i],GoalNode); i++)       
          {
            
        	  IndexOfNextNode=CreateChildNodes(ObjectArray,ObjectArray[i],GoalNode,IndexOfNextNode);
        	  
          }
          count=i;
          System.out.println("\nThe solution path is:");
          PrintNode(ObjectArray[count]);
          
          ApplyAstar(count,ObjectArray);//Do A star and search from the root and display the solution path
          
           
      }
      else if(option == 2)//for 4*4 tile puzzle
      {
    	  Node1[] ObjectArray=new Node1[300000];
    	  Node1 GoalNode=new Node1();
    	  //initialize the goal state matrix
    	  GoalNode.State[0][0]=GoalNode.State[0][1]=GoalNode.State[0][2]=GoalNode.State[0][3]=GoalNode.State[0][4]=GoalNode.State[0][5]=GoalNode.State[1][0]=GoalNode.State[1][5]=GoalNode.State[2][0]=GoalNode.State[2][5]=GoalNode.State[3][0]=GoalNode.State[3][5]=GoalNode.State[4][0]=GoalNode.State[4][5]=GoalNode.State[5][0]=GoalNode.State[5][1]=GoalNode.State[5][2]=GoalNode.State[5][3]=GoalNode.State[5][4]=GoalNode.State[5][5]=-1;
          GoalNode.State[1][1]=1;
          GoalNode.State[1][2]=2;
          GoalNode.State[1][3]=3;
          GoalNode.State[1][4]=4;
          GoalNode.State[2][1]=5;
          GoalNode.State[2][2]=6;
          GoalNode.State[2][3]=7;
          GoalNode.State[2][4]=8;
          GoalNode.State[3][1]=9;
          GoalNode.State[3][2]=10;
          GoalNode.State[3][3]=11;
   		  GoalNode.State[3][4]=12;
   		  
          GoalNode.State[4][1]=13;
          GoalNode.State[4][2]=14;
          GoalNode.State[4][3]=15;
          GoalNode.State[4][4]=0;    
          int InitialList[][]=new int[6][6];
          
          InitialList[0][0]=InitialList[0][1]=InitialList[0][2]=InitialList[0][3]=InitialList[0][4]=InitialList[0][5]=InitialList[1][0]=InitialList[1][5]=InitialList[2][0]=InitialList[2][5]=InitialList[3][0]=InitialList[3][5]=InitialList[4][0]=InitialList[4][5]=InitialList[5][0]=InitialList[5][1]=InitialList[5][2]=InitialList[5][3]=InitialList[5][4]=InitialList[5][5]=-1;
          
          System.out.println("Enter the Initial List");
          for(int i=1;i<5;i++)
        	  for(int j=1;j<5;j++)
                 InitialList[i][j]=scanner.nextInt();
          
          for (int i=1; i<5; i++)
          {
              for (int j=1; j<5; j++) 
              {
                  if(InitialList[i][j]==0)
                   {
                      blankXstate = i;
                      blankYstate = j;
                   }
              }
          }
          int count=0;
          ObjectArray[0]=new Node1(InitialList,blankXstate,blankYstate,0,0,'\0');
          ObjectArray[0].ManhattanDistance=CalculateManhattanDistance1(ObjectArray[0],GoalNode);
          System.out.println("The root node is:");
          PrintNode1(ObjectArray[0]);
          
          System.out.println("The child nodes created are:"+"\n\n");
          
          int IndexOfNextNode=1;
          int i=0;
          for(i=0;CompareNodes1(ObjectArray[i],GoalNode); i++)       
          {
            
        	  IndexOfNextNode=CreateChildNodes1(ObjectArray,ObjectArray[i],GoalNode,IndexOfNextNode);
        	  
          }
          count=i;
          System.out.println("\nThe solution path is:");
          PrintNode1(ObjectArray[count]);
          
          ApplyAstar1(count,ObjectArray); //Do A star and search from the root and display the solution path for 4*4
      }
      else if(option!= 1 && option!=2)
      {	  System.out.println("Invalid Option Selected"); 
          System.exit(0);
	  }
      
      scanner.close();
	}
      
}

