package week3;

public class 신규아이디추천 {
    private String result;

    public String solution(String new_id) {
        this.result = new_id;
        step1();
        step2();
        step3();
        step4();
        step5();
        step6();
        step7();

        return result;
    }
    // 1단계 new_id의 모든 대문자를 대응되는 소문자로 치환합니다.
    public void step1() {
        result = result.toLowerCase();
    }
    // 2단계 new_id에서 알파벳 소문자, 숫자, 빼기(-), 밑줄(_), 마침표(.)를 제외한 모든 문자를 제거합니다.
    public void step2() {
        StringBuilder sd = new StringBuilder();
        for (int i = 0; i < result.length(); i++) {
            char c = result.charAt(i);

            if (('a' <= c && 'z' >= c) || ('0' <= c && '9' >= c) || c == '-' || c == '_' || c == '.')
                sd.append(Character.toString(c));
        }
        result = sd.toString();
    }
    // 3단계 new_id에서 마침표(.)가 2번 이상 연속된 부분을 하나의 마침표(.)로 치환합니다.
    public void step3() {
        StringBuilder sd = new StringBuilder();
        sd.append(result.charAt(0));
        char before = result.charAt(0);

        for (int i = 1; i < result.length(); i++) {
            char c = result.charAt(i);
            if (before == '.' && c == '.') continue;
            else {
                sd.append(c);
                before = c;
            }
        }
        result = sd.toString();
    }
    // 4단계 new_id에서 마침표(.)가 처음이나 끝에 위치한다면 제거합니다.
    public void step4() {
        int start = 0;
        int end = result.length() - 1;
        if (result.charAt(start) == '.' || result.charAt(end) == '.') {
            StringBuilder sd = new StringBuilder();

            for (int i = start; i <= end; i++) {
                if (i == start && result.charAt(start) == '.') continue;
                else if (i == end && result.charAt(end) == '.') continue;
                else sd.append(result.charAt(i));
            }
            result = sd.toString();
        }
    }
    // 5단계 new_id가 빈 문자열이라면, new_id에 "a"를 대입합니다.
    public void step5() {
        if (result.length() == 0)
            result = "a";
    }
    // 6단계 new_id의 길이가 16자 이상이면, new_id의 첫 15개의 문자를 제외한 나머지 문자들을 모두 제거합니다.
    // 만약 제거 후 마침표(.)가 new_id의 끝에 위치한다면 끝에 위치한 마침표(.) 문자를 제거합니다.
    public void step6() {
        int length = result.length();
        if (length >= 16) {
            StringBuilder sd = new StringBuilder();

            for (int i = 0; i < 15; i++) {
                char c = result.charAt(i);
                sd.append(c);
            }
            result = sd.toString();
        }
        step4();
    }
    // 7단계 new_id의 길이가 2자 이하라면, new_id의 마지막 문자를 new_id의 길이가 3이 될 때까지 반복해서 끝에 붙입니다.
    public void step7() {
        while (result.length() <= 2)
            result += result.charAt(result.length() - 1);
    }
}