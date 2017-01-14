package com.oriola.adventofcode2016.day6;

import com.oriola.adventofcode2016.FileUtil;

import java.io.FileNotFoundException;
import java.net.URISyntaxException;
import java.util.*;
import java.util.logging.Logger;

/**
 * Created by Oriola on 17-01-07.
 *
 --- Day 6: Signals and Noise ---

 Something is jamming your communications with Santa. Fortunately, your signal is only partially jammed, and protocol in situations like this is to switch to a simple repetition code to get the message through.

 In this model, the same message is sent repeatedly. You've recorded the repeating message signal (your puzzle input), but the data seems quite corrupted - almost too badly to recover. Almost.

 All you need to do is figure out which character is most frequent for each position. For example, suppose you had recorded the following messages:

 eedadn
 drvtee
 eandsr
 raavrd
 atevrs
 tsrnev
 sdttsa
 rasrtv
 nssdts
 ntnada
 svetve
 tesnvt
 vntsnd
 vrdear
 dvrsen
 enarar

 The most common character in the first column is e; in the second, a; in the third, s, and so on. Combining these characters returns the error-corrected message, easter.

 Given the recording in your puzzle input, what is the error-corrected version of the message being sent?

 Your puzzle answer was gyvwpxaz.
 --- Part Two ---

 Of course, that would be the message - if you hadn't agreed to use a modified repetition code instead.

 In this modified code, the sender instead transmits what looks like random data, but for each character, the character they actually want to send is slightly less likely than the others. Even after signal-jamming noise, you can look at the letter distributions in each column and choose the least common letter to reconstruct the original message.

 In the above example, the least common character in the first column is a; in the second, d, and so on. Repeating this process for the remaining characters produces the original message, advent.

 Given the recording in your puzzle input and this new decoding methodology, what is the original message that Santa is trying to send?

 */
public class Day6 {

    private static final Logger logger = Logger.getLogger( Day6.class.getName() );

    public static void main(String[] args) throws FileNotFoundException, URISyntaxException{

        String frequencyTag = "";
        String failureMessage = "Please provide argument: -m for most common character, -l for least common character";

        try{
            if(args[0].equals("-m")){
                frequencyTag = args[0];
            }else if(args[0].equals("-l")){
                frequencyTag = args[0];
            }else if(args[0].equals("-h")){
                logger.info(failureMessage);
                System.exit(0);
            }else{
                throw new RuntimeException(failureMessage);
            }
        }catch(IndexOutOfBoundsException e){
            throw new RuntimeException(failureMessage);
        }

        Scanner scanner = FileUtil.getScanner(Day6.class, "Day6_Input_RepetitionCode.txt", "\n");

        List<Map<Character, Integer>> commonChar = new LinkedList<>();
        StringBuilder errorCorrectedMessage = new StringBuilder();
        Integer charRepeatCount = 0;
        Character charFound = 0;

        while(scanner.hasNext()) {
            String currentLine = scanner.next();

            // Parse every character in the current line
            for (int i = 0; i < currentLine.length(); i++) {
                Character c = currentLine.charAt(i);

                if (commonChar == null || commonChar.size()-1 < i) {
                    Map<Character, Integer> newMap = new TreeMap<>();
                    newMap.put(c, 1);
                    commonChar.add(i, newMap);
                } else if (commonChar.get(i).containsKey(c)) {
                    Integer temp = commonChar.get(i).get(c) + 1;
                    commonChar.remove(c);
                    commonChar.get(i).put(c, temp);
                } else {
                    Map<Character, Integer> currentMap = commonChar.get(i);
                    currentMap.put(c, 1);
                }

            }
        }

                // Find most common letter
                for(int i = 0; i < commonChar.size(); i++) {

                    for(char key = 'a'; key <= 'z'; key++){
                        Integer currentInt = commonChar.get(i).get(key);

                        if (charRepeatCount == 0) {
                            charRepeatCount = currentInt;
                            charFound = key;
                        }else{
                            if(frequencyTag.equals("-m") && currentInt > charRepeatCount) {
                                charRepeatCount = currentInt;
                                charFound = key;
                            }else if(frequencyTag.equals("-l") && currentInt < charRepeatCount) {
                                charRepeatCount = currentInt;
                                charFound = key;
                            }
                        }
                    }

                    if(frequencyTag.equals("-m")){
                        logger.info("Most common character: " + charFound + " repeated " + charRepeatCount + " times.");
                    }else{
                        logger.info("Least common character: " + charFound + " repeated " + charRepeatCount + " times.");
                    }

                    errorCorrectedMessage.append(charFound);

                    //Reset
                    charRepeatCount = 0;
                    charFound = 0;
                }




        logger.info("The error correct message is: " + errorCorrectedMessage.toString());
    }
}
