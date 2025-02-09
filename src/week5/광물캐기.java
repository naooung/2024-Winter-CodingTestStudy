package week5;
// 재귀 범위 수정하기 -> 광물 캐는건 재귀가 아니라 단순 반복문으로 처리

import java.util.*;
public class 광물캐기 {
    private int answer;

    public int solution(int[] picks, String[] minerals) {
        answer = Integer.MAX_VALUE;
        ArrayDeque<String> mineralList = new ArrayDeque<>();
        for (String mineral : minerals)
            mineralList.addLast(mineral);

        backTracking(0, picks, mineralList, 0, "");

        return answer;
    }

    public void backTracking(int tired, int[] canUse, ArrayDeque<String> minerals, int index, String pick) {

        // 종료 조건: 광산에 있는 모든 광물을 캐거나 || 더이상 사용할 곡괭이가 없을 때
        if (minerals.isEmpty()) {
            answer = Math.min(answer, tired);
            return;
        }

        // 순서가 처음 혹은 광물 5개를 캔 후라면, 곡괭이를 선택하여 재귀호출
        if (index == 0 || index == 6) {
            if (canUse[0] == 0 && canUse[1] == 0 && canUse[2] == 0) { // 더 이상 선택할 곡괭이가 없을 때는 종료
                answer = Math.min(answer, tired);
                return;
            }
            if (canUse[0] - 1 >= 0) { // dia 선택
                int[] currentCanUse = {canUse[0] - 1, canUse[1], canUse[2]};
                backTracking(tired, currentCanUse, minerals, 1, "diamond");
            }
            if (canUse[1] - 1 >= 0) { // iron 선택
                int[] currentCanUse = {canUse[0], canUse[1] - 1, canUse[2]};
                backTracking(tired, currentCanUse, minerals, 1, "iron");
            }
            if (canUse[2] - 1 >= 0) { // stone 선택
                int[] currentCanUse = {canUse[0], canUse[1], canUse[2] - 1};
                backTracking(tired, currentCanUse, minerals, 1, "stone");
            }
        }

        // 광물 연속 5개 캐는 과정 진행중이라면, 광물을 캐고 재귀호출
        else {
            String currentMineral = minerals.pollFirst();
            backTracking(tired + calcTired(pick, currentMineral), canUse, minerals, index + 1, pick);
            minerals.addFirst(currentMineral);
        }
    }

    // pick로 mineral을 캘 때 사용하는 피로도를 리턴 
    public int calcTired(String pick, String mineral) {
        if (pick.equals("stone")) {
            if (mineral.equals("diamond"))
                return 25;
            else if (mineral.equals("iron"))
                return 5;
            else
                return 1;
        }
        else if (pick.equals("iron")) {
            if (mineral.equals("diamond"))
                return 5;
            else
                return 1;
        }
        else
            return 1;
    }
}