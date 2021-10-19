package br.arida.ufc.plugins.silk.entity.impl;

import br.arida.ufc.plugins.silk.entity.Input;
import org.w3c.dom.Element;

import java.util.logging.Logger;

/**
 * Created by gabriellopes9102 on 19/12/2016.
 */
public class InputImpl extends SilkFieldImpl implements Input {
    private static final java.util.logging.Logger log = Logger.getLogger(InputImpl.class.getName());

    private String _var;
    private String _property;

    public InputImpl(Element input) {
        super(input);
        initialize(input);
    }

    protected void initialize(Element input) {
        String path = input.getAttributes().getNamedItem("path").getNodeValue();
        this._var = path.split("/")[0];
        this._property = path.split("/")[1];
    }

    public String getPath() {
        return this._var + "/" + this._property;
    }

    public void setPath(String var, String property) {
        this._var = var;
        this._property = property;
    }

    public void setProperty(String property) {
        this._property = property;
    }

    public String toXML() {
        String str = "";

        str += "\t\t\t\t\t\t"+"<Input path=\""+this.getPath()+"\"/>" + '\n';

        return str;
    }

    /*<Input path="?a/smwprop:KeggDiseaseId" /> <!-- Propriedade EV_T para fazer o match-->
                        <Input path="?b/smwprop:KeggDiseaseId" /> <!-- Propriedade EV_U para fazer o match-->*/
}
