package br.arida.ufc.core.entity.impl;

import br.arida.ufc.core.entity.Factory;
import br.arida.ufc.core.entity.impl.FactoryImpl;
import br.arida.ufc.core.entity.impl.MappingImpl;
import br.arida.ufc.core.entity.impl.R2RMLMapping;

import java.io.File;

/**
 * Created by gabriellopes9102 on 12/12/2016.
 */
public class MappingFactory extends FactoryImpl implements Factory{

    public static MappingImpl getMappingManager(File onto_owl_file) {
        return new R2RMLMapping(onto_owl_file);
    }
}
