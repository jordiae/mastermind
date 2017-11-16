package com.mastermind.domini;

import java.util.ArrayList;
import java.util.Scanner;

public class Utils {
        public static ArrayList<Integer> readNumsFromCommandLine() {

            Scanner s = new Scanner(System.in);

            int count = s.nextInt();
            s.nextLine(); // throw away the newline.

            ArrayList<Integer> numbers = new ArrayList<Integer>(count);
            Scanner numScanner = new Scanner(s.nextLine());
            for (int i = 0; i < count; i++) {
                if (numScanner.hasNextInt()) {
                    numbers.add (numScanner.nextInt());
                } else {
                    System.out.println("Error: no suficients nombres.");
                    break;
                }
            }

            return numbers;
        }
}
