import java.util.Scanner;

public class App {
    public static void main(String[] args) throws Exception {
        Scanner scan = new Scanner(System.in);
        Matrix mt1 = new Matrix(2,2);
        mt1.inputMatrix(scan);

        // Matrix mt2 = new Matrix(1,2);
        // mt2.inputMatrix(scan);
        scan.close();

        mt1.printMatrix();
        //mt2.printMatrix();

        try{
            //mt1.multiMatrix(mt2).printMatrix();
            mt1.transMatrix().printMatrix();
            mt1.callDet().printNum();
        }
        catch(Exception ex){
            System.out.println(ex.getMessage());
        }
        // mt1.addMatrix(mt2).printMatrix();
        // mt1.difMatrix(mt2).printMatrix();
        
    }
}
