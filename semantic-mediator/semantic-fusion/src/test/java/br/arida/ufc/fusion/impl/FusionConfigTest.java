package br.arida.ufc.fusion.impl;

import br.arida.ufc.core.config.MediatorConfigTest;
import br.arida.ufc.fusion.FusionSpec;
import junit.framework.TestCase;

/**
 * Created by gabriellopes9102 on 13/01/2017.
 */
public class FusionConfigTest extends TestCase {
    public void testLoad() throws Exception {
        FusionSpec fusion_spec = FusionSpecImpl.load(MediatorConfigTest.getInstance());

    }
}
