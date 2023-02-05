package class16;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;

// 宽度优先遍历，队列
public class Code01_BFS {

	// 从node出发，进行宽度优先遍历
	public static void bfs(Node start) {
		if (start == null) {
			return;
		}
		Queue<Node> queue = new LinkedList<>();
		HashSet<Node> set = new HashSet<>(); // 用于帮助图的遍历陷入死循环，有环的情况下，辅助哪些点已经出现过
		queue.add(start);
		set.add(start);
		while (!queue.isEmpty()) {
			Node cur = queue.poll(); // 出队列就打印
			System.out.println(cur.value);
			for (Node next : cur.nexts) {
				if (!set.contains(next)) {
					set.add(next);
					queue.add(next);
				}
			}
		}
	}

}
