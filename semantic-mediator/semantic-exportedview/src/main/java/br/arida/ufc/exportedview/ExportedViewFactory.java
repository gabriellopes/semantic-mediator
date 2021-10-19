package br.arida.ufc.exportedview;

import br.arida.ufc.exportedview.impl.ExportedViewImpl;
import br.arida.ufc.exportedview.impl.ExportedViewSpecImpl;

/**
 * Created by gabriellopes9102 on 23/12/16.
 */
public class ExportedViewFactory {

    public static ExportedViewSpec getSpecImpl(ExportedViewConfig config) {
        return new ExportedViewSpecImpl(config);
    }
}
