package main.ashu.graphs;

import java.util.Arrays;

//Given an asymmentric directed graph, find the min cost hamiltonian cycle/cicruit
//appln : post office card distribution using flights by a postman
//NP hard problem
//Note that it is a permutation problem(and not a subset problem like in other DP)
public class TravellingSalesmanProblem {
	int cost[][] = { { 0, 10, 15, 20 }, { 10, 0, 35, 25 }, { 15, 35, 0, 30 },
			{ 20, 25, 30, 0 } };

	public int visit1(int current, boolean v[]) {
		v[current] = true;
		// if all cities visited, return to the starting city
		if (allVisited1(v)) {
			v[current] = false; // un-visit to backtrack
			return cost[current][0];
		}
		// else explore all paths emanating form this city and return the min
		// cost path
		int min = Integer.MAX_VALUE;
		for (int i = 0; i < v.length; i++) {
			if (!v[i]) {
				min = Math.min(min, cost[current][i] + visit1(i, v));
			}
		}
		v[current] = false;
		return min;
	}

	public boolean allVisited1(boolean v[]) {
		for (int i = 0; i < v.length; i++) {
			if (!v[i])
				return false;
		}
		return true;
	}

	// Optimization of visit1 : using bit mask to represent cities instead of
	// boolean array visited[]
	// https://www.youtube.com/watch?v=JE0JE8ce1V0
	// In each implementation, we will need to record a subset of a set of size
	// n. We can use an integer between 0 to 2n ? 1 where the kth bit represents
	// the presence of the kth element.
	// For example, given a set of size n = 5, 11111 represents the entire set,
	// 00000 represents the empty set, and 00101 represents the set with the
	// zeroth and second elements.
	public int visit2(int current, int bitmask) {
		if (allVisited2(bitmask))
			return cost[current][0];
		int min = Integer.MAX_VALUE;
		for (int city = 0; city < cost.length; city++) {
			if ((bitmask & (1 << city)) == 0) {
				min = Math.min(
						min,
						cost[current][city]
								+ visit2(city, bitmask | (1 << city)));
			}
		}
		return min;
	}

	public boolean allVisited2(int mask) {
		int n = cost.length;
		return mask == ((1 << n) - 1);
	}

	// DP solution : top-down with memoization
	public int visit3() {
		int n = cost.length;
		// there are 2 vars in dp state - no of cities(n), distinct values of
		// mask(2^N)
		int dp[][] = new int[n][(1 << n)];
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < (1 << n); j++) {
				dp[i][j] = -1;
			}
		}
		return visit3Util(0, 0, dp);
	}

	public int visit3Util(int current, int mask, int[][] dp) {
		if (allVisited2(mask)) {
			return (dp[current][mask] = cost[current][0]);
		}
		if (dp[current][mask] > 0)
			return dp[current][mask];
		int min = Integer.MAX_VALUE;
		for (int city = 0; city < cost.length; city++) {
			if ((mask & (1 << city)) == 0) {
				min = Math.min(min,
						cost[current][city] + visit2(city, mask | (1 << city)));
			}
		}
		return (dp[current][mask] = min);
	}

	public int visit4() {
		int n = cost.length;
		int dp[][] = new int[(1 << n)][n];
		// for bottom-up we have to move with incremental number of intermediate
		// cities
		// C(S,j)=minC(S−{j},i)+d(i,j)wherei∈S andi≠j
		int start = 0; // let's tart at vertex 0
		// base case...S= phi...no intermediate vertices to cover from vertex i
		// to reach 0
		for (int end = 0; end < n; end++) {
			if (start != end) {
				dp[(1 << start) | (1 << end)][end] = cost[start][end]; // path
																		// contains
																		// two
																		// nodes
																		// only
			}
		}
		for (int mask = 1; mask < (1 << n); mask++) {
			for (int last = 0; last < n; last++) {
				if ((mask & (1 << last)) < 1)
					continue; // if not in subset defined by mask
				dp[mask][last] = Integer.MAX_VALUE;
				for (int prev = 0; prev < n; prev++) {
					if ((mask & (1 << prev)) < 1)
						continue; // if not in subset defined by mask
					int prevMask = mask ^ (1 << last); // flip bit last from
														// mask
					dp[mask][last] = Math.min(dp[mask][last], cost[prev][last]
							+ dp[prevMask][prev]);
				}
			}
		}
		int ans = Integer.MAX_VALUE;
		// connect tour back to start node and minimize the cost
		for (int i = 0; i < n; i++) {
			if (i == start)
				continue;
			ans = Math.min(ans, dp[(1 << n) - 1][i] + cost[i][start]);
		}
		return ans;

	}

	public static void main(String args[]) {
		TravellingSalesmanProblem tsp = new TravellingSalesmanProblem();
		int N = tsp.cost.length; // vertices
		boolean visited[] = new boolean[N];
		Arrays.fill(visited, false);
		int cost = tsp.visit1(0, visited);
		System.out.println("Minimum cost through visit1 is : " + cost);
		cost = tsp.visit2(0, 0);
		System.out.println("Minimum cost through visit2 is : " + cost);
		cost = tsp.visit3();
		System.out.println("Minimum cost through visit3 is : " + cost);
		cost = tsp.visit4();
		System.out.println("Minimum cost through visit4 is : " + cost);
	}
}
