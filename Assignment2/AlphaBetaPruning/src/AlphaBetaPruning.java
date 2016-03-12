import java.util.*;

public class AlphaBetaPruning
{
public static int Count=0;
public static int ContinueGame=0;
	public static void DisplayBoard(TicTacToeBoard b)
	{
		System.out.println("\nBoard:");
		System.out.println("-------");
	
		for(int i=0;i<3;i++)
		{
			
		 for(int j=0;j<3;j++)
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
		for(int i=0;i<3;i++)
			for(int j=0;j<3;j++)
				if ((b.Contents[0][0] == b.Contents[1][1] && b.Contents[0][0] == b.Contents[2][2] && b.Contents[0][0] == 1)|| (b.Contents[0][2] == b.Contents[1][1] && b.Contents[0][2] == b.Contents[2][0] && b.Contents[0][2] == 1)) 
				{
			        
			        return true;
      			}
			    for (int i = 0; i < 3; i++) 
			    {
			        if (((b.Contents[i][0] == b.Contents[i][1] && b.Contents[i][0] == b.Contents[i][2] && b.Contents[i][0] == 1)|| (b.Contents[0][i] == b.Contents[1][i] && b.Contents[0][i] == b.Contents[2][i] && b.Contents[0][i] == 1)))
			        {
			            return true;
			        }
			    }
		return false;
	}
	
	public static boolean HasHumanWon(TicTacToeBoard b)
	{
		for(int i=0;i<3;i++)
			for(int j=0;j<3;j++)
				if ((b.Contents[0][0] == b.Contents[1][1] && b.Contents[0][0] == b.Contents[2][2] && b.Contents[0][0] == 2)|| (b.Contents[0][2] == b.Contents[1][1] && b.Contents[0][2] == b.Contents[2][0] && b.Contents[0][2] == 2)) 
				{
			        
			        return true;
			    }
			    for (int i = 0; i < 3; i++) 
			    {
			        if (((b.Contents[i][0] == b.Contents[i][1] && b.Contents[i][0] == b.Contents[i][2] && b.Contents[i][0] == 2)|| (b.Contents[0][i] == b.Contents[1][i] && b.Contents[0][i] == b.Contents[2][i] && b.Contents[0][i] == 2)))
			        {
			           
			            return true;
			        }
			    }
		return false;
	}
	
	public static boolean HasDraw(TicTacToeBoard b)
	{
		for(int i=0;i<3;i++)
			for(int j=0;j<3;j++)
			{
			  if(b.Contents[i][j]==0)
				  return false;
			}
		return true;
	}

	public static void TakeUserInput(TicTacToeBoard b)
	{
		System.out.println("Enter the coordinates where you want to place X:\n");
		Scanner s=new Scanner(System.in);
		int x=s.nextInt(); int y=s.nextInt();
		b.Contents[x][y]=2;
	}
	
	public static int UtilityFunction(TicTacToeBoard b)
	{
        int UtilityValue = 0;

        for (int i = 0; i < 3; i++) 
        {
            int dash = 0,X=0,O=0;
  
            for (int j = 0; j < 3; j++) 
            {
                if (b.Contents[i][j] == '-')
                {
                    dash++;
                }
                else if (b.Contents[i][j] == 1)
                {
                    X++;
                }
                else
                {
                    O++;
                }

            } 
            UtilityValue=UtilityValue+PriorityExchange(X, O); 
        }

       
        for (int j = 0; j < 3; j++) 
        {
            int DashCount = 0;
            int XCount = 0;
            int OCount = 0;
            for (int i = 0; i < 3; i++)
            {
                if (b.Contents[i][j] == '-') 
                {
                    DashCount++;
                }
                else if (b.Contents[i][j] == 1) 
                {
                    XCount++;
                }
                else
                {
                    OCount++;
                } 
            }
            UtilityValue+=PriorityExchange(XCount, OCount);
        }

        int DashCount=0;
        int XCount = 0;
        int OCount = 0;

      for(int i=0;i<3;i++)
    	  for(int j=0;j<3;j++)
        {
            if (b.Contents[i][j] == 1)
            {
                XCount++;
            } 
            else if (b.Contents[i][j] == 2) 
            {
                OCount++;
            } 
            else
            {
                DashCount++;
            }
        }

        UtilityValue=UtilityValue+PriorityExchange(XCount, OCount);

        DashCount=XCount=OCount=0;
     

    
        for (int i = 2, j = 0; i > -1; --i, j++) 
        {
            if (b.Contents[i][j] == 1)
            {
                XCount++;
            }
            else if (b.Contents[i][j] == 2)
            {
                OCount++;
            }
            else
            {
                DashCount++;
            }
        }

        UtilityValue+=PriorityExchange(XCount, OCount);

        return UtilityValue;
    }

	 public static int PriorityExchange(int X, int O)
	 {
	        int c;
	        if (X == 3)
	        {
	            c = 100;
	        }
	        else if (X == 2 && O == 0)
	        {
	            c = 10;
	        }
	        else if (X == 1 && O == 0)
	        {
	            c = 1;
	        }
	        else if (O == 3) 
	        {
	            c = -100;
	        }
	        else if (O == 2 && X == 0)
	        {
	            c = -10;
	        } 
	        else if (O == 1 && X == 0)
	        {
	            c = -1;
	        } 
	        else 
	        {
	            c = 0;
	        } 
	        return c;
	    }
	 
 public static List<Coordinates> FindEmptyCoordinates(TicTacToeBoard b)
		{
			List<Coordinates> SpaceArray=new ArrayList<>();
			for(int i=0;i<3;i++)
			{
				for(int j=0;j<3;j++)
				{
					if(b.Contents[i][j]==0)
						SpaceArray.add(new Coordinates(i,j));
				}
			}
			
			return SpaceArray;
		}
		
	 
	public static int ApplyAlphaBetaMinimax(TicTacToeBoard b,int AplhaValue, int BetaValue, int depth, int turn)
	{
		if(BetaValue<=AplhaValue)
		{ 
			
        if(turn == 1)
        	return Integer.MAX_VALUE; 
        else 
        	return Integer.MIN_VALUE;
        }
        
		int flag=0;
        if((depth == -1) || (flag=HasAnybodyWon(b))==0) 
        	return UtilityFunction(b);	
        
List<Coordinates> AvailableSpaces = new ArrayList<>();
AvailableSpaces=FindEmptyCoordinates(b);
        
        if(AvailableSpaces.isEmpty()) 
        	return 0;
        
        if(depth==0)
        	b.CoordinateClass.clear(); 
        
        int HighestValue = Integer.MIN_VALUE, LowestValue = Integer.MAX_VALUE;
        
        for(int i=0;i<AvailableSpaces.size(); i++)
        {
            Coordinates Coordinate = AvailableSpaces.get(i);
            
            int Points = 0;
            
            if(turn == 1)
            {
               
                b.Contents[Coordinate.x][Coordinate.y]=1;
                Points = ApplyAlphaBetaMinimax(b,AplhaValue, BetaValue, depth+1, 2);
                HighestValue = Math.max(HighestValue, Points); 
               if(HighestValue<Points)
               {
            	   HighestValue=Points;
               }
              
             if(AplhaValue<Points)
             {
            	 AplhaValue=Points;
             }
                
                if(depth == 0)
                    b.CoordinateClass.add(new CoordinateClass(Coordinate,Points));
                ++Count;
                DisplayBoard(b);
            }
            else if(turn == 2)
            {
            	b.Contents[Coordinate.x][Coordinate.y]=2;
                Points = ApplyAlphaBetaMinimax(b,AplhaValue, BetaValue, depth+1, 1); 
               if(LowestValue>Points)
               {
            	   LowestValue=Points;
               }
                
               if(BetaValue>Points)
               {
            	   BetaValue=Points;
               }
              
              
            }
           
            b.Contents[Coordinate.x][Coordinate.y] = 0; 
            ++Count;
            DisplayBoard(b);
            if(Points == Integer.MAX_VALUE || Points == Integer.MIN_VALUE)
            	break;
        }
        if(turn==1)
        {
        	return HighestValue;
        }
        return LowestValue;
	}
		
			
public static void main(String args[])
	{
	do{
		
	TicTacToeBoard b= new TicTacToeBoard();
	   
	System.out.println("-----Welcome to Tic Tac Toe game using minimax Aplha Beta pruning:------\n\n\nYou : X\n\nComputer : O \n\n\nSelect the options:\n1.You will start.\n2.I will start\n");
		
		Scanner S=new Scanner(System.in);
		int choice=S.nextInt();
		
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
    		    TakeUserInput(b); 
	            DisplayBoard(b);
	            long startTime = System.currentTimeMillis();
    			ApplyAlphaBetaMinimax(b,Integer.MIN_VALUE, Integer.MAX_VALUE, 0, 1);
    			long finishTime = System.currentTimeMillis();
   			 long elapsedTime = finishTime - startTime;
   			 System.out.println("The time taken for this step is:"+elapsedTime);
    			int MAX = -100000;
    	        int efficient = -1;
                int n=b.CoordinateClass.size();
                System.out.println("\nThe number of child nodes for this particular user input is"+Count);
    	        for(int i=0;i<n;i++)
    	        {
    	            if (MAX < b.CoordinateClass.get(i).Score)
    	            {
    	                MAX = b.CoordinateClass.get(i).Score;
    	                efficient = i;
    	            }
    	        }   
    	            Coordinates NewC=new Coordinates(b.CoordinateClass.get(efficient).C);
    			    b.Contents[NewC.x][NewC.y]=1;
    			    System.out.println("\nThe current board after placing computers' move at:("+NewC.x+" "+NewC.y+")");
    				DisplayBoard(b);
    		
	     
    	        
    	      
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
    		            System.out.println("Match is a draw! Nobody wins... \nExiting");
    		        }
    	        
               }
		 
          }
		if(choice==2)
		{
			while((flag=HasAnybodyWon(b))==-1)
			{
			TakeUserInput(b); 
            DisplayBoard(b);
            long startTime = System.currentTimeMillis();
			ApplyAlphaBetaMinimax(b,Integer.MIN_VALUE, Integer.MAX_VALUE, 0, 1);
			long finishTime = System.currentTimeMillis();
			 long elapsedTime = finishTime - startTime;
			 System.out.println("The time taken for this step is:"+elapsedTime);
			int MAX = -100000;
	        int efficient = -1;
	        System.out.println("\nThe number of child nodes for this particular user input is"+Count);
	        for (int i = 0; i < b.CoordinateClass.size(); i++)
	        {
	            if (MAX < b.CoordinateClass.get(i).Score)
	            {
	                MAX = b.CoordinateClass.get(i).Score;
	                efficient = i;
	            }
	        }
	            Coordinates NewC=new Coordinates(b.CoordinateClass.get(efficient).C);
			    b.Contents[NewC.x][NewC.y]=1;
			    System.out.println("\nThe current board after placing computers' move at:("+NewC.x+" "+NewC.y+")");
				DisplayBoard(b);
		
     
	        
	      
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
		            System.out.println("Match is a draw! Nobody wins... \nExiting");
		      }
	        
           }
    
		}
		
		Scanner s=new Scanner(System.in);
		System.out.println("Wanna play another game???\n\n 1. Yes \n\n 2. No\n");
	    ContinueGame=s.nextInt();
	}while(ContinueGame==1);
	System.out.println("Exiting....\n Bye.");
	}

}		


