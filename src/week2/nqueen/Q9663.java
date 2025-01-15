package week2.nqueen;

import java.util.Scanner;
/* Key Point
chess 배열의  
 */

public class Q9663 {
    private static int count;
    private static int[] chess;
    private static int n;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        n = scanner.nextInt();
        chess = new int[n];
        count = 0;

        nQueen(0);
        System.out.println(count);
    }

    public static void nQueen(int index) {
        if (index == n) {
            count++;
            return;
        }

        for (int i = 0; i < n; i++) { // 열 순회
            chess[index] = i;
            if (canMove(index) == true) {
                nQueen(index + 1);
            }
        }
    }

    public static boolean canMove(int index) {
        for (int i = 0; i < index; i++) {
            if (chess[i] == chess[index] || Math.abs(i - index) == Math.abs(chess[index] - chess[i]))
                return false;
        }
        return true;
    }
}
