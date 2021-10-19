package br.arida.ufc.fusion.impl;

import br.arida.ufc.core.step.impl.MashupStepImpl;
import br.arida.ufc.fusion.FusionSpec;

/**
 * Created by gabriellopes9102 on 02/12/2016.
 */
public class FusionRules extends MashupStepImpl {
    private FusionSpec _fusion_spec;

    public FusionRules(FusionSpec step_specification) {
        super(step_specification);
        _fusion_spec = step_specification;
    }
}
