package br.arida.ufc.app.impl;

import br.arida.ufc.app.ApplicationMaterializer;
import br.arida.ufc.app.ApplicationSpec;
import br.arida.ufc.app.ApplicationView;
import br.arida.ufc.core.config.impl.MediatorConfig;
import br.arida.ufc.core.entity.Mapping;
import br.arida.ufc.core.entity.OntologyModel;
import br.arida.ufc.core.step.impl.MashupStepImpl;
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
import br.arida.ufc.linksetview.LinksetViewSpec;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by gabriellopes9102 on 30/11/2016.
 */
public class ApplicationViewImpl extends MashupStepImpl implements ApplicationView{
    private ApplicationSpec _app_spec;


    public ApplicationViewImpl(ApplicationSpec app_spec) {
        super(app_spec);
        this._app_spec = app_spec;

    }





}
