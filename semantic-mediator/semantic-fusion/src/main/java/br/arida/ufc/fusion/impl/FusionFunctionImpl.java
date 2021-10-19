package br.arida.ufc.fusion.impl;

import br.arida.ufc.fusion.FusionFunction;

/**
 * Created by gabriellopes9102 on 13/01/2017.
 */
public class FusionFunctionImpl implements FusionFunction {
    private String _name;

    public FusionFunctionImpl(String function) {
        this._name = function;
    }


    public String getFunctionName() {
        return this._name;
    }
}
