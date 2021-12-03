package util;

import util.drawing.svg.basicshapes.Rect;

public class SVG
{
    StringBuilder svg = new StringBuilder();



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
                "          markerWidth=\"10\"\n" +
                "          markerHeight=\"7\"\n" +
                "          refX=\"10\"\n" +
                "          refY=\"3.5\"\n" +
                "          orient=\"auto\"\n" +
                "        >\n" +
                "          <polygon points=\"10 0, 10 7, 0 3.5\" fill=\"#000\" />\n" +
                "        </marker>\n" +
                "        <marker\n" +
                "          id=\"endarrow\"\n" +
                "          markerWidth=\"10\"\n" +
                "          markerHeight=\"7\"\n" +
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
                        height,
                        width,
                        viewBox.getX(),
                        viewBox.getY(),
                        viewBox.getW(),
                        viewBox.getH(),
                        x,
                        y
                )
        );
    }

    public SVG(int x, int y, int width, int height) {
        this(x, y, new Rect(x, y, width, height), width, height);
    }

    public void addRect(int x, int y, double height, double width) {
        String rectTemplate = "<rect x=\"%d\" y=\"%d\" height=\"%f\" width=\"%f\" style=\"stroke:#000000; fill: #fff\" />";
        svg.append(String.format(rectTemplate, x, y, height, width));
    }

    public void addLine(int x1, int y1, int x2, int y2 ) {
        String lineTemplate ="line x1=\"%d\" y1=\"%d\" x2=\"%d\" y2=\"%d\" style=\"stroke:#000000; fill: #fff\" />";
        svg.append(String.format(lineTemplate, x1, y1, x2, y2));
    }

    /** <-----> */
    public void addArrowLine(int x1, int y1, int x2, int y2) {
        String lineTemplate = "" +
                "<line\n" +
                "    x1=\"%d\"\n" +
                "    y1=\"%d\"\n" +
                "    x2=\"%d\"\n" +
                "    y2=\"%d\"\n" +
                "    stroke=\"#000\"\n" +
                "    marker-end=\"url(#endarrow)\"\n" +
                "    marker-start=\"url(#startarrow)\"\n" +
                "></line>";
        svg.append(String.format(lineTemplate, x1, y1, x2, y2));
    }

    /**  70cm
     * <------>
     * */
    public void addArrowLine(int x1, int y1, int x2, int y2, String text) {
        String lineTemplate = "" +
                "<line\n" +
                "    x1=\"%d\"\n" +
                "    y1=\"%d\"\n" +
                "    x2=\"%d\"\n" +
                "    y2=\"%d\"\n" +
                "    stroke=\"#000\"\n" +
                "    marker-end=\"url(#endarrow)\"\n" +
                "    marker-start=\"url(#startarrow)\"\n" +
                "></line>";
        int xText = x1 + (x2 - x1) / 2;
        int yText = y1 - 20; // Doesn't account for odd angles
        String textTemplate = "<text x=\"%d\" y=\"%d\" class=\"small\">%s</text>";
        svg.append(String.format(lineTemplate, x1, y1, x2, y2));
        svg.append(String.format(textTemplate, xText, yText, text));
    }

    @Override
    public String toString() {
        return svg.toString() + "</svg>" ;
    }
}
