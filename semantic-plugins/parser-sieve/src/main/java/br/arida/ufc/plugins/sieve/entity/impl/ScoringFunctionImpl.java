package br.arida.ufc.plugins.sieve.entity.impl;

import br.arida.ufc.plugins.sieve.SieveTerms;
import br.arida.ufc.plugins.sieve.entity.Param;
import br.arida.ufc.plugins.sieve.entity.ScoringFunction;
import org.w3c.dom.Element;

/**
 * Created by gabriellopes9102 on 20/12/2016.
 */
public class ScoringFunctionImpl extends SieveFieldImpl implements ScoringFunction {
    private String _class;
    private Param _param;

    public ScoringFunctionImpl(Element scoring_function) {
        super(scoring_function);
        initialize(scoring_function);
    }

    protected void initialize(Element scoring_function) {
        Element param;
        _class = scoring_function.getAttributes().getNamedItem("class").getNodeValue();
        param = (Element) scoring_function.getElementsByTagName(SieveTerms.PARAM).item(0);

        this._param = new ParamImpl(param);
    }

    public String getScoreClass() {
        return this._class;
    }

    public void setScoreClass(String _class) {

    }

    public Param getParam() {
        return this._param;
    }

    public String toXML() {
        String str = "";

        str += "\t\t\t" + "<ScoringFunction class=\""+_class+"\">" + '\n' +
                                _param.toXML() + '\n' +
               "\t\t\t" + "</ScoringFunction>";
        return str;
    }
}
