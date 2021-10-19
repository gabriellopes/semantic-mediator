package br.arida.ufc.app;

import br.arida.ufc.core.entity.Filter;
import br.arida.ufc.core.entity.FilterConfig;
import br.arida.ufc.core.entity.OntologyModel;
import br.arida.ufc.core.entity.impl.OntologyModelImpl;
import br.arida.ufc.core.step.MashupStepSpecification;
import br.arida.ufc.integrated.SemanticIntegratedView;

/**
 * Created by gabriellopes9102 on 13/12/2016.
 */
public interface ApplicationSpec extends MashupStepSpecification {
    
    void setAppOntology(OntologyModelImpl ontologyModel);

    void setFilter(FilterConfig filterImpl);

    Filter[] getFilters();

    OntologyModel getAppOntology();

    FilterConfig getFilterConfig();

    ApplicationConfig getAppConfig();

    SemanticIntegratedView getIntegratedView();

    void createApplication(SemanticIntegratedView semantic_view);

}
