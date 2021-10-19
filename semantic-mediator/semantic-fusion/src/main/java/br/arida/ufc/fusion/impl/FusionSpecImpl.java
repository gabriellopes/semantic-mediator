package br.arida.ufc.fusion.impl;

import br.arida.ufc.core.config.Config;
import br.arida.ufc.core.step.MashupStep;
import br.arida.ufc.core.step.MashupStepMaterializer;
import br.arida.ufc.core.step.impl.MashupStepSpecificationImpl;
import br.arida.ufc.fusion.FusionAssertion;
import br.arida.ufc.fusion.FusionConfig;
import br.arida.ufc.fusion.FusionSpec;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by gabriellopes9102 on 02/12/2016.
 */
public class FusionSpecImpl extends MashupStepSpecificationImpl<FusionRules> implements FusionSpec{
    private FusionConfig _config;
    private List<FusionAssertion> _fusion_assertions;
    private Map<String,String> _prefixes;

    public FusionSpecImpl(FusionConfig config) {
        super(config);
        _config = config;
        _fusion_assertions = new ArrayList<FusionAssertion>();
        super.setMaterializer(loadMaterializer());
    }

    private MashupStepMaterializer loadMaterializer() {
        return new FusionMaterializerImpl(this);
    }

    public static FusionSpec load(Config config) {
        return new FusionConfigImpl(config).load();
    }

    public void setPrefixes(Map<String, String> prefixes) {
        this._prefixes = prefixes;
    }

    public FusionAssertion[] getAssertions() {
        return this._fusion_assertions.toArray(new FusionAssertion[_fusion_assertions.size()]);
    }

    public void addFPA(FusionAssertion fpa) {
        this._fusion_assertions.add(fpa);
    }

    public Map<String, String> getPrefixes() {
        return this._prefixes;
    }
}
