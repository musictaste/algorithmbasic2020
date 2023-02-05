package class16;

import java.util.HashMap;
import java.util.HashSet;

// 图
public class Graph {
	public HashMap<Integer, Node> nodes; // 点集
	public HashSet<Edge> edges; // 边集
	
	public Graph() {
		nodes = new HashMap<>();
		edges = new HashSet<>();
	}
}
