package br.arida.ufc.fusion;

import br.arida.ufc.core.step.MashupStepSpecification;

import java.util.Map;

/**
 * Created by gabriellopes9102 on 13/12/2016.
 */
public interface FusionSpec extends MashupStepSpecification {

    void setPrefixes(Map<String,String> prefixes);

    FusionAssertion[] getAssertions();

    void addFPA(FusionAssertion fpa);

    Map<String,String> getPrefixes();
}
