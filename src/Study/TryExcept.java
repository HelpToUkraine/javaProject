package Study;

public class TryExcept {
    public static void main(String[] args) {
        int x = 0;
        try {
            x = 100 / 0;
        }
        catch (Exception e){
            System.out.println("error " + e);
        }
        finally {
            System.out.println("Program is working");
        }
    }
}
