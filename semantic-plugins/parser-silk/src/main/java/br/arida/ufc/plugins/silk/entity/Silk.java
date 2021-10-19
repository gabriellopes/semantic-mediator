package br.arida.ufc.plugins.silk.entity;

import java.util.Map;

/**
 * Created by gabriellopes9102 on 12/01/2017.
 */
public interface Silk extends SilkField {

    Prefix[] getPrefixes();

    void setPrefixes(Map<String,String> prefixes);

    Interlink[] getInterlinks();
}
