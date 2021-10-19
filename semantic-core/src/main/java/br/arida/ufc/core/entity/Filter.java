package br.arida.ufc.core.entity;

/**
 * Created by gabriellopes9102 on 13/12/2016.
 */
public interface Filter {

    Object getCondition();

    Object getProperty();

    Object getEntity();


}
