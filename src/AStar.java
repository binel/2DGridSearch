import java.util.HashMap;
import java.util.HashSet;

public class AStar {
	public static void search(Grid g, Coord goal) {
		HashSet<Node> closed = new HashSet<Node>(); 	
		HashSet<Node> fringe = new HashSet<Node>(); 
		HashMap<Node, Node> cameFrom = new HashMap<Node, Node>(); 
		HashMap<Node, Integer> gScore = new HashMap<Node, Integer>(); 
		HashMap<Node, Integer> fScore = new HashMap<Node, Integer>();
		
		gScore.put(g.getRoot(), 0);
		
		fScore.put(g.getRoot(), hValue(g.getRoot(), goal));
		fringe.add(g.getRoot());
		
		while(!fringe.isEmpty()) {
			Node current = minFScore(fringe, goal);
			current.isExpanded = true;
			g.expandNode(current);
			
			if(goalTest(current, goal)) {
				current.isGoal = true;
				g.solutionDelay();
				return; 
			}
			
			fringe.remove(current);
			closed.add(current);
			
			for(Node n : current.getNeighbors()) {
				n.isActive = true;
				g.solutionDelay();
				n.isActive = false; 
				if (closed.contains(n)) {
					continue; 
				}
				
				if(!fringe.contains(n)) {
					fringe.add(n);
				}
				
				initScores(n, fScore, gScore);
				int possibleGScore= gScore.get(current) + 1;
				if(possibleGScore > gScore.get(n)) {
					continue;
				} else {
					cameFrom.put(n, current);
					gScore.put(n, possibleGScore);
					fScore.put(n, gScore.get(n) + hValue(n, goal));
				}
			}
		}
		
		
	}
	
	private static void initScores(Node n, HashMap<Node, Integer> fScores, HashMap<Node, Integer> gScores) {
		if(!fScores.containsKey(n)) {
			fScores.put(n, Integer.MAX_VALUE);
		}
		if(!gScores.containsKey(n)) {
			gScores.put(n, Integer.MAX_VALUE);
		}
	}
	
	private static boolean goalTest(Node n, Coord goal) {
		if(n.c.equals(goal)) {
			return true;
		} else {
			return false; 
		}
	}
	
	//gets the node in the fringe with the smallest FScore
	private static Node minFScore(HashSet<Node> fringe, Coord goal) {
		int min = Integer.MAX_VALUE; 
		Node minNode = null; 
		for(Node n : fringe) {
			if(hValue(n, goal) < min) {
				min = hValue(n, goal);
				minNode = n; 
			}
		}
		fringe.remove(minNode);
		return minNode; 
	}
	
	//Our heuristic is the manhattan distance 
	private static int hValue(Node n, Coord goal) {
		return Math.abs(n.c.x - goal.x) + Math.abs(n.c.y - goal.y);
	}
}
