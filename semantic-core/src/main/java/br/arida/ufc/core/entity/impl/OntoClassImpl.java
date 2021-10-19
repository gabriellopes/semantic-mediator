package br.arida.ufc.core.entity.impl;

import br.arida.ufc.core.entity.OntoClass;
import br.arida.ufc.core.entity.OntologyModel;
import br.arida.ufc.core.entity.Prefix;
import org.apache.jena.ontology.Ontology;
import org.apache.jena.ontology.impl.OntClassImpl;

/**
 * Created by gabriellopes9102 on 03/01/2017.
 */
public abstract class OntoClassImpl implements OntoClass {
    private String _class;
    private Prefix _prefix;
    private OntologyModel _onto_model;

    public OntoClassImpl(OntologyModel onto_model, String uri) {
        this._onto_model = onto_model;
        setClassFromURI(uri);
        setPrefixFromURI(uri);
    }

    public String getURI() {
        return this._prefix.getURI() + this._class;
    }

    public String getClassName() {
        return this._class;
    }

    @Override
    public abstract <T> T as(Class<T> view);

    public Prefix getPrefix() {
        return this._prefix;
    }

    public void setClassFromURI(String uri) {
        this._class = uri.split("#")[1];
    }

    public void setPrefixFromURI(String uri) {
        String p_uri = uri.split("#")[0] + "#";
        String p_key = _onto_model.getPrefixFromURI(p_uri);
        this._prefix = new PrefixImpl(p_key, p_uri);
    }

    public String toString() {
        return this._prefix.getURI() + this._class;
    }

}
