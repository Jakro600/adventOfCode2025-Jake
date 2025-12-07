package day5;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        BufferedReader input;
        String inputLine;
        String filename = "resources/day5/" + args[0];
        ArrayList<String> freshRangesInput = new ArrayList<>();
        ArrayList<Long[]> freshRanges = new ArrayList<>();
//        ArrayList<Long> ingredients = new ArrayList<>();
//        int freshIngredients = 0;
        Long freshIDs = 0L;

        try {
            input = new BufferedReader(new FileReader(filename));
            inputLine = input.readLine();
            boolean readingRanges = true;
            while(inputLine != null) {
                if(readingRanges) {
                    if(!inputLine.isEmpty()) {
                        freshRangesInput.add(inputLine);
                    } else {
                        readingRanges = false;
                    }
                } else {
//                    ingredients.add(Long.valueOf(inputLine));
                    break;
                }

                inputLine = input.readLine();
            }
        } catch (IOException e) {
            System.out.println("Error Reading Input File");
            System.out.println(e.toString());
            return;
        }

        for(String range: freshRangesInput) {
            String[] splitRange = range.split("-");
            Long[] convertedRange = {Long.valueOf(splitRange[0]), Long.valueOf(splitRange[1])};
            freshRanges.add(convertedRange);
        }

        freshIDs = freshRanges.get(0)[1] - freshRanges.get(0)[0] + 1L;

        for(int rangeIndex = 1; rangeIndex < freshRanges.size(); rangeIndex++) {
            Long[] currentRange = {freshRanges.get(rangeIndex)[0], freshRanges.get(rangeIndex)[1]};
            boolean rangeDuplicate = false;
            ArrayList<Long[]> duplicateRange = new ArrayList<>();

            for(int secondaryRangeIndex = 0; secondaryRangeIndex < rangeIndex; secondaryRangeIndex++) {
                Long[] currentSecondaryRange = {freshRanges.get(secondaryRangeIndex)[0], freshRanges.get(secondaryRangeIndex)[1]};
                boolean lowerBoundInRange = false;
                boolean upperBoundInRange = false;
                boolean containsDuplicateIDs = false;

                if(currentRange[0] >= currentSecondaryRange[0] && currentRange[0] <= currentSecondaryRange[1]) {
                    lowerBoundInRange = true;
                }
                if(currentRange[1] >= currentSecondaryRange[0] && currentRange[1] <= currentSecondaryRange[1]) {
                    upperBoundInRange = true;
                }
                if(currentRange[0] <= currentSecondaryRange[0] && currentRange[1] >= currentSecondaryRange[1] && !lowerBoundInRange && !upperBoundInRange) {
                    containsDuplicateIDs = true;
                }

                if(upperBoundInRange && lowerBoundInRange) {
                    rangeDuplicate = true;
                } else if(upperBoundInRange) {
                    currentRange[1] = currentSecondaryRange[0] - 1;
                } else if(lowerBoundInRange) {
                    currentRange[0] = currentSecondaryRange[1] + 1;
                } else if(containsDuplicateIDs) {
                    duplicateRange.add(currentSecondaryRange);
                }

                if(rangeDuplicate) {
                    break;
                }
            }

            if(!rangeDuplicate) {
                freshIDs += (currentRange[1] - currentRange[0]) + 1L;
                if(!duplicateRange.isEmpty()) {
                    Long[] range = duplicateRange.get(0);
                    freshIDs -= (range[1] - range[0]) + 1L;
                }
            }
        }

//        for(Long ingredient: ingredients) {
//            for(Long[] range: freshRanges) {
//                if(ingredient >= range[0] && ingredient <= range[1]) {
//                    freshIngredients++;
//                    break;
//                }
//            }
//        }

//        System.out.println("Total Fresh Ingredients: " + freshIngredients);
        System.out.println("Total Fresh IDs: " + freshIDs);
    }
}
