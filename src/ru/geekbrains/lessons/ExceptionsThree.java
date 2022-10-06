package ru.geekbrains.lessons;

import java.io.*;

public class ExceptionsThree {

    public static void main(String[] args) {
        String[] arrstr = new String[10];

        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            String str = reader.readLine();
            arrstr = str.split(" ");
        } catch (IOException e) {
            System.err.println("The string cannot be read");
        }
        isCorrectData(arrstr);
    }

    private static void isCorrectData(String[] arrstr) {
        StringBuilder fullName = new StringBuilder();
        char sex = ' ';
        String birthday = null;
        int numPhone = 0;

        if (arrstr.length != 6) {
            throw new LessOrMoreData("Less or more data");
        }

        for (String s : arrstr) {
            if (s.contains(".")) {
                if (correctDate(s)) {
                    birthday = s;
                } else {
                    throw new IncorrectDate("Incorrect date format");
                }
            } else if (s.equals("f") || s.equals("m")) {
                sex = s.charAt(0);

            } else if (s.length() == 5) {
                try {
                    numPhone = Integer.parseInt(s);

                } catch (NumberFormatException e) {
                    return;
                }
            } else {
                fullName.append(s).append(" ");
            }
        }

        if (sex == ' ') {
            throw new IncorrectSex("Incorrect sex");
        }
        if (numPhone == 0) {
            throw new IncorrectNumPhone("The number format is not valid");
        }

        String surname = fullName.toString();  //Хотела сделать проще без этой строки, в массив arr сразу, но тогда перед фамилией пишет null
        String[] arr = surname.split(" ", 2);
        surname = arr[0];

        dataRecord(surname, fullName.toString(), birthday, numPhone, sex);
    }

    private static boolean correctDate(String birthday) {
        String[] date;
        date = birthday.split("\\.", 3);
        if (date[0].length() == 2) {
            if (date[1].length() == 2) {
                if (date[2].length() == 4) {
                    return true;
                }
            }
        }

        return false;
    }

    private static void dataRecord(String surname, String fullName, String birthday, int numPhone, char sex) {
        try (FileWriter writer = new FileWriter(surname + ".txt", true)) {
            writer.write(fullName);
            writer.write(birthday + " ");
            writer.write(numPhone + " ");
            writer.write(sex + "\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
