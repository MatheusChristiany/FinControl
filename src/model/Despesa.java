package model;

public class Despesa extends Movimentacao {

    public Despesa() {
        super();
    }

    public Despesa(int id, String descricao, double valor,
                   String categoria, String data) {

        super(id, descricao, valor, "Despesa", categoria, data);
    }
}