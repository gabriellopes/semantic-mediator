package br.arida.ufc.plugins.sieve.impl;


import br.arida.ufc.core.util.FileUtils;
import br.arida.ufc.plugins.sieve.SieveLayout;
import junit.framework.TestCase;

/**
 * Created by gabriellopes9102 on 12/01/2017.
 */
public class SieveTest extends TestCase {

    public void testSieveLayout() throws Exception {
        SieveLayout sieve = new SieveLayoutImpl(FileUtils.getFileFromResource(this.getClass(),"sieveLayout.xml"));
        sieve.generateSieveFile("C:\\Users\\gabriellopes9102\\Documents\\IdeaProjects\\semantic-framework\\sieve_file.xml");
    }
}
