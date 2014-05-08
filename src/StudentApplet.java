
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.io.*;

/**
 * Class StudentApplet - write a description of the class here
 * 
 * @author (your name) 
 * @version (a version number)
 */
public class StudentApplet extends JApplet implements ActionListener
{
    private JRadioButton addBut, deleteBut, update, sortGPA, sortName, quit;
    private String s;
    private Student[] grbook;
    private int maxstudents;
    private Font ubuntu, ubuntuMono;
    private Color wetAsphalt, alizarin, silver;
    public void init()
    {
       JPanel buttonPanel = new JPanel();
       buttonPanel.setLayout(new GridLayout(2,3));
       
       wetAsphalt = new Color(52, 73, 94);
       alizarin = new Color(231, 76, 60);
       silver = new Color(189, 195, 199);
       
       makeFonts();
       setFont(ubuntu);
       
       
       ButtonGroup g = new ButtonGroup();
       
       addBut = new JRadioButton("Add Student");
       addBut.setBackground(wetAsphalt);
       addBut.setForeground(silver);
       buttonPanel.add(addBut);
       g.add(addBut);
       
       deleteBut = new JRadioButton("Delete Student");
       deleteBut.setBackground(wetAsphalt);
       deleteBut.setForeground(silver);
       buttonPanel.add(deleteBut);
       g.add(deleteBut);
       
       update = new JRadioButton("Update Student's Grades");
       update.setBackground(wetAsphalt);
       update.setForeground(silver);
       buttonPanel.add(update);
       g.add(update);
       
       sortGPA = new JRadioButton("Sort Students by GPA");
       sortGPA.setBackground(wetAsphalt);
       sortGPA.setForeground(silver);
       buttonPanel.add(sortGPA);
       g.add(sortGPA);
       
       sortName = new JRadioButton("Sort Students by Name");
       sortName.setBackground(wetAsphalt);
       sortName.setForeground(silver);
       buttonPanel.add(sortName);
       g.add(sortName);
       
       quit = new JRadioButton("Quit");
       quit.setBackground(wetAsphalt);
       quit.setForeground(silver);
       buttonPanel.add(quit);
       g.add(quit);
       
       JButton perform = new JButton("Run");
       perform.setBackground(alizarin);
       perform.addActionListener(this);
       
       Container c = getContentPane();
       c.add(buttonPanel, BorderLayout.NORTH);
       c.add(perform, BorderLayout.SOUTH);
       
       start();
       repaint();
    }
    
    public void actionPerformed(ActionEvent e)
    {
        if (deleteBut.isSelected())
            delete();
        else if (sortGPA.isSelected())
            sortGpa();
        else if (sortName.isSelected())
            sortName();
        else if (addBut.isSelected())
            add();
            
        repaint();
    }

    public void start()
    {
        maxstudents = 5;
        grbook = new Student[31];
        for(int i = 0; i<31; i++)
            grbook[i]=null;
        grbook[0] = new Student("Witman, Matthew", 2016, new int[]{95,87,92,93,95});
        grbook[3] = new Student("Bar, Foo", 2025, new int[]{65,75,85,95,100});
        grbook[1] = new Student("Name, Fake", 2016, new int[]{89,48,68,74,64});
        grbook[2] = new Student("Sherwood, Tommy", 2016, new int[]{85,86,46,87,46});
        grbook[4] = new Student("Smith, John", 2019, new int[]{15,48,64,53,18});
        repaint();
    }

    public void paint(Graphics g)
    {
        super.paint(g);
        g.setFont(ubuntuMono);
        for (int i = 0; i < maxstudents; i++)
        {
            g.setColor(wetAsphalt);
            if(grbook[i].getgpa() <= 65)
                g.setColor(alizarin);
            g.drawString(grbook[i].toString(), (getWidth()/8), (getHeight()/4+i*20));
        }
    }
    
    public void delete()
    {
        String name;
        
        
        
        int i = 0;
        
        
        Object[] choices = new Object[maxstudents];
        for(int j = 0; j<maxstudents; j++)
            choices[j] = grbook[j].getname();
        name = (String) JOptionPane.showInputDialog(null, "Which Student Should be deleted?", "Delete Student", JOptionPane.QUESTION_MESSAGE, new ImageIcon("graduate6.png") , choices, "" );
        while(i<maxstudents && !name.equalsIgnoreCase(grbook[i].getname()))
            i++;
       
            if (i==maxstudents)
        {
            JOptionPane.showMessageDialog(null,
                "Unable to find student " + name, "Delete", JOptionPane.ERROR_MESSAGE, new ImageIcon("nope.png")); 
            
        }
        else
        {
            grbook[i]=null;
            for(int j = i; j<maxstudents-1; j++)
                grbook[j]=grbook[j+1];
            JOptionPane.showMessageDialog(null,
                "Student : " + name + " has been deleted ", "Delete", JOptionPane.INFORMATION_MESSAGE, new ImageIcon("recycling.png")); 
            maxstudents--; 
        }
        
    }
    
    public void sortGpa()
    {
        Student temp = new Student();
        for(int i = 0; i < maxstudents-1; i++)
            for(int j = i+1; j <maxstudents; j++)
                if (grbook[i].getgpa() < grbook[j].getgpa())
                    {
                        temp = grbook[i];
                        grbook[i] = grbook[j];
                        grbook[j] = temp;
                    }
    }
    
    public void sortName()
    {
        Student temp = new Student();
        for(int i = 0;i<maxstudents-1;i++)
            for(int j = i+1; j <maxstudents; j++)
                if(grbook[i].getname().compareToIgnoreCase(grbook[j].getname())>0)
                {
                    temp = grbook[i];
                    grbook[i] = grbook[j];
                    grbook[j] = temp;
                }
        
    }
    
    public void add()
    {
        JTextField g0, g1, g2, g3, g4, nameField, yearField;

        JPanel addPanel = new JPanel(new GridLayout(3,2));
        JPanel gradePanel = new JPanel(new GridLayout(1,5));

        addPanel.add(new JLabel("Students Name (Last, First)"));
        addPanel.add(nameField = new JTextField());
        addPanel.add(new JLabel("Year Of Graduation"));
        addPanel.add(yearField = new JTextField());
        addPanel.add(new JLabel("Students Grades"));

        gradePanel.add(g0 = new JTextField());
        gradePanel.add(g1 = new JTextField());
        gradePanel.add(g2 = new JTextField());
        gradePanel.add(g3 = new JTextField());
        gradePanel.add(g4 = new JTextField());

        addPanel.add(gradePanel);


        JOptionPane.showMessageDialog(null, addPanel, "Choose Grades", JOptionPane.QUESTION_MESSAGE);

        String name;
        int temp[] = new int[5], year, tempg;
        
        name = JOptionPane.showInputDialog("Add a student", "Last Name, First Name");
        year = Integer.parseInt(JOptionPane.showInputDialog("Year of Graduation", "2017"));
        
        for(int i = 0; i<5; i++)
        {
                do{
                    tempg = Integer.parseInt(JOptionPane.showInputDialog("Grade #"+i));
                    if (tempg > 100 || tempg < 0)
                        JOptionPane.showMessageDialog(null, "Enter a grade between 0 and 100", "", JOptionPane.ERROR_MESSAGE);
                } while (tempg >100 || tempg < 0); 
        }

        grbook[maxstudents] = new Student(name, year, temp);
        maxstudents++;
        
    }
    
    public void makeFonts()
    {
        try{
            ubuntu = Font.createFont(Font.TRUETYPE_FONT, new FileInputStream("Ubuntu-L.ttf"));
        }
        catch(IOException|FontFormatException e){}
        ubuntu = ubuntu.deriveFont(12f);
        try{
            ubuntuMono = Font.createFont(Font.TRUETYPE_FONT, new FileInputStream("UbuntuMono-R.ttf"));
        }
        catch(IOException|FontFormatException e){}
        ubuntuMono = ubuntuMono.deriveFont(12f);
    }
    
 
    
}
