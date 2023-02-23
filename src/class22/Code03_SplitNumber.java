package class22;

// 返回裂开的数的种类
public class Code03_SplitNumber {

	// n为正数
	public static int ways(int n) {
		if (n < 0) {
			return 0;
		}
		if (n == 1) {
			return 1;
		}
		return process(1, n);
	}

	// 上一个拆出来的数是pre
	// 还剩rest需要去拆
	// 返回拆解的方法数
	public static int process(int pre, int rest) {
		if (rest == 0) {
			return 1;
		}
		if (pre > rest) {
			return 0; // base case
		}
		int ways = 0;
		for (int first = pre; first <= rest; first++) {
			ways += process(first, rest - first);
		}
		return ways;
	}

	// 有枚举行为的动态规划，记忆化搜索
	public static int dp1(int n) {
		if (n < 0) {
			return 0;
		}
		if (n == 1) {
			return 1;
		}
		int[][] dp = new int[n + 1][n + 1];
		// 上一个拆出来的数是pre
		for (int pre = 1; pre <= n; pre++) {
			dp[pre][0] = 1; // rest=0的时候返回1
			dp[pre][pre] = 1;
		}
		for (int pre = n - 1; pre >= 1; pre--) {
			for (int rest = pre + 1; rest <= n; rest++) { // 剩余的rest必须大于上一个数pre
				int ways = 0;
				for (int first = pre; first <= rest; first++) {
					ways += dp[first][rest - first];
				}
				dp[pre][rest] = ways;
			}
		}
		return dp[1][n];
	}

	// 去除枚举行为，严格表结构
	public static int dp2(int n) {
		if (n < 0) {
			return 0;
		}
		if (n == 1) {
			return 1;
		}
		int[][] dp = new int[n + 1][n + 1];
		for (int pre = 1; pre <= n; pre++) {
			dp[pre][0] = 1;
			dp[pre][pre] = 1;
		}
		for (int pre = n - 1; pre >= 1; pre--) {
			for (int rest = pre + 1; rest <= n; rest++) {
				dp[pre][rest] = dp[pre + 1][rest];
				dp[pre][rest] += dp[pre][rest - pre];
			}
		}
		return dp[1][n];
	}

	public static void main(String[] args) {
		int test = 39;
		System.out.println(ways(test));
		System.out.println(dp1(test));
		System.out.println(dp2(test));
	}

}
