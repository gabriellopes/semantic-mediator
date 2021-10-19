package br.arida.ufc.plugins.silk.entity;


/**
 * Created by gabriellopes9102 on 19/12/2016.
 */
public interface Interlink extends SilkField{

    public String getId();

    public void setId(String id);

    public LinkageRule getLinkageRule();

    public SourceDataset getSourceDataset();

    public void setSourceDataset(SourceDataset source_dataset);

    public TargetDataset getTargetDataset();

    public void setTargetDataset(TargetDataset target_dataset);
}
