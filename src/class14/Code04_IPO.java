package class14;

import java.util.Comparator;
import java.util.PriorityQueue;

// 输入正整数组costs、正整数组profits、正数k、正数M、输出你最后获得的最大钱数
public class Code04_IPO {

	// 最多K个项目
	// W是初始资金
	// Profits[] 利润 Capital[] 花费 一定等长
	// 返回最终最大的资金
	public static int findMaximizedCapital(int K, int W, int[] Profits, int[] Capital) {
		PriorityQueue<Program> minCostQ = new PriorityQueue<>(new MinCostComparator());
		PriorityQueue<Program> maxProfitQ = new PriorityQueue<>(new MaxProfitComparator());
		for (int i = 0; i < Profits.length; i++) { // 所有项目根据花费大小生成小根堆
			minCostQ.add(new Program(Profits[i], Capital[i]));
		}
		for (int i = 0; i < K; i++) { // K轮，拿K个项目
			while (!minCostQ.isEmpty() && minCostQ.peek().c <= W) { // 项目花费< 起始资金的项目，根据利润生成大根堆
				maxProfitQ.add(minCostQ.poll());
			}
			if (maxProfitQ.isEmpty()) { // 当大根堆没有项目时，虽然没有到达K轮，但是起始资金没有适合的项目，所以提前结算
				return W;
			}
			W += maxProfitQ.poll().p; // 要做的项目是大根堆的堆顶项目，起始资金+项目的利润=新的起始资金
		}
		return W;
	}

	public static class Program {
		public int p; // 利润
		public int c; // 花费

		public Program(int p, int c) {
			this.p = p;
			this.c = c;
		}
	}

	// 花费小的小根堆
	public static class MinCostComparator implements Comparator<Program> {

		@Override
		public int compare(Program o1, Program o2) {
			return o1.c - o2.c;
		}

	}

	// 利润大的大根堆
	public static class MaxProfitComparator implements Comparator<Program> {

		@Override
		public int compare(Program o1, Program o2) {
			return o2.p - o1.p;
		}

	}

}
