package br.arida.ufc.domain.impl;

import br.arida.ufc.core.config.Config;
import br.arida.ufc.core.config.impl.MediatorConfig;
import br.arida.ufc.core.entity.impl.OntologyModelFactory;
import br.arida.ufc.core.step.impl.MashupStepConfigImpl;
import br.arida.ufc.core.util.FileUtils;
import br.arida.ufc.domain.DomainOntologyConfig;
import br.arida.ufc.domain.DomainOntologyFactory;
import br.arida.ufc.domain.DomainOntology;

import java.io.File;
import java.util.logging.Logger;

/**
 * Created by gabriellopes9102 on 08/12/2016.
 */
public class DomainOntologyConfigImpl extends MashupStepConfigImpl implements DomainOntologyConfig {
    private static final Logger log = Logger.getLogger(MediatorConfig.class.getName());

    private Config _config;

    public DomainOntologyConfigImpl(Config config) {
        super(config);
        _config = config;
    }

    protected DomainOntology load(){
        File _onto_dir = _config.getDomainOntoDir();
        File _onto_owl = FileUtils.listFiles(_onto_dir,"owl")[0];
        DomainOntology _domain;

        _domain = getDomainImpl();
        _domain.setOntology(OntologyModelFactory.getOntologyModel(_onto_owl));

        log.info("Ontologia ["+_onto_owl.getName()+"] retornada. . .");
        return _domain;
    }

    private DomainOntology getDomainImpl() {
        return DomainOntologyFactory.getDomainImpl(this);
    }
}
