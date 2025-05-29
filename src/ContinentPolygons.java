import java.awt.Polygon;

public class ContinentPolygons {
    
    public static Polygon getEuropePolygon() {
        Polygon polygon = new Polygon();
        polygon.addPoint(770,100);
        polygon.addPoint(1050,100);
        polygon.addPoint(1050,400);
        polygon.addPoint(720,400);
        return polygon;
    }

    public static Polygon getAsiaPolygon() {
        Polygon polygon = new Polygon();
        polygon.addPoint(1100, 150);
        polygon.addPoint(1700, 150);
        polygon.addPoint(1700, 500);
        polygon.addPoint(1350, 600);
        polygon.addPoint(1050, 480);
        return polygon;
    }

    public static Polygon getAfricaPolygon() {
        Polygon polygon = new Polygon();
        polygon.addPoint(780, 410);
        polygon.addPoint(1000, 410);
        polygon.addPoint(1080, 540);
        polygon.addPoint(1200, 650);
        polygon.addPoint(1100, 700);
        polygon.addPoint(700, 700);
        return polygon;
    }

     public static Polygon getAmericaNordPolygon(){
        Polygon polygon = new Polygon();
        polygon.addPoint(50, 100);//punto in alto a sinistra
        polygon.addPoint(750, 100);//punto in alto a destra 
        polygon.addPoint(680, 460);//punto in basso a destra
        polygon.addPoint(50, 495);//punto in basso a sinistra
        return polygon;
    }
    public static Polygon getAmericaPolygon() {
        //AMERICA DEL SUD
        Polygon polygon = new Polygon();
        polygon.addPoint(50, 500);//punto in alto a sinistra
        polygon.addPoint(750, 475);//punto in alto a destra
        polygon.addPoint(650, 800);//punto in basso a destra
        polygon.addPoint(50, 800);//punto in basso a sinistra
        return polygon;
    }

    public static Polygon getOceaniaPolygon() {
        Polygon polygon = new Polygon();
        polygon.addPoint(1350, 610);
        polygon.addPoint(1700, 510);
        polygon.addPoint(1700, 760);
        polygon.addPoint(1350, 760);
        return polygon;
    }
}