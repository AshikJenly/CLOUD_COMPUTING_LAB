
import java.util.*;

import org.cloudbus.cloudsim.core.CloudSim;

import org.cloudbus.cloudsim.*;

import org.cloudbus.cloudsim.provisioners.*;

public class VmScheduling
{
    public static List<Vm> vmlist;
    public static List<Cloudlet> cloudletlist;
    public static DatacenterBroker broker;
    public static void main(String args[]) throws Exception
    {
        CloudSim.init(1,Calendar.getInstance(),false);

        
        Datacenter datacenter = createDataFrame("datacenter_0");
        broker = new DatacenterBroker("Broker1");
        int bid = broker.getId();

        vmlist = new ArrayList<>();
        cloudletlist = new ArrayList<>();
        
        Vm vm = new Vm(0,bid,1000,1,512,100,100,"Xen",new CloudletSchedulerTimeShared());
        vmlist.add(vm);
        broker.submitVmList(vmlist);


        CloudSim.startSimulation();
        CloudSim.stopSimulation();


        for(Cloudlet cloudlet : broker.getCloudletReceivedList())
        {
            if(cloudlet.getCloudletStatus() == Cloudlet.SUCCESS)
               Log.printLine("Cloudlet ID: " + cloudlet.getCloudletId() + "\tSUCCESS");
            else 
                Log.printLine("Cloudlet ID: " + cloudlet.getCloudletId() + "\tFAILED");
        }
    }

    public static Datacenter createDataFrame(String name) throws Exception
    {
        Datacenter datacenter = null;
        List <Host> hostlist = new ArrayList<>();
        List <Pe> pelist = new ArrayList<>();
        List <Storage> storagelist = new LinkedList<>();

        pelist.add(new Pe(0,new PeProvisionerSimple(10000)));

        hostlist.add(new Host(
            0,
            new RamProvisionerSimple(100000),
            new BwProvisionerSimple(100000),
            1000,
            pelist,
            new VmSchedulerTimeShared(pelist)    

        ));


        DatacenterCharacteristics characteristics = new DatacenterCharacteristics("x86","Linux","Xen",hostlist,10.0,3.0,10.0,10.0,1.0);

        datacenter = new Datacenter(name,characteristics,new VmAllocationPolicySimple(hostlist),storagelist,1);
        return datacenter;
    }
}