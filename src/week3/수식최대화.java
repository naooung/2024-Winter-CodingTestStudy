package week3;

import java.util.ArrayList;

public class 수식최대화 {
    long maxResult = 0;
    char operator[] = {'+', '-', '*'};

    public long solution(String expression) {
        ArrayList<Long> numbers = new ArrayList<>();
        ArrayList<Character> operators = new ArrayList<>();
        StringBuilder sd = new StringBuilder();

        // 1. 숫자와 문자 분리
        for (int i = 0; i < expression.length(); i++) {
            char c = expression.charAt(i);

            if (c == '+' || c == '-' || c == '*') {
                numbers.add(Long.valueOf(sd.toString()));
                sd = new StringBuilder();
                operators.add(c);
            } else
                sd.append(Character.toString(c));
        }
        numbers.add(Long.valueOf(sd.toString()));

        // 2. dfs 실행
        dfs(new ArrayList<>(numbers), new ArrayList<>(operators), new ArrayList<Character>());

        return maxResult;
    }

    // 사용하지 않은 연산자를 사용하여 계산하고 사용하지 않은 연산자를 갱신하여 재귀호출하는 함수
    public void dfs(ArrayList<Long> numbers, ArrayList<Character> operators, ArrayList<Character> used) {

        if (operators.isEmpty()) {
            maxResult = Math.max(maxResult, Math.abs(numbers.get(0))); // 다른 방법의 결과와 이번에 계산된 수 비교
            return;
        }

        for (char op : operator) {
            if (!used.contains(op)) { // 사용하지 않은 연산자만 사용
                ArrayList<Long> newNumbers = new ArrayList<>(numbers);
                ArrayList<Character> newOperators = new ArrayList<>(operators);

                for (int i = 0; i < newOperators.size(); i++) {
                    if (newOperators.get(i) == op) {
                        long result = calc(newNumbers.get(i), newNumbers.get(i + 1), op);
                        newNumbers.remove(i + 1);
                        newOperators.remove(i);
                        newNumbers.set(i, result);
                        i--;
                    }
                }
                ArrayList<Character> newUsed = new ArrayList<>(used);
                newUsed.add(op);

                dfs(newNumbers, newOperators, newUsed);
            }
        }
    }

    // 인자로 받은 연산자로 값을 계산하는 함수
    public long calc(long a, long b, char op) {
        switch (op) {
            case '+': return a + b;
            case '-': return a - b;
            default: return a * b;
        }
    }
}
