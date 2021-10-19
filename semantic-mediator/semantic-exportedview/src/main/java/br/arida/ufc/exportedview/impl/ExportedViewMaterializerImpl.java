package br.arida.ufc.exportedview.impl;

import br.arida.ufc.core.step.MashupStep;
import br.arida.ufc.core.step.MashupStepMaterializer;
import br.arida.ufc.core.step.Materializer;
import br.arida.ufc.core.step.impl.MashupStepMaterializerImpl;
import br.arida.ufc.exportedview.ExportedView;
import br.arida.ufc.exportedview.ExportedViewMaterializer;
import br.arida.ufc.exportedview.ExportedViewSpec;

/**
 * Created by gabriellopes9102 on 11/01/2017.
 */
public class ExportedViewMaterializerImpl extends MashupStepMaterializerImpl implements ExportedViewMaterializer {
    private ExportedViewSpec _e_view_spec;

    public ExportedViewMaterializerImpl(ExportedViewSpec e_view_spec) {
        super(e_view_spec);
        this._e_view_spec = e_view_spec;
    }

    public ExportedView materialize(String output_path) {
        this._e_view_spec.getMapping().generateFile(output_path);
        this._e_view_spec.getOntoSource().generateFile(output_path);
        return null;
    }

}
