package com.KaminnoYoruneko.lots_of_food_reborn.notImportant;

import java.util.Scanner;

public class genLeavesCode {
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        String AIR="A";
        String LEAVE="B";
        int size=7;
        int heigt=3;

        String prefix="LeavesCoords.add(InitialPos";
        String subfix=");";
        String goDown=".below()",
                goUp=".above()",
                goWest=".west()",
                goSouth=".south()",
                goEast=".east()",
                goNorth=".north()";

        int center=(size-1)/2;

        boolean[][][] leaves=new boolean[heigt][size][size];

        //中心为leaves[0][center][center];

        //从上到下输入

        for (int i = 0; i < heigt; i++) {
            for (int j = 0; j < size; j++) {
                String temp=sc.nextLine();
                for (int k = 0; k < size; k++) {
//                    System.out.println(
//                            "input==>i="+i+", j=" +
//                            j+", k="+k+" "+temp.charAt(k) +
//                            " isLeaf="+LEAVE.equals(""+temp.charAt(k))
//                    );
                    leaves[i][j][k]= LEAVE.equals("" + temp.charAt(k));
                }
            }
            String line=sc.nextLine();
            //吸收每次多出来的回车空行
        }

        String method="";

        for (int i = 0; i < heigt; i++) {
            for (int j = 0; j < size; j++) {
                for (int k = 0; k < size; k++) {
                    method="";
//                    System.out.println("process==>i="+i+", j="+j+", k="+k+" "+leaves[i][j][k]);
                    if (!leaves[i][j][k]){//不是树叶就清空method并直接下一个循环
                        continue;
                    }
                    //先算高度
                    int hDist=i-0;//有多少就代表需要向下多少
                    for (int l = 0; l < hDist; l++) {
                        method+=goDown;
                    }
                    //再算南北
                    //也就是纵向vertical
                    int vDist=j-center;//小于0就是北，反之则南
                    boolean isNorth=vDist<0;
                    int vAbsDist=vDist*(isNorth?-1:1);
                    for (int l = 0; l < vAbsDist; l++) {
                        method+=isNorth?goNorth:goSouth;
                    }
                    //再算东西
                    //也就是横向horizontal
                    int horizDist=k-center;//小于0就是西，反之则东
                    boolean isWest=horizDist<0;
                    int hAbsDist=horizDist*(isWest?-1:1);
                    for (int l = 0; l < hAbsDist; l++) {
                        method+=isWest?goWest:goEast;
                    }

                    System.out.println(prefix+method+subfix);
                }
            }
        }
    }
    /*
AAAAAAA
AAABAAA
AABBBAA
ABBBBBA
AABBBAA
AAABAAA
AAAAAAA

AAABAAA
ABBBBBA
ABBABBA
BBAAABB
ABBABBA
ABBBBBA
AAABAAA

AAABAAA
ABAAABA
AAAAAAA
BAAAAAB
AAAAAAA
ABAAABA
AAABAAA
    */

    /*
    口口口口口口口
口口口因口口口
口口因因因口口
口因因因因因口
口口因因因口口
口口口因口口口
口口口口口口口

口口口因口口口
口因因因因因口
口因因口因因口
因因口口口因因
口因因口因因口
口因因因因因口
口口口因口口口

口口口因口口口
口因口口口因口
口口口口口口口
因口口口口口因
口口口口口口口
口因口口口因口
口口口因口口口
    */
}
