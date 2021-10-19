package br.arida.ufc.core.entity.impl;

import br.arida.ufc.core.entity.Prefix;
import org.w3c.dom.Element;

/**
 * Created by gabriellopes9102 on 04/01/2017.
 */
public class PrefixImpl implements Prefix {
    private String _key;
    private String _uri;

    public PrefixImpl(String key, String uri) {
        this._key = key;
        this._uri = uri;
    }



    @Override
    public String getKey() {
        return this._key;
    }

    @Override
    public String getURI() {
        return this._uri;
    }

    public String toString() {
        return _key + " : " + _uri;
    }
}
