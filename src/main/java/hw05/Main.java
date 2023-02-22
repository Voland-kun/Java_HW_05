package hw05;

import java.util.LinkedList;

public class Main {
    public static int startX = 27;
    public static int startY = 1;
    public static int finishX = 1;
    public static int finishY = 27;

    public static void main(String[] args) {
        int[][] map = getMap();
        start(map, startX, startY);
        finish(map, finishX, finishY);
        System.out.println("Лабиринт");
        pprint(map);
        wave(map);
        getPath(map);
        System.out.println("Путь");
        pprint(map);
    }
    static int[][] getMap() {
        return new int[][]{
                {-2,-2,-2,-2,-2,-2,-2,-2,-2,-2,-2,-2,-2,-2,-2,-2,-2,-2,-2,-2,-2,-2,-2,-2,-2,-2,-2,-2,-2},
                {-2,-1,-1,-1,-1,-1,-2,-1,-1,-1,-2,-1,-2,-1,-2,-1,-1,-1,-2,-1,-1,-1,-1,-1,-1,-1,-1,-1,-2},
                {-2,-2,-2,-2,-1,-1,-2,-1,-2,-1,-2,-1,-1,-1,-2,-1,-1,-1,-2,-1,-2,-2,-2,-1,-2,-2,-2,-2,-2},
                {-2,-1,-1,-2,-2,-1,-1,-1,-2,-1,-2,-1,-2,-2,-2,-1,-2,-1,-1,-1,-2,-1,-1,-1,-1,-1,-1,-1,-2},
                {-2,-1,-2,-2,-1,-1,-2,-2,-2,-2,-1,-1,-2,-1,-1,-1,-2,-2,-2,-2,-2,-2,-2,-2,-2,-2,-1,-2,-2},
                {-2,-1,-2,-1,-1,-1,-2,-1,-2,-1,-1,-2,-2,-2,-2,-1,-1,-2,-1,-1,-2,-1,-1,-1,-2,-1,-1,-1,-2},
                {-2,-1,-2,-2,-2,-1,-1,-1,-2,-2,-1,-1,-1,-1,-2,-2,-1,-2,-2,-1,-2,-1,-2,-1,-2,-1,-2,-1,-2},
                {-2,-1,-1,-1,-1,-1,-1,-1,-1,-2,-1,-1,-1,-2,-2,-1,-1,-1,-1,-1,-2,-1,-2,-1,-1,-1,-2,-1,-2},
                {-2,-2,-1,-2,-2,-1,-2,-2,-1,-1,-1,-2,-1,-1,-1,-1,-2,-1,-2,-2,-2,-2,-2,-1,-2,-2,-2,-1,-2},
                {-2,-1,-1,-1,-2,-2,-1,-2,-1,-2,-2,-2,-2,-2,-2,-2,-2,-1,-1,-2,-1,-2,-1,-1,-1,-2,-1,-1,-2},
                {-2,-2,-1,-2,-2,-1,-1,-2,-1,-1,-1,-1,-2,-1,-1,-1,-2,-1,-2,-2,-1,-2,-2,-2,-1,-2,-2,-1,-2},
                {-2,-1,-1,-2,-1,-1,-2,-2,-2,-2,-1,-2,-2,-2,-1,-1,-1,-1,-1,-1,-1,-2,-1,-1,-1,-1,-2,-1,-2},
                {-2,-1,-1,-1,-1,-1,-1,-1,-1,-2,-1,-2,-1,-2,-2,-1,-1,-1,-2,-1,-2,-2,-1,-1,-2,-2,-2,-1,-2},
                {-2,-2,-2,-2,-2,-1,-2,-2,-2,-2,-1,-2,-1,-1,-2,-2,-2,-1,-2,-2,-2,-1,-1,-2,-2,-1,-2,-1,-2},
                {-2,-1,-1,-1,-1,-1,-2,-1,-1,-1,-1,-1,-1,-2,-2,-1,-1,-1,-1,-2,-1,-1,-2,-2,-1,-1,-1,-1,-2},
                {-2,-1,-2,-2,-1,-2,-2,-2,-2,-2,-2,-1,-1,-1,-2,-1,-1,-2,-2,-2,-1,-1,-1,-2,-2,-2,-2,-2,-2},
                {-2,-1,-1,-2,-1,-1,-1,-1,-1,-1,-2,-1,-1,-2,-2,-1,-1,-2,-1,-2,-2,-1,-1,-1,-2,-1,-1,-1,-2},
                {-2,-2,-2,-2,-2,-2,-1,-2,-2,-2,-2,-2,-1,-2,-1,-1,-2,-2,-1,-1,-2,-2,-1,-1,-2,-2,-1,-1,-2},
                {-2,-1,-1,-1,-1,-2,-1,-1,-1,-1,-1,-2,-1,-2,-1,-1,-1,-2,-1,-1,-1,-2,-2,-1,-1,-2,-2,-1,-2},
                {-2,-1,-1,-1,-1,-2,-1,-2,-2,-2,-2,-2,-2,-2,-2,-1,-2,-2,-1,-1,-1,-1,-2,-2,-1,-1,-2,-1,-2},
                {-2,-2,-2,-2,-1,-2,-1,-2,-1,-1,-1,-2,-1,-1,-1,-1,-2,-1,-1,-2,-2,-1,-1,-2,-2,-1,-1,-1,-2},
                {-2,-1,-1,-1,-1,-2,-1,-2,-1,-2,-2,-2,-2,-2,-2,-1,-2,-2,-2,-2,-1,-1,-1,-1,-2,-2,-1,-1,-2},
                {-2,-1,-1,-2,-1,-1,-1,-2,-1,-1,-1,-2,-1,-1,-2,-1,-1,-1,-1,-1,-1,-2,-2,-1,-1,-2,-2,-1,-2},
                {-2,-1,-1,-2,-2,-2,-2,-2,-2,-1,-2,-2,-1,-2,-2,-1,-2,-2,-2,-1,-1,-2,-1,-1,-1,-1,-2,-2,-2},
                {-2,-1,-1,-1,-1,-1,-1,-1,-2,-1,-1,-1,-1,-1,-2,-1,-2,-1,-2,-2,-2,-2,-2,-1,-1,-2,-2,-1,-2},
                {-2,-2,-2,-2,-2,-1,-2,-1,-2,-1,-2,-1,-2,-1,-2,-1,-2,-1,-2,-1,-1,-2,-1,-1,-2,-2,-1,-1,-2},
                {-2,-1,-1,-1,-1,-1,-2,-1,-2,-1,-2,-1,-2,-1,-2,-1,-2,-1,-2,-2,-1,-2,-2,-1,-1,-1,-1,-1,-2},
                {-2,-1,-1,-1,-1,-1,-2,-1,-1,-1,-2,-1,-2,-1,-1,-1,-2,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-2},
                {-2,-2,-2,-2,-2,-2,-2,-2,-2,-2,-2,-2,-2,-2,-2,-2,-2,-2,-2,-2,-2,-2,-2,-2,-2,-2,-2,-2,-2}
        };
    }

    static void pprint(int[][] m) {
        int rows = m.length;
        int colums = m[0].length;

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < colums; j++) {
                if (m[i][j] == -2)
                    System.out.printf("%s", "███");
                else if (m[i][j] == 0)
                    System.out.printf("%s", " @ ");
                else if (m[i][j] == -3)
                    System.out.printf("%s", " # ");
                else if (m[i][j] == -4)
                    System.out.printf("%s", " * ");
                else
                    System.out.printf("%s", "░░░");
            }
            System.out.println();
        }
    }

    static void start(int[][] map, int x, int y) {
        map[x][y] = 0;
    }

    static void finish(int[][] map, int x, int y) {
        map[x][y] = -3;
    }

    public static void wave(int[][] map) {
        LinkedList<int[]> newWave = new LinkedList<>();
        LinkedList<int[]> oldWave = new LinkedList<>();
        oldWave.add(new int[]{startX, startY});
        int waveCount = 1;
        while (map[finishX][finishY] == -3) {

            for (int[] step : oldWave) {
                if ((step[0] - 1) >= 0) {
                    if (map[step[0] - 1][step[1]] == -1 || map[step[0] - 1][step[1]] == -3) {
                        map[step[0] - 1][step[1]] = waveCount;
                        newWave.add(new int[]{step[0] - 1, step[1]});
                    }
                }
                if ((step[0] + 1) < map.length) {
                    if (map[step[0] + 1][step[1]] == -1 || map[step[0] + 1][step[1]] == -3) {
                        map[step[0] + 1][step[1]] = waveCount;
                        newWave.add(new int[]{step[0] + 1, step[1]});
                    }
                }
                if ((step[1] - 1) >= 0) {
                    if (map[step[0]][step[1] - 1] == -1 || map[step[0]][step[1] - 1] == -3) {
                        map[step[0]][step[1] - 1] = waveCount;
                        newWave.add(new int[]{step[0], step[1] - 1});
                    }
                }
                if ((step[1] + 1) < map[0].length) {
                    if (map[step[0]][step[1] + 1] == -1 || map[step[0]][step[1] + 1] == -3) {
                        map[step[0]][step[1] + 1] = waveCount;
                        newWave.add(new int[]{step[0], step[1] + 1});
                    }
                }
            }
            waveCount += 1;
            oldWave.clear();
            oldWave.addAll(newWave);
            newWave.clear();
        }
    }

    public static void getPath(int[][] map) {
        int stepX = finishX;
        int stepY = finishY;
        int pathCount = map[finishX][finishY]-1;
        while (pathCount>0){
            if (stepX - 1 >= 0) {
                if (map[stepX - 1][stepY] == pathCount) {
                    map[stepX - 1][stepY] = -4;
                    stepX = stepX - 1;
                }
            }
            if (stepX + 1 < map.length) {
                if (map[stepX + 1][stepY] == pathCount) {
                    map[stepX + 1][stepY] = -4;
                    stepX = stepX + 1;
                }
            }
            if (stepY - 1 >= 0) {
                if (map[stepX][stepY - 1] == pathCount) {
                    map[stepX][stepY - 1] = -4;
                    stepY = stepY - 1;
                }
            }
            if (stepY + 1 < map[0].length) {
                if (map[stepX][stepY + 1] == pathCount) {
                    map[stepX][stepY + 1] = -4;
                    stepY = stepY + 1;
                }
            }
            pathCount --;
        }
        finish(map, finishX, finishY);
    }
}
