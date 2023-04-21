package class22;

// 返回组成aim的最少货币数
// 从左往右的尝试模型
public class Code02_MinCoinsNoLimit {

	public static int minCoins(int[] arr, int aim) {
		return process(arr, 0, aim);
	}

	// arr[index...]面值，每种面值张数自由选择，
	// 搞出rest正好这么多钱，返回最小张数
	// 拿Integer.MAX_VALUE标记怎么都搞定不了
	// 注意如果怎么都搞不定用-1来标记，去看课堂的代码，这里暂不补充
	public static int process(int[] arr, int index, int rest) {
		// 没有钱了
		if (index == arr.length) {
			return rest == 0 ? 0 : Integer.MAX_VALUE;
		} else {
			int ans = Integer.MAX_VALUE;
			for (int zhang = 0; zhang * arr[index] <= rest; zhang++) {
				int next = process(arr, index + 1, rest - zhang * arr[index]);
				if (next != Integer.MAX_VALUE) {
					// next不是最大值，说明是有效的
					ans = Math.min(ans, zhang + next);
				}
			}
			return ans;
		}
	}

	// 有枚举行为的动态规划，记忆化搜索
	public static int dp1(int[] arr, int aim) {
		if (aim == 0) {
			return 0;
		}
		int N = arr.length;
		int[][] dp = new int[N + 1][aim + 1];
		dp[N][0] = 0;
		for (int j = 1; j <= aim; j++) {
			dp[N][j] = Integer.MAX_VALUE;
		}
		// 因为index依赖index+1的位置，所以index倒序
		for (int index = N - 1; index >= 0; index--) {
			for (int rest = 0; rest <= aim; rest++) {
				int ans = Integer.MAX_VALUE;
				for (int zhang = 0; zhang * arr[index] <= rest; zhang++) {
					int next = dp[index + 1][rest - zhang * arr[index]];
					if (next != Integer.MAX_VALUE) {
						ans = Math.min(ans, zhang + next);
					}
				}
				dp[index][rest] = ans;
			}
		}
		return dp[0][aim];
	}

	// 去除枚举行为，严格表结构
	public static int dp2(int[] arr, int aim) {
		if (aim == 0) {
			return 0;
		}
		int N = arr.length;
		int[][] dp = new int[N + 1][aim + 1];
		dp[N][0] = 0;
		for (int j = 1; j <= aim; j++) {
			dp[N][j] = Integer.MAX_VALUE;
		}
		for (int index = N - 1; index >= 0; index--) {
			for (int rest = 0; rest <= aim; rest++) {
				// 对号先赋值a，a是下方位置
				dp[index][rest] = dp[index + 1][rest];
				// 星号是 对号左边的情况，少使用一张当前货币的情况
				// 星号位置不越界，并且星号位置dp表中有值
				if (rest - arr[index] >= 0 && dp[index][rest - arr[index]] != Integer.MAX_VALUE) {
					dp[index][rest] = Math.min(dp[index][rest], dp[index][rest - arr[index]] + 1);
				}
			}
		}
		return dp[0][aim];
	}

	// 为了测试
	public static int[] randomArray(int maxLen, int maxValue) {
		int N = (int) (Math.random() * maxLen);
		int[] arr = new int[N];
		boolean[] has = new boolean[maxValue + 1];
		for (int i = 0; i < N; i++) {
			do {
				arr[i] = (int) (Math.random() * maxValue) + 1;
			} while (has[arr[i]]);
			has[arr[i]] = true;
		}
		return arr;
	}

	// 为了测试
	public static void printArray(int[] arr) {
		for (int i = 0; i < arr.length; i++) {
			System.out.print(arr[i] + " ");
		}
		System.out.println();
	}

	// 为了测试
	public static void main(String[] args) {
		int maxLen = 20;
		int maxValue = 30;
		int testTime = 300000;
		System.out.println("功能测试开始");
		for (int i = 0; i < testTime; i++) {
			int N = (int) (Math.random() * maxLen);
			int[] arr = randomArray(N, maxValue);
			int aim = (int) (Math.random() * maxValue);
			int ans1 = minCoins(arr, aim);
			int ans2 = dp1(arr, aim);
			int ans3 = dp2(arr, aim);
			if (ans1 != ans2 || ans1 != ans3) {
				System.out.println("Oops!");
				printArray(arr);
				System.out.println(aim);
				System.out.println(ans1);
				System.out.println(ans2);
				break;
			}
		}
		System.out.println("功能测试结束");
	}

}
