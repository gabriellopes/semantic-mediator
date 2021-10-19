package br.arida.ufc.core.entity;

/**
 * Created by gabriellopes9102 on 03/01/2017.
 */

import org.apache.jena.ontology.OntClass;

/**
 * TODO: Criar um pacote para representação própria dos elemetnos de uma ontologia. Assim, não ficaríamos
 * 'presos' ao JENA.
 */
public interface OntoClass{

    String getURI();

    Prefix getPrefix();

    String getClassName();

    /**
      Faz com que cada implementação de ontoclass possa fazer o cast para a classe a ser manipulada pela API de ontologia
     Ex: transforma OntoClass em OntClass (Jena)
     */
    <T> T as(Class<T> view);
}
