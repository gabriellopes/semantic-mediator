package br.arida.ufc.app.impl;

import br.arida.ufc.app.ApplicationMaterializer;
import br.arida.ufc.app.ApplicationSpec;
import br.arida.ufc.app.ApplicationView;
import br.arida.ufc.core.step.MashupStep;
import br.arida.ufc.core.step.Materializer;
import br.arida.ufc.core.step.impl.MashupStepMaterializerImpl;
import br.arida.ufc.core.util.FileUtils;
import br.arida.ufc.exportedview.ExportedViewSpec;
import br.arida.ufc.linksetview.LinksetView;
import br.arida.ufc.linksetview.LinksetViewSpec;
import br.arida.ufc.linksetview.impl.LinksetViewImpl;

import java.util.Map;

/**
 * Created by gabriellopes9102 on 09/01/2017.
 */
public class ApplicationMaterializerImpl extends MashupStepMaterializerImpl implements ApplicationMaterializer{
    private ApplicationSpec _app_spec;
    private Materializer _materializer;

    public ApplicationMaterializerImpl(ApplicationSpec app_spec) {
        super(app_spec);
        this._app_spec = app_spec;
    }

    public ApplicationView materialize(String output_path) {
        String domain_onto_path = this._app_spec.getAppConfig().getDomainOntoDir().getAbsolutePath();
        String e_views_path = this._app_spec.getAppConfig().getExportedViewsDir().getAbsolutePath();
        String l_views_path = this._app_spec.getAppConfig().getLinksetViewsDir().getAbsolutePath();
        String fusion_path = this._app_spec.getAppConfig().getFusionRulesDir().getAbsolutePath();

        _app_spec.getIntegratedView().getDomainOnto().getOntology().generateFile(domain_onto_path+"\\app_onto");

        for(ExportedViewSpec e_spec : _app_spec.getIntegratedView().getExportedViews()) {
            String e_dir = e_views_path+"\\"+e_spec.getName();
            e_spec.materializeView(e_dir,"onto");
        }

        for(LinksetViewSpec l_spec : _app_spec.getIntegratedView().getLinksetViews()) {
            String l_dir = l_views_path;
            l_spec.materializeView(l_dir,l_spec.getName());
        }
        Map<String, String> prefixes = _app_spec.getIntegratedView().getLinksetViews()[0].getPrefixes();

        _app_spec.getIntegratedView().getFusionSpec().setPrefixes(prefixes);
        _app_spec.getIntegratedView().getFusionSpec().materializeView(fusion_path,"rules");
        return null;
    }

    protected Materializer loadMaterializer() {
//        return new GenericAppMaterializer();
        return null;
    }
}
