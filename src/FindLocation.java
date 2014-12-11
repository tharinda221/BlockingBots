import java.io.BufferedWriter;
import java.io.FileWriter;


public class FindLocation {

    private String lt;
    private String ln;
    private String ip;

    public  FindLocation(String lt,String ln,String ip){
        this.lt=lt;
        this.ln=ln;
        this.ip=ip;
        String file = ".\\location"+"_"+ip+".html";

        try{
            BufferedWriter writer = new BufferedWriter (new FileWriter(file));
            writer.write("<!DOCTYPE html>");
            writer.write("\r\n");
            writer.write("<html>");
            writer.write("\r\n");
            writer.write("<head>");
            writer.write("\r\n");
            writer.write("<style type=\"text/css\">");
            writer.write("\r\n");
            writer.write("html, body, #map-canvas { height: 100%; margin: 0; padding: 0;}");
            writer.write("\r\n");
            writer.write("</style>");
            writer.write("\r\n");
            writer.write("<script type=\"text/javascript\"\n" +
                    "  src=\"https://maps.googleapis.com/maps/api/js?libraries=geometry\">\n" +
                    "    </script>");
            writer.write("\r\n");
            writer.write("<script type=\"text/javascript\">");
            writer.write("\r\n");
            writer.write("function initialize() {" );
            writer.write("\r\n");

            writer.write("var myLatlng = new google.maps.LatLng("+ " "+lt+" "+","+ " "+ln+");");
            writer.write("\r\n");

            writer.write("var mapOptions = { zoom: 8, center: myLatlng}");
            writer.write("\r\n");
            writer.write("var map = new google.maps.Map(document.getElementById"+"(\""+"map-canvas"+"\")"+", mapOptions);");
            writer.write("\r\n");
            writer.write(" var marker = new google.maps.Marker({ position: myLatlng, map: map, draggable:true, title:"+"\""+"Drag me!"+"\""+"});}");
            writer.write("\r\n");


            writer.write("google.maps.event.addDomListener(window, 'load', initialize);");
            writer.write("\r\n");
            writer.write("</script>");
            writer.write("\r\n");
            writer.write("</head>");
            writer.write("\r\n");
            writer.write("<body>");
            writer.write("\r\n");
            writer.write("<div id=\"map-canvas\"></div>");
            writer.write("\r\n");
            writer.write("</body>");
            writer.write("\r\n");
            writer.write("</html>");
            writer.write("\r\n");
            writer.close();
        }catch(Exception e){
            System.out.println(e);
        }
    }
}
