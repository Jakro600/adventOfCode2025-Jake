package day3;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        BufferedReader input;
        String filename = "resources/day3/" + args[0];
        String notedBank;
        ArrayList<String> powerBanks = new ArrayList<>();
        long totalVoltage = 0;

        try {
            input = new BufferedReader(new FileReader(filename));
            notedBank = input.readLine();
            while(notedBank != null) {
                powerBanks.add(notedBank);
                notedBank = input.readLine();
            }
        } catch (IOException e) {
            System.out.println("Error Reading Input File");
            System.out.println(e.toString());
            return;
        }

        for(String bank: powerBanks) {
            char[] batteryVoltages = bank.toCharArray();
            int voltageIndex = -1;
            StringBuilder bankVoltage = new StringBuilder();

            for(int batteryNumber = 1; batteryNumber <= 12; batteryNumber++) {
                int voltage = 1;
                voltageIndex += 1;

                for(int battery = voltageIndex; battery < batteryVoltages.length - (12 - batteryNumber); battery++) {
                    int batteryVoltage = Character.getNumericValue(batteryVoltages[battery]);
                    if(voltage < batteryVoltage) {
                        voltage = batteryVoltage;
                        voltageIndex = battery;
                    }
                }

                bankVoltage.append(voltage);
            }

            totalVoltage += Long.parseLong(bankVoltage.toString());
        }

        System.out.println("Total Voltage: " + totalVoltage);
    }
}