package br.arida.ufc.core.entity;


import br.arida.ufc.core.entity.impl.FilterConfigImpl;

import java.io.File;

/**
 * Created by gabriellopes9102 on 13/12/2016.
 */
public interface FilterConfig {


    Filter[] getFilters();


    class FilterFactory {

        public static FilterConfig getFilterImpl(File file) {
            return new FilterConfigImpl(file);
        }
    }
}
