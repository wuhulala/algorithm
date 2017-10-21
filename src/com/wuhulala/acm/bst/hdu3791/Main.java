package com.wuhulala.acm.bst.hdu3791;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int pos[] = new int[2000];
        int curPos[] = new int[2000];
        while (sc.hasNext()) {
            int n = sc.nextInt();
            if (n == 0) {
                break;
            }
            String origin = sc.next();
            clear(pos);
            handlePosition(pos, origin);
            while (n-- > 0) {
                clear(curPos);
                String cur = sc.next();
                handlePosition(curPos, cur);
                if (same(pos, curPos)) {
                    System.out.println("YES");
                } else {
                    System.out.println("NO");
                }
            }
        }
    }

    private static void clear(int[] pos) {
        for (int i = 0; i < pos.length; i++) {
            pos[i] = -1;
        }
    }

    private static boolean same(int[] pos, int[] curPos) {
        for (int i = 0; i < 2000; i++) {
            if (curPos[i] != pos[i]) {
                return false;
            }
        }
        return true;
    }

    private static void handlePosition(int[] pos, String origin) {
        int originValue = Character.getNumericValue(origin.charAt(0));
        pos[1] = originValue;
        for (int i = 1; i < origin.length(); i++) {
            int curValue = Character.getNumericValue(origin.charAt(i));
            insertNode(curValue, pos);
        }
    }

    private static void insertNode(int curValue, int[] pos) {
        int cur = 1;
        while (pos[cur] != -1) {
            if (curValue > pos[cur]) {
                cur = cur * 2 + 1;
            } else {
                cur = cur * 2;
            }
        }
        pos[cur] = curValue;
    }
}
