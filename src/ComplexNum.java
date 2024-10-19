import java.util.Scanner;

public class ComplexNum{
    private int real, imaginary;

    public ComplexNum(){
        this(0,0);
    }
    public ComplexNum(int real, int imaginary){
        this.real = real;
        this.imaginary = imaginary;
    }

    public void copy(ComplexNum src){
        this.real = src.real;
        this.imaginary = src.imaginary;
    }
    public void inputNum(Scanner scan){
        if (scan.hasNextInt()){
            this.real = scan.nextInt();
            this.imaginary = scan.nextInt();
        }
    }
    public void printNum(){
        if (this.imaginary>=0)
            System.out.printf("%d+%d*i\t",this.real,this.imaginary);
        else System.out.printf("%d%d*i\t",this.real,this.imaginary);
    }

    //math_operations
    public ComplexNum addition(ComplexNum second){
        return new ComplexNum(this.real+second.real,this.imaginary+second.imaginary);
    }
    public ComplexNum difference(ComplexNum second){
        return new ComplexNum(this.real-second.real,this.imaginary-second.imaginary);
    }
    public ComplexNum multiplication(ComplexNum second){
        return new ComplexNum(this.real*second.real-this.imaginary*second.imaginary, this.real*second.imaginary+this.imaginary*second.real);
    }
}