package br.arida.ufc.app.spec;


import br.arida.ufc.app.ApplicationConfig;
import br.arida.ufc.app.ApplicationSpec;
import br.arida.ufc.app.impl.ApplicationConfigImpl;
import br.arida.ufc.app.impl.ApplicationSpecImpl;
import br.arida.ufc.core.config.MediatorConfigTest;
import junit.framework.TestCase;

/**
 * Created by gabriellopes9102 on 13/12/2016.
 */
public class ApplicationConfigTest extends TestCase {

    public void testLoadConfig() throws Exception {
        ApplicationSpec spec = ApplicationSpecImpl.load(MediatorConfigTest.getInstance());
        ApplicationConfig config = new ApplicationConfigImpl(MediatorConfigTest.getInstance());

        System.out.println(config.getExportedViewsDir());
    }

    public void testCreateDirs() throws Exception {
        ApplicationConfigImpl config = new ApplicationConfigImpl(MediatorConfigTest.getInstance());
    }
}
