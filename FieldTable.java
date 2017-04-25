class FieldTable
{
	public PointAttribute[][] arr = new PointAttribute[19][19];
	{
		for(int i = 0; i<19; i++)
		{
			for(int j = 0; j<19; j++)
			{
				arr[i][j] = new PointAttribute(i, j);
			}
		}
	}

	public void printValue()
	{
		System.out.println("==Table==");
		for(int i = 0; i<19; i++)
		{
			for(int j = 0; j<19; j++)
			{
				System.out.print(arr[i][j].force+ " ");
			}
				System.out.println();
		}
	}

	public void revive()
	{
		for(int i = 0; i<19; i++)
		{
			for(int j = 0; j<19; j++)
			{
				arr[i][j].ifAlive = true;
			}
		}
	}
	FieldTable()
	{

	}
	FieldTable(FieldTable table)
	{
		for(int i = 0; i<19; i++)
		{
			for(int j = 0; j<19; j++)
			{
				arr[i][j] = table.arr[i][j].copy();
			}
		}
	}
}

class PointAttribute
{
	public int force;
	public boolean ifAlive;
	public boolean traced;
	public int x;
	public int y;

	PointAttribute(int i, int j)
	{
		force = 0;
		ifAlive = true;
		traced = false;
		y = i;
		x = j;
	}

	PointAttribute()
	{

	}

	public PointAttribute copy()
	{
		PointAttribute replica = new PointAttribute();
		replica.force = this.force;
		replica.ifAlive = false;
		replica.traced = this.traced;
		replica.x = this.x;
		replica.y = this.y;

		return replica;
	}
}

class XyPoint
{
	public int x;
	public int y;

	XyPoint()
	{
		x = 0;
		y = 0;
	}
}