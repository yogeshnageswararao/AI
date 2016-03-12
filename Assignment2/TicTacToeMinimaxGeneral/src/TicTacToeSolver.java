import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;


public class TicTacToeSolver {
	
	public static int count;
	public static void TakeUserInput(TicTacToeBoard b)
	{
		System.out.println("Enter the coordinates where you want to place X:\n");
		Scanner s=new Scanner(System.in);
		int x=s.nextInt(); int y=s.nextInt();
		b.Contents[x][y]=2;
	}
	

	public static void DisplayBoard(TicTacToeBoard b)
	{
		System.out.println("\nBoard:");
		System.out.println("-------");
	
		for(int i=0;i<4;i++)
		{
		 for(int j=0;j<4;j++)
		 {
			 if(b.Contents[i][j]==1)
			 System.out.print("|O");
			 else if(b.Contents[i][j]==2)
				 System.out.print("|X");
			 else 
				 System.out.print("|-");
				 
		 }
		 System.out.print("|");
		 System.out.println();
		}
		
		System.out.println("-------");
	}

	public static int HasAnybodyWon(TicTacToeBoard b)
	{
		if(HasComputerWon(b))
			return 0;
		if(HasHumanWon(b))
			return 0;
		if(HasDraw(b))
			return 0;
		return -1;
	}
	
	public static boolean HasComputerWon(TicTacToeBoard b)
	{
		for(int i=0;i<4;i++)
			for(int j=0;j<4;j++)
				if ((b.Contents[0][0] == b.Contents[1][1] && b.Contents[0][0] == b.Contents[2][2] && b.Contents[0][0] == b.Contents[3][3]&&b.Contents[0][0]==1) ||(b.Contents[0][3] == b.Contents[1][2] && b.Contents[0][3] == b.Contents[2][1] && b.Contents[0][3] == b.Contents[3][0] && b.Contents[0][3]== 1)) 
				{
			        return true;
			    }
	   for (int i = 0; i < 4; ++i) 
			    {
			        if (((b.Contents[i][0] == b.Contents[i][1] && b.Contents[i][0] == b.Contents[i][2] && b.Contents[i][0] == b.Contents[i][3] && b.Contents[i][0]==1)|| (b.Contents[0][i] == b.Contents[1][i] && b.Contents[0][i] == b.Contents[2][i] && b.Contents[0][i] == b.Contents[3][i] && b.Contents[0][i]==1)))
			        {
			            return true;
			        }
			    }
		return false;
	}
	
	public static boolean HasHumanWon(TicTacToeBoard b)
	{
		for(int i=0;i<4;i++)
			for(int j=0;j<4;j++)
				if ((b.Contents[0][0] == b.Contents[1][1] && b.Contents[0][0] == b.Contents[2][2] && b.Contents[0][0] == b.Contents[3][3]&&b.Contents[0][0]==2) ||(b.Contents[0][3] == b.Contents[1][2] && b.Contents[0][3] == b.Contents[2][1] && b.Contents[0][3] == b.Contents[3][0] && b.Contents[0][3]== 2)) 
				{
			        return true;
			    }
		
	   for (int i = 0; i < 4; ++i) 
			    {
			        if (((b.Contents[i][0] == b.Contents[i][1] && b.Contents[i][0] == b.Contents[i][2] && b.Contents[i][0] == b.Contents[i][3] && b.Contents[i][0]==2)|| (b.Contents[0][i] == b.Contents[1][i] && b.Contents[0][i] == b.Contents[2][i] && b.Contents[0][i] == b.Contents[3][i] && b.Contents[0][i]==2)))
			        {
			            return true;
			        }
			    }
		return false;
	}
	
	public static boolean HasDraw(TicTacToeBoard b)
	{
		for(int i=0;i<4;i++)
			for(int j=0;j<4;j++)
			{
			  if(b.Contents[i][j]==0)
				  return false;
			}
		return true;
	}
	
	public static List<Coordinates> FindEmptyCoordinates(TicTacToeBoard b)
	{
		List<Coordinates> SpaceArray=new ArrayList<>();
		for(int i=0;i<4;i++)
		{
			for(int j=0;j<4;j++)
			{
				if(b.Contents[i][j]==0)
					SpaceArray.add(new Coordinates(i,j));
			}
		}
		
		return SpaceArray;
	}
	
	public static void CopyBoard(TicTacToeBoard b1, TicTacToeBoard b2)
	{
		for(int i=0;i<4;i++)
		 for(int j=0;j<4;j++)
			 b2.Contents[i][j]=b1.Contents[i][j];
	}
	
	public static void EditBoard(TicTacToeBoard b,Coordinates C,int Player)
	{
		b.Contents[C.x][C.y]=Player;
	}
	public static int ContinueGame=0;
	public static int ApplyMinMaxAlgorithm(int NextPlayer,TicTacToeBoard b)
	{   
		int Score=0;
		List<Coordinates> EmptyCoordinates=new ArrayList<>();
		List<Integer> PointsAcquired=new ArrayList<>();
		
		EmptyCoordinates=FindEmptyCoordinates(b);
		
		if(HasComputerWon(b))
		{
			return 1;
		}
		
		if(HasHumanWon(b))
		{
			return -1;
		}
        	
		if(HasDraw(b))
		{
			return 0;
	
		}
		

		
		for(int i=0;i<EmptyCoordinates.size();i++)
		{
			Coordinates C=new Coordinates(EmptyCoordinates.get(i));
			
			TicTacToeBoard b1=new TicTacToeBoard();
			CopyBoard(b,b1);
			if(NextPlayer==1)
			{
				EditBoard(b1,C,NextPlayer);
				int lowest=ApplyMinMaxAlgorithm(2,b1);
				PointsAcquired.add(lowest);
				Collections.sort(PointsAcquired, Collections.reverseOrder());
				Score=PointsAcquired.get(0);
				++count;
				DisplayBoard(b1);
				
			}
			
			if(NextPlayer==2)
			{
				EditBoard(b1,C,NextPlayer);
				int highest=ApplyMinMaxAlgorithm(1,b1);
				PointsAcquired.add(highest);
				Collections.sort(PointsAcquired);
				Score=PointsAcquired.get(0);
				++count;
				DisplayBoard(b1);
				
			}
			
	b.CoordinateClass.add(new CoordinateClass(C,Score));		
	
		}
		return Score;		
		
		
	}
		
	
	
	public static void main(String args[])
	{
		
		do
			{
			int efficient=0;
			
			
		int n;TicTacToeBoard b= new TicTacToeBoard();
		System.out.println("-----Welcome to Tic Tac Toe game:------\n\n\nYou : X\n\nComputer : O \n\n\nSelect the options:\n1.You will start.\n2.I will start\n");
		
		Scanner s=new Scanner(System.in);
		int choice=s.nextInt();
		
		if(choice==2)
		{
			b.Contents[1][1]=1;
			DisplayBoard(b);
		}
		else if(choice==1)
		{
			
		}
		else
		{
			System.out.println("Invalid Input\nExiting......");
			System.exit(1);
		}
		
		int flag=0;
		
		if(choice==1)
		{
		  while((flag=HasAnybodyWon(b))==-1)
		    {
			  System.out.println("Enter the coordinates where you want to place X:\n");
				
				int x=s.nextInt(); 
				int y=s.nextInt();
				b.Contents[x][y]=2;
	            DisplayBoard(b);
    			b.CoordinateClass.clear();
    			long startTime = System.currentTimeMillis();
				n=ApplyMinMaxAlgorithm(1,b);
				long finishTime = System.currentTimeMillis();
				 long elapsedTime = finishTime - startTime;
				 System.out.println("The time taken for this step is:"+elapsedTime);
				n=-10;
				System.out.println("\nThe number of child nodes for this particular user input is"+count);
			    count=0;
				
                
			    for (int i=0;i<b.CoordinateClass.size(); i++)
			    {
			        if (n < b.CoordinateClass.get(i).Score) 
			        {
			            n=b.CoordinateClass.get(i).Score;
			            efficient=i;
			        }
			    }

			    if(!HasDraw(b))
			    	{
			    	   Coordinates NewC=new Coordinates(b.CoordinateClass.get(efficient).C);
			    	
			    b.Contents[NewC.x][NewC.y]=1;
			    System.out.println("\nThe current board after placing computers' move at:("+NewC.x+" "+NewC.y+")");
				DisplayBoard(b);
			    	}
			}
		  
		  if (HasComputerWon(b)) 
		  {
	            System.out.println("Yayy..!!! I won.");
	       } 
		  else if (HasHumanWon(b))
		  {
	            System.out.println("Winner:Human");
	        }
		  else if(HasDraw(b))
		  {
	            System.out.println("Match is a draw! Nobody wins... \n");
	        }
		
		}
        if(choice==2)
        {
        	while((flag=HasAnybodyWon(b))==-1)
		    {
        		System.out.println("Enter the coordinates where you want to place X:\n");
        	
        		int x=s.nextInt(); int y=s.nextInt();
        		b.Contents[x][y]=2;
	            DisplayBoard(b);
    			b.CoordinateClass.clear();
    			System.out.println("\nThe created child node is:\n");
    			 long startTime = System.currentTimeMillis();
				n=ApplyMinMaxAlgorithm(1,b);
				 long finishTime = System.currentTimeMillis();
				 long elapsedTime = finishTime - startTime;
				 System.out.println("The time taken for this step is:"+elapsedTime);
				n=-10;
				System.out.println("\nThe number of child nodes for this particular user input is"+count);
			  
                int z=b.CoordinateClass.size();
			    for (int i=0;i<z; i++)
			    {
			        if(n<b.CoordinateClass.get(i).Score) 
			        {
			            n=b.CoordinateClass.get(i).Score;
			            efficient=i;
			        }
			    }

			    if(!HasDraw(b))
			    	{
			    	Coordinates NewC=new Coordinates(b.CoordinateClass.get(efficient).C);
			    	
			        b.Contents[NewC.x][NewC.y]=1;
			     	DisplayBoard(b);
				
			    	}
			}
		  
		  if (HasComputerWon(b)) 
		  {
	            System.out.println("Yayy..!!! I won.");
	       } 
		  else if (HasHumanWon(b))
		  {
	            System.out.println("Winner:Human");
	        }
		  else if(HasDraw(b))
		  {
	            System.out.println("Match is a draw! Nobody wins... ");
	        }
		  
        }
        System.out.println("Wanna play another game???\n\n 1. Yes \n\n 2. No\n");
	    ContinueGame=s.nextInt();
			}while(ContinueGame==1);
		
	System.out.println("Exiting....\n Bye.");
	
}
}

