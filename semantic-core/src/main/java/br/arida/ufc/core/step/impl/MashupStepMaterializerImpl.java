package br.arida.ufc.core.step.impl;

import br.arida.ufc.core.step.MashupStepSpecification;
import br.arida.ufc.core.step.Materializer;
import br.arida.ufc.core.step.MashupStep;
import br.arida.ufc.core.step.MashupStepMaterializer;

/**
 * Created by gabriellopes9102 on 06/12/2016.
 */
public abstract class MashupStepMaterializerImpl implements MashupStepMaterializer{
    private MashupStepSpecification _mashup_step_spec;
    private Materializer _materializer; //Cada materializador pode possuir

    public MashupStepMaterializerImpl(MashupStepSpecification mashup_step_spec) {
        this._mashup_step_spec = mashup_step_spec;
    }


}
