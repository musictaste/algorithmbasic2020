package class16;

public class Edge {
	public int weight; // 边的权重
	public Node from; // 从哪个点出发
	public Node to; // 到达那个点

	public Edge(int weight, Node from, Node to) {
		this.weight = weight;
		this.from = from;
		this.to = to;
	}

}
