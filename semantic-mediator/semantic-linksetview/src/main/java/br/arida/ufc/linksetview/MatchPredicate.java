package br.arida.ufc.linksetview;

/**
 * Created by gabriellopes9102 on 13/12/2016.
 */
public interface MatchPredicate {

    //Classe Target que contém um mapeamento em cada e_view
    public Object getClassTarget();

    //Métrica para comparação da propriedade.
    public String getMetric();

    //Propriedade a ser comparada, e.g. gissa:nomeCompleto
    public String getProperty();
}
