package com.oriola.adventofcode2016.day5;

import com.oriola.adventofcode2016.day4.CharacterCount;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Created by Oriola on 17-01-06.
 */
public class Day5_Part2 {

    /**
     * You are faced with a security door designed by Easter Bunny engineers that seem to have acquired most of their security knowledge by watching hacking movies.

     The eight-character password for the door is generated one character at a time by finding the MD5 hash of some Door ID (your puzzle input) and an increasing integer index (starting with 0).

     A hash indicates the next character in the password if its hexadecimal representation starts with five zeroes. If it does, the sixth character in the hash is the next character of the password.

     For example, if the Door ID is abc:

     The first index which produces a hash that starts with five zeroes is 3231929, which we find by hashing abc3231929; the sixth character of the hash, and thus the first character of the password, is 1.
     5017308 produces the next interesting hash, which starts with 000008f82..., so the second character of the password is 8.
     The third time a hash starts with five zeroes is for abc5278568, discovering the character f.

     In this example, after continuing this search a total of eight times, the password is 18f47a30.

     Given the actual Door ID, what is the password?

     Your puzzle input is ffykfhsq.

     --- Part Two ---

     As the door slides open, you are presented with a second door that uses a slightly more inspired security mechanism. Clearly unimpressed by the last version (in what movie is the password decrypted in order?!), the Easter Bunny engineers have worked out a better solution.

     Instead of simply filling in the password from left to right, the hash now also indicates the position within the password to fill. You still look for hashes that begin with five zeroes; however, now, the sixth character represents the position (0-7), and the seventh character is the character to put in that position.

     A hash result of 000001f means that f is the second character in the password. Use only the first result for each position, and ignore invalid positions.

     For example, if the Door ID is abc:

     The first interesting hash is from abc3231929, which produces 0000015...; so, 5 goes in position 1: _5______.
     In the previous method, 5017308 produced an interesting hash; however, it is ignored, because it specifies an invalid position (8).
     The second interesting hash is at index 5357525, which produces 000004e...; so, e goes in position 4: _5__e___.

     You almost choke on your popcorn as the final character falls into place, producing the password 05ace8e3.

     Given the actual Door ID and this new method, what is the password? Be extra proud of your solution if it uses a cinematic "decrypting" animation.

     Your puzzle input is still ffykfhsq.
     * @param args
     */
    public static void main(String[] args) throws UnsupportedEncodingException, NoSuchAlgorithmException{

        char[] password = new char[8];
        String doorID = "ffykfhsq";
        int passwordCount = 0;
        int increasingIndex = 0;

        while(passwordCount < 8){

            String current = doorID.concat(Integer.toString(increasingIndex));

            byte[] input = current.getBytes("UTF-8"); //Puzzle only works with UTF-8, UTF-16 will fail
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] hash = md.digest(input);

            Boolean isPasswordHash = hasFiveLeadingZeros(hash);
            if(isPasswordHash == true){
                String hashedString = toHex(hash);
                System.out.println(current + " -> " + hashedString);
                int passwordIndex;

                // Parse the 6th digit - the password index
                try{
                    passwordIndex = Integer.valueOf(toHex(hash[2]));
                }catch(NumberFormatException e){
                    System.out.println("Password digit location is invalid.");
                    increasingIndex++;
                    continue;
                }


                // Parse the 7th digit - the actual password digit
                char passwordDigit = toHex(hash[3]).substring(0, 1).charAt(0);
                //System.out.println("The password digit is: " + passwordDigit);


                // If the index is valid and empty, then insert the password digit at that index
                if(passwordIndex <= 7 && passwordIndex >= 0) {
                    if(password[passwordIndex] == 0){
                        System.out.println("The password digit at " + passwordIndex + " is: " + passwordDigit);
                        password[passwordIndex] = passwordDigit;
                        passwordCount++;
                    }else{
                        System.out.println("The password has already been set at this index.");
                    }
                }else{
                    System.out.println("The password index is invalid");
                }


            }

            increasingIndex++;
        }

        System.out.println("The password is:  " + new String(password));
    }

    public static Boolean hasFiveLeadingZeros(byte[] byteArray){
        if(byteArray[0] == 0 && byteArray[1] == 0 && byteArray[2] <= 15 && byteArray[2] >= 0){
            return true;
        }else{
            return false;
        }
    }

    public static String toHex(byte[] byteArray){
        StringBuilder stringBuilder = new StringBuilder();

        for(byte b : byteArray) {
            String s = String.format("%02X", b);
            stringBuilder.append(s);
        }

        return stringBuilder.toString().toLowerCase();
    }

    public static String toHex(byte b){
        String string = String.format("%2X", b);

        return string.toLowerCase().trim();
    }
}
