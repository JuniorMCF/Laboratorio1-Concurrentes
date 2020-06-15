package calculatepi;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;

/**
 *
 * @author ingrid.ic
 */
public class CalculatePi {
    
    public static BigDecimal Pi(int inicio,int fin){
        BigDecimal pi = BigDecimal.ZERO;
        BigDecimal sumPi = BigDecimal.ZERO;
        BigDecimal variable = BigDecimal.ZERO;
        BigDecimal numerador;
        BigDecimal denominador;
        int i;
        
        for(i=inicio; i<fin;i++){
            numerador = new BigDecimal(-1).pow(i);
            denominador = new BigDecimal(2).multiply(new BigDecimal(i));
            denominador = denominador.add(new BigDecimal(1));
            variable = numerador.divide(denominador,MathContext.DECIMAL128);
            sumPi = sumPi.add(variable);
        }
        pi = sumPi.multiply(new BigDecimal(4));
        return pi;
    }
    
    public static void main(String[] args) {
        BigDecimal pi;
        int fin = 1_000_000;
        int inicio = 0;

        long inicioTime = System.currentTimeMillis();

        pi = Pi(inicio,fin);

        long finTime = System.currentTimeMillis();
        
        double tiempo = (double) (finTime - inicioTime);
        System.out.println(pi);
        System.out.printf("Tiempo: %s\n",tiempo / 1000.0);
    }
}
