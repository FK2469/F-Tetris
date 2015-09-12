package config;


import org.dom4j.Element;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by admin on 2015/5/23.
 */
public class FrameConfig {

    private final String title;

    private final int width;

    private final int height;

    private final int padding;

    private final int windowUp;

    private final int border;

    private final int sizeRol;

    private final int loseIdx ;


    /*��ť����*/
    private final ButtonConfig buttonConfig;

    /*ͼ������*/
    private final List<LayerConfig> layersConfig;

    public FrameConfig(Element frame) {
        //��ȡ���ڿ��
        this.width = Integer.parseInt(frame.attributeValue("width"));
        //��ȡ���ڸ߶�
        this.height = Integer.parseInt(frame.attributeValue("height"));
        //��ȡ�߿� �ڱ߾�
        this.padding = Integer.parseInt(frame.attributeValue("padding"));
        //��ȡ�߿��ϸ
        this.border = Integer.parseInt(frame.attributeValue("border"));
        //��ȡ����
        this.title = frame.attributeValue("title");
        //��ȡ���ڰθ�
        this.windowUp = Integer.parseInt(frame.attributeValue("windowUp"));
        //ͼ��ߴ���λ��
        this.sizeRol =Integer.parseInt(frame.attributeValue("sizeRol"));
        //��Ϸʧ��ͼƬ
        this.loseIdx =Integer.parseInt(frame.attributeValue("loseIdx"));
        //��ȡ��������
        List<Element> layers = frame.elements("layer");

        layersConfig = new ArrayList<LayerConfig>();

        //�������д�������
        for (Element layer : layers) {
            //���õ�����������
            LayerConfig lc = new LayerConfig(
                    layer.attributeValue("className"),
                    Integer.parseInt(layer.attributeValue("x")),
                    Integer.parseInt(layer.attributeValue("y")),
                    Integer.parseInt(layer.attributeValue("w")),
                    Integer.parseInt(layer.attributeValue("h"))
            );
            layersConfig.add(lc);
        }
        //��ʼ����ť����
        buttonConfig = new ButtonConfig(frame.element("button"));
    }

    public String getTitle() {
        return title;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public int getPadding() {
        return padding;
    }

    public int getWindowUp() {
        return windowUp;
    }

    public int getBorder() {
        return border;
    }

    public int getSizeRol() {
        return sizeRol;
    }

    public int getLoseIdx() {
        return loseIdx;
    }

    public List<LayerConfig> getLayersConfig() {
        return layersConfig;
    }

    public ButtonConfig getButtonConfig() {
        return buttonConfig;
    }
}
