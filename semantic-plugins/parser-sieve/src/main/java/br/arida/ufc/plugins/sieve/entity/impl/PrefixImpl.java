package br.arida.ufc.plugins.sieve.entity.impl;

import br.arida.ufc.plugins.sieve.entity.Prefix;
import org.w3c.dom.Element;

/**
 * Created by gabriellopes9102 on 12/01/2017.
 */
public class PrefixImpl extends SieveFieldImpl implements Prefix {
    private String _id;
    private String _namespace;

    public PrefixImpl(Element prefix) {
        super(prefix);
        initialize(prefix);
    }

    public PrefixImpl(String id, String _namespace) {
        super(null);
        this._id = id;
        this._namespace = _namespace;
    }

    public String toXML() {
        String str = "";

        str += "\t\t"+ "<Prefix id=\""+_id+"\" namespace=\""+_namespace+"\"/>" + '\n';

        return str;
    }

    protected void initialize(Element prefix) {
        this._id = prefix.getAttributes().getNamedItem("id").getNodeValue();
        this._namespace = prefix.getAttributes().getNamedItem("namespace").getNodeValue();
    }

    public String getId() {
        return this._id;
    }

    public String getNamespace() {
        return this._namespace;
    }
}
