package br.arida.ufc.plugins.sieve.entity;

/**
 * Created by gabriellopes9102 on 20/12/2016.
 */
public interface ScoringFunction extends SieveField {

    String getScoreClass();

    void setScoreClass(String _class);

    Param getParam();
}
