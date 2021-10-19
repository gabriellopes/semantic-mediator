package br.arida.ufc.plugins.sieve.entity.impl;

import br.arida.ufc.plugins.sieve.SieveTerms;
import br.arida.ufc.plugins.sieve.entity.FusionFunction;
import br.arida.ufc.plugins.sieve.entity.FusionProperty;
import org.w3c.dom.Element;

/**
 * Created by gabriellopes9102 on 20/12/2016.
 */
public class FusionPropertyImpl extends SieveFieldImpl implements FusionProperty {
    private String _name;
    private FusionFunction _fusion_function;

    public FusionPropertyImpl(Element f_property) {
        super(f_property);
        initialize(f_property);
    }

    public FusionPropertyImpl(String name, Object function) {
        super(null);
        _name = name;
        _fusion_function = (FusionFunction)function;
    }

    protected void initialize(Element f_property) {
        Element f_function;

        f_function = (Element) f_property.getElementsByTagName(SieveTerms.FUSION_FUNCTION).item(0);
        this._name = f_property.getAttributes().getNamedItem("name").getNodeValue();

        this._fusion_function = new FusionFunctionImpl(f_function);
    }

    public String getName() {
        return null;
    }

    public void setName(String name) {

    }

    public FusionFunction getFusionFunction() {
        return null;
    }

    public String toXML() {
        String str = "";

        str += "\t\t\t" + "<Property name=\""+_name+"\">" + '\n' +
                    _fusion_function.toXML() + '\n' +
                "\t\t\t" + "</Property>";
        return str;
    }
}
