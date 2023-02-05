package class16;

import java.util.HashSet;
import java.util.Stack;

// 深度优先遍历，栈
public class Code02_DFS {

	// 迭代版本
	public static void dfs(Node node) {
		if (node == null) {
			return;
		}
		Stack<Node> stack = new Stack<>();
		HashSet<Node> set = new HashSet<>(); // 辅助结构，记录所有已经打印过的节点；禁止走环路
		stack.add(node);
		set.add(node);
		System.out.println(node.value); // 入栈就打印
		while (!stack.isEmpty()) {
			Node cur = stack.pop();
			for (Node next : cur.nexts) {
				if (!set.contains(next)) {
					// 这时候栈是当前的整条路径
					stack.push(cur);
					stack.push(next);
					set.add(next);
					System.out.println(next.value);
					break;
				}
			}
		}
	}
	
	
	

}
