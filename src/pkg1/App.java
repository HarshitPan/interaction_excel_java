package pkg1;

import javax.swing.*;
import pkg1.MySchema;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;



public class App {


    public static List<MySchema> list=new ArrayList<>();

    public static void ArrayDisplayFunction(JFrame frame,String check) {
        // Set up the JFrame

        // Create a JTextArea to display the array content
        JTextArea textArea = new JTextArea();
        textArea.setEditable(false);
        int i=0;
        // Append array data to the JTextArea
        for (MySchema temp:list) {
            if(temp.d_C.toLowerCase().equalsIgnoreCase(check)){
                String value=temp.Sno+"\n"+temp.drugName+"\n"+temp.M_C+"\n"+temp.s_e+"\n"+temp.g_price+"\n"+temp.g_name+"\n"+temp.d_C+"\n"+temp.B_Price+"\n"+temp.B_Name+"\n"
                +"\n"+temp.Activity+"\n"+temp.rx;
                textArea.append("Data "+i+"-:\n"+value+ "\n\n\n\n\n\n\n");
                i++;
            }
        }

        // Set up the layout
        frame.add(new JScrollPane(textArea));

        // Display the JFrame
        frame.setVisible(true);

    }

    public static void getData(String searchText)
    {
        try{
            FileInputStream fis=new FileInputStream("./bin/drugs_data.xlsx");
            Workbook wb= WorkbookFactory.create(fis);
            Sheet s= wb.getSheet("drugs_side_effects");
            Row r;
            int i=0;
            while( (r = s.getRow(i)) !=null)
            {
                try{
                    MySchema obj=new MySchema();
                    obj.Sno=r.getCell(0).getNumericCellValue();
                    obj.drugName=r.getCell(1).getStringCellValue();
                    obj.M_C=r.getCell(2).getStringCellValue();                   
                    obj.s_e=r.getCell(3).getStringCellValue();
                    obj.g_price=r.getCell(4).getNumericCellValue();
                    obj.g_name=r.getCell(5).getStringCellValue();
                    obj.d_C=r.getCell(6).getStringCellValue();
                    obj.B_Price=r.getCell(7).getNumericCellValue();
                    obj.B_Name=r.getCell(8).getStringCellValue();
                    obj.Activity=r.getCell(9).getStringCellValue();
                    obj.rx=r.getCell(10).getStringCellValue();
                    list.add(obj);
                }
                catch(Exception e)
                {
                    // System.out.println("jj");
                }
                i++;
            }
        }
        catch(Exception e)
        {
            System.out.println("inside catch"+e);
        }
        for(MySchema temp:list)
        {
            System.out.println(temp.Sno);
        }
    }
    public static void main(String[] args) {
        // Create a frame (window)
        JFrame frame = new JFrame("Basic Swing Layout");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 200);

        // Create a panel
        JPanel panel = new JPanel();

        // Create a label
        JLabel searchLabel = new JLabel("Enter Salt Name:");

        // Create a search input field (text field)
        JTextField searchField = new JTextField(20);

        // Create a search button
        JButton searchButton = new JButton("Search");

        // Create a dropdown (combo box) with options
        String[] options = { "Option 1", "Option 2", "Option 3", "Option 4" };
        JComboBox<String> dropdown = new JComboBox<>(options);

        // Add components to the panel
        panel.add(searchLabel);
        panel.add(searchField);
        panel.add(searchButton);
        panel.add(dropdown);

        // Add the panel to the frame
        frame.add(panel);
        // Set frame visibility
        frame.setVisible(true);

        // Add an action listener to the search button
        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String searchText = searchField.getText();
                getData(searchText);
                ArrayDisplayFunction(frame,searchText);
                frame.remove(panel);
                // You can perform database operations here based on the search text
            }
        });
    }

}

