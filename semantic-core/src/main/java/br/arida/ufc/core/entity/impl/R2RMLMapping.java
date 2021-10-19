package br.arida.ufc.core.entity.impl;

import br.arida.ufc.core.entity.*;
import eu.optique.api.mapping.*;
import eu.optique.api.mapping.impl.InvalidR2RMLMappingException;
import eu.optique.api.mapping.impl.R2RMLViewImpl;
import eu.optique.api.mapping.impl.jena.JenaR2RMLMappingManagerFactory;
import org.apache.jena.graph.Triple;
import org.apache.jena.ontology.OntModel;
import org.apache.jena.query.Dataset;
import org.apache.jena.query.DatasetFactory;
import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.ModelFactory;
import org.apache.jena.rdf.model.Statement;
import org.apache.jena.rdf.model.StmtIterator;
import org.apache.jena.rdf.model.impl.StmtIteratorImpl;
import org.apache.jena.util.PrintUtil;

import java.io.*;
import java.util.*;
import java.util.logging.Logger;

/**
 * Created by gabriellopes9102 on 08/12/2016.
 */
public class R2RMLMapping extends MappingImpl implements Mapping{
    private static final java.util.logging.Logger log = Logger.getLogger(R2RMLMapping.class.getName());

    private File _mapping_file;
    private List<TriplesMap> _mappings; //transformar "TriplesMap" em objeto próprio, estilo SilkLayout
    private Model _map_model;

    public R2RMLMapping(File mapping_file) {
        super(mapping_file);
        this._mapping_file = mapping_file;

        init();
    }

    public R2RMLMapping() {

    }

    public List<TriplesMap> getTriplesMap() {
        return this._mappings;
    }

    @Override
    public void addFilter(Filter[] filters) {
        for(Filter f : filters) {
            for(Iterator<TriplesMap> i = getTriplesMap().iterator(); i.hasNext();) {
                TriplesMap tm = i.next();

                for(Object obj : tm.getSubjectMap().getClasses(Object.class)) {
                    if(obj.toString().contains(f.getEntity().toString().split(":")[1])){ //Filtro é dessa classe
                        String tmp = tm.getLogicalTable().getSQLQuery();
                        if(tmp.contains("WHERE")) {
                            tmp += '\t' + "AND "+ f.getProperty() + f.getCondition();
                        } else {
                            tmp += "WHERE "+ f.getProperty() + f.getCondition();
                        }

                        System.out.println(tmp);
                        R2RMLViewImpl r = (R2RMLViewImpl) tm.getLogicalTable();
                        r.setR2RMLView(tmp);
                        tm.setLogicalTable(r);
                    }
                }
            }
        }
    }

    private void updateTripleMap(TriplesMap tm) {
        R2RMLMappingManager mm = new JenaR2RMLMappingManagerFactory().getR2RMLMappingManager();

        Set<Statement> stms = tm.serialize(Statement.class);
        removeTripleMap(tm);
        this.getTriplesMap().add(tm);
        this._map_model = this._map_model.add(new StmtIteratorImpl(stms.iterator()));
    }

    /**
     *
     * @param type
     * @return
     */
    public Object getClassByType(Object type) {
        Object _class;
        Iterator<TriplesMap> it = _mappings.iterator();
        while (it.hasNext()) {
            TriplesMap current = it.next();
            SubjectMap sm = current.getSubjectMap();
            List<PredicateObjectMap> poms = current.getPredicateObjectMaps();
            List<Object> classes = sm.getClasses(Object.class);

            for(Object obj : classes){
                if(obj.toString().split("#")[1].contains(type.toString().split(":")[1]))
                    return type;
            }

            //Verifica se ela tem um mapeamento do tipo 'rdf:type' para uma classe Target Ct.
            for (PredicateObjectMap pom : poms) {
                List<ObjectMap> o_maps = pom.getObjectMaps();
                for (PredicateMap pm : pom.getPredicateMaps()) {
                    if (pm.getConstant().contains("type")) { //Se o predicato for type;
                        //verifica se no objectMap há uma referência para a Classe Target
                        for (ObjectMap om : o_maps) {
                            String prefix = type.toString().split(":")[0];
                            String _obj = type.toString().split(":")[1];
                            String uri_prefix = _map_model.getNsPrefixURI(prefix);
                            String tmp = uri_prefix + _obj;

                            if (om.getTemplateString().contains(_obj) ||
                                om.getTemplateString().contains(uri_prefix+_obj)) {

                                System.out.println(om.getTemplateString());
                                //Verifica se a TripleMap contém a Classe Source | Cs -> Ct.
                                for (Object obj : classes) {
                                    _class = obj; // Armazena a classe source para, caso haja o mapeamento, retorná-la.
                                    String tmp2 = _map_model.getNsURIPrefix(_class.toString().split("#")[0]+"#");
                                    String tmp3 = _class.toString().split("#")[1];
                                    return tmp2+":"+tmp3;
                                }
                            }
                        }
                    }
                }
            }
        }
        // verifica o tipo no predicate
        // verifica se o template contém a string type
        // retorna a classe


        return null;
    }

    private boolean hasMappingToClass(TriplesMap tm, Object _class) {
        boolean contem = false;
        int index = 0;

        List<Object> classes = tm.getSubjectMap().getClasses(Object.class);

        //Pode estar no subject map ou no predicateobjmap.
        for (Object obj : classes) {
            if (obj.toString().contains(_class.toString())) {
                return true; //Essa TripleMap contém um mapeamento para _class
            }
        }

        for(PredicateObjectMap pom : tm.getPredicateObjectMaps()) {
            for(PredicateMap pm : pom.getPredicateMaps()) {
                if(pm.getConstant().contains("type")) {
                    for(ObjectMap om : pom.getObjectMaps()) {
                        if(om.getTemplateString().contains(_class.toString())) {
                            System.out.println(pm.toString()); //Se for igual a type, verificar se o template contém a _class;
                            return true;
                        }
                    }
                }
            }
            index++;
        }

        return false;
    }

    public Mapping getMappingIntersectionWithOntology(OntologyModel ontology) {
        R2RMLMapping m = (R2RMLMapping) MappingFactory.getMappingManager(this._mapping_file);
        boolean contem = false;

        for (Iterator<TriplesMap> tm = _mappings.iterator(); tm.hasNext(); ) {
            TriplesMap t = tm.next();
            contem = false;
            for (OntoClass o : ontology.getOntoClasses()) { //testa se o triplemap contém mapeamentos para a classe o
                if (hasMappingToClass(t, o.getClassName())) {
                    contem = true;
                    log.info("Found mapping for class ["+o.getClassName()+"] on triplemap ["+t.toString()+"]. . .");
                    break; // passa para o próximo triple map
                }
            }
            //remove ainda está com erro
            if(!contem) {
                try {
                    log.info("Trying to remove [" + t.toString() + "]. . . ");
                    m.removeTripleMap(m.getEquivalentIfExists(t));
                } catch (ClassNotFoundException ex) {
                    log.warning("Class [" + t.toString() + "] not found. . . ");
                    log.warning("Exception: \n");
                }
            }
        }
        return m;
    }


    /*@
    t
    public <T> T getEquivalentIfExists(T obj) {
        return null;
    }*/

    public void removeTripleMap(Object obj) {
        TriplesMap tm = (TriplesMap) obj;
        R2RMLMappingManager mm = new JenaR2RMLMappingManagerFactory().getR2RMLMappingManager();

        Set<Statement> stms = tm.serialize(Statement.class);
        for(Iterator<Statement> i = stms.iterator(); i.hasNext(); ) {
            Statement s = i.next();
            System.out.println(i.toString());
        }

        StmtIterator s2 = new StmtIteratorImpl(stms.iterator());
        this.getTriplesMap().remove(tm);
        this._map_model = this._map_model.remove(s2);
    }

    @Override
    public Object getEquivalentIfExists(Object tm) throws ClassNotFoundException {
        boolean subject = false;
        String log1, log2;
        TriplesMap tmp = (TriplesMap) tm;
        for(Iterator<TriplesMap> i = this._mappings.iterator(); i.hasNext();){
            TriplesMap m = i.next();
            //melhorar essa condição de equivalência entre os triplemaps.
            log1 = m.getLogicalTable().getSQLQuery();
            log2 = tmp.getLogicalTable().getSQLQuery();

            if(log1.contains(log2)) {
                return m;
            }

        }
        throw new ClassNotFoundException();
    }


    /*
    if(m.getSubjectMap().getClasses(Object.class).size() ==
       tmp.getSubjectMap().getClasses(Object.class).size())
    for(Object c : m.getSubjectMap().getClasses(Object.class)) {
        for(Object obj : tmp.getSubjectMap().getClasses(Object.class)) {
            if(c.toString().equals(obj.toString())) {
                subject = true;
                return m;
            }
        }
    }*/

    private void init() {
        initMappingManager();
        readFile();
    }

    private void initMappingManager(){
        this._map_model = ModelFactory.createDefaultModel();
    }

    @Override
    public void generateFile(String output_path) {
        output_path += ".ttl";
        File file = new File(output_path);
        R2RMLMappingManager mm = new JenaR2RMLMappingManagerFactory().getR2RMLMappingManager();
        /*for(StmtIterator i = this._map_model.listStatements(); i.hasNext();) {
            Statement s = i.next();
            System.out.println(s.getString());
        }*/

        try {
            Model tmp = this._map_model;
            if(getTriplesMap().size() > 0) {
                this._map_model = mm.exportMappings(getTriplesMap(), Model.class);
                this._map_model.setNsPrefixes(tmp.getNsPrefixMap());

                this._map_model.write(new FileWriter(file), "TURTLE");
                Dataset dataset = DatasetFactory.create(this._map_model);

                dataset.commit();
                PrintUtil.printOut(dataset.asDatasetGraph().find());
            } else {
                file.createNewFile();
            }
        } catch (IOException e) {
            e.printStackTrace();
            log.warning("Error while generating file [" + output_path + "]. . . ");
            log.warning(e.getMessage());
        } catch (IllegalArgumentException e) {

        }
    }

    private void readFile() {
        R2RMLMappingManager mm = new JenaR2RMLMappingManagerFactory().getR2RMLMappingManager();

        try {

            this._map_model = this._map_model.read(new FileReader(this._mapping_file), "testMapping", "TURTLE");
            this._mappings = new ArrayList<TriplesMap>(mm.importMappings(this._map_model));

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }  catch (InvalidR2RMLMappingException e) {
            e.printStackTrace();
        }
    }



}
