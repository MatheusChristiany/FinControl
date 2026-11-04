package model;

public class Receita extends Movimentacao {

    public Receita() {
        super();
    }

    public Receita(int id, String descricao, double valor,
                   String categoria, String data) {

        super(id, descricao, valor, "Receita", categoria, data);
    }
}