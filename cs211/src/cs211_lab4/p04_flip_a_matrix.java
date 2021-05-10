package cs211_lab4;
/*
Given a m x n binary matrix mat. In one step, 
you can choose one cell and flip it and all the 
four neighbors of it if they exist (Flip is 
changing 1 to 0 and 0 to 1). A pair of cells 
are called neighbors if they share one edge.
*/
import java.util.HashMap;
import java.util.Map;
import java.util.Queue;
import java.util.LinkedList;

public class p04_flip_a_matrix {
	static class bfs {
		private Map<String, Boolean> map;

		public int minFlips(int[][] mat) {

			int m = mat.length;
			int n = mat[0].length;

			if (m == 0 || n == 0) {
				return 0;
			}
			Queue<node> q = new LinkedList<>();
			map = new HashMap<>();
			node x = new node(mat, 0);
			update(mat);
			q.add(x);

			while (!q.isEmpty()) {
				node now = q.poll();
				if (check(now.matrix))
					return now.step;
				for (int i = 0; i < m; i++)
					for (int j = 0; j < n; j++) {
						if (work(now, i, j)) {
							node tmp = new node(now.matrix, 0);
							tmp.matrix[i][j] = 1 - tmp.matrix[i][j];
							if (i > 0) {
								tmp.matrix[i - 1][j] = 1 - tmp.matrix[i - 1][j];
							}
							if (j > 0) {
								tmp.matrix[i][j - 1] = 1 - tmp.matrix[i][j - 1];
							}
							if (i < m - 1) {
								tmp.matrix[i + 1][j] = 1 - tmp.matrix[i + 1][j];
							}
							if (j < n - 1) {
								tmp.matrix[i][j + 1] = 1 - tmp.matrix[i][j + 1];
							}
							tmp.step = now.step + 1;
							q.add(tmp);
							update(tmp.matrix);
						}
					}
			}

			return -1;
		}
//      update the element
		private void update(int[][] mat) {
			StringBuilder str = new StringBuilder();
			for (int i = 0; i < mat.length; i++)
				for (int j = 0; j < mat[0].length; j++)
					str.append(String.valueOf(mat[i][j]));
			map.put(str.toString(), true);
		}
		
//      check the element
		private boolean check(int[][] mat) {
			for (int i = 0; i < mat.length; i++)
				for (int j = 0; j < mat[0].length; j++)
					if (mat[i][j] == 1)
						return false;
			return true;
		}

		private boolean judge(int[][] mat) {
			StringBuilder str = new StringBuilder();
			for (int i = 0; i < mat.length; i++)
				for (int j = 0; j < mat[0].length; j++)
					str.append(String.valueOf(mat[i][j]));
			if (map.containsKey(str.toString()))
				return true;
			return false;
		}

		private boolean work(node now, int i, int j) {
			node tmp = new node(now.matrix, 0);
			tmp.matrix[i][j] = 1 - tmp.matrix[i][j];
			if (i > 0)
				tmp.matrix[i - 1][j] = 1 - tmp.matrix[i - 1][j];
			if (j > 0)
				tmp.matrix[i][j - 1] = 1 - tmp.matrix[i][j - 1];
			if (i < now.matrix.length - 1)
				tmp.matrix[i + 1][j] = 1 - tmp.matrix[i + 1][j];
			if (j < now.matrix[0].length - 1)
				tmp.matrix[i][j + 1] = 1 - tmp.matrix[i][j + 1];
			if (judge(tmp.matrix))
				return false;
			return true;
		}

		class node {
			int[][] matrix;
			int step;

			public node(int[][] matrix, int step) {
				this.matrix = new int[matrix.length][matrix[0].length];

				for (int i = 0; i < matrix.length; i++)
					for (int j = 0; j < matrix[0].length; j++)
						this.matrix[i][j] = matrix[i][j];
				this.step = step;
			}
		}
	}

	public static void main(String args[]) {
		bfs a = new bfs();
//		Example 1
//		int[][] mat = { { 0, 0 }, { 0, 1 } };
//		Example 2
//		int[][] mat = { { 0 } };
//		Example 3
//		int[][] mat = { { 1, 1, 1 }, { 1, 0, 1 }, { 0, 0, 0 } };
//		Example 4
		int[][] mat = { { 1, 0, 0 }, { 1, 0, 0 } };
		int result = a.minFlips(mat);
		System.out.println(result);
	}
}