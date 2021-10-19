package br.arida.ufc.app.impl;

import br.arida.ufc.app.ApplicationSpec;
import br.arida.ufc.app.ApplicationView;
import br.arida.ufc.core.config.MediatorConfigTest;
import br.arida.ufc.core.util.Log4jHelper;
import br.arida.ufc.exportedview.ExportedViewSpec;
import br.arida.ufc.exportedview.impl.ExportedViewSpecImpl;
import br.arida.ufc.fusion.FusionSpec;
import br.arida.ufc.fusion.impl.FusionSpecImpl;
import br.arida.ufc.integrated.SemanticIntegratedView;
import br.arida.ufc.integrated.SemanticIntegratedViewTest;
import br.arida.ufc.integrated.impl.SemanticIntegratedViewImpl;
import br.arida.ufc.linksetview.LinksetViewSpec;
import br.arida.ufc.linksetview.impl.LinksetViewSpecImpl;
import junit.framework.TestCase;

/**
 * Created by gabriellopes9102 on 09/01/2017.
 */
public class ApplicationViewTest extends TestCase {


    public void testCreateApplication() throws Exception {
        SemanticIntegratedView view = SemanticIntegratedViewTest.getSemanticIntegratedViewTest();
        ApplicationSpec spec = ApplicationSpecImpl.load(MediatorConfigTest.getInstance());

        spec.createApplication(view);

        spec.materializeView("C:\\Users\\gabriellopes9102\\Documents\\IdeaProjects\\semantic-framework","");

    }


}
