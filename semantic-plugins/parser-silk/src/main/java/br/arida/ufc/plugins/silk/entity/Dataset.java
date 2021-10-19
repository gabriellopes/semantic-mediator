package br.arida.ufc.plugins.silk.entity;

/**
 * Created by gabriellopes9102 on 19/12/2016.
 */
public interface Dataset extends SilkField{

    public String getDataSource();

    public void setDataSource(String data_source);

    public String getVar();

    public void setVar(String var);

    public RestrictTo getRestrictTo();

    public void setRestrictTo(RestrictTo restrict_to);


}
