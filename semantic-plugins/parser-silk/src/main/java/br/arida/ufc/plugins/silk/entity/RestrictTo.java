package br.arida.ufc.plugins.silk.entity;

/**
 * Created by gabriellopes9102 on 19/12/2016.
 */
public interface RestrictTo extends SilkField{

    public String getRestriction();

    public void setClassRestriction(String class_restriction);

    public String toXML();
}
