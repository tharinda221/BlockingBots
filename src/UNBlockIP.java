import java.util.concurrent.ScheduledFuture;


public class UNBlockIP implements Runnable {
    private int k;
    private String ip;
    private String v;
    static ScheduledFuture<?> t;
    public UNBlockIP(String ip,String v){
        this.v=v;
        this.ip=ip;
    }

    public void run(){
        k=0;
        RunThis();
        k++;
        if (k>0) {
            t.cancel(true);
        }
    }
    public void RunThis(){
        if(! ip.equals("")){
            if(v.equals("ns")) {
                System.out.println("iptables" + " " + "-D" + " " + "INPUT" + " " + "-s" + " " + ip + " " + "-j" + " " + "DROP");
            }
            else{
                System.out.println("iptables"+" "+"-D"+" "+"INPUT"+" "+"-s"+" " + ip+" " + "-j"+" "+ "ACCEPT");
            }
        }
    }
}
