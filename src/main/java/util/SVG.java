package util;

public class SVG
{
    StringBuilder svg = new StringBuilder();

    public SVG(int x, int y, String viewBox, int width, int height) {
        String headerTemplate = "<svg height=\"%d%%\" " +
                "width=\"%d%%\" " +
                "viewBox=\"%s\" " +
                "x=\"%d\"   " +
                "y=\"%d\"   " +
                " preserveAspectRatio=\"xMinYMin\">";
        svg.append(String.format(headerTemplate, height, width, viewBox, x, y ));
    }

    public void addRect(int x, int y, double height, double width) {
        String rectTemplate = "<rect x=\"%d\" y=\"%d\" height=\"%f\" width=\"%f\" style=\"stroke:#000000; fill: #ffffff\" />";
        svg.append(String.format(rectTemplate, x, y, height, width));
    }

    public void addLine(int x1, int y1, int x2, int y2 ) {
        String lineTemplate ="line x1=\"%d\" y1=\"%d\" x2=\"%d\" y2=\"%d\" style=\"stroke:#000000; fill: #ffffff\" />";
        svg.append(String.format(lineTemplate, x1, y1, x2, y2));
    }

    public void addSvg(SVG innerSVG) {
        svg.append(innerSVG.toString());
    }

    @Override
    public String toString() {
        return svg.toString() + "</svg>" ;
    }
}
