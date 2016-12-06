package com.oriola.adventofcode2016;

import java.io.File;
import java.io.FileNotFoundException;
import java.net.URISyntaxException;
import java.util.Scanner;

/**
 * Created by Oriola on 16-12-05.
 */
public class FileUtil {

    public static Scanner getScanner(Class classObject, String filename, String delimiter) throws URISyntaxException, FileNotFoundException{

        File file = new File(classObject.getClassLoader().getResource(filename).toURI());
        Scanner scanner = new Scanner(file);
        scanner.useDelimiter(delimiter);

        return scanner;
    }
}
