package br.arida.ufc.plugins.silk.entity.impl;

import br.arida.ufc.plugins.silk.entity.TargetDataset;
import org.w3c.dom.Element;

import java.util.logging.Logger;

/**
 * Created by gabriellopes9102 on 19/12/2016.
 */
public class TargetDatasetImpl extends DatasetImpl implements TargetDataset{
    private static final java.util.logging.Logger log = Logger.getLogger(TargetDatasetImpl.class.getName());

    public TargetDatasetImpl(Element target_source) {
        super(target_source);

        log.info("Inicializando TargetDataset. . .");
        initialize(target_source);
    }

    public String toXML() {
        String str = "";
        str += "\t\t\t"+"<TargetDataset dataSource=\""+this.getDataSource()+"\" var=\""+this.getVar()+"\">"+'\n'+
                this.getRestrictTo().toXML() +
                "\t\t\t"+"</TargetDataset>"+'\n';

        return str;
    }
}
