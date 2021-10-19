package br.arida.ufc.fusion;

/**
 * Created by gabriellopes9102 on 13/01/2017.
 */
public interface FusionAssertion {

    void setFusionClass(Object obj);

    void setFusionProperty(Object obj);

    void setFusionFunction(FusionFunction f_function);

    Object getFusionClass();

    Object getFusionProperty();

    Object getFusionFunction();
}
