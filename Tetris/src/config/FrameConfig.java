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


    /*按钮属性*/
    private final ButtonConfig buttonConfig;

    /*图层属性*/
    private final List<LayerConfig> layersConfig;

    public FrameConfig(Element frame) {
        //获取窗口宽度
        this.width = Integer.parseInt(frame.attributeValue("width"));
        //获取窗口高度
        this.height = Integer.parseInt(frame.attributeValue("height"));
        //获取边框 内边距
        this.padding = Integer.parseInt(frame.attributeValue("padding"));
        //获取边框粗细
        this.border = Integer.parseInt(frame.attributeValue("border"));
        //获取标题
        this.title = frame.attributeValue("title");
        //获取窗口拔高
        this.windowUp = Integer.parseInt(frame.attributeValue("windowUp"));
        //图块尺寸左位移
        this.sizeRol =Integer.parseInt(frame.attributeValue("sizeRol"));
        //游戏失败图片
        this.loseIdx =Integer.parseInt(frame.attributeValue("loseIdx"));
        //获取窗体属性
        List<Element> layers = frame.elements("layer");

        layersConfig = new ArrayList<LayerConfig>();

        //设置所有窗体属性
        for (Element layer : layers) {
            //设置单个窗体属性
            LayerConfig lc = new LayerConfig(
                    layer.attributeValue("className"),
                    Integer.parseInt(layer.attributeValue("x")),
                    Integer.parseInt(layer.attributeValue("y")),
                    Integer.parseInt(layer.attributeValue("w")),
                    Integer.parseInt(layer.attributeValue("h"))
            );
            layersConfig.add(lc);
        }
        //初始化按钮属性
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
