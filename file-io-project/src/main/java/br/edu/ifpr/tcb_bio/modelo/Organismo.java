package br.edu.ifpr.tcb_bio.modelo;

public class Organismo {
    private int idOrganismo;
    private String nomeOrganismo;
    private String descricaoOrganismo;
    private Classe classe;
    private Filo filo;
    private Reino reino;
    
public Organismo(){

}

public int getIdOrganismo() {
    return idOrganismo;
}

public void setIdOrganismo(int idOrganismo) {
    this.idOrganismo = idOrganismo;
}

public String getNomeOrganismo() {
    return nomeOrganismo;
}

public void setNomeOrganismo(String nomeOrganismo) {
    this.nomeOrganismo = nomeOrganismo;
}

public String getDescricaoOrganismo() {
    return descricaoOrganismo;
}

public void setDescricaoOrganismo(String descricaoOrganismo) {
    this.descricaoOrganismo = descricaoOrganismo;
}

public Classe getClasse() {
    return classe;
}

public void setClasse(Classe classe) {
    this.classe = classe;
}

public Filo getFilo() {
    return filo;
}

public void setFilo(Filo filo) {
    this.filo = filo;
}

public Reino getReino() {
    return reino;
}

public void setReino(Reino reino) {
    this.reino = reino;
}

}

