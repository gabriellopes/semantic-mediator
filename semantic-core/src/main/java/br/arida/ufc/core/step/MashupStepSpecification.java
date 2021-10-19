package br.arida.ufc.core.step;

/**
 * Created by gabriellopes9102 on 13/12/2016.
 */

//Get Materializer
    // qual o fluxo para a materialização
public interface MashupStepSpecification {

    <T extends MashupStep> T materializeView(String output_dir, String view_name);
}
