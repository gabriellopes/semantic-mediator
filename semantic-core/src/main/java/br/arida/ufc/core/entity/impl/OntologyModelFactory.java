package br.arida.ufc.core.entity.impl;

import br.arida.ufc.core.entity.Factory;

import java.io.File;

/**
 * Created by gabriellopes9102 on 08/12/2016.
 */
public class OntologyModelFactory extends FactoryImpl implements Factory{

    public static OntologyModelImpl getOntologyModel(File onto_owl_file) {
        return new JenaOntologyModelImpl(onto_owl_file);
    }
}
