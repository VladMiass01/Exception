import java.io.*;
import java.nio.file.FileSystemException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

public class Main {
    public static void main(String[] args) {

        try {
            makeRecord();
            System.out.println("Данные записаны");
        }
        catch (FileSystemException e) {
            System.out.println(e.getMessage());
        }
        catch (Exception e) {
            System.out.println(Arrays.toString(e.getStackTrace()));
        }
    }

    public static void makeRecord() throws Exception {
        System.out.println("Введите фамилию, имя, отчество, дату рождения (в формате dd.mm.yyyy)," +
                "номер телефона (число без разделителей) и пол(символ латиницей f или m), разделенные пробелом");
        String text = null;
        try (BufferedReader bf = new BufferedReader(new InputStreamReader(System.in))) {
            text = bf.readLine();

        String[] array = text.split(" ");
        if (array.length != 6) {
            throw new DataException("Введено неверное количество параметров");
        }
        String surname = array[0].trim();
        String name = array[1].trim();
        String patronymic = array[2].trim();
        String birthday = parseBirthday(array[3].trim());
        long phone = parsePhone(array[4].trim());
        String sex = parseSex(array[5].toLowerCase().trim());
        writer(surname, name, patronymic, birthday, phone, sex);
        }
        catch (IOException e) {
            System.out.println("Произошла ошибка при работе с консолью");
            System.out.println(e.getMessage());
        }
    }

    public static String parseBirthday(String adate) {
        SimpleDateFormat format = new SimpleDateFormat("dd.MM.yyyy");
        Date birthday = null;
        try {
            birthday = format.parse(adate);
        }
        catch (ParseException e) {
            System.out.println("Неверный формат даты рождения");
            System.out.println(e.getMessage());
        }
        return format.format(birthday);
    }

    public static long parsePhone(String aphone) throws DataException {
        long phone = 0;
        if (aphone.length() != 10) {
            throw new DataException("Не верное кол-во знаков в номере телефона.");
        }
        try {
            phone = Long.parseLong(aphone.trim());
        } catch (NumberFormatException e) {
            System.out.println("Неверный формат телефона");
            System.out.println(e.getMessage());
        }
        return phone;
    }

    public static String parseSex(String asex) throws DataException {
        if (asex.equals("m") || asex.equals("f")) {
            return asex;
        }
        throw new DataException("Неверно введен пол");
    }

    public static void writer(String surname, String name, String patronymic, String birthday, long phone, String sex) {
        String fileName = "./" + surname.toLowerCase() + ".txt";
        File file = new File(fileName);
        try (FileWriter fileWriter = new FileWriter(file, true)) {
            if (file.length() > 0){
                fileWriter.write('\n');
            }
            fileWriter.write(String.format("%s %s %s %s %s %s", surname, name, patronymic, birthday, phone, sex));
        }
        catch (IOException e) {
            System.out.println("Возникла ошибка при работе с файлом");
            System.out.println(e.getMessage());
        }
    }
}