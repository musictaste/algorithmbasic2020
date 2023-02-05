package class16;

import java.util.ArrayList;

// 点结构的描述
public class Node {
	public int value; // 当前节点的值
	public int in; // 入度，有几条线指向当前节点
	public int out; // 出度，有几条先从由当前节点出发
	public ArrayList<Node> nexts; // 直接节点，就当前节点出发能直接找到的邻居
	public ArrayList<Edge> edges; // 直接边，从当前节点出发能找到的边

	public Node(int value) {
		this.value = value;
		in = 0;
		out = 0;
		nexts = new ArrayList<>();
		edges = new ArrayList<>();
	}
}
