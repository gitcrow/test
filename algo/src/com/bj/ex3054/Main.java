package com.bj.ex3054;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
문제
"피터팬 프레임"은 단어를 다이아몬드 형태로 장식하는 것이다.

알파벳 X를 피터팬 프레임으로 장식하면 다음과 같다.

..#..
.#.#.
#.X.#
.#.#.
..#..
"웬디 프레임"은 피터팬 프레임과 유사하지만, 다이아몬드를 '*'로 만드는 것이다. 

알파벳 X를 웬디 프레임으로 장식하면 다음과 같다.

..*..
.*.*.
*.X.*
.*.*.
..*..
단어가 주어졌을 때, 3의 배수 위치(세 번째, 여섯 번째, 아홉번째, ...)에 있는 알파벳은 웬디 프레임으로, 나머지 알파벳은 피터팬 프레임으로 장식하는 프로그램을 작성하시오.

웬디 프레임과 피터팬 프레임이 겹칠 경우에는, 웬디 프레임이 위에 있다.

입력
첫째 줄에 알파벳 대문자로 이루어진 최대 15글자 단어가 주어진다.

출력
다섯 줄에 걸쳐, 입력으로 주어진 단어를 피터팬 프레임과 웬디 프레임으로 장식한 결과를 출력한다.
*/
public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		String input = st.nextToken();
		System.out.println(getP(input));

	}
	
	private static String getP(String str) {
		StringBuilder tmpStr = new StringBuilder();
		for(int i = 0 ; i < 5 ; i++) {
			switch(i) {
				case 0 :
					for (int j = 1; j <= str.length(); j++) {
						if(str.length()>1 && j < str.length()) {
							if(j%3==0) {
								tmpStr.append("..*.");
							}else {
								tmpStr.append("..#.");
							}
						}else {
							if(j%3==0) {
								tmpStr.append("..*..");
							}else {
								tmpStr.append("..#..");
							}
							
						}
					}
					break;
				case 1 :
					for (int j = 1; j <= str.length(); j++) {
						if(str.length()>1 && j < str.length()) {
							if(j%3==0) {
								tmpStr.append(".*.*");
							}else {
								tmpStr.append(".#.#");
							}
						}else {
							if(j%3==0) {
								tmpStr.append(".*.*.");
							}else {
								tmpStr.append(".#.#.");
							}
						}
						
					}
					break;
				case 2 :
					for (int j = 1; j <= str.length(); j++) {
						if(str.length()>1 && j < str.length()) {
							if(j%3==0) {
								tmpStr.append("*."+str.charAt(j-1)+".");
							}else if(j>3 && j%3==1){
								tmpStr.append("*."+str.charAt(j-1)+".");
							}else {
								tmpStr.append("#."+str.charAt(j-1)+".");
							}
						}else {
							if(j%3==0) {
								tmpStr.append("*."+str.charAt(j-1)+".*");
							}else if(j>3 && j%3==1){
								tmpStr.append("*."+str.charAt(j-1)+".#");
							}else {
								tmpStr.append("#."+str.charAt(j-1)+".#");
							}
						}
						
					}
					break;
				case 3 :
					for (int j = 1; j <= str.length(); j++) {
						if(str.length()>1 && j < str.length()) {
							
							if(j%3==0) {
								tmpStr.append(".*.*");
							}else {
								tmpStr.append(".#.#");
							}
						}else {
							if(j%3==0) {
								tmpStr.append(".*.*.");
							}else {
								tmpStr.append(".#.#.");
							}
						}
					}
					break;
				case 4 :
					for (int j = 1; j <= str.length(); j++) {
						if(str.length()>1 && j < str.length()) {
							if(j%3==0) {
								tmpStr.append("..*.");
							}else {
								tmpStr.append("..#.");
							}
							
						}else {
							if(j%3==0) {
								tmpStr.append("..*..");
							}else {
								tmpStr.append("..#..");
							}
						}
					}
					break;
				default:
					break;
			}
			tmpStr.append("\n");
		}
		return tmpStr.toString();
	}

}
