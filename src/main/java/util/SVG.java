package util;

import util.drawing.svg.basicshapes.Rect;

public class SVG
{
    StringBuilder svg = new StringBuilder();
    private final int MARKER_WIDTH = 10;

    /**
     * todo: Scaling
     *
     * Let's say the viewport width is 800 units.
     * Inside the viewport is the viewbox.
     *
     * One centimeter translates to one unit in SVG.
     * If a carport is only 400 cm, we can make the viewbox 400 units,
     * so that for every one unit in the viewport, the viewbox will show two units.
     *
     * That way, the carport drawing will always be the same size,
     * no matter the real world size of the carport.
     *
     * */

    public SVG(int x, int y, Rect viewBox, int width, int height) {
        String headerTemplate = "<svg height=\"%d\" " +
                "width=\"%d\" " +
                "viewBox=\"%d %d %d %d\" " +
                "x=\"%d\"   " +
                "y=\"%d\"   " +
                "preserveAspectRatio=\"xMinYMin\" " +
                "xmlns=\"http://www.w3.org/2000/svg\">" +
                "    <defs>\n" +
                "        <marker\n" +
                "          id=\"startarrow\"\n" +
                "          markerWidth=\"%d\"\n" +
                "          markerHeight=\"%d\"\n" +
                "          refX=\"10\"\n" +
                "          refY=\"3.5\"\n" +
                "          orient=\"auto\"\n" +
                "        >\n" +
                "          <polygon points=\"10 0, 10 7, 0 3.5\" fill=\"#000\" />\n" +
                "        </marker>\n" +
                "        <marker\n" +
                "          id=\"endarrow\"\n" +
                "          markerWidth=\"%d\"\n" +
                "          markerHeight=\"%d\"\n" +
                "          refX=\"0\"\n" +
                "          refY=\"3.5\"\n" +
                "          orient=\"auto\"\n" +
                "          markerUnits=\"strokeWidth\"\n" +
                "        >\n" +
                "          <polygon points=\"0 0, 10 3.5, 0 7\" fill=\"#000\" />\n" +
                "        </marker>\n" +
                "    </defs>";
        svg.append(
                String.format(
                        headerTemplate,
                        height, width,
                        viewBox.getX(), viewBox.getY(),
                        viewBox.getW(), viewBox.getH(),
                        x, y,
                        MARKER_WIDTH, MARKER_WIDTH,
                        MARKER_WIDTH, MARKER_WIDTH
                )
        );
    }

    public SVG(int x, int y, int width, int height) {
        this(x, y, new Rect(x, y, width, height), width, height);
    }

    public void addElement(SVGElement element) {
        svg.append(element.toString());
    }

    public void addElements(SVGElement... elements) {
        for (SVGElement elem : elements) {
            svg.append(elem.toString());
        }
    }

    @Override
    public String toString() {
        return svg.toString() + "</svg>" ;
    }
}
