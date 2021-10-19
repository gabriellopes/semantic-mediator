package br.arida.ufc.plugins.sieve.entity.impl;

import br.arida.ufc.plugins.sieve.entity.Param;
import org.w3c.dom.Element;

/**
 * Created by gabriellopes9102 on 20/12/2016.
 */
public class ParamImpl extends SieveFieldImpl implements Param {
    private String _name;
    private String _value;

    public ParamImpl(Element param) {
        super(param);
        initialize(param);
    }

    protected void initialize(Element param) {
        _name = param.getAttributes().getNamedItem("name").getNodeValue();
        _value = param.getAttributes().getNamedItem("value").getNodeValue();
    }

    public String getName() {
        return null;
    }

    public void setString(String name) {

    }

    public String getValue() {
        return null;
    }

    public void setValue(String value) {
        this._value = value;
    }

    public String toXML() {
        String str = "";

        str += "\t\t\t\t" + "<Param name=\""+_name+"\" value=\""+_value+"\" />" + '\n';

        return str;
    }
}
