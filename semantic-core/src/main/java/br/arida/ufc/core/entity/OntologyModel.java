package br.arida.ufc.core.entity;

import br.arida.ufc.core.step.MashupStep;

/**
 * Created by gabriellopes9102 on 14/12/2016.
 */
public interface OntologyModel extends MashupStep{

    OntologyModel getIntersectionWithOntology(OntologyModel onto);

    OntoClass[] getOntoClasses();

    void addPrefix(Prefix prefix);

    Object getPrefixes();

    String getPrefixFromURI(String uri);

    String getName();

    void setName(String name);

    String toString();

    void generateFile(String output);

    boolean hasStatement(Object stm);

    void addStatement(Object stm);

    Object getModel();

    Object getOntoClass(String aClass);
}
