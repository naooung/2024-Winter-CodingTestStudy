package week1;

public class 최대공약수와최소공배수 {
    public int[] solution(int n, int m) {
        int[] answer = new int[2];

        // 최대공약수를 answer[0]에 저장
        int max = 0;
        for (int i = 1; i <= Math.max(n, m); i++) {
            if ((n % i == 0) && (m % i == 0))
                max = i;
        }
        answer[0] = max;

        // 최소공배수를 answer[1]에 저장
        answer[1] = n * m / max;

        return answer;
    }
}
