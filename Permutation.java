 /*
   Permutations with Duplicate : Write a method to compute all permutations of a string whose characters are not necessarily unique. The list of permutations should not have duplicates.
  */

import java.util.Hashtable;
import java.util.Collection;
import java.util.ArrayList;
import java.util.Scanner;

public class Permutation {
    /**
    * This method iterates through a String and creates a hashtable
    * with a key of character in a string and its count.
    * @param input A given string
    * @return Hashtable A created hashtable
    */
    private static Hashtable<Character, Integer> createHashTable(String input) {
        Hashtable<Character, Integer> table = new Hashtable<Character, Integer>();
        for(int i = 0; i < input.length(); i++) {
            char c = input.charAt(i);
            if(table.containsKey(c)) {
                table.put(c, table.get(c) + 1);
            }
            else {
                table.put(c, 1);
            }
        }
        return table;
    }
    
    public static ArrayList<String> permutation(String input) {
        ArrayList<String> results = new ArrayList<String>();
        permutation(input.length(), createHashTable(input), new StringBuilder(), results);
        return results;
    }

    private static void permutation(int remainder, Hashtable<Character, Integer> table, StringBuilder result, ArrayList<String> results) {
        if(remainder == 0) {
            results.add(result.toString());
            return;
        }
        for(char c : table.keySet()) {
            int cnt = (int) table.get(c);
            if(cnt != 0) {
                StringBuilder res = new StringBuilder(result);
                table.put(c, cnt - 1);
                permutation(remainder - 1, table, res.append(c), results);
                table.put(c, cnt);
            }
        }
        return;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        ArrayList<String> perms = permutation(input);
        for(String s : perms) {
            System.out.println(s);
        }
        return;
    }
}

