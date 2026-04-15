import java.util.ArrayList;
import java.util.Collections;

public class Sudoku {
    // notes
    //  
    private int[][] sudoku;
    
    public Sudoku()
    {
        sudoku = new int[9][9];
        pathfinder(0, 0);
    }
    
    public boolean pathfinder(int r, int c) 
    {
        if (c == 9) 
        {
            return true;
        }
        
        if (r == 9) 
        {
            return pathfinder(0, c + 1);
        }
        
        if (sudoku[r][c] != 0) 
        {
            return pathfinder(r + 1, c);
        }
        
        ArrayList<Integer> nums = new ArrayList<>();
        for (int x = 1; x <= 9; x++)
            nums.add(x);
        Collections.shuffle(nums);
        
        for (int k = 0; k < 9; k++) 
        {
            int oneNum = nums.get(k);
        
            if (valid(r, c, oneNum)) 
            {
                sudoku[r][c] = oneNum;
                
                if (pathfinder(r + 1, c)) 
                {
                    return true;
                }
                
                sudoku[r][c] = 0;
            }
        }
        
        return false;
    }
    
    private boolean valid(int r, int c, int num) 
    {  
        for (int x = 0; x < 9; x++)
            if (sudoku[r][x] == num)
                return false;
    
        for (int x = 0; x < 9; x++)
            if (sudoku[x][c] == num)
                return false;
                
        int rowBox = (r / 3) * 3, colBox = (c / 3) * 3;
        
        for (int x = 0; x < 3; x++)
            for (int y = 0; y < 3; y++)
                if (sudoku[rowBox + x][colBox + y] == num)
                    return false;
                    
        return true;
    }
    
    public String toString()
    {
        String product = "";
        
        for (int y = 0; y < 9; y++)
        {
            if (y % 3 == 0)
                product += "+-------+-------+-------+\n";
            for (int x = 0; x < 9; x++)
            {
                if (x % 3 == 0)
                    product += "| ";
                product += sudoku[y][x] + " ";
            }
            
            product += "|\n";
        }
        
        product += "+-------+-------+-------+";
        
        return product;
    }
}