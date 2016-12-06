package com.oriola.adventofcode2016.day1;

import com.oriola.adventofcode2016.FileUtil;

import java.io.*;
import java.net.URISyntaxException;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

/**
 * Created by Oriola on 16-12-03.
 */
public class Day1 {

    /**
     * You're airdropped near Easter Bunny Headquarters in a city somewhere. "Near", unfortunately, is as close as you can get - the instructions on the Easter Bunny Recruiting Document the Elves intercepted start here, and nobody had time to work them out further.

     The Document indicates that you should start at the given coordinates (where you just landed) and face North. Then, follow the provided sequence: either turn left (L) or right (R) 90 degrees, then walk forward the given number of blocks, ending at a new intersection.

     There's no time to follow such ridiculous instructions on foot, though, so you take a moment and work out the destination. Given that you can only walk on the street grid of the city, how far is the shortest path to the destination?

     For example:

     1. Following R2, L3 leaves you 2 blocks East and 3 blocks North, or 5 blocks away.
     2. R2, R2, R2 leaves you 2 blocks due South of your starting position, which is 2 blocks away.
     3. R5, L5, R5, R3 leaves you 12 blocks away.
     How many blocks away is Easter Bunny HQ?

     --- Part Two ---

     Then, you notice the instructions continue on the back of the Recruiting Document. Easter Bunny HQ is actually at the first location you visit twice.

     For example, if your instructions are R8, R4, R4, R8, the first location you visit twice is 4 blocks away, due East.

     How many blocks away is the first location you visit twice?
     */


    public static void main(String [] args) throws URISyntaxException, IOException{
        int x = 0;
        int y = 0;
        int totalBlocks;
        int currentDir = 0; //0 = North, 1 = East, 2 = South, 3 = West
        Set<Point> pointsVisited = new HashSet<>();

        Scanner scanner = FileUtil.getScanner(Day1.class, "CityBlockDirections.txt", ",");

        while(scanner.hasNext()){
            String fullInstruction = scanner.next().trim();
            String direction = fullInstruction.substring(0,1);
            int blockNum = Integer.parseInt(fullInstruction.substring(1));

            // Set current direction clockwise or counterclockwise
            if(direction.equals("R")){
                currentDir++;
                currentDir = currentDir % 4;
            }else if(direction.equals("L")){
                currentDir = currentDir + 3;
                currentDir = currentDir % 4;
            }

            // Step by blockNum in the current direction
            for(int i = 0; i < blockNum; i++) {
                if (currentDir == 0) {
                    y = y + 1;
                } else if (currentDir == 1) {
                    x = x + 1;
                } else if (currentDir == 2) {
                    y = y - 1;
                } else if (currentDir == 3) {
                    x = x - 1;
                }

                Point point = new Point(x, y);

                //Check if point is visited and stop if you have visited location twice
                if(pointsVisited.contains(point)){
                    System.out.println("You have visited this location twice: " + x + "," + y);
                    totalBlocks = x + y;
                    System.out.println("Total blocks: " + totalBlocks);
                    return;
                }else {
                    pointsVisited.add(point);
                    System.out.println("Travelled to: " + x + "," + y);
                }

            }

        }

       // Part one, count total blocks travelled
//        System.out.println("Final x: " + x);
//        System.out.println("Final y: " + y);
//        totalBlocks = x + y;
//        System.out.println("Total blocks: " + totalBlocks);
    }

}
