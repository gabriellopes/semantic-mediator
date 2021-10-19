package br.arida.ufc.plugins.sieve.entity;

import java.util.List;

/**
 * Created by gabriellopes9102 on 12/01/2017.
 */
public interface Sieve extends SieveField{

    QualityAssessment getQualityAssessment();

    Fusion getFusion();

    Prefix[] getPrefixes();

    void setPrefixes(List<Prefix> prefixes);

    void addClass(Object _class, Object property, Object function);
}
