package bo;

import java.time.LocalDate;

public class Todo {
    private final LocalDate date;
    private int id;
    private final String texte;
    private LocalDate reussi;

    @Override
    public String toString() {
        return "Todo{" +
                "date=" + date +
                ", id=" + id +
                ", texte='" + texte + '\'' +
                '}';
    }

    public Todo(int id, LocalDate date, String texte, LocalDate reussi) {
        this.date = date;
        this.id = id;
        this.texte = texte;
        this.reussi = reussi;
    }

    public Todo(String texte) {
        this.texte = texte;
        this.date = getDate();
    }

    public LocalDate getReussi() {
        return reussi;
    }

    public String getTexte() {
        return texte;
    }

    public LocalDate getDate() {
        return date;
    }

    public int getId() {
        return id;
    }
}
