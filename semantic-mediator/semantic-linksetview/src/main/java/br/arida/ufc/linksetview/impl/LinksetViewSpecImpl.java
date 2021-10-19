package br.arida.ufc.linksetview.impl;

import br.arida.ufc.core.config.Config;
import br.arida.ufc.core.step.impl.MashupStepSpecificationImpl;
import br.arida.ufc.exportedview.ExportedViewSpec;
import br.arida.ufc.exportedview.impl.ExportedViewSpecImpl;
import br.arida.ufc.exportedview.util.ExportedViewNotFoundException;
import br.arida.ufc.linksetview.*;

import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

/**
 * Created by gabriellopes9102 on 01/12/2016.
 */

/*
*
* ESPECIFICAÇÃO LINKSETVIEW LS = (ML, T, U, C, p1,...,pn, u), onde:
* ML -> nome;
* T -> Visão exportada T;
* U -> Visão Exportada U;
* C -> Classe Target, tal que E Ct pertencente a T ^ E MT: Ct -> C. O mesmo para U.
* p1,..,pn -> propriedade de C, tal que exista propriedades em T e U com mapeaemnto para pi.
* u -> Regras de link. match predicate
 */
public class LinksetViewSpecImpl extends MashupStepSpecificationImpl<LinksetView> implements LinksetViewSpec{
    private static final Logger log = Logger.getLogger(LinksetViewSpecImpl.class.getName());

    private LinksetViewConfig _ls_config;

    private String _name;
    private Map<String,String> _prefixes;
    private ExportedViewSpec _exported_view_T;
    private ExportedViewSpec _exported_view_U;
    private MatchPredicate _match_predicate;

    public LinksetViewSpecImpl(LinksetViewConfig config) {
        super(config);
        _ls_config =  config;
        super.setMaterializer(loadMaterializer());
    }

    private LinksetViewMaterializer loadMaterializer() {
        return new LinksetViewMaterializerImpl(this);
    }

    public LinksetViewSpecImpl(LinksetViewConfig config, ExportedViewSpec exported_view_T,
                               ExportedViewSpec exported_view_U) {
        super(config);
        _ls_config =  config;
        this._exported_view_T = exported_view_T;
        this._exported_view_U = exported_view_U;
        super.setMaterializer(loadMaterializer());
    }

    public MatchPredicate getMatchPredicate() {
        return _match_predicate;
    }

    /**********
    * SETTERS *
    ***********/


    public void setPrefixes(Map<String,String> prefixes) {
        this._prefixes = prefixes;
    }

    public Map<String,String> getPrefixes() {
        return this._prefixes;
    }

    public void setName(String name) {
        this._name = name;
    }

    public void setMatchPredicate(MatchPredicate match_predicate) {
        this._match_predicate = match_predicate;
    }

    public void setExportedViewsByName(String e_view_T, String e_view_U) {
        try {
            this._exported_view_T = ExportedViewSpecImpl.getExportedViewByName(e_view_T);
            this._exported_view_U = ExportedViewSpecImpl.getExportedViewByName(e_view_U);

        } catch (ExportedViewNotFoundException e) {
            log.warning("Visões Exportadas ainda não foram geradas");
            e.printStackTrace();
        }
    }

    public void setMatchPredicate(MatchPredicateImpl m_predicate) {

    }

    /**********
     * GETTERS *
     ***********/


    /*
    * Função de Load
     */
    public static LinksetViewSpec[] load(Config config) {
        return new LinksetViewConfigImpl(config).loadConfig();
    }

    public String getName() {
        return this._name;
    }

    public ExportedViewSpec getExportedView_T() {
        return this._exported_view_T;
    }

    public ExportedViewSpec getExportedView_U() {
        return this._exported_view_U;
    }

}
