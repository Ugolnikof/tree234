package org.example;

import java.io.IOException;

import static util.Utils.*;

public class Main {
    public static void main(String[] args) throws IOException {

        long value;
        Tree234 tree = new Tree234();

        boolean run = true;
        while (run) {
            System.out.println("--------------------------------------------------");
            System.out.println("Enter first letter of show, insert, find or exit: ");
            char choice = getChar(System.in);

            switch(choice) {
                case 's':
                    tree.displayTree();
                    break;
                case 'i':
                    System.out.print("Enter value to insert: ");
                    value = getInt(System.in);
                    tree.insert(value);
                    break;
                case 'f':
                    System.out.print("Enter value to find: ");
                    value = getInt(System.in);
                    int found = tree.find(value);
                    if (found != -1) {
                        System.out.println("Found " + value);
                    } else {
                        System.out.println("Could not find " + value);
                    }
                    break;
                case 'e':
                    run = false;
                    break;
                default:
                    System.out.println("Invalid entry\n");
            }
        }


    }
}