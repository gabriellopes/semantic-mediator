package br.arida.ufc.plugins.sieve.entity;

/**
 * Created by gabriellopes9102 on 20/12/2016.
 */
public interface Fusion extends SieveField {

    FusionClass[] getClasses();

    void addClass(Object _class, Object _prop, Object _function);
}
