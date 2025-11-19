package model  ;

import java.time.LocalDate;

public class Despesas {

    private int id;
    private int usuarioId;
    private String descricao;
    private String categoria;
    private double valor;
    private LocalDate data_cadastro;


    public Despesas(int id, int usuarioId, String descricao, String categoria, double valor, LocalDate data_cadastro) {
        this.id = id;
        this.usuarioId = usuarioId;
        this.descricao = descricao;
        this.categoria = categoria;
        this.valor = valor;
        this.data_cadastro = data_cadastro;

    }
    public int getId() {return id;}
    public void setId(int id) {this.id = id;}

    public  int getUsuarioId() {return usuarioId;}
    public void setUsuarioId(int usuario){this.usuarioId = usuario;}

    public String getDescricao() {return descricao;}
    public void setDescricao(String descricao) {this.descricao = descricao;}

    public String getCategoria() {return categoria;}
    public void setCategoria(String categoria){this.categoria = categoria;}

    public double getValor() {return valor;}
    public void setValor(double valor){this.valor= valor;}

    public LocalDate getData_cadastro() {return data_cadastro;}
    public void setData_cadastro(LocalDate data_cadastro){this.data_cadastro = data_cadastro;}


}
