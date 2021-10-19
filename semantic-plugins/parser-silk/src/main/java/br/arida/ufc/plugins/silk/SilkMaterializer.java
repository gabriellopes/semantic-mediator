package br.arida.ufc.plugins.silk;

import br.arida.ufc.core.step.LinksMaterializer;
import br.arida.ufc.core.step.Materializer;

import java.util.Map;

/**
 * Created by gabriellopes9102 on 19/12/2016.
 */
public interface SilkMaterializer extends LinksMaterializer {
//    id_interlink; ev_name_T; ev_name_U; Class_source_T; class_source_T; type_aggregate;
//    metric_compare; property_EV_T; property_EV_U

    void materialize(String output_path, Map<String,String> prefixes, String id_interlink, String s_dataSource, String s_class_restriction,
                            String t_dataSource, String t_class_restriction, String type_aggregate,
                            String metric, String[] input_props);
}
