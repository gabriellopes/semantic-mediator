package br.arida.ufc.app;

import br.arida.ufc.core.config.Config;
import br.arida.ufc.core.step.MashupStepConfig;

import java.io.File;

/**
 * Created by gabriellopes9102 on 13/12/2016.
 */
public interface ApplicationConfig extends MashupStepConfig, Config {

    File getExportedViewsDir();

    File getLinksetViewsDir();

    File getDomainOntoDir();
}
