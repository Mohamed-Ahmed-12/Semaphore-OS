package pkgNetwork;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Scanner;
import java.util.ArrayList;

public class Network {

    public static Scanner input = new Scanner(System.in);
    public static ArrayList<Device> devices = new ArrayList<Device>();
    public static ArrayList<String> names = new ArrayList<String>();
    public static ArrayList<Boolean> state = new ArrayList<Boolean>();
    public static int N;    //maximum number of connections that router accept
    public static int TC;   //total number of devices wish to connect
    
    public synchronized static int connectionNumber(String DeviceName, int x){
        int connNum = 0;
        int flag = 0;
        if (x == 1) {
            for (int k = 0; k < N; k++) {
                if (names.get(k).equals(DeviceName)) {
                    names.set(k, "");
                    state.set(k, false);
                    connNum = k + 1;
                }
            }
        } else {
            for (int i = 0; i < N; i++) {
                if (DeviceName.equals(names.get(i))) {
                    connNum = i + 1;
                    flag++;
                }
            }
            if (flag == 0) {
                for (int j = 0; j < N; j++) {
                    if (state.get(j) == false) {
                        state.set(j, true);
                        connNum = j + 1;
                        names.set(j, DeviceName);
                        break;
                    }
                }
            }
        }
        return connNum;
    }
        
    public static void main(String[] args) throws InterruptedException, IOException {
        System.out.println("What is number of WI-FI Connections?");
        N = input.nextInt();
        
        System.out.println("What is number of devices clients want to connect?");
        TC = input.nextInt();

        for (int i = 0; i < TC; i++) {
            String name = input.next();
            String type = input.next();
            devices.add(new Device(name, type));
        }
        
        for (int j = 0; j < N; j++) {
            names.add("");
            state.add(false);
        }
        //to start connection in router
        Router routerObj = new Router();
        //To write the output in file called logs.txt
        
        try{
            PrintStream fileOut = new PrintStream("./logs.txt");
            System.setOut(fileOut);
            
        }catch(FileNotFoundException ex)
        {
        }
       
        routerObj.connect();     
    }
} 
/*
3
6
C1 phone 
C2 phone2 
C3 Tv
C4 Tablet
C5 pc
C6 Server
*/