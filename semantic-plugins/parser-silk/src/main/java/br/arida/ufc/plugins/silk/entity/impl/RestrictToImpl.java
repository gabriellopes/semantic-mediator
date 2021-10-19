package br.arida.ufc.plugins.silk.entity.impl;

import br.arida.ufc.plugins.silk.entity.RestrictTo;
import org.w3c.dom.Element;

/**
 * Created by gabriellopes9102 on 19/12/2016.
 */
public class RestrictToImpl extends SilkFieldImpl implements RestrictTo {
    private String _subject;
    private String _predicate;
    private String _object;

    public RestrictToImpl(Element restrict_to) {
        super(restrict_to);
        initialize(restrict_to);
    }

    protected void initialize(Element restrict_to) {
        String restriction = restrict_to.getFirstChild().getNodeValue();
        _subject = restriction.split(" ")[0];
        _predicate = restriction.split(" ")[1];
        _object = restriction.split(" ")[2];
    }

    public String getRestriction() {
        return this._subject + " " + this._predicate + " " + this._object;
    }

    public void setClassRestriction(String class_restriction) {
        this._object = class_restriction;
    }

    public String toXML() {
        String str = "";
        str += "\t\t\t\t" + "<RestrictTo>"+this.getRestriction()+"</RestrictTo>" + '\n';

        return str;
    }

    /*<SourceDataset dataSource="" var="a"> <!-- Exported View-->
                <RestrictTo>?a rdf:type ex:ClasseSource_EV1</RestrictTo>  <!--Classe source da Exported View 1-->
            </SourceDataset> <!--Essa classe, Cs, é uma classe na ontol*/

}
