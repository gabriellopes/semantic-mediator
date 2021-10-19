package br.arida.ufc.core.entity;

import java.util.Collection;

/**
 * Created by gabriellopes9102 on 14/12/2016.
 */
public interface Mapping {

    Object getClassByType(Object type);

    <T> Collection<T> getTriplesMap();
// throws ClassNotFoundException
    Object getEquivalentIfExists(Object obj) throws ClassNotFoundException;

//    void addFilter()
    Mapping getMappingIntersectionWithOntology(OntologyModel ontology);

    void addFilter(Filter[] filters);

    void generateFile(String output_path);

    void removeTripleMap(Object triple_map);
}
