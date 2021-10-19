package br.arida.ufc.linksetview.impl;

import br.arida.ufc.core.config.MediatorConfigTest;
import br.arida.ufc.exportedview.ExportedViewSpec;
import br.arida.ufc.exportedview.impl.ExportedViewSpecImpl;
import br.arida.ufc.linksetview.LinksetView;
import br.arida.ufc.linksetview.LinksetViewMaterializer;
import br.arida.ufc.linksetview.LinksetViewSpec;
import junit.framework.TestCase;

/**
 * Created by gabriellopes9102 on 14/12/2016.
 */
public class SilkMaterializerTest extends TestCase{

    public void testMaterialize() throws Exception {
        ExportedViewSpec[] e_views_spec = ExportedViewSpecImpl.load(MediatorConfigTest.getInstance());
        LinksetViewSpec[] ls = LinksetViewSpecImpl.load(MediatorConfigTest.getInstance());
        LinksetView l_view = new LinksetViewImpl(ls[0]);
//        l_view.generateLinksetView();
    }
}
