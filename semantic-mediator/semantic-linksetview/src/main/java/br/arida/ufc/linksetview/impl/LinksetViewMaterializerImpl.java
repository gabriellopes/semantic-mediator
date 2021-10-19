package br.arida.ufc.linksetview.impl;

import br.arida.ufc.core.step.LinksMaterializer;
import br.arida.ufc.core.step.impl.MashupStepMaterializerImpl;
import br.arida.ufc.linksetview.LinksetView;
import br.arida.ufc.linksetview.LinksetViewMaterializer;
import br.arida.ufc.linksetview.LinksetViewSpec;
import br.arida.ufc.plugins.silk.impl.SilkMaterializerImpl;

import java.util.Map;

/**
 * Created by gabriellopes9102 on 06/12/2016.
 */
public class LinksetViewMaterializerImpl extends MashupStepMaterializerImpl implements LinksetViewMaterializer {

    private LinksetViewSpec _linkset_view_spec;
    private LinksMaterializer _materializer;

    public LinksetViewMaterializerImpl(LinksetViewSpec linkset_view_spec) {
        super(linkset_view_spec);
        _linkset_view_spec = linkset_view_spec;
        _materializer = loadMaterializer();
    }

    public LinksMaterializer loadMaterializer() {
        return new SilkMaterializerImpl();
    }

    public LinksetViewSpec getLinksetViewSpec() {
        return this._linkset_view_spec;
    }

    /*id_interlink; ev_name_T; ev_name_U; Class_source_T; class_source_U; type_aggregate;
    metric_compare; property_EV_T; property_EV_U*/

    public LinksetView materialize(String output_path) {
        output_path += getExt();

        Object class_target = this._linkset_view_spec.getMatchPredicate().getClassTarget();
        String[] props = {this._linkset_view_spec.getMatchPredicate().getProperty(),
                           this._linkset_view_spec.getMatchPredicate().getProperty()};

        Object class_source_ev_T = this._linkset_view_spec.getExportedView_T().getClassByType(class_target);
        Object class_source_ev_U = this._linkset_view_spec.getExportedView_U().getClassByType(class_target);

        Map<String,String> prefixes = this._linkset_view_spec.getPrefixes();

        //Silk Materializer
        _materializer.materialize(output_path, prefixes, this._linkset_view_spec.getName(),
                                  this._linkset_view_spec.getExportedView_T().getName(),
                                  this._linkset_view_spec.getExportedView_U().getName(),
                                  class_source_ev_T.toString(), class_source_ev_U.toString(),"max",
                                  this._linkset_view_spec.getMatchPredicate().getMetric(),
                                  props);
        return null;
    }

    private String getExt() {
        return ".xml";
    }

}

