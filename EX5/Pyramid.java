import java.util.Scanner;
class Pyramid
{
    public static void printPyramid(int n)
    {
        for(int i = 0;i <= n;i++)
        {
            for(int j=1;j<= n -i;j++)
            {
                System.out.print(" ");
            }
            for(int k=1;k<=2 * i - 1;k++)
            {
                System.out.print("*");
            }
            System.out.println();
        }
    }
    public static void main(String[] args)
    {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter number of rows : ");
        int rows = sc.nextInt();
        printPyramid(rows);

    }
}