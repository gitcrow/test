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
		
		char [][] map1 = new char[ROWS][COLS];
		char [][] map2 = new char[ROWS][COLS];
		char [][] map3 = new char[ROWS][COLS];
		char [][] map4 = new char[ROWS][COLS];
		for(int i = 0 ; i < ROWS ; i++) {
			input = br.readLine();
			for(int j = 0 ; j < COLS ; j++) {
				map1[i][j]= input.charAt(j);
				map2[i][j]= input.charAt(j);
				map3[i][j]= input.charAt(j);
				map4[i][j]= input.charAt(j);
			}
		}
		br.close();
	
		int rslt1 = 0;
		int rslt2 = 0;
		int rslt3 = 0;
		int rslt4 = 0;
		for (int i = 0; i < 2500; i++) {
			runCnt++;
			map1 = moving(map1,"ROW_LEFT");
			if(null == map1) {
				if("OVER"==msg) {
					System.out.println("left : KAKTUS");
					rslt1 = 0;
					break;
				}else {
					System.out.println("left : "+ runCnt);
					rslt1 = runCnt;
					break;
				}
			}
		}
		
		runCnt=0;
		msg="";
		for (int i = 0; i < 2500; i++) {
			runCnt++;
			map2 = moving(map2,"ROW_RIGHT");
			if(null == map2) {
				if("OVER"==msg) {
					System.out.println("right : KAKTUS");
					rslt2 = 0;
					break;
				}else {
					System.out.println("right : "+ runCnt);
					rslt2 = runCnt;
					break;
				}
			}
		}
		runCnt=0;
		msg="";
		for (int i = 0; i < 2500; i++) {
			runCnt++;
			map3 = moving(map3,"COL_UP");
			if(null == map3) {
				if("OVER"==msg) {
					System.out.println("up : KAKTUS");
					rslt3 = 0;
					break;
				}else {
					System.out.println("up : "+ runCnt);
					rslt3 = runCnt;
					break;
				}
			}
		}
		runCnt=0;
		msg="";
		for (int i = 0; i < 2500; i++) {
			runCnt++;
			map4 = moving(map4,"COL_DOWN");
			if(null == map4) {
				if("OVER"==msg) {
					System.out.println("down : KAKTUS");
					rslt4 = 0;
					break;
				}else {
					System.out.println("down : "+ runCnt);
					rslt4 = runCnt;
					break;
				}
			}
		}
		
		int[] rsltArr = {rslt1,rslt2,rslt3,rslt4};
		int min = 9999;
		for(int i=0; i<4 ; i++) {
			if(rsltArr[i] != 0 && rsltArr[i] < min) min = rsltArr[i];
		}
		if(min==9999) {
			System.out.println("KAKTUS"); 
		}
		System.out.println(min);
		
	}
	
	private static char[][] moving(char [][] map, String direction){
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
	
		
		//물이 옆칸으로 이동 가능한지 판단 후 이동
		for(int i = 0 ; i < ROWS ; i++) {
			for(int j = 0 ; j < COLS ; j++) {
				if(map[i][j] =='*') {
					//행이동(위,아래)
					if(i > 0 && (map[i-1][j] =='.' || map[i-1][j] =='Z')) {
						tmpMap[i-1][j] = '*';
					}
					if(i < ROWS-1 && (map[i+1][j] =='.' || map[i+1][j] =='Z')) {
						tmpMap[i+1][j] = '*';
					}
					//행이동(좌,우)
					if(j > 0 && (map[i][j-1] =='.' || map[i][j-1] =='Z')) {
						tmpMap[i][j-1] = '*';
					}
					if(j < COLS-1 && (map[i][j+1] =='.' || map[i][j+1] =='Z')) {
						tmpMap[i][j+1] = '*';
					}
				}
			}
		}
		//두더지가 옆칸으로 이동 가능한지 판단 후 이동
		int mvCnt = 0;
		boolean rChk = false;
		boolean cChk = false;
		for(int i = 0 ; i < ROWS ; i++) {
			for(int j = 0 ; j < COLS ; j++) {				
				if(tmpMap[i][j] =='S') {
					if("ROW".equals(direction.split("_")[0])) {
						if(dd[0]>i) {//두더지보다 집이 아래에 있나? 아래에 장애물있으면 false
							//for(int k =i; k < ROWS ; k++) {
								if(tmpMap[(i+1)==ROWS?ROWS-1:i+1][j] == '*' || tmpMap[(i+1)==ROWS?ROWS-1:i+1][j] == 'X') {
									rChk = false;
								}else rChk = true;
							//}						
						}
						if(dd[0]<i) {//두더지보다 집이 위에 있나? 위에 장애물있으면 false
							//for(int k =0; k < i ; k++) {
								if(tmpMap[(i-1)==0?0:i-1][j] == '*' || tmpMap[(i-1)==0?0:i-1][j] == 'X') {
									rChk = false;
								}else rChk = true;
							//}						
						}//이동우선순위 아래 위 (왼쪽 오른쪽) (오른쪽 왼쪽)
						if(rChk ==true && dd[0]>i && tmpMap[i+1][j] =='.' && mvCnt==0) {//두더지보다 집이 아래에 있고 이동가능?
							tmpMap[i+1][j] = 'S';
							tmpMap[i][j] = 'Z';
							mvCnt ++;
						}
						if(rChk ==true && dd[0]<i && tmpMap[i-1][j] =='.'&& mvCnt==0) {//두더지보다 집이 위에 있고 이동가능?
							tmpMap[i-1][j] = 'S';
							tmpMap[i][j] = 'Z';
							mvCnt ++;
						}
						if("LEFT".equals(direction.split("_")[1]) && mvCnt==0 ) {
							if(tmpMap[i][(j-1)==-1?0:j-1] =='.') {// 왼쪽 이동가능?
								tmpMap[i][j-1] = 'S';
								tmpMap[i][j] = 'Z';
								mvCnt ++;
							}
							if(tmpMap[i][(j+1)==COLS?COLS-1:j+1] =='.'&& mvCnt==0) {// 오른쪽 이동가능?
								tmpMap[i][j+1] = 'S';
								tmpMap[i][j] = 'Z';
								mvCnt ++;
							}
						}
						if("RIGHT".equals(direction.split("_")[1]) && mvCnt==0) {
							if(tmpMap[i][(j+1)==COLS?COLS-1:j+1] =='.') {//오른쪽 이동가능?
								tmpMap[i][j+1] = 'S';
								tmpMap[i][j] = 'Z';
								mvCnt ++;
							}
							if(tmpMap[i][(j-1)==-1?0:j-1] =='.'&& mvCnt==0) {//왼쪽 이동가능?
								tmpMap[i][j-1] = 'S';
								tmpMap[i][j] = 'Z';
								mvCnt ++;
							}
						}
					}
					if("COL".equals(direction.split("_")[0])) {
						if(dd[1]>j) {
							//for(int k =i; k < ROWS ; k++) {
								if(tmpMap[i][(j+1)==COLS?COLS-1:j+1] == '*' || tmpMap[i][(j+1)==COLS?COLS-1:j+1] == 'X') {
									cChk = false;
								}else cChk = true;
							//}						
						}
						if(dd[1]<j) {
							//for(int k =0; k < i ; k++) {
								if(tmpMap[i][(j-1)==0?0:j-1] == '*' || tmpMap[i][(j-1)==0?0:j-1] == 'X') {
									cChk = false;
								}else cChk = true;
							//}						
						}
						if(cChk ==true && dd[1]>j && tmpMap[i][j+1] =='.' && mvCnt==0) {//두더지보다 집이 아래에 있고 이동가능?
							tmpMap[i][j+1] = 'S';
							tmpMap[i][j] = 'Z';
							mvCnt ++;
						}
						if(cChk ==true && dd[1]<j && tmpMap[i][j-1] =='.'&& mvCnt==0) {//두더지보다 집이 위에 있고 이동가능?
							tmpMap[i][j-1] = 'S';
							tmpMap[i][j] = 'Z';
							mvCnt ++;
						}
						if("UP".equals(direction.split("_")[1]) && mvCnt==0) {
							if(tmpMap[(i-1)==-1?0:i-1][j] =='.') {
								tmpMap[i-1][j] = 'S';
								tmpMap[i][j] = 'Z';
								mvCnt ++;
							}
							if(tmpMap[(i+1)==ROWS?ROWS-1:i+1][j] =='.'&& mvCnt==0) {
								tmpMap[i+1][j] = 'S';
								tmpMap[i][j] = 'Z';
								mvCnt ++;
							}
						}else if("DOWN".equals(direction.split("_")[1]) && mvCnt==0) {
							if(tmpMap[(i+1)==ROWS?ROWS-1:i+1][j] =='.') {
								tmpMap[i+1][j] = 'S';
								tmpMap[i][j] = 'Z';
								mvCnt ++;
							}
							if(tmpMap[(i-1)==-1?0:i-1][j] =='.'&& mvCnt==0) {
								tmpMap[i-1][j] = 'S';
								tmpMap[i][j] = 'Z';
								mvCnt ++;
							}
						}
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
