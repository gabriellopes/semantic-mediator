package br.arida.ufc.core.step;

import java.util.Map;

/**
 * Created by gabriellopes9102 on 13/01/2017.
 */
public interface LinksMaterializer extends Materializer {

    void materialize(String output_path, Map<String,String> prefixes, String name, String name1, String name2, String s, String s1,
                     String max, String metric, String[] props);
}
