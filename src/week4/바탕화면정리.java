package week4;
public class 바탕화면정리 {
    public int[] solution(String[] wallpaper) {
        int result[] = {51, 51, 0, 0};

        for (int i = 0; i < wallpaper.length; i++) {
            for (int j = 0; j < wallpaper[i].length(); j++) {
                char c = wallpaper[i].charAt(j);

                if (c == '#') {
                    result[0] = Math.min(result[0], i); // 가장 상단에 위치한 파일
                    result[1] = Math.min(result[1], j); // 가장 좌측에 위치한 파일
                    result[2] = Math.max(result[2], i + 1); // 가장 하단에 위치한 파일
                    result[3] = Math.max(result[3], j + 1); // 가장 우측에 위치한 파일
                }
            }
        }
        return result;
    }
}