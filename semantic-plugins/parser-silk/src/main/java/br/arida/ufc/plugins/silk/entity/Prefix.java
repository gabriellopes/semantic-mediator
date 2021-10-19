package br.arida.ufc.plugins.silk.entity;

/**
 * Created by gabriellopes9102 on 12/01/2017.
 */
public interface Prefix extends SilkField {


    void setId(String id);

    String getId();

    String getNamespace();

    void setNamespace(String namespace);
}
