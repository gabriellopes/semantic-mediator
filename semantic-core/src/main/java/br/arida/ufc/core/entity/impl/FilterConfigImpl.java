package br.arida.ufc.core.entity.impl;

import br.arida.ufc.core.entity.Filter;
import br.arida.ufc.core.entity.FilterConfig;
import br.arida.ufc.core.util.FileUtils;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by gabriellopes9102 on 13/12/2016.
 */
public class FilterConfigImpl implements FilterConfig {
    private File _file_filter;
    private Filter[] _filters;

    private String[] _operators = {"<",">","=",">=","<=","!="};

    public FilterConfigImpl(File file_filter) {
        this._file_filter = file_filter;

        config();
    }

    public Filter[] getFilters() {
        return _filters;
    }

    //    FILTER = gissa:Pessoa/idadeReal > 20 .

    private void config() {
        String[] filters;
        List<Filter> filters_list = new ArrayList<Filter>();

        String entity = "", property = "", condition = "";
        try {
            filters = FileUtils.readFile(_file_filter).split("FILTER =");

            for(int i = 0; i < filters.length; i++) {
                if(!filters[i].equals("")) {
                    String[] tmp = filters[i].split("/");

                    entity = tmp[0];
                    property = splitOperator(tmp[1])[0];
                    condition = splitOperator(tmp[1])[1];

                    filters_list.add(getFilterImpl(entity,property,condition));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        this._filters = filters_list.toArray(new FilterImpl[filters_list.size()]);
    }

    private Filter getFilterImpl(String entity, String property, String condition) {
        return new FilterImpl(entity,property,condition);
    }

    private String[] splitOperator(String str) {
        for(int i = 0; i < _operators.length; i++) {
            if(str.contains(_operators[i])) {
//                ("((?<=;)|(?=;))")

                /*Split lookahead e behind
                String[] tmp = str.trim().split("((?<="+(_operators[i])+")"+
                                         "|(?="+_operators[i]+"))");*/

                String[] tmp = str.trim().split("(?="+_operators[i]+")");
                return tmp;
            }
        }
        return null;
    }
}
