package br.arida.ufc.plugins.silk.entity.impl;

import br.arida.ufc.plugins.silk.entity.SilkField;
import org.w3c.dom.Element;

/**
 * Created by gabriellopes9102 on 19/12/2016.
 */
public abstract class SilkFieldImpl implements SilkField {
    private Element _silk_field;

    public SilkFieldImpl(Element silk_field) {
        this._silk_field = silk_field;
    }

    protected abstract void initialize(Element silk_field);
}

