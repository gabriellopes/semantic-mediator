package br.arida.ufc.exportedview.impl;

import br.arida.ufc.core.config.Config;
import br.arida.ufc.core.entity.Mapping;
import br.arida.ufc.core.entity.OntologyModel;
import br.arida.ufc.core.entity.impl.MappingImpl;
import br.arida.ufc.core.entity.impl.OntologyModelImpl;
import br.arida.ufc.core.step.MashupStep;
import br.arida.ufc.core.step.impl.MashupStepSpecificationImpl;
import br.arida.ufc.exportedview.*;
import br.arida.ufc.exportedview.util.ExportedViewNotFoundException;

import java.io.IOException;
import java.util.logging.Logger;

/**
 * Created by gabriellopes9102 on 29/11/2016.
 */

/*
* Essa classe conhece o método de armazenamento dos objetos. Recebe um File do objeto Config e o transforma
* numa linguagem legível, como ontomodel (jena), etc.
 */
public class ExportedViewSpecImpl extends MashupStepSpecificationImpl<ExportedView> implements ExportedViewSpec {
    private final static Logger log = Logger.getLogger(ExportedViewSpecImpl.class.getName());

    private ExportedViewConfig _ev_config; //Será usado para chamar os objetos da configuração XML, e.g. mapeamentos, etc.
    private String _name;
    private Mapping _mapping;
    private OntologyModel _onto_source;

    private static ExportedViewSpec[] _exported_views;

    /*
    *
    *
    *
     */
    public ExportedViewSpecImpl(ExportedViewConfig config) {
        super(config);
        super.setMaterializer(loadMaterializer());
        _ev_config = config;

    }

    protected ExportedViewMaterializer loadMaterializer() {
        return new ExportedViewMaterializerImpl(this);
    }

    public Object getClassByType(Object type) {
        return _mapping.getClassByType(type);
    }

    public void setOntoSource(OntologyModel onto_source) {
        this._onto_source = onto_source;
    }

    public void setMapping(Mapping mapping) {
        this._mapping = mapping;
    }

    public Mapping getMapping() {
        return this._mapping;
    }

    public OntologyModel getOntoSource() {
        return this._onto_source;
    }

    public ExportedViewSpec createExportedView(OntologyModel onto) {
        ExportedViewSpec e_view = ExportedViewFactory.getSpecImpl(_ev_config);


        //onto = onto - this._onto_source;
        //Mapping new_mapping = this._mapping - onto;

        //e_view.setMapping(new_mapping);
        //e_view.setOntoSource(onto);


        return e_view;
    }

    public void setName(String name) {
        this._name = name;
    }

    public String getName() {
        return this._name;
    }

    protected Object getClassSourceFromClassTarget(Object class_target) {

        return null;
    }

    /********************
    * MÉTODOS ESTÁTICOS
     ********************/
    public static ExportedViewSpec[] load(Config config){
        ExportedViewSpecImpl._exported_views = new ExportedViewConfigImpl(config).loadConfig();
        return ExportedViewSpecImpl._exported_views;
    };


    public static ExportedViewSpec getExportedViewByName(String name) throws ExportedViewNotFoundException {
        for(int i = 0 ; i < _exported_views.length; i++) {
            if(_exported_views[i].getName().equals(name)) {
                return _exported_views[i];
            }
        }
        throw new ExportedViewNotFoundException("Exported View ["+name+"] not found");
    }

    public ExportedView materializeView(String output_path) {
        return null;
    }
}



