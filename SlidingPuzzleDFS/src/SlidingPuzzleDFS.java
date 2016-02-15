
import java.util.LinkedList;
import java.util.Queue;//To use queue interfaces
import java.util.Scanner;//Usage for inputs 
public class SlidingPuzzleDFS {
	
int IndexOfNextNode=1;	

    static boolean CompareNodes(Node Obj1,Node Obj2) //comparing function for 3*3 nodes
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
	
	static boolean CheckUpDirection(Node Obj) //To check whether the space can move up for 3*3 nodes
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
	
	static boolean CheckDownDirectionDirection(Node Obj)//To check whether the space can move down for 3*3 nodes
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
	
	static boolean CheckRightDirection(Node Obj)//To check whether the space can move right for 3*3 nodes
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
	
	static boolean CheckLeftDirection(Node Obj)//To check whether the space can move left for 3*3 nodes
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
	
	static void CopyObject(Node Obj1, Node Obj2)//Copy the 3*3 object 
	{
		Obj1.OwnIndex=Obj2.OwnIndex;
		Obj1.ParentIndex=Obj2.ParentIndex;
		for(int i=0;i<5;i++)
		 {
			 for(int j=0;j<5;j++)
		 
			 {
				 Obj1.State[i][j]=Obj2.State[i][j];
			 }
		 }
	Obj1.BlankXcoordinate=Obj2.BlankXcoordinate;
	Obj1.BlankYcoordinate=Obj2.BlankYcoordinate;
	Obj1.Direction=Obj2.Direction;
	
	
	
	}
	
	static void ExchangeUp(Node Obj)//Change the coordinates of blank up direction
	{
	    Obj.State[Obj.BlankXcoordinate][Obj.BlankYcoordinate]=Obj.State[Obj.BlankXcoordinate-1][Obj.BlankYcoordinate];
	    Obj.State[Obj.BlankXcoordinate-1][Obj.BlankYcoordinate]=0;    

	}
	
	static void ExchangeDown(Node Obj)//Change the coordinates of blank down direction
	{
	    Obj.State[Obj.BlankXcoordinate][Obj.BlankYcoordinate]=Obj.State[Obj.BlankXcoordinate+1][Obj.BlankYcoordinate];
	    Obj.State[Obj.BlankXcoordinate+1][Obj.BlankYcoordinate]=0;    

	}
	
	static void ExchangeRight(Node Obj)//Change the coordinates of blank right direction
	{
	    Obj.State[Obj.BlankXcoordinate][Obj.BlankYcoordinate]=Obj.State[Obj.BlankXcoordinate][Obj.BlankYcoordinate+1];
	    Obj.State[Obj.BlankXcoordinate][Obj.BlankYcoordinate+1]=0;    

	}
	
	static void ExchangeLeft(Node Obj)//Change the coordinates of blank left direction
	{
	    Obj.State[Obj.BlankXcoordinate][Obj.BlankYcoordinate]=Obj.State[Obj.BlankXcoordinate][Obj.BlankYcoordinate-1];
	    Obj.State[Obj.BlankXcoordinate][Obj.BlankYcoordinate-1]=0;    

	}
	
	static void PrintNode(Node Obj)//output the contents of the state array in the node
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
	
	static int CreateChildNodes(Node[] Array,Node Parent,Node Goal,int NextIndex)//create the child nodes for all 4 directions
	{
		int Index=NextIndex;
		if(CheckUpDirection(Parent)) //check whether the space can go up
		{
	        Array[Index]=new Node(Parent.State,Parent.BlankXcoordinate,Parent.BlankYcoordinate,Index,Parent.OwnIndex,'U');	        
	        ExchangeUp(Array[Index]);//change the x and y coordinate for blank space
	        Array[Index].BlankXcoordinate--;//change the x coordinate for blank space
	        
	        
            
	        boolean cond=CompareNodes(Array[Index],Array[Parent.ParentIndex]);//comparing with its grand parent
	        
           if(cond==false)//remove child
	        {	
	        	Array[Index]=null;//--Index;
	        
	        }
	        else
	        {  
	        	PrintNode(Array[Index]);
	        	Parent.Child1Index=Index;
	        	++Index;
	        	
		
	        }
		}
		
	if(CheckDownDirectionDirection(Parent)) //check whether the space can go down
	{
		Array[Index]=new Node(Parent.State,Parent.BlankXcoordinate,Parent.BlankYcoordinate,Index,Parent.OwnIndex,'D');	        
        ExchangeDown(Array[Index]);//change the x and y coordinate for blank space
        Array[Index].BlankXcoordinate++;//change the x coordinate for blank space
        
        
        
        boolean cond=CompareNodes(Array[Index],Array[Parent.ParentIndex]);//comparing with its grand parent
        
        if(cond==false)//remove child
        {	
        	Array[Index]=null;//--Index;
        
        }
        else
        {  
        	PrintNode(Array[Index]);
        	Parent.Child2Index=Index;
        	++Index;
	
        }
      }
 	
	if(CheckRightDirection(Parent)) //check whether the space can right up
	{
		Array[Index]=new Node(Parent.State,Parent.BlankXcoordinate,Parent.BlankYcoordinate,Index,Parent.OwnIndex,'R');	        
        ExchangeRight(Array[Index]);//change the x and y coordinate for blank space
        Array[Index].BlankYcoordinate++;//change the y coordinate for blank space
     
        
        boolean cond=CompareNodes(Array[Index],Array[Parent.ParentIndex]);//comparing with its grand parent
        
        if(cond==false)//remove child
        {	
        	Array[Index]=null;//--Index;
        
        }
        else
        {  
        	PrintNode(Array[Index]);
        	Parent.Child3Index=Index;
        	++Index;
	
        }
       }

	if(CheckLeftDirection(Parent)) //check whether the space can go left
	{
		Array[Index]=new Node(Parent.State,Parent.BlankXcoordinate,Parent.BlankYcoordinate,Index,Parent.OwnIndex,'L');	        
        ExchangeLeft(Array[Index]);//change the x and y coordinate for blank space
        Array[Index].BlankYcoordinate--;//change the y coordinate for blank space
      
        
        boolean cond=CompareNodes(Array[Index],Array[Parent.ParentIndex]);//comparing with its grand parent
        
        if(cond==false)//remove child
        {	
        	Array[Index]=null;//--Index;
        
        }
        else
        {  
        	PrintNode(Array[Index]);
        	Parent.Child4Index=Index;
        	++Index;
	
        }
	}
	
	return Index;//return the next index inside object array
	}	
	
	static void ApplyDFS(Node[] Array,Node Goal,int j,int cc)//Apply dfs recursively 
	{
		int c=cc;
		while(Array[c].ParentIndex!=0)
        {
      	  
      	  PrintNode(Array[Array[cc].ParentIndex]);//print the 3*3 matrix
      	  c=Array[c].ParentIndex;
        }	  
		
		
		
		
		System.out.println("Node Visited:");
		  c=cc;
		  while(Array[c].ParentIndex!=0)
          {
        	  
        	  PrintNode(Array[Array[c].ParentIndex]);//print the 3*3 matrix
        	  c=Array[c].ParentIndex;
          }	 
    	if(!(CompareNodes(Array[j],Goal)))
    	{
	  	  while(!(CompareNodes(Array[j],Goal)))
	  	  {
	  		   if(Array[j].Child1Index>0)
	  		   {
	  			   PrintNode(Array[j]);
	  			   ApplyDFS(Array,Goal,j,cc);
	  		   }
	  		   else if(Array[j].Child2Index>0)
	  		   {
	  			 PrintNode(Array[j]);  
	  			 ApplyDFS(Array,Goal,j,cc);
	  		   }
	  		   else if(Array[j].Child3Index>0)
	  		   {
	  			 PrintNode(Array[j]);
	  			 ApplyDFS(Array,Goal,j,cc);
	  		   }
	  		   else if(Array[j].Child4Index>0)
	  		   {
	  			 PrintNode(Array[j]);
	  			 ApplyDFS(Array,Goal,j,cc);
	  		   }
	  	  }
    	}
    	else System.exit(0);
	}
	
	static void ApplyDFS1(Node1[] Array,Node1 Goal,int j,int cc)//Apply dfs recursively 
	{
		
		  System.out.println("Node Visited:");
		  int c=cc;
		  while(Array[c].ParentIndex!=0)
          {
        	  
        	  PrintNode1(Array[Array[c].ParentIndex]);//print the 4*4 matrix
        	  c=Array[c].ParentIndex;
          }	 
		  if(!(CompareNodes1(Array[j],Goal)))
		  {
	  	  while(!(CompareNodes1(Array[j],Goal)))
	  	  {
	  		   if(Array[j].Child1Index>0)
	  		   {
	  			   PrintNode1(Array[j]);
	  			   ApplyDFS1(Array,Goal,j,cc);
	  		   }
	  		   else if(Array[j].Child2Index>0)
	  		   {
	  			 PrintNode1(Array[j]);  
	  			 ApplyDFS1(Array,Goal,j,cc);
	  		   }
	  		   else if(Array[j].Child3Index>0)
	  		   {
	  			 PrintNode1(Array[j]);
	  			 ApplyDFS1(Array,Goal,j,cc);
	  		   }
	  		   else if(Array[j].Child4Index>0)
	  		   {
	  			 PrintNode1(Array[j]);
	  			 ApplyDFS1(Array,Goal,j,cc);
	  		   }
	  	  }
		  }
		  else System.exit(0);
	}
	
	
	
	//same functions for 4*4 nodes
	
	
	
	

	
	
	
	
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
	
	static void CopyObject1(Node1 Obj1, Node1 Obj2)
	{
		Obj1.OwnIndex=Obj2.OwnIndex;
		Obj1.ParentIndex=Obj2.ParentIndex;
		for(int i=0;i<6;i++)
		 {
			 for(int j=0;j<6;j++)
		 
			 {
				 Obj1.State[i][j]=Obj2.State[i][j];
			 }
		 }
	Obj1.BlankXcoordinate=Obj2.BlankXcoordinate;
	Obj1.BlankYcoordinate=Obj2.BlankYcoordinate;
	Obj1.Direction=Obj2.Direction;
	Obj1.Child1Index=Obj2.Child1Index;
	Obj1.Child2Index=Obj2.Child2Index;
	Obj1.Child3Index=Obj2.Child3Index;
	Obj1.Child4Index=Obj2.Child4Index;
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
	        
	        
            
	        boolean cond=CompareNodes1(Array[Index],Array[Parent.ParentIndex]);
	        
           if(cond==false)
	        {	
	        	Array[Index]=null;//--Index;
	        
	        }
	        else
	        {  
	        	PrintNode1(Array[Index]);
	        	Parent.Child1Index=Index;
	        	++Index;
	        	
		
	        }
		}
		
	if(CheckDownDirectionDirection1(Parent))
	{
		Array[Index]=new Node1(Parent.State,Parent.BlankXcoordinate,Parent.BlankYcoordinate,Index,Parent.OwnIndex,'D');	        
        ExchangeDown1(Array[Index]);
        Array[Index].BlankXcoordinate++;
        
        
        
        boolean cond=CompareNodes1(Array[Index],Array[Parent.ParentIndex]);
        
        if(cond==false)
        {	
        	Array[Index]=null;//--Index;
        
        }
        else
        {  
        	PrintNode1(Array[Index]);
        	Parent.Child2Index=Index;
        	++Index;
	
        }
      }
 	
	if(CheckRightDirection1(Parent))
	{
		Array[Index]=new Node1(Parent.State,Parent.BlankXcoordinate,Parent.BlankYcoordinate,Index,Parent.OwnIndex,'R');	        
        ExchangeRight1(Array[Index]);
        Array[Index].BlankYcoordinate++;
     
        
        boolean cond=CompareNodes1(Array[Index],Array[Parent.ParentIndex]);
        
        if(cond==false)
        {	
        	Array[Index]=null;//--Index;
        
        }
        else
        {  
        	PrintNode1(Array[Index]);
        	Parent.Child3Index=Index;
        	++Index;
	
        }
       }

	if(CheckLeftDirection1(Parent))
	{
		Array[Index]=new Node1(Parent.State,Parent.BlankXcoordinate,Parent.BlankYcoordinate,Index,Parent.OwnIndex,'L');	        
        ExchangeLeft1(Array[Index]);
        Array[Index].BlankYcoordinate--;
      
        
        boolean cond=CompareNodes1(Array[Index],Array[Parent.ParentIndex]);
        
        if(cond==false)
        {	
        	Array[Index]=null;//--Index;
        
        }
        else
        {  
        	PrintNode1(Array[Index]);
        	Parent.Child4Index=Index;
        	++Index;
	
        }
	}
	
	return Index;
	}	
	
	
	
	
	
public static void main(String[] args)
	{
     System.out.println("TileSliding Puzzle for 8 and 16 tiles using BFS:---\n\n");
      
     System.out.println("Enter your option"+"\n 1.3*3 \n 2.4*4\n");
     Scanner scanner = new Scanner(System.in);
     int option=scanner.nextInt();
     if(option==1)//if 3*3 is selected
     {
     int blankXstate=0;
      int blankYstate=0;
      Node[] ObjectArray=new Node[300000];
      Node GoalNode=new Node();
      //set the goal state
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
      //set the initial root node state
      InitialList[0][0]=InitialList[0][1]=InitialList[0][2]=InitialList[0][3]=InitialList[0][4]=InitialList[1][0]=InitialList[1][4]=InitialList[2][0]=InitialList[2][4]=InitialList[3][0]=InitialList[3][4]=InitialList[4][0]=InitialList[4][1]=InitialList[4][2]=InitialList[4][3]=InitialList[4][4]=-1;
      //input the initial state
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
      System.out.println("The root node is:");
      PrintNode(ObjectArray[0]);
      
      System.out.println("The child nodes created are:"+"\n\n");
      // create the child nodes 
      int IndexOfNextNode=1;
      int i=0;
      for(i=0;CompareNodes(ObjectArray[i],GoalNode); i++)       
      {
        
    	  IndexOfNextNode=CreateChildNodes(ObjectArray,ObjectArray[i],GoalNode,IndexOfNextNode);
    	  
      }
      
      count=i;
      //print the solution path using a queue
      System.out.println("\n------The solution path is:---------");
      
      ApplyDFS(ObjectArray,GoalNode,0,count);
     
      
      
      
     }
     //if 4*4 is selected
      else if(option==2)//if 4*4 is selected
      {
    	     int blankXstate=0;
    	      int blankYstate=0;
    	      Node1[] ObjectArray=new Node1[300000];
    	      Node1 GoalNode=new Node1();
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
    	      System.out.println("\n------The solution path is:---------");
    	      ApplyDFS1(ObjectArray,GoalNode,0,count);      
    	      }
   
     
      else System.out.println("the selected option is invalid");
    	  
      
        scanner.close();
  }

}
      


