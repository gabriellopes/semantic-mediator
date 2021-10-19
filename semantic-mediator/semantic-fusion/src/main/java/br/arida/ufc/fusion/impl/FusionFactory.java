package br.arida.ufc.fusion.impl;

import br.arida.ufc.fusion.FusionConfig;
import br.arida.ufc.fusion.FusionSpec;

/**
 * Created by gabriellopes9102 on 13/01/2017.
 */
public class FusionFactory {

    public static FusionSpec getSpecImpl(FusionConfig config) {
        return new FusionSpecImpl(config);
    }

}
