
import java.math.BigDecimal;

public class PiCalculator {
    private static final short CPU_CORES = 8;
    private static final long FINAL_LIMIT = 10000;
    
    public static void main(String[] args) throws InterruptedException{
        PIThread threads[] = new PIThread[CPU_CORES];
        final long startTime = System.currentTimeMillis();
        BigDecimal sum = BigDecimal.ZERO;
        BigDecimal pi = BigDecimal.ZERO;
        
        for(int i=0; i<CPU_CORES;i++){
            long start = (long) (i*FINAL_LIMIT/CPU_CORES);
            long end = (long) ((i+1)*FINAL_LIMIT/CPU_CORES);
            
            threads[i] = new PIThread(start,end);
            threads[i].start();
        }
        
        for(int i=0; i<CPU_CORES; i++){
            threads[i].join();
            sum.add(threads[i].getSum());
        }
        
        pi = sum.multiply(new BigDecimal(4));
        
        final long endTime = System.currentTimeMillis();
        System.out.println(pi);
        System.out.printf("Tiempo: %s\n",(endTime - startTime) / 1000.0);
    }
}