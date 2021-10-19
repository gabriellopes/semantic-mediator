package br.arida.ufc.linksetview.impl;

import br.arida.ufc.core.step.impl.MashupStepImpl;
import br.arida.ufc.linksetview.LinksetViewMaterializer;
import br.arida.ufc.linksetview.LinksetViewSpec;
import br.arida.ufc.linksetview.LinksetView;

/**
 * Created by gabriellopes9102 on 01/12/2016.
 */

/*
* Essa classe será responsável pela materialização do LinksetSpec. Como o objetivo desse trabalho não é desenvolver um
 * segundo LDIF, a materialização de uma linksetview será uma especificação do SILK.
 */
public class LinksetViewImpl extends MashupStepImpl implements LinksetView {
    private LinksetViewSpec _linkset_view_spec;


    public LinksetViewImpl(LinksetViewSpec linkset_view) {
        super(linkset_view);
        this._linkset_view_spec = linkset_view;

    }


    public LinksetViewSpec getLinksetViewSpec() {
        return this._linkset_view_spec;
    }

    public String getName() {
        return _linkset_view_spec.getName();
    }

//    public void generateLinksetView(String output_path) {
//        _linkset_view_materializer.materialize(output_path);
//    }
}
