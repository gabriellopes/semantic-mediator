package br.arida.ufc.plugins.silk.impl;

import br.arida.ufc.core.util.FileUtils;

import br.arida.ufc.plugins.silk.SilkLayout;
import junit.framework.TestCase;

/**
 * Created by gabriellopes9102 on 19/12/2016.
 */
public class SilkLayoutImplTest extends TestCase {

    public void testInitialize() throws Exception {
        SilkLayout silk = new SilkLayoutImpl(FileUtils.getFileFromResource(this.getClass(),"silkLayout.xml"));
        silk.generateSilkFile("C:\\Users\\gabriellopes9102\\Documents\\IdeaProjects\\semantic-framework\\silk_file.xml");
    }
}
