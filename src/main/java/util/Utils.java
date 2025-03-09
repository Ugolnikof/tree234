package util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class Utils {

    public static String getString(InputStream inputStream) throws IOException {
        InputStreamReader isr = new InputStreamReader(inputStream);
        BufferedReader br = new BufferedReader(isr);
        return br.readLine();
    }

    public static char getChar(InputStream inputStream) throws IOException {
        return getString(inputStream).charAt(0);
    }

    public static int getInt(InputStream inputStream) throws IOException {
        return Integer.parseInt(getString(inputStream));
    }
}
