package br.arida.ufc.plugins.silk.entity;

/**
 * Created by gabriellopes9102 on 19/12/2016.
 */
public interface Compare extends SilkField {


    String getMetric();

    void setMetric(String metric);

    Input[] getInputList();
}
