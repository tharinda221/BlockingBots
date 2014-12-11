
public class BlockIP {
    private String ip;
    private GetLocation obj = new GetLocation();


    public   BlockIP(String ip){
        this.ip=ip;

        RunThis();
    }
    public void RunThis(){
        if(!ip.isEmpty()){
            System.out.println("iptables" + " " + "-A" + " " + "INPUT" + " " + "-s" + " " + ip + " " + "-j" + " " + "DROP");
            obj.getLocation(ip);
        }
        else{
            System.out.print("");
        }
    }

}

