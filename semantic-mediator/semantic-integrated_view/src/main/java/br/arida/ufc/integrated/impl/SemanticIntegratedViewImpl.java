package br.arida.ufc.integrated.impl;

import br.arida.ufc.core.entity.OntologyModel;
import br.arida.ufc.domain.DomainOntology;
import br.arida.ufc.exportedview.ExportedViewSpec;
import br.arida.ufc.fusion.FusionSpec;
import br.arida.ufc.integrated.SemanticIntegratedView;
import br.arida.ufc.linksetview.LinksetViewSpec;

/**
 * Created by gabriellopes9102 on 02/12/2016.
 */
// Classe
public class SemanticIntegratedViewImpl implements SemanticIntegratedView {
    private DomainOntology _domain_onto;
    private ExportedViewSpec[] _exported_views;
    private LinksetViewSpec[] _linkset_views;
    private FusionSpec _fusion_spec;

    public SemanticIntegratedViewImpl(DomainOntology _domain_onto, ExportedViewSpec[] _exported_views,
                                LinksetViewSpec[] _linkset_views, FusionSpec fusion_spec) {

        this._domain_onto = _domain_onto;
        this._exported_views = _exported_views;
        this._linkset_views = _linkset_views;
        this._fusion_spec = fusion_spec;
    }

    /*
        Para pegar as novas exported_view tem que seguir o descrito no artigo;

     */

    public DomainOntology getDomainOnto() {
        return _domain_onto;
    }

    public ExportedViewSpec[] getExportedViews() {
        return _exported_views;
    }

    public LinksetViewSpec[] getLinksetViews() {
        return _linkset_views;
    }

    public FusionSpec getFusionSpec() {
        return _fusion_spec;
    }



    /*public static void main(String[] a){
        SemanticIntegratedView m = ;
        AppView app = new AppView();

        LinkedDataMashup mashup = new (new SemanticIntegratedView(...));

        mashup.createMashup(new ApplicationView(MediatorConfig.getInstance()));
    }

    *//*Realizar a reescrita da especificação, aplicando V1 = <Ov,F> em M = <M,En...,LS,u>, gerando uma especificação
    M'*//*
    public void createMashup(ApplicationView app_view) {

    }*/
}
