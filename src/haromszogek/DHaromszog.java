package haromszogek;

public class DHaromszog {

    private double aOldal;
    private double bOldal;
    private double cOldal;

    private int sorSzam;
    
    public DHaromszog(String sor, int sorSzama) throws Exception{
        String[] oldalak = sor.replace(',', '.').split(" ");

        this.sorSzam = sorSzama;
        
        this.setaOldal(Double.parseDouble(oldalak[0]));
        this.setbOldal(Double.parseDouble(oldalak[1]));
        this.setcOldal(Double.parseDouble(oldalak[2]));
        
        if (!EllNovekvoSorrend() ) { 
           throw new Exception(
                   String.format("%d. sor: Az adatok nincsenek növekvő sorrendben", 
                           sorSzama)); 
        }
        if (!EllMegszerkesztheto()){
            throw new Exception(
                    String.format("%d. sor: A háromszöget nem lehet megszerkeszteni!", 
                           sorSzama)); 
        }
        if (!EllDerekszogu()){
            throw new Exception(
                    String.format("%d. sor: A háromszög nem derékszögű", 
                           sorSzama)); 
        }
        
    }
    
    private boolean EllDerekszogu(){
        return Math.pow(cOldal,2) == Math.pow(aOldal,2) + Math.pow(bOldal, 2);
    }

    private boolean EllMegszerkesztheto(){
        return this.aOldal + this.bOldal > this.cOldal;
    }
    
    private boolean EllNovekvoSorrend(){
        return this.aOldal <= this.bOldal && this.bOldal <= this.cOldal;
    }
    
    public double kerulet(){
        return this.aOldal + this.bOldal + this.cOldal;
    }

    public double terulet(){
        return this.aOldal * this.bOldal / 2;
    }
    
    public int SorSzama(){
        return this.sorSzam;
    }
    
    public double getaOldal() {
        return this.aOldal;
    }

    public void setaOldal(double aOldal) throws Exception{
        if (aOldal > 0){
            this.aOldal = aOldal;
        }else{
            throw new Exception(String.format("%d. sor: Az 'a' oldal nem lehet nulla vagy negatív", this.sorSzam));
        }
    }

    public double getbOldal() {
        return this.bOldal;
    }

    public void setbOldal(double bOldal) throws Exception{
        if (bOldal > 0){
            this.bOldal = bOldal;
        }else{
            throw new Exception(String.format("%d. sor: Az 'b' oldal nem lehet nulla vagy negatív", this.sorSzam));
        }
    }

    public double getcOldal() {
        return this.cOldal;
    }

    public void setcOldal(double cOldal) throws Exception{
        if (cOldal > 0){
            this.cOldal = cOldal;
        }else{
            throw new Exception(String.format("%d. sor: Az 'c' oldal nem lehet nulla vagy negatív", this.sorSzam));
        }
    }

    @Override
    public String toString() {
        return String.format("%d. sor: a=%.2f b=%.2f c=%.2f",sorSzam, aOldal,bOldal,cOldal);
    }
    
    
    
    
    
    
}
