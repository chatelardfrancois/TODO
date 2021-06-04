package bo;

import java.time.LocalDate;

public class Todo {
    private LocalDate date;
    private int id;
    private String texte;

    public Todo(LocalDate date, String texte) {
        this.date = date;
        this.texte = texte;
    }

    public Todo(String texte) {
        this.texte = texte;
    }

    public Todo(int id, LocalDate date, String texte) {
        this.date = date;
        this.id = id;
        this.texte = texte;
    }
}
