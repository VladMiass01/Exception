package Task1;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        String path = "./file.txt";
        List<String[]> names = read(path);
        change(names);
        writer(names, path);
        for (String[] s: names) {
            System.out.println(s[0] + " = " + s[1]);
        }
    }
    public static List<String[]> read(String path) {
        List<String[]> names = new ArrayList<>();
        try {
            BufferedReader bufferedreader = new BufferedReader(new FileReader(path));
            String line;
            while ((line = bufferedreader.readLine()) != null) {
                names.add(line.split("="));
            }
        }
        catch (IOException e) {
            System.out.println(e.getMessage());
        }
        return names;
    }

    public static boolean writer(List<String[]> names, String path) {
        try {
            BufferedWriter bufferedwriter = new BufferedWriter(new FileWriter(path));
            for (String[] s : names) {
                bufferedwriter.write(s[0] + "=" + s[1] + "\n");
            }
            bufferedwriter.close();
        }
        catch (IOException e) {
            System.out.println(e.getMessage());
            return false;

        }
        return true;
    }

    public static void change(List<String[]> names) {
        for (String[] s: names) {
            if (!s[1].equals("?") && !isNumber(s[1])) {
                throw new IllegalArgumentException("В строке " + s[0] + " " + s[1] + " не число и не ?");
            }
            else {
                if (s[1].equals("?")) {
                    s[1] = String.valueOf(s[0].length());
                }
            }
        }
    }

    public static boolean isNumber(String s) {
        try{
            Integer.parseInt(s);
            return true;
        }
        catch (NumberFormatException e) {
            return false;
        }
    }
}
