package br.arida.ufc.plugins.sieve.impl;

import br.arida.ufc.core.util.FileUtils;
import br.arida.ufc.plugins.sieve.SieveLayout;
import br.arida.ufc.plugins.sieve.SieveMaterializer;
import br.arida.ufc.plugins.sieve.entity.Prefix;
import br.arida.ufc.plugins.sieve.entity.impl.PrefixImpl;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by gabriellopes9102 on 20/12/2016.
 */
public class SieveMaterializerImpl implements SieveMaterializer{
        private File _sieve_layout_xml;
        private SieveLayout _sieve_layout;

    public SieveMaterializerImpl() {
        try {
            this._sieve_layout_xml = FileUtils.getFileFromResource(SieveMaterializerImpl.class, "sieveLayout.xml");
            this._sieve_layout = loadLayout();
//            _output_path = output_path;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    private SieveLayout loadLayout() {
        return new SieveLayoutImpl(_sieve_layout_xml);
    }
    public void run() {

    }

    public void materialize(String output_path, Map<String, String> prefixes, Object[] _class, Object[] _property, Object[] _fusion_function) {
        List<Prefix> _prefixes = new ArrayList<Prefix>();
        for(String key : prefixes.keySet()) {
            String uri = prefixes.get(key);

            _prefixes.add(new PrefixImpl(key,uri));
        }

        _sieve_layout.getSieve().setPrefixes(_prefixes);

        for(int i = 0 ; i < _class.length; i++) {
            _sieve_layout.getSieve().addClass(_class[i], _property[i], _fusion_function[i]);
        }
        _sieve_layout.getSieve().getQualityAssessment().getAssessmentMetric().getScoringFunction().getParam().setValue("");

        _sieve_layout.generateSieveFile(output_path);
    }

    /*public void materialize(String output_path, Map<String,String> prefixes, String id_interlink, String s_dataSource, String s_class_restriction,
                            String t_dataSource, String t_class_restriction, String type_aggregate,
                            String metric, String[] input_props) {


        _silk_layout.getSilk().setPrefixes(prefixes);

        Interlink interlink = _silk_layout.getSilk().getInterlinks()[0];
        interlink.setId(id_interlink);
        //source
        interlink.getSourceDataset().setDataSource(s_dataSource);
        interlink.getSourceDataset().getRestrictTo().setClassRestriction(s_class_restriction);
        //target
        interlink.getTargetDataset().setDataSource(t_dataSource);
        interlink.getTargetDataset().getRestrictTo().setClassRestriction(t_class_restriction);

        interlink.getLinkageRule().getAggregate().setType(type_aggregate);
        interlink.getLinkageRule().getAggregate().getCompare().setMetric(metric);

        interlink.getLinkageRule().getAggregate().getCompare().getInputList()[0].setProperty(input_props[0]);
        interlink.getLinkageRule().getAggregate().getCompare().getInputList()[1].setProperty(input_props[1]);

        //Implementar
        _silk_layout.generateSilkFile(output_path);
    }*/
}
