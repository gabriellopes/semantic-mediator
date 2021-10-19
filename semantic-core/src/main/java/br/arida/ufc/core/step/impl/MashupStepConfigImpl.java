package br.arida.ufc.core.step.impl;

import br.arida.ufc.core.config.Config;
import br.arida.ufc.core.step.MashupStepConfig;

import java.io.File;

/**
 * Created by gabriellopes9102 on 29/11/2016.
 */

/*
* Classe que conhece o diretório e, caso tenha, o arquivo xml de configuração de cada step.
* Vantagem: caso o schema ou formato de arquivo seja modificado, basta alterar na classe config.
 */

public abstract class MashupStepConfigImpl implements MashupStepConfig{
    private Config _config;

    public MashupStepConfigImpl(Config config) {
        _config = config;
    }

    private MashupStepConfigImpl fromXML(File file) {
        return null;
    }
    //public abstract void loadConfig(File file);
}
