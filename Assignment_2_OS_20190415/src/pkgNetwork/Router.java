
package pkgNetwork;

public class Router extends Thread {
    Network networkObj = new Network();
    Semaphore semaphoreObj;

    Router() {
        semaphoreObj = new Semaphore(networkObj.N);
    }

    public void connect() throws InterruptedException {      
        for (int i = 0; i < networkObj.devices.size(); i++) {
            Thread t = new Thread(this, networkObj.devices.get(i).getName());//(This Thread , Name of Device)
            t.start();
        }        
    }
    

    @Override
    public void run() {
    // here all code will be executed when call start() in main function
        try {
            String name = "";
            semaphoreObj.acquire(Thread.currentThread().getName());
            name = Thread.currentThread().getName();
            Thread.currentThread().sleep(100);
            String outF1 = "- Connection " + Network.connectionNumber(name, 0) + ": " + Thread.currentThread().getName() + " occupied";
            System.out.println(outF1);
            
            Thread.currentThread().sleep(200 );
            String outF0 = "- Connection " + Network.connectionNumber(name, 0) + ": " + Thread.currentThread().getName() + " login";
            System.out.println(outF0);  
            
            Thread.currentThread().sleep(300);
            String outF2 = "- Connection " + Network.connectionNumber(name, 0) + ": " + Thread.currentThread().getName() + " Performs online activity";
            System.out.println(outF2);
            
            Thread.currentThread().sleep(400);
            semaphoreObj.release(Thread.currentThread().getName());
            Thread.currentThread().stop();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}