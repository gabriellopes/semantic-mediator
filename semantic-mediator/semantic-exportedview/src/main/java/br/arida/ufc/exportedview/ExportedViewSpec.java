package br.arida.ufc.exportedview;

import br.arida.ufc.core.entity.Mapping;
import br.arida.ufc.core.entity.OntologyModel;
import br.arida.ufc.core.step.MashupStepSpecification;

/**
 * Created by gabriellopes9102 on 13/12/2016.
 */
public interface ExportedViewSpec extends MashupStepSpecification{

    String getName();

    Object getClassByType(Object type);

    void setName(String nodeValue);

    void setOntoSource(OntologyModel ontologyModel);

    void setMapping(Mapping mappingManager);

    Mapping getMapping();

    OntologyModel getOntoSource();

}
