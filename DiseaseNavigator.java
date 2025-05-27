import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;

public class DiseaseNavigator extends JFrame implements ActionListener 
{
    
    private CardLayout cardLayout;
    private JPanel mainPanel;
	
    private JPanel welcomePanel;
    private JPanel infoPanel;
	
    private JTextField searchTF;
    private JTextArea symptomsArea, preventionArea, treatmentArea, historyArea;
    private JButton searchBtn, clearBtn, saveBtn, backBtn, historyBtn;
    private JScrollPane historyScroll;

    public DiseaseNavigator() 
	{
        super("Disease Navigator");
        this.setSize(1000, 600);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
           
        cardLayout = new CardLayout();
        mainPanel = new JPanel(cardLayout);
		
        createWelcomePanel();
        createInfoPanel();
      
        mainPanel.add(welcomePanel, "Welcome");
        mainPanel.add(infoPanel, "Info");
      
        cardLayout.show(mainPanel, "Welcome");
        
        this.add(mainPanel); 
        this.setLocationRelativeTo(null);
    }
    
    private void createWelcomePanel() 
	{
        welcomePanel = new JPanel();
        welcomePanel.setLayout(null);
        welcomePanel.setBackground(new Color(173, 216, 230));
        
        JLabel welcomeLabel = new JLabel("<html><center>Welcome to Disease Navigator<br><br>Find information about symptoms, prevention, and treatments</center></html>");
        welcomeLabel.setBounds(200, 150, 600, 100);
        welcomeLabel.setFont(new Font("Arial", Font.BOLD, 24));
        welcomePanel.add(welcomeLabel);
        
        JButton startButton = new JButton("Get Started");
        startButton.setBounds(400, 300, 200, 50);
        startButton.setFont(new Font("Arial", Font.PLAIN, 18));
        startButton.setBackground(new Color(0, 102, 204));
        startButton.setForeground(Color.WHITE);
        startButton.addActionListener(ae -> cardLayout.show(mainPanel, "Info"));
        welcomePanel.add(startButton);
    }
    
    private void createInfoPanel() 
	{
        infoPanel = new JPanel();
        infoPanel.setLayout(null);
        infoPanel.setBackground(new Color(240, 248, 255));
        
        // Title --------
		
        JLabel titleLabel = new JLabel("Disease Information");
        titleLabel.setBounds(350, 10, 300, 30);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 20));
        titleLabel.setForeground(new Color(0, 102, 204));
        infoPanel.add(titleLabel);
        
        // Search --------
		
        JLabel searchLabel = new JLabel("Search Disease:");
        searchLabel.setBounds(50, 60, 100, 30);
        infoPanel.add(searchLabel);
        
        searchTF = new JTextField();
        searchTF.setBounds(150, 60, 200, 30);
        infoPanel.add(searchTF);
        
        searchBtn = new JButton("Search");
        searchBtn.setBounds(360, 60, 100, 30);
        searchBtn.setBackground(new Color(0, 153, 76));
        searchBtn.setForeground(Color.WHITE);
        searchBtn.addActionListener(this);
        infoPanel.add(searchBtn);
        
        // Symptoms area --------
		
        JLabel symptomsLabel = new JLabel("Symptoms:");
        symptomsLabel.setBounds(50, 100, 100, 30);
        infoPanel.add(symptomsLabel);
        
        symptomsArea = new JTextArea();
        symptomsArea.setEditable(false);
        symptomsArea.setLineWrap(true);
        symptomsArea.setWrapStyleWord(true);
        symptomsArea.setBackground(new Color(255, 255, 240));
        JScrollPane symptomsScroll = new JScrollPane(symptomsArea);
        symptomsScroll.setBounds(50, 130, 400, 100);
        infoPanel.add(symptomsScroll);
        
        // Prevention area --------
		
        JLabel preventionLabel = new JLabel("Prevention:");
        preventionLabel.setBounds(50, 240, 100, 30);
        infoPanel.add(preventionLabel);
        
        preventionArea = new JTextArea();
        preventionArea.setEditable(false);
        preventionArea.setLineWrap(true);
        preventionArea.setWrapStyleWord(true);
        preventionArea.setBackground(new Color(255, 255, 240));
        JScrollPane preventionScroll = new JScrollPane(preventionArea);
        preventionScroll.setBounds(50, 270, 400, 100);
        infoPanel.add(preventionScroll);
        
        // Treatment area ---------
		
        JLabel treatmentLabel = new JLabel("Treatment:");
        treatmentLabel.setBounds(50, 380, 100, 30);
        infoPanel.add(treatmentLabel);
        
        treatmentArea = new JTextArea();
        treatmentArea.setEditable(false);
        treatmentArea.setLineWrap(true);
        treatmentArea.setWrapStyleWord(true);
        treatmentArea.setBackground(new Color(255, 255, 240));
        JScrollPane treatmentScroll = new JScrollPane(treatmentArea);
        treatmentScroll.setBounds(50, 410, 400, 100);
        infoPanel.add(treatmentScroll);
        
        // Buttons ---------
		
        clearBtn = new JButton("Clear");
        clearBtn.setBounds(500, 130, 100, 30);
        clearBtn.setBackground(new Color(204, 0, 0));
        clearBtn.setForeground(Color.WHITE);
        clearBtn.addActionListener(this);
        infoPanel.add(clearBtn);
        
        saveBtn = new JButton("Save Info");
        saveBtn.setBounds(500, 170, 100, 30);
        saveBtn.setBackground(new Color(0, 102, 204));
        saveBtn.setForeground(Color.WHITE);
        saveBtn.addActionListener(this);
        infoPanel.add(saveBtn);
        
        backBtn = new JButton("Back to Welcome");
        backBtn.setBounds(500, 210, 150, 30);
        backBtn.setBackground(new Color(102, 102, 102));
        backBtn.setForeground(Color.WHITE);
        backBtn.addActionListener(ae -> cardLayout.show(mainPanel, "Welcome"));
        infoPanel.add(backBtn);
        
        historyBtn = new JButton("Load History");
        historyBtn.setBounds(500, 250, 150, 30);
        historyBtn.setBackground(new Color(75, 0, 130));
        historyBtn.setForeground(Color.WHITE);
        historyBtn.addActionListener(ae -> loadDiseaseHistory());
        infoPanel.add(historyBtn);
        
        // History area ---------
		
        historyArea = new JTextArea();
        historyArea.setEditable(false);
        historyScroll = new JScrollPane(historyArea);
        historyScroll.setBounds(500, 300, 450, 250);
        infoPanel.add(historyScroll);
    }
    
    public void actionPerformed(ActionEvent ae) 
	{
        if (ae.getSource() == searchBtn) 
		{
            searchDisease();
        } 
		else if (ae.getSource() == clearBtn) 
		{
            clearFields();
        } 
		else if (ae.getSource() == saveBtn) 
		{
            saveDiseaseInfo();
        }
    }
    
    private void searchDisease() 
	{
        String diseaseName = searchTF.getText().trim().toLowerCase();
        
        if (diseaseName.isEmpty()) 
		{
            JOptionPane.showMessageDialog(this, "Please enter a disease name");
            return;
        }
        
        DiseaseInfo disease = new DiseaseInfo(diseaseName);
        
        if (diseaseName.equals("influenza")) 
		{
            disease.setSymptoms("Fever, cough, sore throat, runny nose, muscle aches");
            disease.setPrevention("Annual flu vaccine, hand washing, avoid sick people");
            disease.setTreatment("Rest, fluids, antiviral medications if prescribed");
        } 
		else if (diseaseName.equals("diabetes")) 
		{
            disease.setSymptoms("Increased thirst, frequent urination, hunger, fatigue");
            disease.setPrevention("Healthy diet, regular exercise, maintain healthy weight");
            disease.setTreatment("Insulin therapy, oral medications, blood sugar monitoring");
        } 
		else 
		{
            disease.setSymptoms("Information not available");
            disease.setPrevention("Information not available");
            disease.setTreatment("Information not available");
        }
        
        displayDiseaseInfo(disease);
    }
    
    private void displayDiseaseInfo(DiseaseInfo disease) 
	{
        symptomsArea.setText(disease.getSymptoms());
        preventionArea.setText(disease.getPrevention());
        treatmentArea.setText(disease.getTreatment());
    }
    
    private void clearFields() 
	{
        searchTF.setText("");
        symptomsArea.setText("");
        preventionArea.setText("");
        treatmentArea.setText("");
    }
    
    private void saveDiseaseInfo() 
	{
        String diseaseName = searchTF.getText().trim();
        
        if (diseaseName.isEmpty() || symptomsArea.getText().isEmpty()) 
		{
            JOptionPane.showMessageDialog(this, "No disease information to save");
            return;
        }
        
        try (FileWriter writer = new FileWriter("DiseaseData.txt", true)) 
		{
            writer.write("Disease, " + diseaseName + "\n");
            writer.write("Symptoms, " + symptomsArea.getText() + "\n");
            writer.write("Prevention, " + preventionArea.getText() + "\n");
            writer.write("Treatment, " + treatmentArea.getText() + "\n");
            writer.write("------------------------------------\n");
            JOptionPane.showMessageDialog(this, "Disease information saved successfully");
        } 
		catch (IOException ex) 
		{
            JOptionPane.showMessageDialog(this, "Error saving disease information: " + ex.getMessage());
        }
    }
    
    private void loadDiseaseHistory() 
	{
        try (BufferedReader reader = new BufferedReader(new FileReader("DiseaseData.txt"))) 
		{
            StringBuilder content = new StringBuilder();
            String line;
            int recordCount = 1;
            
            while ((line = reader.readLine()) != null) 
			{
                if (line.startsWith("Disease,")) 
				{
                    content.append("\n=== Record ").append(recordCount++).append(" ===\n");
                }
                else if (line.startsWith("----")) 
				{
                    continue;
                }
                
                String[] parts = line.split(", ");
                if (parts.length >= 2) {
                    content.append(parts[0]).append(": ").append(parts[1]).append("\n");
                }
            }
            
            historyArea.setText(content.length() > 0 ? content.toString() : "No disease history found");
        } 
		catch (IOException ex) 
		{
            historyArea.setText("Error reading history: " + ex.getMessage());
        }
    }
}