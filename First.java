import java.util.Scanner;

class First
{
	public static void main(String args[])
	{
		System.out.println("Hello world");

		FieldTable table = new FieldTable();
		table.printValue();

		int i = 0;
		int j = 0;

		Turn manager = new Turn();

		while(j < 20)
		{
			Scanner input = new Scanner(System.in);
			i = input.nextInt();
			j = input.nextInt();

			manager.input(table, i, j);

			table.printValue();
		}
	}
}