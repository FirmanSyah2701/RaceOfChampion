import java.util.ArrayList;
class Motorcycle {
    public String name;
    public int speed;
    public int fuel;
    public int maxFuel;

    public Motorcycle(String name, int speed, int maxFuel) {
        this.name    = name;
        this.speed   = speed;
        //this.fuel    = fuel;
        this.maxFuel = maxFuel;
    }

    public void setFuel(int fuel){
        if(fuel<=0){
            this.fuel = 0;
        }else if(fuel>maxFuel){
            this.fuel = this.maxFuel;
        }else{
            this.fuel = fuel;
        }
    }

    @Override
    public String toString(){
        return "Name : " + name + "\nspeed : " + speed + "\nfuel : " + fuel + "\nMax Fuel : " + maxFuel;
    }    

}

public class Circuit {
    public ArrayList<Motorcycle> motor = new ArrayList<Motorcycle>();
    public String circuitName;
    public int circuitLength;
    public int raceCount;

    public Circuit(String circuitName, int circuitLength,int raceCount) {
        this.circuitName   = circuitName; 
        this.circuitLength = circuitLength;
        this.raceCount     = raceCount; 
    }
    
    public void refuel(int amount){
        for (Motorcycle mc : motor) {
            mc.fuel = mc.fuel + amount;
        }
    }

    public void addMotorcylce(Motorcycle motorcycle){
        motor.add(motorcycle);
    }

    public void doRace(){
        //process sorting by speed
        for (int i=0; i < motor.size(); i++) {
            int dummy = i;      
            for (int j = i; j < motor.size(); j++) {
                if (motor.get(dummy).speed <= motor.get(j).speed) {
                    dummy = j;
                }
            }
            Motorcycle temp = motor.get(dummy);
            motor.set(dummy, motor.get(i));
            motor.set(i, temp);
        }
        int limitWin = 3;
        int countMotor = motor.size();
            for (int k=0; k<motor.size(); k++) {
                motor.get(k).fuel = motor.get(k).fuel - (circuitLength * raceCount);
                if(motor.get(k).fuel<0){
                    motor.remove(motor.get(k));
                    k--;
                }else{
                    System.out.println("<<Race " + (k+1) + " | " + circuitName + ">>");
                    System.out.println("[ Winner ]");
                    System.out.println(motor.get(k).toString());
                }
            }
             if(countMotor>=limitWin){
                 System.out.println("<<Race " + (limitWin) + " | " + circuitName + ">>");
                 System.out.println("[ Winner ]");
                 System.out.println("no one complete this race");
             }
    }

    public static void main(String[] args) {
        Motorcycle m1 = new Motorcycle("Andrea Dovizioso", 30, 1000);
        m1.setFuel(100);
        Motorcycle m2 = new Motorcycle("Marc Marquez", 25, 1000);
        m2.setFuel(120);
        Circuit c1     = new Circuit("Losail international Circuit", 120, 1);
        c1.addMotorcylce(m1);
        c1.addMotorcylce(m2);
        c1.doRace();
        System.out.println("");
        Motorcycle m3 = new Motorcycle("Maverick Vinales", 40, 150);
        m3.setFuel(70);
        Motorcycle m4 = new Motorcycle("Joan Mir", 25, 225);
        m4.setFuel(45);
        Motorcycle m5 = new Motorcycle("Johann Zarco", 50, 200);
        m5.setFuel(10);
        Motorcycle m6 = new Motorcycle("Rossi", 30, 10);
        m6.setFuel(-1);
        Circuit c2    = new Circuit("TT Circuit Assen", 10, 2);
        //c2.refuel(2);
        c2.addMotorcylce(m3);
        c2.addMotorcylce(m4);
        c2.addMotorcylce(m5);
        c2.addMotorcylce(m6);
        c2.doRace();
    }
}