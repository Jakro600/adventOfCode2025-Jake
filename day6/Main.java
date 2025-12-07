package day6;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        BufferedReader input;
        String inputLine;
        String filename = "resources/day6/" + args[0];
        ArrayList<String[]> problemRows = new ArrayList<>();
        Long grandTotal = 0L;

        try {
            input = new BufferedReader(new FileReader(filename));
            inputLine = input.readLine();
            while(inputLine != null) {
                inputLine = inputLine.trim();
                problemRows.add(inputLine.split("\s+"));
                inputLine = input.readLine();
            }
        } catch (IOException e) {
            System.out.println("Error Reading Input File");
            System.out.println(e.toString());
            return;
        }

        int operationRowIndex = problemRows.size() - 1;

        for(int problem = 0; problem < problemRows.get(0).length; problem++) {
            String operation = problemRows.get(operationRowIndex)[problem];
            Long answer = Long.valueOf(problemRows.get(0)[problem]);

            for(int rowIndex = 1; rowIndex < problemRows.size() - 1; rowIndex++) {
                Long number = Long.valueOf(problemRows.get(rowIndex)[problem]);
                if(operation.equals("+")) {
                    answer += number;
                } else {
                    answer *= number;
                }
            }

            grandTotal += answer;
        }

        System.out.println("Grand Total: " + grandTotal);
    }
}