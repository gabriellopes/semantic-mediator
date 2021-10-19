package br.arida.ufc.core.step;

import java.util.Map;

/**
 * Created by gabriellopes9102 on 13/01/2017.
 */
public interface FusionRulesMaterializer extends Materializer {

    void materialize(String output_path, Map<String, String> prefixes, Object[] _class, Object[] _property, Object[] _fusion_function);
}
