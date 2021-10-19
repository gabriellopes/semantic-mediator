package br.arida.ufc.fusion.impl;


import br.arida.ufc.fusion.FusionAssertion;
import br.arida.ufc.fusion.FusionFunction;


/**
 * Created by gabriellopes9102 on 13/01/2017.
 */
public class FusionAssertionImpl implements FusionAssertion{
    private FusionFunction _fusion_function;
    private Object _fusion_property;
    private Object _fusion_class;

    public FusionAssertionImpl(String fpa) {
        read(fpa);
    }

//    FPA: moa:labelName[mo:Record] = KeepSingleValueByReputation/moa:labelName
    public void read(String fpa) {
        fpa = fpa.substring(fpa.indexOf(":")+1,fpa.length()); // Começa o string a partir do FPA: em diante
        String left = fpa.split("=")[0];
        String right = fpa.split("=")[1];

        String _property = left.substring(0,left.indexOf("[")).trim(); // propriedade é o que estiver escrito até '['
        String _class = left.substring(left.indexOf("[")+1,left.indexOf("]")).trim();

        String _function = right.substring(0,right.indexOf("/"));

        this.setFusionClass(_class);
        this.setFusionProperty(_property);
        this.setFusionFunction(new FusionFunctionImpl(_function));
    }

    public void setFusionClass(Object f_class) {
        this._fusion_class = f_class;
    }

    public void setFusionProperty(Object f_prop) {
        this._fusion_property = f_prop;
    }

    public void setFusionFunction(FusionFunction f_function) {
        this._fusion_function = f_function;
    }

    public Object getFusionClass() {
        return this._fusion_class;
    }

    public Object getFusionProperty() {
        return this._fusion_property;
    }

    public Object getFusionFunction() {
        return this._fusion_function;
    }
}
