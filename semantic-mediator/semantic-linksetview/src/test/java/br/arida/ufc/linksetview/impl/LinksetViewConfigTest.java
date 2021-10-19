package br.arida.ufc.linksetview.impl;

import br.arida.ufc.core.config.Config;
import br.arida.ufc.core.config.MediatorConfigTest;
import br.arida.ufc.linksetview.LinksetViewConfig;
import br.arida.ufc.linksetview.LinksetViewSpec;
import junit.framework.TestCase;

/**
 * Created by gabriellopes9102 on 15/12/2016.
 */
public class LinksetViewConfigTest extends TestCase {

    public void testLoad() throws Exception {
        LinksetViewSpec[] spec = LinksetViewSpecImpl.load(MediatorConfigTest.getInstance());
        System.out.println("teste");

    }

    public class LinksetViewConfigImplTest extends LinksetViewConfigImpl {

        public LinksetViewConfigImplTest(Config config) {
            super(config);
        }
    }
    public class LinksetViewTest extends LinksetViewSpecImpl {

        public LinksetViewTest(LinksetViewConfig config) {
            super(config);
        }
    }
}
