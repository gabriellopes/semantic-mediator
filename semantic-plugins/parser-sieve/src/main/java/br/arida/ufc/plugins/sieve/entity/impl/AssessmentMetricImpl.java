package br.arida.ufc.plugins.sieve.entity.impl;

import br.arida.ufc.plugins.sieve.SieveTerms;
import br.arida.ufc.plugins.sieve.entity.AssessmentMetric;
import br.arida.ufc.plugins.sieve.entity.ScoringFunction;
import org.w3c.dom.Element;

/**
 * Created by gabriellopes9102 on 20/12/2016.
 */
public class AssessmentMetricImpl extends SieveFieldImpl implements AssessmentMetric{
    private ScoringFunction _scoring_function;

    public AssessmentMetricImpl(Element assessment_metric) {
        super(assessment_metric);
        initialize(assessment_metric);
    }

    protected void initialize(Element a_metric) {
        Element scoring_function;

        scoring_function = (Element) a_metric.getElementsByTagName(SieveTerms.SCORING_FUNCTION).item(0);

        this._scoring_function = new ScoringFunctionImpl(scoring_function);
    }

    public String toXML() {
        String str = "";

        str += "\t\t" + "<AssessmentMetric>" + '\n' +
                            _scoring_function.toXML() + '\n' +
               "\t\t" + "</AssessmentMetric>";
        return str;
    }

    public String getId() {
        return null;
    }

    public void setId(String id) {

    }

    public ScoringFunction getScoringFunction() {
        return this._scoring_function;
    }
}
