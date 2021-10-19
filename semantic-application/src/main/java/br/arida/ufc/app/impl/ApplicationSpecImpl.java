package br.arida.ufc.app.impl;

import br.arida.ufc.app.ApplicationConfig;
import br.arida.ufc.app.ApplicationMaterializer;
import br.arida.ufc.app.ApplicationSpec;
import br.arida.ufc.app.ApplicationView;
import br.arida.ufc.core.config.impl.MediatorConfig;
import br.arida.ufc.core.entity.Filter;
import br.arida.ufc.core.entity.FilterConfig;
import br.arida.ufc.core.config.Config;
import br.arida.ufc.core.entity.Mapping;
import br.arida.ufc.core.entity.OntologyModel;
import br.arida.ufc.core.entity.impl.OntologyModelImpl;
import br.arida.ufc.core.step.impl.MashupStepSpecificationImpl;
import br.arida.ufc.core.util.Log4jHelper;
import br.arida.ufc.domain.DomainOntology;
import br.arida.ufc.domain.DomainOntologyConfig;
import br.arida.ufc.domain.DomainOntologyFactory;
import br.arida.ufc.domain.impl.DomainOntologyConfigImpl;
import br.arida.ufc.exportedview.ExportedViewConfig;
import br.arida.ufc.exportedview.ExportedViewFactory;
import br.arida.ufc.exportedview.ExportedViewSpec;
import br.arida.ufc.exportedview.impl.ExportedViewConfigImpl;
import br.arida.ufc.exportedview.impl.ExportedViewSpecImpl;
import br.arida.ufc.integrated.SemanticIntegratedView;
import br.arida.ufc.integrated.impl.SemanticIntegratedViewImpl;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by gabriellopes9102 on 12/12/2016.
 */
public class ApplicationSpecImpl extends MashupStepSpecificationImpl<ApplicationView> implements ApplicationSpec{
    private ApplicationConfig _config;
    private OntologyModel _app_ontology;
    private FilterConfig _filter_config;

    private SemanticIntegratedView _semantic_view;

    public ApplicationSpecImpl(ApplicationConfig config) {
        super(config);
        super.setMaterializer(loadMaterializer());
        this._config = config;
    }

    /*public ApplicationSpecImpl(OntologyModel app_onto, ) {

    }
*/
    public void createApplication(SemanticIntegratedView semantic_view) {
        List<ExportedViewSpec> e_specs = new ArrayList<ExportedViewSpec>();
        ExportedViewConfig e_config = new ExportedViewConfigImpl(_config);
        DomainOntologyConfig d_config = new DomainOntologyConfigImpl(_config);

        DomainOntology domain_onto = DomainOntologyFactory.getDomainImpl(d_config);
        domain_onto.setOntology(getAppOntology());

        for(ExportedViewSpec e_spec : semantic_view.getExportedViews()) {
            OntologyModel o = getAppOntology().getIntersectionWithOntology(e_spec.getOntoSource());
            Mapping m = e_spec.getMapping().getMappingIntersectionWithOntology(o);
            m.addFilter(getFilters());

            ExportedViewSpec e = ExportedViewFactory.getSpecImpl(e_config);

            e.setMapping(m);
            e.setOntoSource(o);
            e.setName("app_"+e_spec.getName());

            e_specs.add(e);
        }

        this._semantic_view = new SemanticIntegratedViewImpl(domain_onto,e_specs.toArray(new ExportedViewSpecImpl[e_specs.size()]),semantic_view.getLinksetViews(),semantic_view.getFusionSpec());
    }




    /**
     *
     *
     *
     * GETTERS & SETTERS
     *
     *
     *
     *
     */



    private ApplicationMaterializer loadMaterializer() {
        return new ApplicationMaterializerImpl(this);
    }

    public OntologyModel getAppOntology() {
        return this._app_ontology;
    }

    public FilterConfig getFilterConfig() {
        return this._filter_config;
    }

    public ApplicationConfig getAppConfig() {
        return this._config;
    }

    public SemanticIntegratedView getIntegratedView() {
        return this._semantic_view;
    }

    public void setAppOntology(OntologyModelImpl app_ontology) {
        this._app_ontology = app_ontology;
    }

    public void setFilter(FilterConfig filterConfig) {
        this._filter_config = filterConfig;
    }

    public Filter[] getFilters() {

        return this._filter_config.getFilters();
    }

    public static ApplicationSpec load(Config config) {
        return new ApplicationConfigImpl(config).loadConfig();
    }


}
