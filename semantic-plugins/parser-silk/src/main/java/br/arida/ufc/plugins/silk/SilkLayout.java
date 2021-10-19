package br.arida.ufc.plugins.silk;

import br.arida.ufc.plugins.silk.entity.Interlink;
import br.arida.ufc.plugins.silk.entity.Silk;

import java.io.File;
import java.util.List;

/**
 * Created by gabriellopes9102 on 19/12/2016.
 */
public interface SilkLayout{

    Silk getSilk();

    File generateSilkFile(String output_path);



    /* XML SILK

    <Silk>
    <Interlinks>
        <Interlink id="genes"> -> nome do LinksetView
            <LinkType>owl:sameAs</LinkType> -> Tipo LinksetView

            <!-- a dummy dataset, the real dataset is defined at runtime -->
            <SourceDataset dataSource="KEGG_GENES" var="a"> -> Exported View
                <RestrictTo>?a rdf:type smwcat:Disease</RestrictTo> -> Classe source
            </SourceDataset>

            <TargetDataset dataSource="Wiki" var="b"> -> Exported View
                <RestrictTo>?b rdf:type smwcat:Disease</RestrictTo> -> Classe source
            </TargetDataset>

            <LinkageRule> -> Por enquanto, só serão comparados uma propriedade
                <Aggregate type="max"> -> default
                    <Compare metric="equality">
                        <Input path="?a/smwprop:KeggDiseaseId" /> -> Propriedade EV_T
                        <Input path="?b/smwprop:KeggDiseaseId" /> -> Propriedade EV_U
                    </Compare>
                </Aggregate>
            </LinkageRule>

            <Filter threshold="1.0" />

        </Interlink>

    </Interlinks>
</Silk>

     */
}
