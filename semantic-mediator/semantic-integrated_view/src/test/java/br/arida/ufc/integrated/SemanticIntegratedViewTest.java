package br.arida.ufc.integrated;

import br.arida.ufc.core.config.MediatorConfigTest;
import br.arida.ufc.domain.DomainOntology;
import br.arida.ufc.domain.impl.DomainOntologyImpl;
import br.arida.ufc.exportedview.ExportedViewSpec;
import br.arida.ufc.exportedview.impl.ExportedViewSpecImpl;
import br.arida.ufc.fusion.FusionSpec;
import br.arida.ufc.fusion.impl.FusionSpecImpl;
import br.arida.ufc.integrated.impl.SemanticIntegratedViewImpl;
import br.arida.ufc.linksetview.LinksetViewSpec;
import br.arida.ufc.linksetview.impl.LinksetViewSpecImpl;
import junit.framework.TestCase;

/**
 * Created by gabriellopes9102 on 13/12/2016.
 */
public class SemanticIntegratedViewTest extends TestCase {

    public void testMediatedView() throws Exception {
        DomainOntology domain_ontology = DomainOntologyImpl.load(MediatorConfigTest.getInstance());
        ExportedViewSpec[] e_views_spec = ExportedViewSpecImpl.load(MediatorConfigTest.getInstance());
        LinksetViewSpec[] ls_views_spec = LinksetViewSpecImpl.load(MediatorConfigTest.getInstance());
        FusionSpec fusion_spec = FusionSpecImpl.load(MediatorConfigTest.getInstance());

        SemanticIntegratedView view = new SemanticIntegratedViewImpl(domain_ontology,e_views_spec,ls_views_spec,fusion_spec);


    }

    public static SemanticIntegratedView getSemanticIntegratedViewTest() {
        DomainOntology domain_ontology = DomainOntologyImpl.load(MediatorConfigTest.getInstance());
        ExportedViewSpec[] e_views_spec = ExportedViewSpecImpl.load(MediatorConfigTest.getInstance());
        LinksetViewSpec[] ls_views_spec = LinksetViewSpecImpl.load(MediatorConfigTest.getInstance());
        FusionSpec fusion_spec = FusionSpecImpl.load(MediatorConfigTest.getInstance());

        SemanticIntegratedView view = new SemanticIntegratedViewImpl(domain_ontology,e_views_spec,ls_views_spec,fusion_spec);

        return view;
    }
}
