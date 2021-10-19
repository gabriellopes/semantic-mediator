package br.arida.ufc.integrated;

import br.arida.ufc.core.entity.OntologyModel;
import br.arida.ufc.domain.DomainOntology;
import br.arida.ufc.exportedview.ExportedViewSpec;
import br.arida.ufc.fusion.FusionSpec;
import br.arida.ufc.linksetview.LinksetViewSpec;

/**
 * Created by gabriellopes9102 on 13/12/2016.
 */
public interface SemanticIntegratedView {

    DomainOntology getDomainOnto();

//    ExportedView[] getExportedViews();
    ExportedViewSpec[] getExportedViews();

//    LinksetView[] getLinksetViews();
    LinksetViewSpec[] getLinksetViews();

    FusionSpec getFusionSpec();
}
