package br.arida.ufc.plugins.sieve.entity.impl;

import br.arida.ufc.plugins.sieve.entity.SieveField;
import org.w3c.dom.Element;

/**
 * Created by gabriellopes9102 on 20/12/2016.
 */
public abstract class SieveFieldImpl implements SieveField {
    private Element _sieve_field;

    public SieveFieldImpl(Element sieve_field){
        this._sieve_field = sieve_field;
    }

    protected abstract void initialize(Element sieve_field);

}
