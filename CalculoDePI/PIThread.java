
import java.math.BigDecimal;
import java.math.MathContext;

public class PIThread extends Thread{
    private final long start;
    private final long end;
    private BigDecimal sum;
    
    public PIThread(long start,long end){
        this.start = start;
        this.end = end;
        this.sum = BigDecimal.ZERO;
    }
    
    @Override
    public void run(){
        BigDecimal variable = BigDecimal.ZERO;
        BigDecimal numerador;
        BigDecimal denominador;
        
        for (long i=start;i<end;i++){
            numerador = new BigDecimal(-1).pow((int) i);
            denominador = new BigDecimal(2).multiply(new BigDecimal(i));
            denominador = denominador.add(new BigDecimal(1));
            variable = numerador.divide(denominador,MathContext.DECIMAL128);
            sum = sum.add(variable);
        }
    }
    
    public BigDecimal getSum(){
        return sum;
    }
}
