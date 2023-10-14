package Entitites;

import java.time.LocalDate;

public class Sale {
    private Double valor;
    private LocalDate date;

    public Double getValor() {
        return valor;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public Sale(Double valor, LocalDate date) {
        this.valor = valor;
        this.date = date;
    }
}
