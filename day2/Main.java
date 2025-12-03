package day2;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Main {
    static long answer = 0;

    public static void main(String[] args) {
        BufferedReader input;
        String rangeString;
        String filename = "resources/day2/" + args[0];

        try {
            input = new BufferedReader(new FileReader(filename));
            rangeString = input.readLine();
        } catch (IOException e) {
            System.out.println("Error Reading Input File");
            System.out.println(e.toString());
            return;
        }

        String[] ranges = rangeString.split(",");
        for(String range : ranges) {
            String[] boundsString = range.split("-");
            long[] bounds = {Long.parseLong(boundsString[0]), Long.parseLong(boundsString[1])};

            for(long i = bounds[0]; i <= bounds[1]; i++) {
                String id = Long.toString(i);
                String[] splitId = {"", ""};
                if(id.length() % 2 == 0) {
                    splitId[0] = id.substring(0, id.length() / 2);
                    splitId[1] = id.substring(id.length() / 2);
                    if(splitId[0].equals(splitId[1])) {
                        answer += i;
                    }
                }
            }
        }

        System.out.println("The Answer is: " + answer);
    }
}