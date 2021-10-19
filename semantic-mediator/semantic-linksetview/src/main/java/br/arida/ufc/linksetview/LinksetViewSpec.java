package br.arida.ufc.linksetview;

import br.arida.ufc.core.step.MashupStepSpecification;
import br.arida.ufc.exportedview.ExportedView;
import br.arida.ufc.exportedview.ExportedViewSpec;
import br.arida.ufc.linksetview.impl.MatchPredicateImpl;

import java.util.Map;

/**
 * Created by gabriellopes9102 on 13/12/2016.
 */
public interface LinksetViewSpec extends MashupStepSpecification{

    Map<String,String> getPrefixes();

    void setPrefixes(Map<String,String> prefixes);

    String getName();

    ExportedViewSpec getExportedView_T();

    ExportedViewSpec getExportedView_U();

    void setName(String name);

    void setExportedViewsByName(String nodeValue, String nodeValue1);

    MatchPredicate getMatchPredicate();

    void setMatchPredicate(MatchPredicate m_predicate);
}
