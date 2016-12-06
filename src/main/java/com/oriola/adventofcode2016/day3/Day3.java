package com.oriola.adventofcode2016.day3;

import com.oriola.adventofcode2016.FileUtil;

import java.io.File;
import java.io.FileNotFoundException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

/**
 * Created by Oriola on 16-12-03.
 */
public class Day3 {

    /**
     * Now that you can think clearly, you move deeper into the labyrinth of hallways and office furniture that makes up this part of Easter Bunny HQ. This must be a graphic design department; the walls are covered in specifications for triangles.

     Or are they?

     The design document gives the side lengths of each triangle it describes, but... 5 10 25? Some of these aren't triangles. You can't help but mark the impossible ones.

     In a valid triangle, the sum of any two sides must be larger than the remaining side. For example, the "triangle" given above is impossible, because 5 + 10 is not larger than 25.

     In your puzzle input, how many of the listed triangles are possible?

     --- Part Two ---

     Now that you've helpfully marked up their design documents, it occurs to you that triangles are specified in groups of three vertically. Each set of three numbers in a column specifies a triangle. Rows are unrelated.

     For example, given the following specification, numbers with the same hundreds digit would be part of the same triangle:

     101 301 501
     102 302 502
     103 303 503
     201 401 601
     202 402 602
     203 403 603
     In your puzzle input, and instead reading by columns, how many of the listed triangles are possible?
     */
    public static void main(String [] args) throws URISyntaxException, FileNotFoundException{
        Scanner scanner = FileUtil.getScanner(Day3.class, "ImpossibleTriangles.txt", "\n");

        ArrayList<Integer> column1 = new ArrayList<>();
        ArrayList<Integer> column2 = new ArrayList<>();
        ArrayList<Integer> column3 = new ArrayList<>();
        List<ArrayList<Integer>> allColumnsList = new ArrayList<>();

        int validTriangle = 0;

        //Parse file into 3 columns
        while(scanner.hasNext()){
            String line = scanner.next();
            line = line.trim();

            String[] sidesString = line.split(" +");

            column1.add(Integer.parseInt(sidesString[0]));
            column2.add(Integer.parseInt(sidesString[1]));
            column3.add(Integer.parseInt(sidesString[2]));
        }

        allColumnsList.add(column1);
        allColumnsList.add(column2);
        allColumnsList.add(column3);

        int sideCount = 0;
        int[] sides = new int[3];

        // Parse the list for triangles and check if the triangles are valid
        Iterator listIterator = allColumnsList.iterator();
        while (listIterator.hasNext()) {
            ArrayList<Integer> col = (ArrayList) listIterator.next();

            for (int i = 0; i < col.size(); i++) {
                sides[sideCount] = col.get(i);

                if (sideCount == 2) {
                    validTriangle += isTriangleValid(sides);
                    sideCount = 0;
                } else {
                    sideCount++;
                }
            }
        }

        System.out.println("Total Possible Triangles: " + validTriangle);
    }

    /**
     * Calculates if a triangle is valid, where in a valid triangle, the sum of any two sides
     * is greater than the remaining side
     * @param sides
     * @return
     */
    public static int isTriangleValid(int[] sides){
        int result = 0;

        int sum12 = sides[0] + sides[1];
        int sum23 = sides[1] + sides[2];
        int sum31 = sides[2] + sides[0];

        if(sum12 > sides[2] && sum23 > sides[0] && sum31 > sides[1]){
            result = 1;
        }

        return result;
    }
}
