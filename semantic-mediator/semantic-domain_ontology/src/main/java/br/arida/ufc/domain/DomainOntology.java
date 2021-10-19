package br.arida.ufc.domain;

import br.arida.ufc.core.entity.OntologyModel;
import br.arida.ufc.core.step.MashupStepSpecification;

/**
 * Created by gabriellopes9102 on 13/12/2016.
 */
public interface DomainOntology extends MashupStepSpecification{

    void setOntology(OntologyModel ontologyModel);

    OntologyModel getOntology();
}
