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

    public String getTexte() {
        return texte;
    }

    public void setTexte(String texte) {
        this.texte = texte;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
