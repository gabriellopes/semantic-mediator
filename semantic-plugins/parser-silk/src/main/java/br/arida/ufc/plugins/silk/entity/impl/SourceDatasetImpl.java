package br.arida.ufc.plugins.silk.entity.impl;

import br.arida.ufc.plugins.silk.SilkTerms;
import br.arida.ufc.plugins.silk.entity.SourceDataset;
import br.arida.ufc.plugins.silk.impl.SilkLayoutImpl;
import org.w3c.dom.Element;

import java.util.logging.Logger;

/**
 * Created by gabriellopes9102 on 19/12/2016.
 */
public class SourceDatasetImpl extends DatasetImpl implements SourceDataset {
    private static final java.util.logging.Logger log = Logger.getLogger(SourceDatasetImpl.class.getName());

    public SourceDatasetImpl(Element source_dataset) {
        super(source_dataset);

        log.info("Inicializando SourceDataset. . .");
        initialize(source_dataset);
    }

    public String toXML() {
        String str = "";
        str += "\t\t\t"+"<SourceDataset dataSource=\""+this.getDataSource()+"\" var=\""+this.getVar()+"\">"+'\n'+
                    this.getRestrictTo().toXML() +
               "\t\t\t"+"</SourceDataset>"+'\n';

        return str;
    }

}
