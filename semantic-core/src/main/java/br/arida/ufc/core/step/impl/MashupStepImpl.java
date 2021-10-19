package br.arida.ufc.core.step.impl;

import br.arida.ufc.core.step.MashupStep;
import br.arida.ufc.core.step.MashupStepMaterializer;
import br.arida.ufc.core.step.MashupStepSpecification;

/**
 * Created by gabriellopes9102 on 29/11/2016.
 */
/*
 Um MashupStep representa um objeto materializado.
 Uma exported_view, por exemplo, ao ser materializada, passa a conter informações que não fazem sentido para
 uma especificação. O objeto exported_view é adquirido por meio da materialização.

 */

public abstract class MashupStepImpl implements MashupStep {
    private MashupStepSpecification _step_specification;
    private MashupStepMaterializer _step_materializer;

    public MashupStepImpl(MashupStepSpecification step_specification) {
        this._step_specification = step_specification;
    }

    //Cada MashupStepImpl tem domínio sobre como carregar seu arquivo de configuração
    /*public static MashupStepImpl load(File _file){

    }*/
}
