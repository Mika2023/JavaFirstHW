import java.util.Scanner;

public class Matrix{
    private int size_i;
    private int size_j;
    private ComplexNum matrix[][];
    
    public Matrix(int size_i,int size_j){
        this.size_i = size_i;
        this.size_j = size_j;
        this.matrix = new ComplexNum[size_i][size_j];
        for (int i = 0; i<this.size_i;++i)
            for (int j = 0; j<this.size_j;++j) this.matrix[i][j] = new ComplexNum();
    }
    public Matrix(){
        this(1,1);
    }
    public Matrix(int size){
        this(size,size);
    }

    public void inputMatrix(Scanner scan){
        
        for (int i = 0; i<this.size_i;++i)
            for(int j = 0; j<this.size_j;++j){
                this.matrix[i][j].inputNum(scan);
            }
        
    }
    public void copyMatrix(ComplexNum[][] src, int size_i, int size_j) throws Exception{
        if (src!=this.matrix && this.size_i == size_i && this.size_j == size_j){
            for (int i = 0; i<this.size_i;++i)
                for(int j = 0; j<this.size_j;++j) this.matrix[i][j] = src[i][j];
        }
        else throw new Exception();
    }
    public void printMatrix(){
        for (int i = 0; i<this.size_i;++i){
            for(int j = 0; j<this.size_j;++j) this.matrix[i][j].printNum();
            System.out.println();
        }
    }

    public Matrix addMatrix(Matrix second) throws Exception{
        if (second.size_i!=this.size_i || second.size_j!=this.size_j) throw new Exception("The size of second matrix must be equal to the first one");
        else{
            Matrix res = new Matrix(this.size_i,this.size_j);
            for (int i = 0; i<this.size_i;++i)
                for (int j = 0; j<this.size_j;++j) res.matrix[i][j].copy(this.matrix[i][j].addition(second.matrix[i][j]));
            return res;
        }
    }
    public Matrix difMatrix(Matrix second) throws Exception{
        if (second.size_i!=this.size_i || second.size_j!=this.size_j) throw new Exception("The size of second matrix must be equal to the first one");
        else{
            Matrix res = new Matrix(this.size_i,this.size_j);
            for (int i = 0; i<this.size_i;++i)
                for (int j = 0; j<this.size_j;++j) res.matrix[i][j].copy(this.matrix[i][j].difference(second.matrix[i][j]));
            return res;
        }
    }
    public Matrix multiMatrix(Matrix second) throws Exception{
        if (second.size_i!=this.size_j || second.size_j!=this.size_i) throw new Exception("Miltiplication is not allowed. Check both sizes of matrix");
        else{
            Matrix res = new Matrix(this.size_i,this.size_j);
            for (int i = 0; i<this.size_i;++i)
                for (int j = 0; j<this.size_j;++j){
                    ComplexNum sum = new ComplexNum();
                    for (int k = 0; k<this.size_j;++k) sum.copy(sum.addition(this.matrix[i][k].multiplication(second.matrix[k][j])));
                    res.matrix[i][j].copy(sum);
                }
                    
            return res;
        }
    }

    public Matrix transMatrix(){
        Matrix res = new Matrix(this.size_j,this.size_i);
        for (int i = 0; i<this.size_i;++i)
                for (int j = 0; j<this.size_j;++j)
                    res.matrix[j][i].copy(this.matrix[i][j]);
        return res;
    }

    public Matrix createMinor(int size, int pos_j, Matrix src){
        Matrix res = new Matrix(size-1);
        for (int i = 1; i<size;++i){
            int c = 0;
            for(int j = 0; j<size;++j){
                if(j!=pos_j) res.matrix[i-1][c++] = src.matrix[i][j]; 
            }
        }
        return res;
    }

    public ComplexNum detMatrix(int size, Matrix minor){
        if(size==1) return minor.matrix[0][0];
        if (size==2) return minor.matrix[0][0].multiplication(minor.matrix[1][1]).difference(minor.matrix[0][1].multiplication(minor.matrix[1][0]));
        ComplexNum det = new ComplexNum();
        for(int i = 0; i<size;++i){
            Matrix subMinor = createMinor(size, i, minor);
            ComplexNum pow = new ComplexNum((int)Math.pow(-1,i+2),0);
            det.addition(minor.matrix[0][i].multiplication(detMatrix(size-1, subMinor)).multiplication(pow));
        }
        return det;
    }
    public ComplexNum callDet() throws Exception{
        if(size_i!=size_j) throw new Exception("Determinant does not exist");
        else return detMatrix(size_i, this);
    }
}