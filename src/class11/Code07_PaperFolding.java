package class11;

// 二叉树折纸问题
public class Code07_PaperFolding {

	public static void printAllFolds(int N) {
		process(1, N, true); // 这棵树的头结点是凹的
		System.out.println();
	}

	// 当前你来了一个节点，脑海中想象的！
	// 这个节点在第i层，一共有N层，N固定不变的
	// 这个节点如果是凹的话，down = T
	// 这个节点如果是凸的话，down = F
	// 函数的功能：中序打印以你想象的节点为头的整棵树！
	public static void process(int i, int N, boolean down) {
		if (i > N) {
			return;
		}
		process(i + 1, N, true); // 所有左子树的头结点是凹的
		System.out.print(down ? "凹 " : "凸 ");
		process(i + 1, N, false); // 所有右子树的头结点是凸的
	}

	public static void main(String[] args) {
		int N = 4;
		printAllFolds(N);
	}
}
