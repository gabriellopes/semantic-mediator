package br.arida.ufc.plugins.silk.entity;

/**
 * Created by gabriellopes9102 on 19/12/2016.
 */
public interface Aggregate extends SilkField {

    public String getType();

    public void setType(String type);

    public Compare getCompare();
}
