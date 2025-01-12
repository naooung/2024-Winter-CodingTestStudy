package week2;

public class 옹알이2 {
    public int solution(String[] babbling) {
        int result = 0;
        String[] possible = {"aya", "ye", "woo", "ma"};

        for (int i = 0; i < babbling.length; i++) {
            int used = -1; // 이미 발음한 인덱스 저장
            StringBuilder sd = new StringBuilder();

            for (int j = 0; j < babbling[i].length(); j++) {
                sd.append(babbling[i].charAt(j));

                for (int k = 0; k < possible.length; k++) {
                    if (sd.toString().equals(possible[k]) && (k != used)) {
                        used = k;
                        sd = new StringBuilder();
                    }
                }
            }

            if (sd.toString().length() == 0) {
                result++;
            }
        }

        return result;
    }
}
