package br.arida.ufc.plugins.sieve.entity;

/**
 * Created by gabriellopes9102 on 20/12/2016.
 */
public interface AssessmentMetric extends SieveField {

    String getId();

    void setId(String id);

    ScoringFunction getScoringFunction();


}
