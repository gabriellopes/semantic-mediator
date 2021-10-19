package br.arida.ufc.app.impl;

import br.arida.ufc.app.ApplicationSpec;
import br.arida.ufc.core.config.MediatorConfigTest;
import junit.framework.TestCase;

import java.io.File;

/**
 * Created by gabriellopes9102 on 10/01/2017.
 */
public class ApplicationConfigTest extends TestCase {

    public void testDirs() throws Exception {
        ApplicationSpec spec = ApplicationSpecImpl.load(MediatorConfigTest.getInstance());
        String domain = spec.getAppConfig().getDomainOntoDir().getAbsolutePath();
        String e_views = spec.getAppConfig().getExportedViewsDir().getAbsolutePath();
        String l_vies = spec.getAppConfig().getLinksetViewsDir().getAbsolutePath();
        File f = new File(".\\Teste");
        f.mkdir();

        spec.getAppOntology().generateFile("C:\\Users\\gabriellopes9102\\Documents\\IdeaProjects\\semantic-framework\\semantic-application\\..\\example\\teste.owl");

        System.out.println(f.getAbsolutePath());
    }
}
