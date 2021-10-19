package br.arida.ufc.core.entity.impl;

import br.arida.ufc.core.config.MediatorConfigTest;
import br.arida.ufc.core.entity.*;
import br.arida.ufc.core.util.FileUtils;
import eu.optique.api.mapping.*;
import eu.optique.api.mapping.impl.R2RMLViewImpl;
import eu.optique.api.mapping.impl.jena.JenaR2RMLMappingManagerFactory;
import junit.framework.TestCase;
import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.ModelFactory;


import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

/**
 * Created by gabriellopes9102 on 09/12/2016.
 */

public class R2RMLMappingTest extends TestCase {

    public void testGetClassByType() throws Exception {
        File file = FileUtils.getFileFromResource(this.getClass(), "sinasc.ttl");

        R2RMLMapping rm = new R2RMLMapping(file);
        Object _mae = rm.getClassByType("Pessoa");
    }

    public void testAddFilter() throws Exception {
        File file = FileUtils.getFileFromResource(this.getClass(), "esus_r2rml_mapping.ttl");
        R2RMLMappingManager mm = new JenaR2RMLMappingManagerFactory().getR2RMLMappingManager();

        Model m = ModelFactory.createDefaultModel();
        m = m.read(new FileReader(file), "testMapping", "TURTLE");

        Collection<TriplesMap> coll = mm.importMappings(m);

        Iterator<TriplesMap> it = coll.iterator();
        while (it.hasNext()) {
            TriplesMap current = it.next();

            current.toString();
            R2RMLViewImpl l = (R2RMLViewImpl) current.getLogicalTable();
            String query = l.getSQLQuery();
            query = query.concat("\t\t\t WHERE age > 25");


            System.out.println("TOSTRING: \n"+current.toString());
            System.out.println("\nSql Query: \n");
//            System.out.println(l.getSQLQuery());
            System.out.println(query);
        }
    }

    public void testReadMapping() throws Exception {
        File file = FileUtils.getFileFromResource(this.getClass(), "esus_r2rml_mapping.ttl");
        R2RMLMappingManager mm = new JenaR2RMLMappingManagerFactory().getR2RMLMappingManager();

        Model m = ModelFactory.createDefaultModel();
        m = m.read(new FileReader(file), "testMapping", "TURTLE");

        Collection<TriplesMap> coll = mm.importMappings(m);
        Mapping r2rml = MappingFactory.getMappingManager(file);


        Iterator<TriplesMap> it = coll.iterator();
        while (it.hasNext()) {
            TriplesMap current = it.next();

            LogicalTable l = current.getLogicalTable();

            System.out.println("\nSql Query: \n");
            System.out.println(l.getSQLQuery());

            System.out.println("\nSubject Map: \n");
            System.out.println("\tclass: "+current.getSubjectMap().toString());

            System.out.println("\nPredicate Object Maps: \n");
            for(PredicateObjectMap pom : current.getPredicateObjectMaps()) {
                System.out.println(pom.toString());
            }

//            Assert.assertTrue(l.getSQLQuery().contains("SELECT DEPTNO"));

        }
    }

    public void testAddFilter2() throws Exception {
        File filter = FileUtils.getFileFromResource(this.getClass(),"filter.txt");
        FilterConfigImpl f = new FilterConfigImpl(filter);

        File file = FileUtils.getFileFromResource(this.getClass(), "esus_r2rml_mapping.ttl");
        Mapping r2rml = MappingFactory.getMappingManager(file);

        r2rml.addFilter(f.getFilters());

        r2rml.getTriplesMap();
    }


    public void testIntersection() throws Exception {
        File file = FileUtils.getFileFromResource(this.getClass(), "esus_r2rml_mapping.ttl");
        Mapping r2rml = MappingFactory.getMappingManager(file);

        File owl = FileUtils.getFileFromResource(this.getClass(),"esus2.owl");
        JenaOntologyModelImpl jena2 = new JenaOntologyModelImpl(owl);

        Mapping tmp = r2rml.getMappingIntersectionWithOntology(jena2);
        Filter f = new FilterImpl("gissa:Pessoa","idadeReal"," > 20");
        List<Filter> list = new ArrayList<Filter>();
        list.add(f);

        tmp.addFilter(list.toArray(new Filter[list.size()]));

        tmp.generateFile("..\\novo_mapping.ttl");
    }
}


    /*
    SystemLoader loader = new SystemLoader();
        String mapping_file = FileUtils.getFileFromResource(this.getClass(),"esus_r2rml_mapping.ttl").toURI().toString();
        loader.setMappingFile(mapping_file);

        R2RMLReader reader = loader.getR2RMLReader();
        reader.getMapping().predicateObjectMaps();
//        reader.getMapping()
        System.out.println("Triples Map\n");
        for(org.d2rq.r2rml.TriplesMap tm : reader.getMapping().triplesMaps().components()) {
//            Resource s = tm.getLogicalTable().;
            System.out.println(PrettyPrinter.toString(tm.getLogicalTable()));

            for (PredicateObjectMap r : reader.getMapping().predicateObjectMaps().getAll(tm.getPredicateObjectMaps())) {
                for(Resource c : r.getPredicateMaps()) {
                    System.out.println(PrettyPrinter.toString(c));
                }
            }
        }
        /*for(TriplesMap tm : reader.getMapping().triplesMaps().resources()) {

            System.out.println(PrettyPrinter.toString(tm));
        }*/
//        reader.getMapping().triplesMaps().get();

   /* SystemLoader loader = new SystemLoader();
		loader.setJdbcURL(db.getJdbcURL());
		loader.setUsername(db.getUser());
		loader.setMappingFile(mappingFile);
		loader.setStartupSQLScript(sqlFile);
		loader.setSystemBaseURI(BASE_URI);

    R2RMLReader reader = loader.getR2RMLReader();
    MappingValidator validator = new MappingValidator(
            reader.getMapping(), loader.getSQLConnection());
			validator.setReport(reader.getReport());
			validator.run();
			if (!reader.getReport().hasError()) {
        fail("Expected validation error");
    }
			return;
}
    Model actualTriples = ModelFactory.createDefaultModel();
		actualTriples.add(loader.getModelD2RQ());
                Model expectedTriples = FileManager.get().loadModel(resultFile, "N-TRIPLES");
                ModelAssert.assertIsomorphic(expectedTriples, actualTriples);*//*
}*/


    /*public String toString() {
        StringBuilder s = new StringBuilder();
        s.append(problem.name());
        if (detailCode != null) {
            s.append('/');
            s.append(detailCode);
        }
        s.append('(');
        s.append(problem.getLevel());
        s.append(')');
        if (subject != null) {
            s.append("; resource: ");
            s.append(PrettyPrinter.toString(subject));
        }
        if (predicate != null) {
            s.append("; predicate: ");
            s.append(PrettyPrinter.toString(predicate));
        }
        if (object != null) {
            s.append("; object: ");
            s.append(PrettyPrinter.toString(object));
        }
        if (expectDetails) {
            s.append(" (details)");
        }
        return s.toString();
    }*/