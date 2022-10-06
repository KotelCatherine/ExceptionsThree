package ru.geekbrains.lessons;

public class IncorrectDate extends RuntimeException{
    public IncorrectDate(String massage) {
        super(massage);
    }
}
