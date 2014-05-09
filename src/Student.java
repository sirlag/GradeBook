
/**
 * Write a description of class Student here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.util.*;
public class Student
{
    private String name;
    private int year;
    private int grades[];
    private double gpa;

    public Student()
    { 
        name = "";
        year = 0;
        grades = new int[]{0,0,0,0,0};
        calcGpa();
    }
    
    public Student(String name, int year, int grades[])
    {
        this.name = name;
        this.year = year;
        this.grades = grades;
        calcGpa();
    }
    
    public void setname(String name)
    {
        this.name = name;
    }
    
    public void setyear(int year)
    {
        this.year = year;
    }
    
    public void setgrades(int[] grades)
    {
        System.out.print("Which grade would you like to change : ");
        Scanner s = new Scanner(System.in);
        int i = s.nextInt();
        this.grades[i-1] = grades[i-1];
        calcGpa();
    }
    
    public String getname()
    {
        return name;    
    }
    
    public double getgpa()
    {
        return gpa;
    }
    
    public int getgrade(int index)
    {
        if (index > 4 || index < 0)
            return 0;
        else
            return grades[index];
    }
    
    public int getyear()
    {
        return year;
    }
    
    private void calcGpa()
    {
        gpa = 0;
        for(int x:grades)
            gpa+=x;
        gpa /= 5;
    }
    
    public String toString()
    {
        return String.format("%-20s %4d %3d %3d %3d %3d %3d %3.2f", name, year, grades[0], grades[1], grades[2], grades[3], grades[4], gpa);
    }
}
