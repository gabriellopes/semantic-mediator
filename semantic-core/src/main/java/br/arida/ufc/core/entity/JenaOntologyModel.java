package br.arida.ufc.core.entity;

import org.apache.jena.ontology.OntModel;
import org.apache.jena.rdf.model.Model;

/**
 * Created by gabriellopes9102 on 15/12/2016.
 */
public interface JenaOntologyModel extends OntologyModel {

    public void listClasses();

    public OntModel getModel();
}
