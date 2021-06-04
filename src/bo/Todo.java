package bo;

import java.time.LocalDate;

public class Todo {
    private LocalDate date;
    private int id;
    private String texte;
    private LocalDate reussi;

    @Override
    public String toString() {
        return "Todo{" +
                "date=" + date +
                ", id=" + id +
                ", texte='" + texte + '\'' +
                '}';
    }



    public Todo(LocalDate date, String texte) {
        this.date = date;
        this.texte = texte;
    }

    public Todo(String texte) {
        this.texte = texte;
        this.date = getDate();
    }

    public Todo(int id, LocalDate date, String texte) {
        this.date = date;
        this.id = id;
        this.texte = texte;
    }

    public LocalDate getReussi() {
        return reussi;
    }

    public void setReussi(LocalDate reussi) {
        this.reussi = reussi;
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
