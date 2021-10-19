package br.arida.ufc.core.entity.impl;

import br.arida.ufc.core.entity.Filter;

/**
 * Created by gabriellopes9102 on 13/12/2016.
 */
public class FilterImpl implements Filter {
    private Object _entity;
    private Object _property;
    private Object _condition;

    public FilterImpl(Object entity, Object property, Object condition) {
        this._entity = entity;
        this._property = property;
        this._condition = condition;
    }

    public Object getEntity() {
        return _entity;
    }

    public void setEntity(Object entity) {
        this._entity = entity;
    }

    public Object getProperty() {
        return _property;
    }

    public void setProperty(Object _property) {
        this._property = _property;
    }

    public Object getCondition() {
        return _condition;
    }

    public void setCondition(Object _condition) {
        this._condition = _condition;
    }
}
