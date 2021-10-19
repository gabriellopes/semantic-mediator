package br.arida.ufc.linksetview;

import br.arida.ufc.core.step.MashupStep;

/**
 * Created by gabriellopes9102 on 13/12/2016.
 */
public interface LinksetView extends MashupStep {

    LinksetViewSpec getLinksetViewSpec();

    String getName();

//    void generateLinksetView(String output_path);
}
