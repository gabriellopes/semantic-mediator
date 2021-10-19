package br.arida.ufc.exportedview.impl;

import br.arida.ufc.core.step.impl.MashupStepImpl;
import br.arida.ufc.exportedview.ExportedViewSpec;
import br.arida.ufc.exportedview.ExportedView;

/**
 * Created by gabriellopes9102 on 30/11/2016.
 */
public class ExportedViewImpl extends MashupStepImpl implements ExportedView{

    private ExportedViewSpec _exported_view;

    public ExportedViewImpl(ExportedViewSpec exported_view) {
        super(exported_view);
        this._exported_view = exported_view;
    }

}
