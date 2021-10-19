package br.arida.ufc.core.entity.impl;

import br.arida.ufc.core.entity.JenaOntoClass;
import br.arida.ufc.core.entity.JenaOntologyModel;
import br.arida.ufc.core.entity.OntologyModel;
import org.apache.jena.ontology.OntClass;

/**
 * Created by gabriellopes9102 on 05/01/2017.
 */
public class JenaOntoClassImpl extends OntoClassImpl implements JenaOntoClass {
    private JenaOntologyModel _jena_model;

    public JenaOntoClassImpl(OntologyModel onto_model, String uri) {
        super(onto_model, uri);
        this._jena_model = (JenaOntologyModel) onto_model;
    }

    @Override
    public <T> T as(Class<T> view) {
        if(view == OntClass.class) {
            return (T) _jena_model.getModel().getOntClass(this.getURI());
        }
        throw new ClassCastException();
    }
}
