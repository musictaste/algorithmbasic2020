package class22;

// 返回K次打击后英雄把怪兽砍死的几率
// 样本对应模型
public class Code01_KillMonster {

	public static double right(int N, int M, int K) {
		if (N < 1 || M < 1 || K < 1) {
			return 0;
		}
		// 总的情况数=（M+1）^K
		long all = (long) Math.pow(M + 1, K);
		long kill = process(K, M, N);
		return (double) ((double) kill / (double) all);
	}

	// 怪兽还剩hp点血
	// 每次的伤害在[0~M]范围上
	// 还有times次可以砍
	// 返回砍死的情况数！
	public static long process(int times, int M, int hp) {
		if (times == 0) {
			return hp <= 0 ? 1 : 0;
		}
		if (hp <= 0) {
			return (long) Math.pow(M + 1, times);
		}
		long ways = 0;
		for (int i = 0; i <= M; i++) {
			ways += process(times - 1, M, hp - i);
		}
		return ways;
	}

	// 记忆化搜索
	public static double dp1(int N, int M, int K) {
		if (N < 1 || M < 1 || K < 1) {
			return 0;
		}
		long all = (long) Math.pow(M + 1, K);
		long[][] dp = new long[K + 1][N + 1];
		dp[0][0] = 1;
		for (int times = 1; times <= K; times++) {
			// 0血量的情况，第一列
			// 怪兽血量为0，还有times刀可以砍，那么dp[times][0]的能将怪兽砍死的可能性就是(M+1)^times
			dp[times][0] = (long) Math.pow(M + 1, times);
			for (int hp = 1; hp <= N; hp++) {
				long ways = 0;
				for (int i = 0; i <= M; i++) {
					// 为了防止越界，hp-i超过二维表的范围
					// 在范围内，直接查dp表；如果血量已经小于0，直接查或者直接计算
					if (hp - i >= 0) {
						ways += dp[times - 1][hp - i];
					} else {
						// 如果血量已经小于0，直接查或者直接计算
//						ways += (long) Math.pow(M + 1, times - 1);
						ways += dp[times-1][0];
					}
				}
				dp[times][hp] = ways;
			}
		}
		long kill = dp[K][N];
		return (double) ((double) kill / (double) all);
	}

	// 严格的表结构，去掉枚举行为
	public static double dp2(int N, int M, int K) {
		if (N < 1 || M < 1 || K < 1) {
			return 0;
		}
		long all = (long) Math.pow(M + 1, K);
		long[][] dp = new long[K + 1][N + 1];
		dp[0][0] = 1;
		for (int times = 1; times <= K; times++) {
			dp[times][0] = (long) Math.pow(M + 1, times);
			for (int hp = 1; hp <= N; hp++) {
				dp[times][hp] = dp[times][hp - 1] + dp[times - 1][hp];
				if (hp - 1 - M >= 0) {
					dp[times][hp] -= dp[times - 1][hp - 1 - M];
				} else {
					dp[times][hp] -= Math.pow(M + 1, times - 1); // 这块需要注意
				}
			}
		}
		long kill = dp[K][N];
		return (double) ((double) kill / (double) all);
	}

	public static void main(String[] args) {
		int NMax = 10;
		int MMax = 10;
		int KMax = 10;
		int testTime = 200;
		System.out.println("测试开始");
		for (int i = 0; i < testTime; i++) {
			int N = (int) (Math.random() * NMax);
			int M = (int) (Math.random() * MMax);
			int K = (int) (Math.random() * KMax);
			double ans1 = right(N, M, K);
			double ans2 = dp1(N, M, K);
			double ans3 = dp2(N, M, K);
			if (ans1 != ans2 || ans1 != ans3) {
				System.out.println("Oops!");
				break;
			}
		}
		System.out.println("测试结束");
	}

}
