package week2;

import java.util.HashMap;

public class 성격유형검사하기 {
    public String solution(String[] survey, int[] choices) {
        String[][] types = {{"R", "T"}, {"C", "F"}, {"J", "M"}, {"A", "N"}};
        HashMap<String, Integer> score = new HashMap<>();
        for (int i = 0; i < 4; i++)
            for (int j = 0; j < 2; j++)
                score.put(types[i][j], 0);

        // 점수 계산
        for (int i = 0; i < choices.length; i++) {
            int choice = choices[i];

            if (choice < 4) {
                String s = Character.toString(survey[i].charAt(0));
                score.replace(s, score.get(s) + (4 - choice));
            }
            else if (choice > 4) {
                String s = Character.toString(survey[i].charAt(1));
                score.replace(s, score.get(s) + (choice - 4));
            }
        }

        // 결과 도출
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < types.length; i++) {
            String s1 = types[i][0];
            String s2 = types[i][1];

            if (score.get(s1) < score.get(s2))
                sb.append(s2);
            else
                sb.append(s1);
        }

        return sb.toString();
    }
}
