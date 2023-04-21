package class19;

public class Code01_Knapsack {

	// 所有的货，重量和价值，都在w和v数组里
	// 为了方便，其中没有负数
	// bag背包容量，不能超过这个载重
	// 返回：不超重的情况下，能够得到的最大价值
	public static int maxValue(int[] w, int[] v, int bag) {
		if (w == null || v == null || w.length != v.length || w.length == 0) {
			return 0;
		}
		// 尝试函数！
		return process(w, v, 0, bag);
	}


	// 当前考虑到了index号货物，index后的所有货物都可以自由选择
	// 做的选择不能超过背包容量
	// 返回最大价值
	public static int process(int[] w, int[] v, int index, int rest) {
		// 注意rest 不等于0，为什么？有可能背包重量w=0，并且价值v为正数
		if (rest < 0) {
			return -1;
		}
		if (index == w.length) {
			return 0;
		}
		// 有货，考虑index位置的货
		// 背包有容量，哪怕是0
		// 不考虑index位置的货
		int p1 = process(w, v, index + 1, rest);
		// 考虑index位置的货
		int p2 = 0;
		int next = process(w, v, index + 1, rest - w[index]);
		if (next != -1) {
			p2 = v[index] + next;
		}
		return Math.max(p1, p2);
	}

	public static int dp(int[] w, int[] v, int bag) {
		if (w == null || v == null || w.length != v.length || w.length == 0) {
			return 0;
		}
		int N = w.length;
		// 可变参数的变化范围：index 0~N
		// 可变参数的变化范围：rest 负~bag
		int[][] dp = new int[N + 1][bag + 1];
		for (int index = N - 1; index >= 0; index--) {
			for (int rest = 0; rest <= bag; rest++) {
				int p1 = dp[index + 1][rest];
				int p2 = 0;
				int next = rest - w[index] < 0 ? -1 : dp[index + 1][rest - w[index]];
				if (next != -1) {
					p2 = v[index] + next;
				}
				dp[index][rest] = Math.max(p1, p2);
			}
		}
		return dp[0][bag];
	}

	public static void main(String[] args) {
		int[] weights = { 3, 2, 4, 7, 3, 1, 7 };
		int[] values = { 5, 6, 3, 19, 12, 4, 2 };
		int bag = 15;
		System.out.println(maxValue(weights, values, bag));
		System.out.println(dp(weights, values, bag));
	}

}
