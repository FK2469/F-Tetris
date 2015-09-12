package config;


import org.dom4j.Element;

import java.util.List;

/**
 * Created by admin on 2015/5/23.
 */
public class DataConfig {

    private final int maxRow;
    private final DataInterfaceConfig dataA;
    private final DataInterfaceConfig dataB;

    public DataConfig(Element data) {
        /*List<Element>dataInterfaces =  data.elements("dataInterface");*/
        this.maxRow = Integer.parseInt(data.attributeValue("maxRow"));
        this.dataA = new DataInterfaceConfig(data.element("dataA"));
        this.dataB = new DataInterfaceConfig(data.element("dataB"));
    }

    public DataInterfaceConfig getDataA() {
        return dataA;
    }

    public DataInterfaceConfig getDataB() {
        return dataB;
    }

    public int getMaxRow() {
        return maxRow;
    }
}
