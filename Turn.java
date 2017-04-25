import java.util.Scanner;

class Turn
{
	int player = 1;
	int i, j;

	public void input(FieldTable table, int i, int j)
	{
		i = i%19;
		j = j%19;

		if(table.arr[i][j].force != 0)
			return;
		else
		{
			table.arr[i][j].force = player;
			checker(table, player);

			player = player%2+1;
			checker(table, player);
		}

	}

	private void checker(FieldTable table, int turnPlayer)
	{
		for(int i = 0; i<19; i++)
		{
			for(int j = 0; j<19; j++)
			{
				if(table.arr[i][j].force == turnPlayer)
				{
					FieldTable table2 = new FieldTable(table);
					table.arr[i][j].ifAlive = checker_slave(table2, turnPlayer,table2.arr[i][j]).ifAlive;
				}
			}
		}

		death(table);
	}

	private void death(FieldTable table)
	{
		for(int i=0; i<19; i++)
		{
			for(int j=0; j<19; j++)
			{
				if(table.arr[i][j].ifAlive == false)
				{
					table.arr[i][j].force = 0;
				}
			}
		}
		table.revive();
	}

	private PointAttribute checker_slave(FieldTable table, int turnPlayer, PointAttribute dot)
	{
		int opponent = turnPlayer%2+1;
		
		PointAttribute left, right, up, down;
		left = table.arr[dot.y][(dot.x+18)%19];
		right = table.arr[dot.y][(dot.x+1)%19];
		up = table.arr[(dot.y+18)%19][dot.x];
		down = table.arr[(dot.y+1)%19][dot.y];

		dot.traced = true;

		if(left.force == 0 || right.force == 0 || up.force == 0 || down.force == 0)
		{
			dot.ifAlive = true;
			return dot;
		}
		else
		{
			checker_slave2(table, turnPlayer, left);
			checker_slave2(table, turnPlayer, right);
			checker_slave2(table, turnPlayer, up);
			checker_slave2(table, turnPlayer, down);

			if(left.ifAlive || right.ifAlive || up.ifAlive || down.ifAlive)
			{
				dot.ifAlive = true;
			}
			else
			{
				dot.ifAlive = false;
			}

			return dot;
		}
	}

	private void checker_slave2(FieldTable table, int turnPlayer, PointAttribute dot)
	{
		if(dot.force == turnPlayer && dot.traced == false)
		{
			checker_slave(table, turnPlayer, dot);
			return;
		}
		else
		{
			return;
		}
	}
}

class DeadOrAlive
{
	
}