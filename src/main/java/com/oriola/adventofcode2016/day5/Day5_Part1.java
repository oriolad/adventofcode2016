package com.oriola.adventofcode2016.day5;

import com.sun.javafx.binding.StringFormatter;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Created by Oriola on 17-01-06.
 */
public class Day5_Part1 {

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
     * @param args
     */
    public static void main(String[] args) throws UnsupportedEncodingException, NoSuchAlgorithmException{

        StringBuilder password = new StringBuilder();
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

                String passwordDigit = toHex(hash[2]);
                System.out.println("The password digit is: " + passwordDigit);

                password.append(passwordDigit);

                passwordCount++; //increase password count
            }

            increasingIndex++;
        }

        System.out.println("The password is:  " + password.toString());
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
