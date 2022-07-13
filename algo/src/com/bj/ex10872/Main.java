package com.bj.ex10872;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
문제
0보다 크거나 같은 정수 N이 주어진다. 이때, N!을 출력하는 프로그램을 작성하시오.

입력
첫째 줄에 정수 N(0 ≤ N ≤ 12)이 주어진다.

출력
첫째 줄에 N!을 출력한다.
*/
public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int inputNum = Integer.parseInt(st.nextToken());
		
		System.out.println(getPactorial(inputNum));
	}
	
	private static int getPactorial(int data) {
		if (data > 1) {
			System.out.println(data);
			System.out.println("master 브랜치에서 수정");
			return data*getPactorial(data -1);
		}else{
			return 1;
		}
	}

}
