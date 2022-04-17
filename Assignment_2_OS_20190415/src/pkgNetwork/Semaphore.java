package pkgNetwork;
import java.io.IOException;

public class Semaphore {
    public int value = 0;
    public String output = "";

    Semaphore(int bound) {
        this.value = bound;
    }
    /*
        This method acquires a permit, if one is available and returns immediately, reducing the number of available permits by one.
        If the current thread is interrupted while waiting for a permit then InterruptedException is thrown.
    */
    public synchronized void acquire(String nameOfDevice) throws InterruptedException, IOException {
        value--;        
        if (value < 0) {
            for (int i = 0; i < Network.devices.size(); i++) {
                if (Network.devices.get(i).getName().equals(nameOfDevice)) {
                    output = "- " + nameOfDevice + " ( " + Network.devices.get(i).getType() + " )" + " arrived and waiting";
                    break;
                }
            }
            System.out.println(output);
            wait();
        } else {
            for (int i = 0; i < Network.devices.size(); i++) {
                if (Network.devices.get(i).getName().equals(nameOfDevice)) {
                    output = "- "+ nameOfDevice + " ( " + Network.devices.get(i).getType() + " )" + " arrived";
                    break;
                }
            }
            System.out.println(output);
        }
    }
/*
    This method releases a permit, increasing the number of available permits by one. 
    If any threads are trying to acquire a permit, then one is selected and given the permit that was just released.
*/
    public synchronized void release(String nameOfDevice) throws IOException {
        value++;
        output = "- Connection " + Network.connectionNumber(nameOfDevice, 1) + ": " + nameOfDevice + " Logged out";
        if (value <= 0){
            notify();   
        }
        System.out.println(output);
    }

}