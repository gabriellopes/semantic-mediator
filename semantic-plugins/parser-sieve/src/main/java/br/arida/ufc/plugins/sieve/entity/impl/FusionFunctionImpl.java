package br.arida.ufc.plugins.sieve.entity.impl;

import br.arida.ufc.plugins.sieve.entity.FusionFunction;
import org.w3c.dom.Element;

/**
 * Created by gabriellopes9102 on 20/12/2016.
 */
public class FusionFunctionImpl extends SieveFieldImpl implements FusionFunction {
    private String _function_class;
    private String _function_metric;


    public FusionFunctionImpl(Element f_function) {
        super(f_function);
        initialize(f_function);
    }

    public FusionFunctionImpl(String function_class, String metric) {
        super(null);
        this._function_class = function_class;
        this._function_metric = metric == "" ? "sieve:reputation" : metric;
    }

    protected void initialize(Element f_function) {
        this._function_class = f_function.getAttributes().getNamedItem("class").getNodeValue();
        this._function_metric = f_function.getAttributes().getNamedItem("metric").getNodeValue();
    }

    public String getFunctionClass() {
        return this._function_class;
    }

    public void setFunctionClass(String _class) {

    }

    public String getMetric() {
        return this._function_metric;
    }

    public void setMetric(String metric) {

    }

    public String toXML() {
        String str = "";

        str += "\t\t\t\t" + "<FusionFunction class=\""+_function_class+"\" metric=\""+_function_metric+"\"/>";
        return str;
    }
}
