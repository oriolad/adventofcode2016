package com.oriola.adventofcode2016.day4;

import com.oriola.adventofcode2016.FileUtil;
import sun.misc.Sort;

import java.io.FileNotFoundException;
import java.net.URISyntaxException;
import java.util.*;

/**
 * Created by Oriola on 16-12-05.
 */
public class Day4_Part1 {

    public static Integer sectorIDSum = 0;
    /**
     * --- Day 4: Security Through Obscurity ---
     * <p/>
     * Finally, you come across an information kiosk with a list of rooms. Of course, the list is encrypted and full of decoy data, but the instructions to decode the list are barely hidden nearby. Better remove the decoy data first.
     * <p/>
     * Each room consists of an encrypted name (lowercase letters separated by dashes) followed by a dash, a sector ID, and a checksum in square brackets.
     * <p/>
     * A room is real (not a decoy) if the checksum is the five most common letters in the encrypted name, in order, with ties broken by alphabetization. For example:
     * <p/>
     * aaaaa-bbb-z-y-x-123[abxyz] is a real room because the most common letters are a (5), b (3), and then a tie between x, y, and z, which are listed alphabetically.
     * a-b-c-d-e-f-g-h-987[abcde] is a real room because although the letters are all tied (1 of each), the first five are listed alphabetically.
     * not-a-real-room-404[oarel] is a real room.
     * totally-real-room-200[decoy] is not.
     * Of the real rooms from the list above, the sum of their sector IDs is 1514.
     * <p/>
     * What is the sum of the sector IDs of the real rooms?
     */
    public static void main(String[] args) throws FileNotFoundException, URISyntaxException {

        Scanner scanner = FileUtil.getScanner(Day4_Part1.class, "Day4_Input_RealRooms.txt", "\n");

        while (scanner.hasNext()) {
            String currentLine = scanner.next();

            String roomName = currentLine.replaceAll("(\\-[0-9].*$)|(\\-)", "");
            Integer sectorID = Integer.parseInt(currentLine.replaceAll("[^0-9]", ""));
            String checkSum = currentLine.replaceAll("^.+(\\[)|]", "");
            Boolean realRoom = false;

            TreeSet<CharacterCount> characterCountSet = new TreeSet<>();

            for (int i = 0; i < roomName.length(); i++) {
                Character c = roomName.toLowerCase().charAt(i);
                CharacterCount newCharCount = new CharacterCount(c);

                // Check if character exists in set
                Iterator setIterator = characterCountSet.iterator();
                Boolean setContainsChar = false;

                while (setIterator.hasNext()) {
                    CharacterCount currentCharCount = (CharacterCount) setIterator.next();
//                    System.out.println("Current character: " + currentCharCount.character + " -> " + currentCharCount.count);

                    if (currentCharCount.character.equals(c)) {
                        setContainsChar = true;
                        newCharCount = currentCharCount;
                    }
                }

                // If character exists in the set, get its current count and increment it
                if (setContainsChar == true) {
                    characterCountSet.remove(newCharCount);
                    newCharCount.increment();
                    characterCountSet.add(newCharCount);
//                    System.out.println("Updated character: " + newCharCount.character + " -> " + newCharCount.count);

                } else {
                    // Add a new character
//                    System.out.println("New character count: " + newCharCount.character + " -> " + newCharCount.count);
                    characterCountSet.add(newCharCount);
                }

            }

            if(characterCountSet.size() >= 5){
                Iterator descendingIterator = characterCountSet.descendingIterator();
                StringBuilder checkSumBuilder = new StringBuilder();
                int checkSumCount = 0;

                while(descendingIterator.hasNext() && checkSumCount < 5){
                    CharacterCount cc = (CharacterCount) descendingIterator.next();
                    System.out.println(cc.character + " -> " + cc.count);
                    checkSumBuilder.append(cc.character);
                    checkSumCount++;
                }

                if(checkSum.equals(checkSumBuilder.toString())){
                    realRoom = true;
                }
            }else{
                realRoom = false;
            }

            if(realRoom){
                sectorIDSum += sectorID;
            }

        }

        System.out.println("Sector ID Sum is: " + sectorIDSum);
    }
}
