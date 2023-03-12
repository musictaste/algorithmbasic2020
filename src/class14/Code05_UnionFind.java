package class14;

import java.util.HashMap;
import java.util.List;
import java.util.Stack;

// 课上讲的并查集实现
// 请务必看补充的Code06_UnionFind
// 那是数组实现的并查集，并且有测试链接
// 可以直接通过
// 这个文件的并查集是用map实现的
// 但是笔试或者平时用的并查集一律用数组实现
// 所以Code06_UnionFind更具实战意义
// 一定要看！
public class Code05_UnionFind {

	public static class Node<V> {
		V value;

		public Node(V v) {
			value = v;
		}
	}

	public static class UnionFind<V> {
		// 需要注意哈希表虽然时间复杂度O(1),但是是大常数
		public HashMap<V, Node<V>> nodes;
		public HashMap<Node<V>, Node<V>> parents; // 当前节点的父节点，key是子节点，value是直系父节点
		public HashMap<Node<V>, Integer> sizeMap; // 代表节点 下的节点个数，用于别的用途，例如统计当前集合的个数，size的个数=集合的个数

		public UnionFind(List<V> values) {
			nodes = new HashMap<>();
			parents = new HashMap<>();
			sizeMap = new HashMap<>();
			for (V cur : values) {
				Node<V> node = new Node<>(cur);
				nodes.put(cur, node);
				parents.put(node, node);
				sizeMap.put(node, 1);
			}
		}

		// 给你一个节点，请你往上到不能再往上，把代表节点返回
		public Node<V> findFather(Node<V> cur) {
			Stack<Node<V>> path = new Stack<>();
			while (cur != parents.get(cur)) {
				path.push(cur);
				cur = parents.get(cur);
			}
			while (!path.isEmpty()) { // 优化2：代表节点下的同一个路径下的节点，优化为扁平结构，这样以后这条链上的节点再查找代表节点时，不需要遍历整个链了
				parents.put(path.pop(), cur);
			}
			return cur;
		}

		// 代表节点是否相同
		public boolean isSameSet(V a, V b) {
			return findFather(nodes.get(a)) == findFather(nodes.get(b));
		}

		// 把a所在的集合  union b所在的集合
		public void union(V a, V b) {
			Node<V> aHead = findFather(nodes.get(a)); // 找到代表节点
			Node<V> bHead = findFather(nodes.get(b));
			if (aHead != bHead) { // 不是同一个集合
				int aSetSize = sizeMap.get(aHead); // 找到代表节点下的节点个数
				int bSetSize = sizeMap.get(bHead);
				Node<V> big = aSetSize >= bSetSize ? aHead : bHead; // 哪个代表节点下的节点个数多，谁就是大集合
				Node<V> small = big == aHead ? bHead : aHead; // == 判断是否为同一个节点
				parents.put(small, big);
				sizeMap.put(big, aSetSize + bSetSize); // 小集合 挂在 大集合下面
				sizeMap.remove(small); // 小集合变成非代表节点
			}
		}

		public int sets() {
			return sizeMap.size();
		}

	}
}
