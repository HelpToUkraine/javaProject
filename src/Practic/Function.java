package Practic;
 
public class Function {
    private int a;
    private int b;

    public Function (int a, int b) {
        this.a = a;
        this.b = b;
    }
    public int getA (int a){
        return a;
    }
    public void setA (int a){
        this.a = a;
    }

    int sum(int a, int b){
        return a + b;
    }   
}
