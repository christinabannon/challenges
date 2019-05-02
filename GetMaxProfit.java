
import java.io.BufferedReader; 
import java.io.InputStreamReader;
import java.util.Random;
import java.io.IOException;

/**
 * 
 * I found a less involved version of this as an online 
 * programming challenge (s/o interviewcake.com)
 * 
 * https://www.interviewcake.com/question/python/stock-price
 * 
 * I thought it was cool, so I built a 
 * little program to essentially 
 * check all of the values in an array and return 
 * the greatest difference between 2 values. 
 * 
 * That's a little vague, 
 * This program simulates day trading. 
 * Translates minutes into 
 * array indices, finds the best gap in
 * retrospect of when you should have bought and 
 * then sold your stocks. 
 * 
 * Check the link. 
 * 
 * @author christinabannon
 *
 */
public class GetMaxProfit {
	
	static BufferedReader reader =
			new BufferedReader( 
					new InputStreamReader(System.in));
	
	public static void main(String [] args)
			throws IOException
	{			
		double [] stockPrices = 
				new double[getSizeOfStockPriceArray()];
		
		int menuSelection = makeMenuSelection();
		while (menuSelection != 0)
		{
			switch (menuSelection)
			{
				case 1: 
					stockPrices = 
					new double[ getSizeOfStockPriceArray()];
					break;
					
				case 2:
					fillStockPrices(stockPrices);
					break;
				
				case 3: 
					getMaxProfit(stockPrices);
					break;
				
			}
			menuSelection = makeMenuSelection();
		}
		
		System.out.println("Goodbye!!");
		System.exit(0);
	}
	
	/**
	 * CB avoiding repeated code - takes input for
	 * the switch statement
	 * 
	 * @return integer to represent the menu selection
	 */
	public static int makeMenuSelection() 
			throws IOException
	{
		System.out.println("\n  Please select from "
				+ "the following menu: ");
		System.out.println("0. Exit\n"
				         + "1. Enter a new Time interval\n"
				         + "2. Fill stock prices!\n"
				         + "3. Get your profit!");
		
		int menuSelection;
		
		try 
		{
			menuSelection = 
				Integer.parseInt(reader
					         	.readLine()
					        	.trim());
			System.out.println(menuSelection);
		} 
		catch (NumberFormatException e)
		{ 
			System.out.println("Enter an INT!!!"
					+ " COME ON MAN " );
			menuSelection = 
					Integer.parseInt(reader
						         	.readLine()
						        	.trim());
			System.out.println(menuSelection);
			
		}
		return menuSelection;
	}
	
	/**
	 * This method gets an int to be the 
	 * size of the getSizeOfStockPriceArray,
	 * by asking the user which times to check. 
	 * 
	 * Times put in must be within 12 hours of each other
	 * or the this method will pretend that they are. 
	 * 
	 * Also, if you are watching stocks for over
	 * 12 hours at a time, may God have
	 * mercy upon your soul. 
	 * 
	 * @return
	 * @throws IOException
	 */
	private static int getSizeOfStockPriceArray() throws IOException
	{
		System.out.println("Enter the time that " + 
				           "you will begin stock watching: ");
		
		String openingTime = reader.readLine().trim();
		
		int openingToMin = clockTimeToMinutes(openingTime);
		
		System.out.println("Enter the time that " + 
						   "you will finish stock watching");
		
		String finishTime = reader.readLine().trim();
		
		int finishToMin = clockTimeToMinutes(finishTime);
		
		if (finishToMin <= openingToMin)
		{
			finishToMin += (60 * 12); //correct the PM likelihood
		}
		
		System.out.println(finishToMin - openingToMin);
		
		return (finishToMin - openingToMin);
	}
	
	/**
	 * translates regular time format
	 * to integers of minutes. 
	 * 
	 * ex) 
	 *  12:00 to 720
	 * 
	 * @param clockTime
	 * @return
	 */
	private static int clockTimeToMinutes(String clockTime)
	{
		int pivot = clockTime.lastIndexOf(":");
		int numHours = Integer.parseInt(clockTime.substring(0, pivot));
		
		int numMinutes = Integer.parseInt(clockTime.substring(pivot + 1, pivot + 3));

		return (numHours * 60) + numMinutes;
	}
	
	/**
	 * fillStockPrices fills the prices with 
	 * rando numbers, and prints the numbers out 
	 * as it iterates
	 * 
	 * @param stockPrices
	 */
	public static void fillStockPrices(double [] stockPrices)
	{
		Random rando = new Random();
		
		for (int i = 0; i < stockPrices.length; i++)
		{
			              //.nextGaussian() esketitttt
			stockPrices[i] = (rando.nextGaussian() + 5);
			
			System.out.printf("minute %2d : ", (i + 1));
			System.out.printf("$%,.2f \n" ,stockPrices[i]);
		}
	}
	
	public static void getMaxProfit(double [] stockPrices)
	{
		double maxProfit = 0;
		int buyTime = 0;
		int sellTime = 0;
		
		for (int i = 0; i < stockPrices.length; i++)
		{
			for (int k = i; k < stockPrices.length; k++)
			{
				if( ((stockPrices[k] - stockPrices[i]) > maxProfit ))
				{
					maxProfit = (stockPrices[k] - stockPrices[i]);
					
					buyTime = i;
					sellTime = k;
				}
			}
		}
		
		if (maxProfit > 0)
		{
			System.out.println("You should have bought your stock "
	 	           	        + "@ minute : " + (buyTime + 1)
	 	           	        + "\n and sold them "
	 	           			+ "@ minute " + (sellTime + 1));
			System.out.printf(" to get your max profit of $%,.2f",
					        maxProfit);
		}
		else
		{
			System.out.println("Nothing good, bub!");
		}
	}
}
