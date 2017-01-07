package com.oriola.adventofcode2016.day4;

import com.oriola.adventofcode2016.FileUtil;

import java.io.FileNotFoundException;
import java.net.URISyntaxException;
import java.text.Normalizer;
import java.util.Iterator;
import java.util.Scanner;
import java.util.TreeSet;

/**
 * Created by Oriola on 16-12-05.
 */
public class Day4_Part2 {

    public static Integer searchSectorID = 0;
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

     * --- Part Two ---

     With all the decoy data out of the way, it's time to decrypt this list and get moving.

     The room names are encrypted by a state-of-the-art shift cipher, which is nearly unbreakable without the right software. However, the information kiosk designers at Easter Bunny HQ were not expecting to deal with a master cryptographer like yourself.

     To decrypt a room name, rotate each letter forward through the alphabet a number of times equal to the room's sector ID. A becomes B, B becomes C, Z becomes A, and so on. Dashes become spaces.

     For example, the real name for qzmt-zixmtkozy-ivhz-343 is very encrypted name.

     What is the sector ID of the room where North Pole objects are stored?
     * @param args
     * @throws FileNotFoundException
     * @throws URISyntaxException
     */
    public static void main(String[] args) throws FileNotFoundException, URISyntaxException {

        Scanner scanner = FileUtil.getScanner(Day4_Part2.class, "Day4_Input_RealRooms.txt", "\n");

        while (scanner.hasNext()) {
            String currentLine = scanner.next();
            StringBuilder realRoomName = new StringBuilder();

            String roomName = currentLine.replaceAll("(\\-[0-9].*$)|(\\-)", "");
            int sectorID = Integer.parseInt(currentLine.replaceAll("[^0-9]", ""));

            for (int i = 0; i < roomName.length(); i++) {
                char originalChar = roomName.toLowerCase().charAt(i);
                char c = rotateCharacter(originalChar, sectorID);

                realRoomName.append(c);
            }
            System.out.println(realRoomName.toString());

            CharSequence matchPattern = "northpole";
            if(realRoomName.toString().contains(matchPattern)) {
                searchSectorID = sectorID;
            }
        }

        System.out.println("The Sector ID of the room where North Pole objects are stored is:  " + searchSectorID);
    }


    public static char rotateCharacter(char character, int rotateCount){
        char c = (char) (((character - 'a' + rotateCount) % 26) + 'a');
        return c;
    }
}
