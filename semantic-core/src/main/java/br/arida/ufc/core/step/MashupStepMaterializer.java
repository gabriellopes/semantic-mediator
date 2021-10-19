package br.arida.ufc.core.step;

/**
 * Created by gabriellopes9102 on 13/12/2016.
 */
public interface MashupStepMaterializer {

    <T extends MashupStep> T materialize(String output_path);
}
