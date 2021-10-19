package br.arida.ufc.fusion.impl;

import br.arida.ufc.core.step.FusionRulesMaterializer;
import br.arida.ufc.core.step.MashupStep;
import br.arida.ufc.core.step.Materializer;
import br.arida.ufc.core.step.impl.MashupStepMaterializerImpl;
import br.arida.ufc.fusion.FusionMaterializer;
import br.arida.ufc.fusion.FusionSpec;
import br.arida.ufc.plugins.sieve.impl.SieveMaterializerImpl;

/**
 * Created by gabriellopes9102 on 13/01/2017.
 */
public class FusionMaterializerImpl extends MashupStepMaterializerImpl implements FusionMaterializer {
    private FusionRulesMaterializer _materializer;
    private FusionSpec _fusion_spec;

    public FusionMaterializerImpl(FusionSpec fusion_spec) {
        super(fusion_spec);
        this._fusion_spec = fusion_spec;
        _materializer = loadMaterializer();
    }

    private FusionRulesMaterializer loadMaterializer() {
        return new SieveMaterializerImpl();
    }

    public <T extends MashupStep> T materialize(String output_path) {
        int tam = _fusion_spec.getAssertions().length;
        Object[] classes = new Object[tam];
        Object[] properties = new Object[tam];
        Object[] f_functions = new Object[tam];

        for(int i = 0; i < tam; i++) {
            classes[i] = _fusion_spec.getAssertions()[i].getFusionClass();
            properties[i] = _fusion_spec.getAssertions()[i].getFusionProperty();
            f_functions[i] = _fusion_spec.getAssertions()[i].getFusionFunction();
        }

        _materializer.materialize(output_path,_fusion_spec.getPrefixes(), classes,properties,f_functions);
        return null;
    }
}
