package br.arida.ufc.plugins.silk.entity.impl;

import br.arida.ufc.plugins.silk.SilkTerms;
import br.arida.ufc.plugins.silk.entity.Dataset;
import br.arida.ufc.plugins.silk.entity.RestrictTo;
import org.w3c.dom.Element;

/**
 * Created by gabriellopes9102 on 19/12/2016.
 */
public abstract class DatasetImpl extends SilkFieldImpl implements Dataset{
    private String _data_source;
    private String _var;
    private RestrictTo _restrict_to;

    public DatasetImpl(Element source_dataset) {
        super(source_dataset);
        initialize(source_dataset);
    }

    protected void initialize(Element source_dataset) {
        Element restrictTo;

        restrictTo = (Element) source_dataset.getElementsByTagName("RestrictTo").item(0);

        String tmp = source_dataset.getAttributes().getNamedItem("dataSource").getNodeValue();

        this.setVar(source_dataset.getAttributes().getNamedItem("var").getNodeValue());
        this.setDataSource(source_dataset.getAttributes().getNamedItem("dataSource").getNodeValue());
        this.setRestrictTo(new RestrictToImpl(restrictTo));
    }


    public String getDataSource() {
        return this._data_source;
    }

    public void setDataSource(String data_source) {
        this._data_source = data_source;
    }

    public String getVar() {
        return this._var;
    }

    public void setVar(String var) {
        this._var = var;
    }

    public RestrictTo getRestrictTo() {
        return this._restrict_to;
    }

    public void setRestrictTo(RestrictTo restrict_to) {
        this._restrict_to = restrict_to;
    }
}
