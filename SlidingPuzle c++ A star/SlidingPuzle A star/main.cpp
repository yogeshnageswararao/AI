
#include<iostream>
#include<algorithm> 
#include<vector>
#include<sstream>
using namespace std;


	class board {
public:
	int n,values[5][5], blankXcoordinate, blankYcoordinate, ParentIndexPointer, OwnIndex, ManhattanDistance;
	public:	
	board(int ParamValues[5][5], int ParamX, int ParamY, int ParamIndex, int ParamParentIndex)
	{
		for (int i = 0; i<5; i++)
			for (int j = 0; j<5; j++)
				values[i][j] = ParamValues[i][j];
		blankXcoordinate = ParamX;
		blankYcoordinate = ParamY;
		OwnIndex = ParamIndex;
		ParentIndexPointer = ParamParentIndex;
	}
	
	public:
		board(const board &b1)
	{
		for (int i = 0; i < 5; i++)
			for (int j = 0; j < 5; j++)
				values[i][j] = b1.values[i][j];
		n = b1.n;
		blankXcoordinate = b1.blankXcoordinate;
		blankYcoordinate = b1.blankYcoordinate;
		OwnIndex = b1.OwnIndex;
		ManhattanDistance = b1.ManhattanDistance;
	
	}
	void printBoard()
	{
		for (int i = 1; i<4; i++)
		{
			for (int j = 1; j<4; j++)
				cout << values[i][j] << "  ";
		}
		cout << endl;
	}
	bool checkUp()
	{
		if (values[blankXcoordinate - 1][blankYcoordinate] == -1)
			return false;
		else
			return true;


	}
	bool checkDown()
	{
		if (values[blankXcoordinate + 1][blankYcoordinate] == -1)
			return false;
		else
			return true;

	}

	bool checkLeft()
	{
		if (values[blankXcoordinate][blankYcoordinate - 1] == -1)
			return false;
		else
			return true;

	}

	bool checkRight()
	{
		if (values[blankXcoordinate][blankYcoordinate + 1] == -1)
			return false;
		else
			return true;

	}
	int RetrieveParentNode()
	{
		return ParentIndexPointer;
	}

	void moveUp()
	{
		blankXcoordinate--;
	}
	void moveDown()
	{
		blankXcoordinate + 1;
	}
	void moveLeft()
	{
		blankYcoordinate--;
	}
	void moveRight()
	{
		blankYcoordinate--;
	}

	int CreateChildNodes(vector<board>& Tree1, int ParentIndex, int Num)
	{
		Tree1.push_back(board(values, blankXcoordinate, blankYcoordinate, Num, ParentIndex));
		return ++Num;

	}

	void UpExchangeCoordinates()
	{
		values[blankXcoordinate][blankYcoordinate] = values[blankXcoordinate - 1][blankYcoordinate];
		values[blankXcoordinate - 1][blankYcoordinate] = 0;
	}


	void DownExchangeCoordinates()
	{
		values[blankXcoordinate][blankYcoordinate] = values[blankXcoordinate + 1][blankYcoordinate];
		values[blankXcoordinate + 1][blankYcoordinate] = 0;
	}


	void LeftExchangeCoordinates()
	{
		values[blankXcoordinate][blankYcoordinate] = values[blankXcoordinate][blankYcoordinate - 1];
		values[blankXcoordinate][blankYcoordinate - 1] = 0;
	}

	void RightExchangeCoordinates()
	{
		values[blankXcoordinate][blankYcoordinate] = values[blankXcoordinate][blankYcoordinate + 1];
		values[blankXcoordinate][blankYcoordinate + 1] = 0;
	}

	bool CompareNodes(board _goal)
	{
		for (int i = 0; i<5; i++)
			for (int j = 0; j<5; j++)
				if (values[i][j] != _goal.values[i][j])
				{

					return false;
				}
		return  true;
	}

	void CalculateManhattanDistance(board _goal)
	{
		int ManhattanCounter = 0;
		for (int i = 1; i<4; i++)
			for (int j = 1; j<4; j++)
				if (values[i][j] != _goal.values[i][j])
					ManhattanCounter++;

		ManhattanDistance = ManhattanCounter;
	}

	int RetrieveManhattanDistance()
	{
		return ManhattanDistance;
	}


	int NextDepth(vector<board> &VectorOfBoards, int NoOfNextNode, int ParentIndexNumber, board _goal)
	{
		if (VectorOfBoards[ParentIndexNumber].checkUp())
		{
			n = CreateChildNodes(VectorOfBoards, ParentIndexNumber, NoOfNextNode);
			int CurrentNode = n - 1;
			VectorOfBoards[CurrentNode].UpExchangeCoordinates();
			VectorOfBoards[CurrentNode].moveUp();
			VectorOfBoards[CurrentNode].CalculateManhattanDistance(_goal);
			VectorOfBoards[CurrentNode].printBoard();
			board TemporaryBoard = VectorOfBoards[CurrentNode];
			board parent = VectorOfBoards[RetrieveParentNode()];
			if (TemporaryBoard.CompareNodes(VectorOfBoards[parent.RetrieveParentNode()]))
			{

				VectorOfBoards.pop_back();
				--n;
			}
			else if (TemporaryBoard.RetrieveManhattanDistance()>parent.RetrieveManhattanDistance())
			{
				VectorOfBoards.pop_back();
				--n;
			}
		}

		if (VectorOfBoards[ParentIndexNumber].checkDown())
		{
			n = CreateChildNodes(VectorOfBoards, ParentIndexNumber, NoOfNextNode);
			int CurrentNode = n - 1;
			VectorOfBoards[CurrentNode].DownExchangeCoordinates();
			VectorOfBoards[CurrentNode].moveDown();
			VectorOfBoards[CurrentNode].CalculateManhattanDistance(_goal);
			VectorOfBoards[CurrentNode].printBoard();
			board TemporaryBoard = VectorOfBoards[CurrentNode];
			board parent = VectorOfBoards[RetrieveParentNode()];
			if (TemporaryBoard.CompareNodes(VectorOfBoards[parent.RetrieveParentNode()]))
			{
				VectorOfBoards.pop_back();
				--n;
			}
			else if (TemporaryBoard.RetrieveManhattanDistance()>parent.RetrieveManhattanDistance())
			{
				VectorOfBoards.pop_back();
				--n;
			}
		}
		if (VectorOfBoards[ParentIndexNumber].checkLeft())
		{
			n = CreateChildNodes(VectorOfBoards, ParentIndexNumber, NoOfNextNode);
			int CurrentNode = n - 1;
			VectorOfBoards[CurrentNode].LeftExchangeCoordinates();
			VectorOfBoards[CurrentNode].moveLeft();
			VectorOfBoards[CurrentNode].CalculateManhattanDistance(_goal);
			VectorOfBoards[CurrentNode].printBoard();
			board TemporaryBoard = VectorOfBoards[CurrentNode];
			board parent = VectorOfBoards[RetrieveParentNode()];
			if (TemporaryBoard.CompareNodes(VectorOfBoards[parent.RetrieveParentNode()]))
			{
				VectorOfBoards.pop_back();
				--n;
			}
			else if (TemporaryBoard.RetrieveManhattanDistance()>parent.RetrieveManhattanDistance())
			{
				VectorOfBoards.pop_back();
				--n;
			}
		}

		if (VectorOfBoards[ParentIndexNumber].checkRight())
		{
			n = CreateChildNodes(VectorOfBoards, ParentIndexNumber, NoOfNextNode);
			int CurrentNode = n - 1;
			VectorOfBoards[CurrentNode].RightExchangeCoordinates();
			VectorOfBoards[CurrentNode].moveRight();
			VectorOfBoards[CurrentNode].CalculateManhattanDistance(_goal);
			VectorOfBoards[CurrentNode].printBoard();
			board TemporaryBoard = VectorOfBoards[CurrentNode];
			board parent = VectorOfBoards[RetrieveParentNode()];
			if (TemporaryBoard.CompareNodes(VectorOfBoards[parent.RetrieveParentNode()]))
			{
				VectorOfBoards.pop_back();
				--n;
			}
			else if (TemporaryBoard.RetrieveManhattanDistance()>parent.RetrieveManhattanDistance())
			{
				VectorOfBoards.pop_back();
				--n;
			}
		}
		return n;
	}



};

int main()
{
    int InitialBoard[5][5], GoalBoard[5][5] = { { -1,-1,-1,-1,-1 },{ -1,1,2,3,-1 },{ -1,4,5,6,-1 },{ -1,7,8,0,-1 },{ -1,-1,-1,-1,-1 } }, NumberOfNodes = 0, counter = 0, BlankX = 0, BlankY = 0, TempStore = 0, count = 0, count1 = 0, ParentIndex = 0;
	string InitialStateString, GoalStateString, Test;
	vector<int> InitialList, GoalList;
	vector<board> StatesTree;
	vector<int> nodes;
	vector<int> SolutionPath;
	cout << "Enter the initial state" << endl;
	InitialBoard[0][0] = -1;
	InitialBoard[0][1] = -1; InitialBoard[0][2] = -1; InitialBoard[0][3] = -1; InitialBoard[0][4] = -1;
	InitialBoard[4][0] = -1; InitialBoard[0][1] = -1; InitialBoard[0][2] = -1; InitialBoard[0][3] = -1;
	InitialBoard[4][4] = -1;
	InitialBoard[1][0] = -1; InitialBoard[1][4] = -1;
	InitialBoard[2][0] = -1; InitialBoard[2][4] = -1;
	InitialBoard[3][0] = -1; InitialBoard[3][4] = -1;




	for (int i = 1; i<4; i++)
	{
		for (int j = 1; j<4; j++)
		{
			cin >> InitialBoard[i][j];
			if (InitialBoard[i][j] == 0)
			{
				BlankX = i;
				BlankY = j;
			}
			count++;
		}
	}

	board FirstStateBoard(InitialBoard, BlankX, BlankY, 0, 0);
	board GoalStateBoard(GoalBoard, 0, 0, 0, 0);
	StatesTree.push_back(FirstStateBoard);
	StatesTree[0].CalculateManhattanDistance(GoalStateBoard);

	int NumberOfNextNode = 1;
	for (int i = 0; !(StatesTree[i].CompareNodes(GoalStateBoard)); i++)
	{
		NumberOfNextNode = StatesTree[i].NextDepth(StatesTree, NumberOfNextNode, i, GoalStateBoard);

		counter = i + 1;
	}


	while (counter != -1)
	{
		SolutionPath.push_back(counter);
		counter = StatesTree[counter].RetrieveParentNode();
		if (counter == 0)
		{
			counter = -1;
		}
	}

	SolutionPath.push_back(0);
	reverse(SolutionPath.begin(), SolutionPath.end());

	for (int i = 0; i<SolutionPath.size(); i++)
	{
		cout << "Step" << i << endl;
		StatesTree[SolutionPath[i]].printBoard();
		cout << endl << endl << endl;
	}
	return 0;

}