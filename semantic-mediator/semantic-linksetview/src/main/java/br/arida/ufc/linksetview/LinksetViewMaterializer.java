package br.arida.ufc.linksetview;

import br.arida.ufc.core.step.MashupStepMaterializer;
import br.arida.ufc.core.step.Materializer;

/**
 * Created by gabriellopes9102 on 13/12/2016.
 */
public interface LinksetViewMaterializer extends MashupStepMaterializer {

//    id_interlink; ev_name_T; ev_name_U; Class_source_T; class_source_T; type_aggregate;
//    metric_compare; property_EV_T; property_EV_U

    LinksetViewSpec getLinksetViewSpec();

    Materializer loadMaterializer();

    /*public void materialize(String id_linksetview, String name_e_view_T, String name_e_view_U,
                            String class_source_T, String class_source_U, String aggregate_type,
                            String match_predicate, String property_ev_T, String property_ev_U);*/


}
