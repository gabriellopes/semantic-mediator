package br.arida.ufc.linksetview;

import br.arida.ufc.core.config.impl.MediatorConfig;
import br.arida.ufc.linksetview.impl.LinksetViewConfigImpl;
import br.arida.ufc.linksetview.impl.LinksetViewSpecImpl;

/**
 * Created by gabriellopes9102 on 23/12/16.
 */
public class LinksetViewFactory {

    public static LinksetViewSpec getSpecImpl(LinksetViewConfig config) {
        return new LinksetViewSpecImpl(config);
    }
}
