package main.ashu.dp;

//Implementation - https://miafish.wordpress.com/2015/01/03/boolean-parenthesization-problem/
//Theory explained - http://stackoverflow.com/questions/6701812/counting-boolean-parenthesizations-implementation

public class BooleanParenthesization {
	//counts the total number of ways to parenthesize teh expression so that it evaluates to result(true/false)
	//complexity is O(n^3)
	public int getWaysOfParenthesizeExpressionDP(String expression, boolean result)
	{
		// dp[start][end][result - False or True]
		int size = expression.length();
		int[][][] dp = new int[size][][];
		for (int i = 0; i < size; i++)
		{
			dp[i] = new int[size][];
			for (int j = 0; j < size; j++)
			{
				// 0  for false; 1 for true
				dp[i][j] = new int[2];
			}
		}
		//base case : expression has only one literal - T/F
		for (int i = 0; i < size; i = i + 2)
		{
			if (expression.charAt(i)=='T')
			{
				dp[i][i][1] = 1;
			}
			else
			{
				dp[i][i][0] = 1;
			}
		}

		//iterating over problem size
		for (int length = 3; length <= size; length = length + 2)
		{
			for (int start = 0; start + length <= size; start = start + 2)
			{   //choosing cut points 

				for (int k = start + 1; k < start + length; k = k + 2)
				{
					int end = start + length - 1;
					switch (expression.charAt(k))
					{
					case '|':
					{
						dp[start][end][1] += dp[start][k - 1][1] * dp[k + 1][end][1]
								+ dp[start][k - 1][0] * dp[k + 1][end][1]
										+ dp[start][k - 1][1] * dp[k + 1][end][0];
						dp[start][end][0] += dp[start][k - 1][0] * dp[k + 1][end][0];
					}
					break;
					case '&':
					{
						dp[start][end][1] += dp[start][k - 1][1] * dp[k + 1][end][1];
						dp[start][end][0] += dp[start][k - 1][0] * dp[k + 1][end][1]
								+ dp[start][k - 1][1] * dp[k + 1][end][0]
										+ dp[start][k - 1][0] * dp[k + 1][end][0];
					}
					break;
					case '^':
					{
						dp[start][end][1] += dp[start][k - 1][1] * dp[k + 1][end][0]
								+ dp[start][k - 1][0] * dp[k + 1][end][1];
						dp[start][end][0] += dp[start][k - 1][1] * dp[k + 1][end][1]
								+ dp[start][k - 1][0] * dp[k + 1][end][0];
					}
					break;
					}
				}
			}
		}
		return dp[0][size - 1][result ? 1 : 0];
	}


	public static void main(String args []) {
		String expression = "T|T&F^T";
		boolean result = true;
		BooleanParenthesization bp = new BooleanParenthesization();
		int totalWays = bp.getWaysOfParenthesizeExpressionDP(expression, result);
		System.out.println("Number of parenthesis for making "+result+" = "+totalWays);
	}
}

