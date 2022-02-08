package Maze;

import java.util.ArrayList;
import java.util.Stack;

public class Maze implements GridColors {

	/** The maze */
	private TwoDimGrid maze;

	public Maze(TwoDimGrid m) {
		maze = m;
	}

	/** Wrapper method. */
	public boolean findMazePath() {
		findMazePathMin(0, 0);
		findAllMazePaths(0, 0);
		return findMazePath(0, 0); // (0, 0) is the start point.
	}

	public boolean findMazePath(int x, int y) {
		// COMPLETE HERE FOR PROBLEM 1
		if (x < 0 || y < 0 || x > maze.getNCols() - 1 || y > maze.getNRows() - 1
				|| maze.getColor(x, y) != NON_BACKGROUND) {
			return false;
		}
		if (x == maze.getNCols() - 1 && y == maze.getNRows() - 1) {
			maze.recolor(x, y, PATH);
			return true;
		} else {
			maze.recolor(x, y, PATH);
			if ((this.findMazePath(x - 1, y) || this.findMazePath(x + 1, y) || this.findMazePath(x, y - 1)
					|| this.findMazePath(x, y + 1))) {
				return true;
			} else {
				maze.recolor(x, y, TEMPORARY);
				return false;
			}
		}
	}

	// ADD METHOD FOR PROBLEM 2 HERE
	public void findMazePathStackBased(int x, int y, ArrayList<ArrayList<PairInt>> result, Stack<PairInt> trace) {
		if (x < 0 || y < 0 || x > maze.getNCols() - 1 || y > maze.getNRows() - 1
				|| maze.getColor(x, y) != NON_BACKGROUND) {
			return;
		}
		if (x == maze.getNCols() - 1 && y == maze.getNRows() - 1) {
			PairInt objOfPairInt = new PairInt(x, y);
			ArrayList<PairInt> findPath = new ArrayList<PairInt>();
			trace.push(objOfPairInt);
			findPath.addAll(trace);
			result.add(findPath);
			trace.pop();
			return;
		} else {
			PairInt objOfPairInt = new PairInt(x, y);
			maze.recolor(x, y, PATH);
			trace.push(objOfPairInt);
			findMazePathStackBased(x - 1, y, result, trace);
			findMazePathStackBased(x, y - 1, result, trace);
			findMazePathStackBased(x + 1, y, result, trace);
			findMazePathStackBased(x, y + 1, result, trace);
			trace.pop();
			maze.recolor(x, y, NON_BACKGROUND);
		}
	}

	public ArrayList<ArrayList<PairInt>> findAllMazePaths(int x, int y) {
		ArrayList<ArrayList<PairInt>> result = new ArrayList<>();
		Stack<PairInt> trace = new Stack<>();
		findMazePathStackBased(0, 0, result, trace);
		if (result.size() != 0) {
			System.out.println("Founded Path: ");
			System.out.println(result.get(0));
			for (int i = 1; i < result.size(); i++) {
				System.out.println(result.get(i));
			}
		}
		return result;
	}

	// ADD METHOD FOR PROBLEM 3 HERE
	public boolean findMin(int x, int y, Stack<PairInt> path, ArrayList<PairInt> result) {
		if (x < 0 || y < 0 || x > maze.getNCols() - 1 || y > maze.getNRows() - 1
				|| maze.getColor(x, y) != NON_BACKGROUND) {
			return false;
		}
		if (x == maze.getNCols() - 1 && y == maze.getNRows() - 1) {
			path.push(new PairInt(x, y));
			if (path.size() < result.size() || result.size() == 0) {
				result.clear();
				result.addAll(path);
			}
			path.pop();
			return true;
		} else {
			maze.recolor(x, y, PATH);
			path.push(new PairInt(x, y));
			if (findMin(x - 1, y, path, result) | findMin(x + 1, y, path, result) | findMin(x, y - 1, path, result)
					| findMin(x, y + 1, path, result)) {
				maze.recolor(x, y, NON_BACKGROUND);
				path.pop();
				return true;
			} else {
				maze.recolor(x, y, NON_BACKGROUND);
				path.pop();
				return false;
			}

		}
	}

	public ArrayList<PairInt> findMazePathMin(int x, int y) {
		ArrayList<PairInt> result = new ArrayList<>();
		Stack<PairInt> path = new Stack<>();
		findMin(0, 0, path, result);
		if (result.size() != 0) {
			System.out.println("Shortest Path:");
			System.out.println(result);
		} else {
			System.out.println("Shortest Path:");
			System.out.println("[" + result + "]");
		}
		return result;
	}

	/* <exercise chapter="5" section="6" type="programming" number="2"> */
	public void resetTemp() {
		maze.recolor(TEMPORARY, BACKGROUND);
	}
	/* </exercise> */

	/* <exercise chapter="5" section="6" type="programming" number="3"> */
	public void restore() {
		resetTemp();
		maze.recolor(PATH, BACKGROUND);
		maze.recolor(NON_BACKGROUND, BACKGROUND);
	}
	/* </exercise> */
}
/* </listing> */
