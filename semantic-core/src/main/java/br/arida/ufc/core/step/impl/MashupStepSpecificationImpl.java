package br.arida.ufc.core.step.impl;

import br.arida.ufc.core.step.MashupStep;
import br.arida.ufc.core.step.MashupStepConfig;
import br.arida.ufc.core.step.MashupStepMaterializer;
import br.arida.ufc.core.step.MashupStepSpecification;
import br.arida.ufc.core.util.FileUtils;

import java.util.logging.Logger;

/**
 * Created by gabriellopes9102 on 29/11/2016.
 */
public abstract class MashupStepSpecificationImpl<T extends MashupStep> implements MashupStepSpecification{
    private Logger log = Logger.getLogger(MashupStepSpecificationImpl.class.getName());

    private MashupStepConfig _config;
    private MashupStepMaterializer _materializer;

    public MashupStepSpecificationImpl(MashupStepConfig config) {
        this._config = config;
    }

    public T materializeView(String output_dir, String view_name) {
        FileUtils.createDirIfNotExists(output_dir);
        String tmp = output_dir + "\\" + view_name;
        log.info("Materializing view ["+view_name+"] on path ["+tmp+"]. . .");

        return this._materializer.materialize(tmp);
    }

    public void setMaterializer(MashupStepMaterializer materializer) {
        this._materializer = materializer;
    }

}
