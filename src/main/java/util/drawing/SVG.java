package util.drawing;

public class SVG
{
    private StringBuilder svg = new StringBuilder();
    private final int MARKER_WIDTH = 10;

    private final String HEADER_TEMPLATE = "" +
            "<svg height=\"%d\" " +
            "   width=\"%d\" " +
            "   viewBox=\"%d %d %d %d\" " +
            "   x=\"%d\"   " +
            "   y=\"%d\"   " +
            "   preserveAspectRatio=\"xMinYMin\" " +
            "   xmlns=\"http://www.w3.org/2000/svg\">" +
            "       <defs>\n" +
            "           <marker\n" +
            "             id=\"startarrow\"\n" +
            "             markerWidth=\"%d\"\n" +
            "             markerHeight=\"%d\"\n" +
            "             refX=\"10\"\n" +
            "             refY=\"3.5\"\n" +
            "             orient=\"auto\"\n" +
            "           >\n" +
            "             <polygon points=\"10 0, 10 7, 0 3.5\" fill=\"#000\" />\n" +
            "           </marker>\n" +
            "           <marker\n" +
            "             id=\"endarrow\"\n" +
            "             markerWidth=\"%d\"\n" +
            "             markerHeight=\"%d\"\n" +
            "             refX=\"0\"\n" +
            "             refY=\"3.5\"\n" +
            "             orient=\"auto\"\n" +
            "             markerUnits=\"strokeWidth\"\n" +
            "           >\n" +
            "             <polygon points=\"0 0, 10 3.5, 0 7\" fill=\"#000\" />\n" +
            "           </marker>\n" +
            "       </defs>";

    public SVG(SVGRect viewport, SVGRect viewBox) {
        svg.append(
                String.format(
                        HEADER_TEMPLATE,
                        viewport.getH(), viewport.getW(),
                        viewBox.getX(), viewBox.getY(),
                        viewBox.getW(), viewBox.getH(),
                        viewport.getX(), viewport.getY(),
                        MARKER_WIDTH, MARKER_WIDTH,
                        MARKER_WIDTH, MARKER_WIDTH
                )
        );
    }

    public SVG(int x, int y, int width, int height, SVGRect viewBox) {
        this(SVGRect.of(x, y, width, height), viewBox);
    }

    public SVG(int x, int y, int w, int h) {
        this(x, y, w, h, SVGRect.of(x, y, w, h));
    }

    public void addElement(SVGElement element) {
        svg.append(element.toString());
    }

    public void addElements(SVGElement... elements) {
        for (SVGElement elem : elements) {
            svg.append(elem.toString());
        }
    }

    public void addElements(SVGElement[]... elementLists) {
        for (SVGElement[] elemList : elementLists) {
            addElements(elemList);
        }
    }

    @Override
    public String toString() {
        return svg.toString() + "</svg>" ;
    }
}
