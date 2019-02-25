package main.ashu.arrays;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

//https://leetcode.com/problems/the-skyline-problem/
//https://www.programcreek.com/2014/06/leetcode-the-skyline-problem-java/
//greedy+heap
public class SkylineProblem {
	class BuildingPoint {
		int x;
		int height;
		boolean isStart;

		public BuildingPoint(int x, int height, boolean isStart) {
			this.x = x;
			this.height = height;
			this.isStart = isStart;
		}
	}

	public List<int[]> getSkyline(int[][] buildings) {
		List<int[]> result = new ArrayList<int[]>();

		if (buildings == null || buildings.length == 0
				|| buildings[0].length == 0) {
			return result;
		}

		List<BuildingPoint> points = new ArrayList<BuildingPoint>();

		// add all left/right points
		for (int[] building : buildings) {
			BuildingPoint startEdge = new BuildingPoint(building[0], building[2], true);
			points.add(startEdge);
			BuildingPoint endEdge = new BuildingPoint(building[1], building[2], false);
			points.add(endEdge);
		}

		// sort points
		Collections.sort(points, new Comparator<BuildingPoint>() {
			public int compare(BuildingPoint a, BuildingPoint b) {
				//starting points don't overlap, pick the one with smaller starting point first
				if (a.x != b.x)
					return Integer.compare(a.x, b.x);
                //if startings overlap, pick the one with greater height
				if (a.isStart && b.isStart) {
					return Integer.compare(b.height, a.height);
				}
				//if end points ovelap, pick the one with lower height first
				if (!a.isStart && !b.isStart) {
					return Integer.compare(a.height, b.height);
				}
                //if one is starting point and other is end point, pick the starting point first
				return a.isStart ? -1 : 1;
			}
		});

		// process points
		//if height of current starting point exceeds the previous max height, add current point to the answer and to the PQ
		//else if it is the end point, remove it from the PQ first and if the max changes, add it to the answer
		PriorityQueue<Integer> heightHeap = new PriorityQueue<Integer>(10,
				Collections.reverseOrder());
 
		for (BuildingPoint point : points) {
			if (point.isStart) {
				if (heightHeap.isEmpty() || point.height > heightHeap.peek()) {
					result.add(new int[] { point.x, point.height });
				}
				heightHeap.add(point.height);
			} else {
				heightHeap.remove(point.height);
				if (heightHeap.isEmpty()) {
					result.add(new int[] { point.x, 0 });
				} else if (point.height > heightHeap.peek()) {
					result.add(new int[] { point.x, heightHeap.peek() });
				}
			}
		}

		return result;
	}
}
