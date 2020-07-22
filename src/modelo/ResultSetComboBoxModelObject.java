/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

/**
 *
 * @author ANTONIO-LIBORIO
 */
public class ResultSetComboBoxModelObject {
    
    private Integer codigo;
    private String descri;

    public ResultSetComboBoxModelObject(Integer codigo, String descri) {
        this.codigo = codigo;
        this.descri = descri;
    }

    @Override
    public String toString() {
        return this.getDescri();
    }

    public Integer getCodigo() {
        return codigo;
    }

    public void setCodigo(Integer codigo) {
        this.codigo = codigo;
    }

    public String getDescri() {
        return descri;
    }

    public void setDescri(String descri) {
        this.descri = descri;
    }
}
