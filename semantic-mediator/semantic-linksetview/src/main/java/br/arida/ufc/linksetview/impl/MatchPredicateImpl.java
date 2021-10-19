package br.arida.ufc.linksetview.impl;

import br.arida.ufc.linksetview.MatchPredicate;

import java.util.List;

/**
 * Created by gabriellopes9102 on 06/12/2016.
 */
public class MatchPredicateImpl implements MatchPredicate{
    private String _class_target; //Class Target
    private String _metric;
    private String _property;


    protected MatchPredicateImpl(String _class, String metric, String property) {
        this._class_target = _class;
        this._metric = metric;
        this._property = property;
    }

    public String getClassTarget() {
        return _class_target;
    }

    public String getMetric() {
        return _metric;
    }

    public String getProperty() {
        return _property;
    }
}
