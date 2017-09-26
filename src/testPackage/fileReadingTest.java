package testPackage;

import java.util.*;
import java.io.*;

public class fileReadingTest {

	int n = 8;
	int m = 5000;
	int i, j, k = 0, s, e, min;
	int[] v = new int[8];
	int[] distance = new int[8];
	int[] via = new int[8];

	int[][] data = { { 0, 2, m, m, m, 3, m, m }, { 2, 0, 4, 1, m, m, m, m }, { m, 4, 0, m, 3, m, m, m },
			{ m, 1, m, 0, 3, m, 2, m }, { m, m, 3, 3, 0, m, m, 4 }, { 3, m, m, m, m, 0, 6, m },
			{ m, m, m, 2, m, 6, 0, 4 }, { m, m, m, m, 4, m, 4, 0 }

	};

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		System.out.print("\n출발점을 입력하시오 : ");
		s = Integer.parseInt(br.readLine());
		System.out.print("\n도착점을 입력하시오 : ");
		e = Integer.parseInt(br.readLine());

		for (j = 0; j < dimension; j++) {
			v[j] = 0;
			distance[j] = m;
		}

		distance[s - 1] = 0;

		for (i = 0; i < dimension; i++) {
			min = m;
			for (j = 0; j < dimension; j++) {
				if (v[j] == 0 && distance[j] < min) {
					k = j;
					min = distance[j];
				}
			}
			v[k] = 1;
			if (min == m)
				break;

			for (j = 0; j < 8; j++) {
				if (distance[j] > distance[k] + data[k][j]) {
					distance[j] = distance[k] + data[k][j];
					via[j] = k;
				}
			}
		}
		System.out.printf("\n %d에서 출발하여, %d로 가는 최단 거리는 %d입니다.\n", s, e, distance[e - 1]);

		int path[] = new int[dimension];
		int path_cnt = 0;

		k = e - 1;
		while (true) {
			path[path_cnt++] = k;
			if (k == s - 1)
				break;
			k = via[k];
		}

		System.out.print(" 경로는 : ");

		for (int i = path_cnt - 1; i >= 1; i--) {
			System.out.printf("%d -> ", path[i] + 1);
		}

		System.out.printf("%d입니다", path[i] + 1);
	}
}
