package Practic;

public class Credit_Calc {

    public void calculator(double credit_sum, int procent, int time) {
        double credit_pay;
        
        for (int i = 1; i <= time; i++) {
            credit_pay = credit_sum / 100 * procent;
            System.out.printf("\n%d) Месяц\nПлатеж %d%% от %.1f = %.1f", i, procent, credit_sum, credit_pay);

            credit_sum = credit_sum - credit_pay;
            System.out.printf("\nОстаток долга: %.1f\n", credit_sum);
        }
        
    }
}
