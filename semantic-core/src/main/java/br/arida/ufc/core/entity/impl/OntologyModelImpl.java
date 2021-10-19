package br.arida.ufc.core.entity.impl;

import br.arida.ufc.core.entity.OntologyModel;

import java.io.File;
import java.util.logging.Logger;

/**
 * Created by gabriellopes9102 on 07/12/2016.
 */
public abstract class OntologyModelImpl implements OntologyModel {
    private String _ontology_path;

    public OntologyModelImpl(String ontology_path) {
        this._ontology_path = ontology_path;
    }
    public OntologyModelImpl(File ontology_file) {
        this._ontology_path = ontology_file.toURI().toString();
    }

    public abstract Object getOntoClass(String _class);

    public abstract void createClass(String _class);
    public abstract void removeClass(String _class);

}
