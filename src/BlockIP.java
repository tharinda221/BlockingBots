
public class BlockIP {
    private String ip;



    public   BlockIP(String ip){
        this.ip=ip;

        RunThis();
    }
    public void RunThis(){
        if(!ip.isEmpty()){
            System.out.println("iptables" + " " + "-A" + " " + "INPUT" + " " + "-s" + " " + ip + " " + "-j" + " " + "DROP");

        }
        else{
            System.out.print("");
        }
    }

}

