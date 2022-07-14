package com.bj.ex3055;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	static int ROWS = 0;
	static int COLS = 0;
	static int runCnt = 0;
	static String msg = "";
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		String input = br.readLine();
		ROWS = Integer.parseInt(input.substring(0, input.indexOf(" ")));
		COLS = Integer.parseInt(input.substring(input.indexOf(" ")+1,input.length()));
		
		char [][] map = new char[ROWS][COLS];
		for(int i = 0 ; i < ROWS ; i++) {
			input = br.readLine();
			for(int j = 0 ; j < COLS ; j++) {
				map[i][j]= input.charAt(j);			
			}
		}
		br.close();
	
		int[] dd = new int[2];
		int[] ss = new int[2];
		for(int i = 0 ; i < ROWS ; i++) {
			for(int j = 0 ; j < COLS ; j++) {
				if(map[i][j] =='D') {dd[0]=i;dd[1]=j;}
				if(map[i][j] =='S') {ss[0]=i;ss[1]=j;}
			}
		
		}
		for (int i = 0; i < 2500; i++) {
			runCnt++;
			map = moving(map);
			if(null == map) {
				if("OVER"==msg) {
					System.out.println("KAKTUS");
					break;
				}else {
					System.out.println(runCnt);
				}
			}
		}
	}

	
	private static char[][] moving(char [][] map){
		//현재 자신의 좌표를 던져서 1분뒤 map에 적용하는 함수
		char [][] tmpMap = new char[ROWS][COLS];
		int[]dd = new int[2];
		int[]ss = new int[2];
		int[]xx = new int[ROWS*COLS-2];
		int xCnt = 0;
		//깊은 복사 && 주요 물체 마킹
		for (int i = 0; i < ROWS; i++) {
			for (int j = 0; j < COLS; j++) {
				if(map[i][j] =='D') {dd[0]=i;dd[1]=j;}
				if(map[i][j] =='S') {ss[0]=i;ss[1]=j;}
				if(map[i][j] =='X') {xx[xCnt++]=i;xx[xCnt++]=j;}
				tmpMap[i][j] = map[i][j];
			}
		}
		
		//집에 인접했나 판단
		int distance = 0;
		if(dd[0] >= ss[0]) {
			distance = dd[0] - ss[0];
		}else {
			distance = ss[0] - dd[0];
		}
		if(dd[1] >= ss[1]) {
			distance = distance + dd[1] - ss[1];
		}else {
			distance = distance + ss[1] - dd[1];
		}
		if(distance ==1) {
			msg = "SUCCESS";
			return null;
		}
		//방향설정
		
		
		
		//물이 옆칸으로 이동 가능한지 판단 후 이동
		for(int i = 0 ; i < ROWS ; i++) {
			for(int j = 0 ; j < COLS ; j++) {
				if(map[i][j] =='*') {
					//행이동(위,아래)
					if(i > 0 && map[i-1][j] =='.') {
						tmpMap[i-1][j] = '*';
					}
					if(i < ROWS-1 && map[i+1][j] =='.') {
						tmpMap[i+1][j] = '*';
					}
					//행이동(좌,우)
					if(j > 0 && map[i][j-1] =='.') {
						tmpMap[i][j-1] = '*';
					}
					if(j < COLS-1 && map[i][j+1] =='.') {
						tmpMap[i][j+1] = '*';
					}
				}
			}
		}
		//두더지가 옆칸으로 이동 가능한지 판단 후 이동
		int mvCnt = 0;
		boolean rChk = false;
		for(int i = 0 ; i < ROWS ; i++) {
			for(int j = 0 ; j < COLS ; j++) {				
				if(tmpMap[i][j] =='S') {
					if(dd[0]>i) {//두더지보다 집이 아래에 있나?
						for(int k =i; k < ROWS ; k++) {
							if(tmpMap[k][j] == '*' || tmpMap[k][j] == 'X') {
								rChk = false;
								break;
							}else rChk = true;
						}						
					}
					if(dd[0]<i) {//두더지보다 집이 위에 있나?
						for(int k =0; k < i ; k++) {
							if(tmpMap[k][j] == '*' || tmpMap[k][j] == 'X') {
								rChk = false;
								break;
							}else rChk = true;
						}						
					}
					if(rChk ==true && dd[0]>i && map[i+1][j] =='.' && mvCnt==0) {//두더지보다 집이 아래에 있고 이동가능?
						tmpMap[i+1][j] = 'S';
						tmpMap[i][j] = '.';
						mvCnt ++;
					}
					if(rChk ==true && dd[0]<i && map[i-1][j] =='.'&& mvCnt==0) {//두더지보다 집이 위에 있고 이동가능?
						tmpMap[i-1][j] = 'S';
						tmpMap[i][j] = '.';
						mvCnt ++;
					}
					
					if(dd[1]<j && map[i][j-1] =='.'&& mvCnt==0 ) {//두더지보다 집이 왼쪽에 있고 이동가능?
						tmpMap[i][j-1] = 'S';
						tmpMap[i][j] = '.';
						mvCnt ++;
					}else if(dd[1]<j && map[i][j-1] =='X'&& mvCnt==0 ) {
						
					}
					if( dd[1]>j && map[i][j+1] =='.' && mvCnt==0) {//두더지보다 집이 오른쪽에 있고 이동가능?
						tmpMap[i][j+1] = 'S';
						tmpMap[i][j] = '.';
						mvCnt ++;
					}

				}
			}
		}
		if(mvCnt ==0) {
			msg = "OVER";
			return null;
		}
		
		printMap(tmpMap);
		return tmpMap;
	}
	
	private static void printMap(char [][] map) {
		StringBuilder sb = new StringBuilder();
		for(int i = 0 ; i < ROWS ; i++) {
			for(int j = 0 ; j < COLS ; j++) {
				sb.append(map[i][j]);
			}
			sb.append("\n");
		}
		System.out.println(sb.toString());
	}
}
