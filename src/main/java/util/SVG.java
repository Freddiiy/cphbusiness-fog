package util;

import util.drawing.svg.basicshapes.Rect;

public class SVG
{
    StringBuilder svg = new StringBuilder();
    private final int MARKER_WIDTH = 10;
    private final int MARKER_HEIGHT = 7;

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
                        MARKER_WIDTH, MARKER_HEIGHT,
                        MARKER_WIDTH, MARKER_HEIGHT
                )
        );
    }

    public SVG(int x, int y, int width, int height) {
        this(x, y, new Rect(x, y, width, height), width, height);
    }

    public void addRect(int x, int y, int width, int height) {
        String rectTemplate = "<rect x=\"%d\" y=\"%d\" height=\"%d\" width=\"%d\" style=\"stroke:#000; fill: #fff\" />";
        svg.append(String.format(rectTemplate, x, y, height, width));
    }

    public void addRect(Rect rect) {
        String rectTemplate = "<rect x=\"%d\" y=\"%d\" height=\"%d\" width=\"%d\" style=\"stroke:#000; fill: #fff\" />";
        svg.append(String.format(rectTemplate, rect.getX(), rect.getY(), rect.getW(), rect.getH()));
    }

    public void addLine(int x1, int y1, int x2, int y2 ) {
        String lineTemplate ="<line x1=\"%d\" y1=\"%d\" x2=\"%d\" y2=\"%d\" stroke=\"#000\" />";
        svg.append(String.format(lineTemplate, x1, y1, x2, y2));
    }

    /** A line with arrowheads in both ends and text above or next to it.
     *  Useful for conveying scale. */
    public void addArrowLine(int x1, int y1, int x2, int y2, String text) {
        if (lineIsHorizontal(x1, y1, x2, y2)) {
            this.addHorizontalArrowLine(x1, y1, x2, y2, text);
        } else {
            this.addVerticalArrowLine(x1, y1, x2, y2, text);
        }
    }

    /**  70cm
     * <------>
     * */
    private void addHorizontalArrowLine(int x1, int y1, int x2, int y2, String text) {
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
        int yText = y1 - 10; // Doesn't account for odd angles
        String textTemplate = "" +
                "<text " +
                "   x=\"%d\" " +
                "   y=\"%d\" " +
                "   font-size=\"smaller\" " +
                "   text-anchor=\"middle\"" +
                ">%s</text>";
        svg.append(
                String.format(
                        lineTemplate,
                        x1 + MARKER_WIDTH,
                        y1,
                        x2 - MARKER_WIDTH,
                        y2
                )
        );
        svg.append(String.format(textTemplate, xText, yText, text));
    }

    /**      /\
     *       |
     * 10 cm |
     *       |
     *      \/
     * */
    private void addVerticalArrowLine(int x1, int y1, int x2, int y2, String text) {
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
        int xText = x1 - MARKER_WIDTH;
        int yText = y1 + (y2 - y1) / 2;
        String textTemplate = "" +
                "<text " +
                "   x=\"%d\" " +
                "   y=\"%d\" " +
                "   font-size=\"smaller\" " +
                "   text-anchor=\"end\"" +
                "   dominant-baseline=\"middle\"" +
                ">%s</text>";
        svg.append(
                String.format(
                        lineTemplate,
                        x1,
                        y1 + MARKER_HEIGHT,
                        x2,
                        y2 + MARKER_HEIGHT
                )
        );
        svg.append(String.format(textTemplate, xText, yText, text));
    }

    /** We won't be dealing with lines that are not 0, π/2, π or 3π/2 radians */
    public boolean lineIsHorizontal(int x1, int y1, int x2, int y2) {
        if (y1 == y2 && x1 != x2) return true;
        if (x1 == x2 && y1 != y2) return false;

        System.out.println("WARNING: We're not equipped to draw skewed lines. See SVG.lineIsHorizontal()");
        int xDiff = x2 - x1;
        int yDiff = y2 - y1;
        return xDiff > yDiff;
    }

    @Override
    public String toString() {
        return svg.toString() + "</svg>" ;
    }
}
