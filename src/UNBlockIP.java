import java.util.concurrent.ScheduledFuture;


public class UNBlockIP implements Runnable {
    private int k;
    private String ip;
    static ScheduledFuture<?> t;
    public UNBlockIP(String ip){
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
            System.out.println("iptables" + " " + "-D" + " " + "INPUT" + " " + "-s" + " " + ip + " " + "-j" + " " + "DROP");
        }


    }
}
