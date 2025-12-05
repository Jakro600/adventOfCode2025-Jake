package day4;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        BufferedReader input;
        String filename = "resources/day4/" + args[0];
        String rowRead;
        ArrayList<char[]> map = new ArrayList<>();
        ArrayList<Integer> rollsToRemoveY = new ArrayList<>();
        ArrayList<Integer> rollsToRemoveX = new ArrayList<>();
        int removableRolls = 0;

        try {
            input = new BufferedReader(new FileReader(filename));
            rowRead = input.readLine();
            while(rowRead != null) {
                map.add(rowRead.toCharArray());
                rowRead = input.readLine();
            }
        } catch (IOException e) {
            System.out.println("Error Reading Input File");
            System.out.println(e.toString());
            return;
        }

        boolean rollsAccessible = true;
        while(rollsAccessible) {
            for(int y = 0; y < map.size(); y++) {
                char[] row = map.get(y);

                for(int x = 0; x < row.length; x++) {
                    char spot = row[x];
                    int obstructions = 0;

                    if(spot != '@') {
                        continue;
                    }

                    int[] adjacentY = {y - 1, y + 1};
                    int[] adjacentX = {x - 1, x, x + 1};

                    for(int adjacentSpot: adjacentX) {
                        if(adjacentSpot < 0 || adjacentSpot >= row.length || adjacentSpot == x) {
                            continue;
                        }

                        if(row[adjacentSpot] == '@') {
                            obstructions++;
                        }
                    }

                    for(int adjacentRowIndex: adjacentY) {
                        if(adjacentRowIndex < 0 || adjacentRowIndex >= map.size()) {
                            continue;
                        }

                        for(int adjacentSpotIndex: adjacentX) {
                            if(adjacentSpotIndex < 0 || adjacentSpotIndex >= row.length) {
                                continue;
                            }

                            if(map.get(adjacentRowIndex)[adjacentSpotIndex] == '@') {
                                obstructions++;
                            }
                        }
                    }

                    if(obstructions < 4) {
                        removableRolls++;
                        rollsToRemoveY.add(y);
                        rollsToRemoveX.add(x);
                    }
                }
            }

            if(rollsToRemoveY.isEmpty()) {
                rollsAccessible = false;
            } else {
                for(int i = 0; i < rollsToRemoveY.size(); i++) {
                    map.get(rollsToRemoveY.get(i))[rollsToRemoveX.get(i)] = '.';
                }
                rollsToRemoveY.clear();
                rollsToRemoveX.clear();
            }
        }

        System.out.println("Total Rolls Removable: " + removableRolls);
    }
}