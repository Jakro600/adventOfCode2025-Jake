package day1;

import java.io.BufferedReader;
import java.io.FileReader;

class Main {
    static BufferedReader document;
    static String instruction;
    static int change;
    static int pointer = 50;
    static int passCount = 0;
    static int[] turnResults;

    public static void main(String[] args) {
        String fileName = "resources/day1/" + args[0];

        try {
            document = new BufferedReader(new FileReader(fileName));
            instruction = document.readLine();
            while(instruction != null) {
                change = parse(instruction);
                turnResults = turnPointer(pointer, change);
                pointer = turnResults[0];
                passCount += turnResults[1];
                if(pointer == 0) {
                    passCount++;
                }
                //System.out.println("Pointer at " + pointer + " / passCount at " + passCount);
                instruction = document.readLine();
            }
            System.out.println("The Passcode is: " + passCount);
        } catch(java.io.FileNotFoundException e) {
            System.out.println("Error Opening File");
            System.out.println(e.toString());
        } catch(java.io.IOException e) {
            System.out.println("Error Reading File");
            System.out.println(e.toString());
        }
    }

    private static int parse(String instruction) {
        char operation = instruction.charAt(0);
        int magnitude = Integer.parseInt(instruction.substring(1));

        if(operation == 'L') {
            magnitude -= magnitude*2;
        }

        return magnitude;
    }

    private static int[] turnPointer(int pointer, int change) {
        boolean initialZero = (pointer == 0);

        int passClicks = Math.abs(change / 100);
        change = change % 100;

        pointer += change;

        if(pointer > 99) {
            pointer -= 100;
            if(pointer != 0) {
                passClicks++;
            }
        } else if(pointer < 0) {
            pointer += 100;
            if(!initialZero) {
                passClicks++;
            }
        }

        return new int[]{pointer, passClicks};
    }
}