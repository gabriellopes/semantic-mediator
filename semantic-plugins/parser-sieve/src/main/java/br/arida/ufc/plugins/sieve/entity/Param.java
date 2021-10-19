package br.arida.ufc.plugins.sieve.entity;

/**
 * Created by gabriellopes9102 on 20/12/2016.
 */
public interface Param extends SieveField {

    String getName();

    void setString(String name);

    String getValue();

    void setValue(String value);
}
