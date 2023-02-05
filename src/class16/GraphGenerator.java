package class16;

public class GraphGenerator {

	// 将其他形式表示图的结构，转化为我们熟悉的表示图的数据结构
	// matrix 所有的边
	// N*3 的矩阵
	// [weight, from节点上面的值，to节点上面的值]
	// 
	// [ 5 , 0 , 7] ，权重为5，从0出发，到达7
	// [ 3 , 0,  1]
	public static Graph createGraph(int[][] matrix) {
		Graph graph = new Graph();
		for (int i = 0; i < matrix.length; i++) {
			 // 拿到每一条边， matrix[i] 
			int weight = matrix[i][0];
			int from = matrix[i][1];
			int to = matrix[i][2];

			// 得到图的点集
			if (!graph.nodes.containsKey(from)) {
				graph.nodes.put(from, new Node(from));
			}
			if (!graph.nodes.containsKey(to)) {
				graph.nodes.put(to, new Node(to));
			}

			Node fromNode = graph.nodes.get(from);
			Node toNode = graph.nodes.get(to);
			Edge newEdge = new Edge(weight, fromNode, toNode); // 边
			fromNode.nexts.add(toNode); // 相邻的点
			fromNode.out++; // 出度
			toNode.in++; // 入度
			fromNode.edges.add(newEdge); // 相邻的边
			graph.edges.add(newEdge); // 图的边集
		}
		return graph;
	}

}
