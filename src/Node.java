import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.io.FileWriter;
import java.io.BufferedWriter;

public class Node {

    private  String ip;
    private String time;
    private int count;



    public Node(String ip,int count,String time){

        this.count=count;
        this.time=time;
        this.ip=ip;
    }
    public String getIp(){
        return ip;
    }
    public int getCount(){
        return count;
    }
    public String getTime(){
        return time;
    }

    public void display(){
        System.out.println(ip+" "+count+" "+time);
    }
    public static boolean searchNode(HashMap<String, Node> IPs,String ipAdd){
        for (String key : IPs.keySet()) {
            if(IPs.get(key).getIp().equals(ipAdd)) {
                return true;

            }
        }
        return false;
    }
    public static void UpdateNode(HashMap<String, Node> IPs,String ipAdd,int value){

        for(String key:IPs.keySet()){
            if(IPs.get(key).getIp().equals(ipAdd)) {
                IPs.put(key,new Node(key,IPs.get(key).getCount()+value,IPs.get(key).getTime()) );
                //System.out.println(IPs.get(key).getCount());
            }
        }
    }
    public static boolean SelectIpAdd(HashMap<String, Node> IPs,String ipAdd,int value){

        for (String key : IPs.keySet()) {
            if(IPs.get(key).getIp().equals(ipAdd)) {
                if(IPs.get(key).getCount()==value){
                    return true;
                }

            }
        }
        return false;
    }

    public static int searchKey(HashMap<String, Node> IPs,String ipAdd){
        int r=0;
        for (String key : IPs.keySet()) {
            if(IPs.get(key).getIp().equals(ipAdd)) {
                r=IPs.get(key).getCount();

            }
        }
        return r;
    }
    public  static void writeIPS(String ip,String time,String file){

        try{
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file, true)));
            writer.write(ip+" "+time);
            writer.write("\r\n");
            writer.close();
        }catch (Exception e){
            System.out.println(e);
        }
    }
    public  static  void PrintNode(HashMap<String, Node> IPs){
        for (String key : IPs.keySet()) {

            IPs.get(key).display();
        }
    }



}
