package br.arida.ufc.plugins.silk.entity.impl;

import br.arida.ufc.plugins.silk.entity.Prefix;
import org.w3c.dom.Element;

/**
 * Created by gabriellopes9102 on 12/01/2017.
 */
public class PrefixImpl extends SilkFieldImpl implements Prefix {
    private String _id;
    private String _namespace;

    public PrefixImpl(String id, String namespace) {
        super(null);
        setNamespace(namespace);
        setId(id);
    }

    public PrefixImpl(Element prefix) {
        super(prefix);
        initialize(prefix);
    }

    public String toXML() {
        String str = "";

        str += "\t\t"+ "<Prefix id=\""+_id+"\" namespace=\""+_namespace+"\"/>";

        return str;
    }

    protected void initialize(Element prefix) {
        this._id = prefix.getAttributes().getNamedItem("id").getNodeValue();
        this._namespace = prefix.getAttributes().getNamedItem("namespace").getNodeValue();
    }

    public void setId(String id) {
        this._id = id;
    }

    public String getId() {
        return this._id;
    }

    public String getNamespace() {
        return this._namespace;
    }

    public void setNamespace(String namespace) {
        this._namespace = namespace;
    }
}
