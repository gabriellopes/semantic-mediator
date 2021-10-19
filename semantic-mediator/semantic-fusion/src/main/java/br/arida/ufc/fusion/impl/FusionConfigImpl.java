package br.arida.ufc.fusion.impl;

import br.arida.ufc.core.config.Config;
import br.arida.ufc.core.step.impl.MashupStepConfigImpl;
import br.arida.ufc.core.util.FileUtils;
import br.arida.ufc.fusion.FusionConfig;
import br.arida.ufc.fusion.FusionSpec;

import java.io.File;
import java.io.IOException;
import java.util.logging.Logger;

/**
 * Created by gabriellopes9102 on 02/12/2016.
 */
public class FusionConfigImpl extends MashupStepConfigImpl implements FusionConfig {
    private final Logger log = Logger.getLogger(FusionConfigImpl.class.getName());
    private Config _config;

    public FusionConfigImpl(Config config) {
        super(config);
        _config = config;
    }

    //Ler arquivo XML e popular fusionspec
//    FPA: moa:labelName[mo:Record] = KeepSingleValueByReputation/moa:labelName
    protected FusionSpec load() {
        File fusion_dir = _config.getFusionRulesDir();
        File[] filesXML = FileUtils.listFiles(fusion_dir,"fpa");
        String fpa = "";
        String[] set_fpa;
        FusionSpec f_spec = getFusionSpecImpl();

        try {
            for (File f : filesXML) {
                fpa = FileUtils.readFile(f);
                set_fpa = fpa.split("\n");

                for(String fp : set_fpa) {
                    if(fp != null)
                        f_spec.addFPA(new FusionAssertionImpl(fp));
                }
            }

        } catch (IOException e) {
            log.warning("Error found while trying to read the file ["+filesXML[0].getName()+"] on ["+fusion_dir+"] directory. . .");
            e.printStackTrace();
        }

        return f_spec;
    }

    public FusionSpec getFusionSpecImpl() {
        return FusionFactory.getSpecImpl(this);
    }
}
