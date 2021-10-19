package br.arida.ufc.plugins.sieve.entity.impl;

import br.arida.ufc.plugins.sieve.SieveTerms;
import br.arida.ufc.plugins.sieve.entity.AssessmentMetric;
import br.arida.ufc.plugins.sieve.entity.QualityAssessment;
import org.w3c.dom.Element;

/**
 * Created by gabriellopes9102 on 20/12/2016.
 */
public class QualityAssessmentImpl extends SieveFieldImpl implements QualityAssessment {
    private AssessmentMetric _assessment_metric;

    public QualityAssessmentImpl(Element quality) {
        super(quality);
        initialize(quality);
    }

    protected void initialize(Element quality) {
        Element a_metric;

        a_metric = (Element) quality.getElementsByTagName(SieveTerms.ASSESSMENT_METRIC).item(0);

        this._assessment_metric = new AssessmentMetricImpl(a_metric);
    }

    public AssessmentMetric getAssessmentMetric() {
        return this._assessment_metric;
    }

    public String toXML() {
        String str = "";

        str += '\t' + "<QualityAssessment>" + '\n' +
                _assessment_metric.toXML() + '\n' +
                '\t'+ "</QualityAssessment>";

        return str;
    }
}
