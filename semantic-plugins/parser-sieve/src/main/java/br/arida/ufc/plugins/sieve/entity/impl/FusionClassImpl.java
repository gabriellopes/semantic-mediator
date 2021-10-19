package br.arida.ufc.plugins.sieve.entity.impl;

import br.arida.ufc.plugins.sieve.SieveTerms;
import br.arida.ufc.plugins.sieve.entity.FusionClass;
import br.arida.ufc.plugins.sieve.entity.FusionProperty;
import org.w3c.dom.Element;

/**
 * Created by gabriellopes9102 on 20/12/2016.
 */
public class FusionClassImpl extends SieveFieldImpl implements FusionClass {
    private String _name;
    private FusionProperty _property;

    public FusionClassImpl(Element fusion_class) {
        super(fusion_class);
        initialize(fusion_class);
    }

    public FusionClassImpl(String name, Object property) {
        super(null);
        this._name = name;
        this._property = (FusionProperty) property;
    }

    protected void initialize(Element f_class) {
        Element property;

        this._name = f_class.getAttributes().getNamedItem("name").getNodeValue();
        property = (Element) f_class.getElementsByTagName(SieveTerms.FUSION_PROPERTY).item(0);

        this._property = new FusionPropertyImpl(property);
    }

    public String getName() {
        return null;
    }

    public void setName(String name) {

    }

    public String getFusionClass() {
        return null;
    }

    public void setFusionClass(String name) {

    }

    public FusionProperty getFusionProperty() {
        return null;
    }

    public String toXML() {
        String str = "";

        str += "\t\t" + "<Class name=\""+_name+"\">" + '\n' +
                _property.toXML() + '\n' +
                "\t\t" + "</Class>";

        return str;
    }
}
