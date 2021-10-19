package br.arida.ufc.core.entity.impl;

import br.arida.ufc.core.entity.Mapping;

import java.io.File;
import java.util.Collection;

/**
 * Created by gabriellopes9102 on 08/12/2016.
 */
public abstract class MappingImpl implements Mapping{
    private File _mapping_file;
    private Collection<Object> _mappings;

    public MappingImpl(File mapping_file) {
        this._mapping_file = mapping_file;
    }

    public MappingImpl(){

    }
}
