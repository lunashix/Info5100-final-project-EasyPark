import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.*;
import java.time.LocalTime;
import java.util.ArrayList;

import static java.awt.Image.SCALE_SMOOTH;

public class GUI implements ActionListener, ItemListener {
    private int regularSpotNumber;
    private int oversizedSpotNumber;
    private JComboBox regularSpotNumBox;
    private JComboBox oversizedSpotNumBox;

    private JFrame mainFrame;
    private JLabel headerLabel;
    private JPanel regularPanel;
    private ArrayList<JButton> regularSpotButtons;
    private JPanel oversizedPanel;
    private ArrayList<JButton> oversizedSpotButtons;
    private JPopupMenu spotSubMenu;
    private JTabbedPane tabbedPane;
    private JLabel statusLabel;
    private JButton labelButton;
    private JPanel controlPanel;
    private JPanel headerpanel;
    private JTextArea msglabel;
    private ParkingLot parkingLot;

    public GUI() {
        this.parkingLot = new ParkingLot();
        this.spotSubMenu = spotSubMenu();
        PrepareGUI();
    }

    private void PrepareGUI() {

        mainFrame = new JFrame("EasyPark Management System"); // Init new JFrame
        mainFrame.setSize(1000, 800);
        mainFrame.setLayout(new GridBagLayout()); // JFrame is 3 rows 1 column
        GridBagConstraints gbc = new GridBagConstraints();

        mainFrame.addWindowListener(new WindowAdapter() { // Exit on clicking top right cross button
            public void windowClosing(WindowEvent windowEvent) {
                System.exit(0);
            }
        });

          // create a panel for the cover image 

          JPanel coverPanel = new JPanel(new BorderLayout());
          // Load the cover image
          ImageIcon coverImageIcon = new ImageIcon("car.png"); 
          Image coverImage = coverImageIcon.getImage().getScaledInstance(mainFrame.getWidth(), mainFrame.getHeight(), Image.SCALE_SMOOTH);
          ImageIcon scaledCoverImageIcon = new ImageIcon(coverImage);
          JLabel coverLabel = new JLabel(scaledCoverImageIcon);
          coverPanel.add(coverLabel, BorderLayout.CENTER);
  
          // Create a panel for the button and title
          
          JButton coverButton = new JButton("Get Started");
  
          JLabel coverTitleLabel = new JLabel("Welcome to EasyPark!");
          coverTitleLabel.setFont(new Font("Serif", Font.BOLD, 30));
          coverTitleLabel.setBounds(380, 200, 300, 400); 
          coverButton.setBounds(450, 500, 100, 40); 
          coverLabel.add(coverButton); 
          coverLabel.add(coverTitleLabel); 
  
          // Add the cover panel to the main frame
         
          mainFrame.add(coverPanel, gbc);
  

        JPanel imagePanel = new JPanel(new BorderLayout()); // adding header panel containing image and text with 2
        JLabel picLabel = new JLabel(); // new label for icon in the header
        ImageIcon logo = new ImageIcon(
                "car-logo.png");
        Image logoImg = logo.getImage().getScaledInstance(100, 80, SCALE_SMOOTH);
        picLabel.setIcon(new ImageIcon(logoImg));

        picLabel.setBorder(BorderFactory.createEmptyBorder(0, 180, 0, 20));
        imagePanel.add(picLabel, BorderLayout.WEST);

        JLabel textLabel = new JLabel("EasyPark Management System"); // Text label for headerpanel
        textLabel.setFont(new Font("Serif", Font.BOLD, 30));
        textLabel.setIconTextGap(50);
        imagePanel.add(textLabel, BorderLayout.CENTER);

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weighty = 0.5;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.anchor = GridBagConstraints.CENTER;
        mainFrame.add(imagePanel, gbc); // add everything in the image panel to the main JFrame

        headerpanel = new JPanel(); // middle Jpanel with grid layout
        headerpanel.setBackground(Color.WHITE);
        Border blackline = BorderFactory.createLineBorder(Color.DARK_GRAY); // add a border to it
        headerpanel.setBorder(blackline);
        headerpanel.setSize(1000, 500);

        gbc.gridy = 1;
        gbc.weighty = 1.5;

        mainFrame.add(headerpanel, gbc); // add to main JFrame
        headerpanel.setVisible(false);

     

        String[] btnText = new String[] {
                "Show All Parking Spots",
                "Add Parking Spot",
                "Delete Parking Spot",
                "Park Car",
                "Unpark Car",
                "Find Car",
                "Clear Screen",
                "Exit Application",
        };

        JPanel main = new JPanel(new GridBagLayout());
        GridBagConstraints mainGbc = new GridBagConstraints();
        mainGbc.fill = GridBagConstraints.BOTH;

        mainGbc.insets = new Insets(5, 5, 5, 5);
        JButton[] buttons = new JButton[8];
        for (int i = 0; i < 8; i++) {
            buttons[i] = new JButton(btnText[i]);
            mainGbc.gridx = i % 4;
            mainGbc.gridy = i / 4;
            buttons[i].setBackground(Color.LIGHT_GRAY);
            buttons[i].setPreferredSize(new Dimension(200, 40));
            main.add(buttons[i], mainGbc);
        }

        buttons[0].addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                headerpanel.removeAll();
                showAllParkingSpots();
            }
        });

        buttons[1].addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addSpot();
            }
        });
        buttons[2].addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                deleteSpot();
            }
        });

        buttons[3].addActionListener(new parkCarActionListener());

        buttons[4].addActionListener(new removeCarActionListener());
        buttons[5].addActionListener(new findCarActionListener());
        buttons[7].addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                exitApp();
            }
        });
        buttons[6].addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showHomeScreen();
            }
        });
        gbc.gridheight = 2;
        gbc.gridy = 2;
        gbc.weighty = 1.0;

        mainFrame.add(main, gbc);
        mainFrame.setVisible(true);

        main.setVisible(false);
    
        coverButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                coverPanel.setVisible(false);
                main.setVisible(true);
                headerpanel.setVisible(true);
                imagePanel.setVisible(true);
            }
        });

        initializeParkingLot();

    }

    private void showHomeScreen() {
        headerpanel.removeAll();
        headerLabel = new JLabel("Welcome to EasyPark!");
        headerLabel.setFont(new Font("Consolas", Font.PLAIN, 20));
        headerLabel.setHorizontalAlignment(SwingConstants.CENTER);
        headerLabel.setVerticalAlignment(SwingConstants.CENTER);
        headerpanel.add(headerLabel);
        headerpanel.revalidate();
        mainFrame.setVisible(true);
    }

    public class submenuDeleteListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (regularSpotButtons.contains(e.getSource())) {
                regularSpotNumber--;
                initRegularSpots();
                showAllParkingSpots();
            } else {
                oversizedSpotNumber--;
                initOversizedSpots();
                showAllParkingSpots();
                tabbedPane.setSelectedIndex(1);
            }
        }
    }

    private JPopupMenu spotSubMenu() {
        JPopupMenu spotMenu = new JPopupMenu("Menu");
        JMenuItem item1 = new JMenuItem("Delete parking spot");
        JMenuItem item2 = new JMenuItem("Park vehicle");
        JMenuItem item3 = new JMenuItem("Unpark vehicle");

        spotMenu.add(item1);
        spotMenu.add(item2);
        spotMenu.add(item3);

        spotMenu.setBorder(new LineBorder(Color.BLACK, 1));
        spotMenu.setPreferredSize(new Dimension(170, 80));

        return spotMenu;
    }

    @SuppressWarnings({ "rawtypes", "unchecked" })
    public void initializeParkingLot() {

        headerpanel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        gbc.gridwidth = 2;
        gbc.gridheight = 1;
        gbc.fill = GridBagConstraints.VERTICAL;

        JLabel prompt = new JLabel("Please select number of each spot type: ");
        prompt.setFont(new Font("Arial", Font.BOLD, 16));
        prompt.setBorder(BorderFactory.createEmptyBorder(0, 0, 30, 0));

        headerpanel.add(prompt, gbc);

        JLabel regularLabel = new JLabel("Regular spots: ");
        JLabel oversizedLabel = new JLabel("Oversized spots: ");

        regularLabel.setBorder(BorderFactory.createEmptyBorder(0, 50, 0, 0));
        oversizedLabel.setBorder(BorderFactory.createEmptyBorder(20, 50, 30, 0));

        String[] spotNumbersArray = new String[11];
        for (int i = 0; i <= 10; i++) {
            spotNumbersArray[i] = String.valueOf(i * 10);
        }

        regularSpotNumBox = new JComboBox(spotNumbersArray);
        oversizedSpotNumBox = new JComboBox(spotNumbersArray);

        // regularSpotNumBox.setBorder(BorderFactory.createEmptyBorder(30, 0, 0, 400));
        // oversizedSpotNumBox.setBorder(BorderFactory.createEmptyBorder(20, 0, 20,
        // 400));

        regularSpotNumBox.setBorder(BorderFactory.createLineBorder(Color.white));
        oversizedSpotNumBox.setBorder(BorderFactory.createLineBorder(Color.white));

        regularSpotNumBox.addItemListener(this);
        oversizedSpotNumBox.addItemListener(this);
        regularSpotNumBox.addActionListener(this);
        oversizedSpotNumBox.addActionListener(this);

        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        gbc.gridheight = 2;

        headerpanel.add(regularLabel, gbc);

        gbc.gridx = 1;

        headerpanel.add(regularSpotNumBox, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;

        headerpanel.add(oversizedLabel, gbc);

        gbc.gridx = 1;

        headerpanel.add(oversizedSpotNumBox, gbc);

        JButton confirmBtn = new JButton("Confirm");
        confirmBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                regularSpotNumber = Integer.valueOf((String) regularSpotNumBox.getSelectedItem());
                oversizedSpotNumber = Integer.valueOf((String) oversizedSpotNumBox.getSelectedItem());
                initRegularSpots();
                initOversizedSpots();
                JOptionPane.showConfirmDialog(headerpanel,
                        "<html>You have successfully created " + regularSpotNumBox.getSelectedItem()
                                + " regular parking spots"
                                + " and " + oversizedSpotNumBox.getSelectedItem() + " oversized parking spots.<html>",
                        "Parking lot initialization", JOptionPane.DEFAULT_OPTION);
                showAllParkingSpots();
            }
        });

        gbc.gridx = 0;
        gbc.gridy = 5;
        gbc.gridwidth = 2;
        gbc.gridheight = 1;

        headerpanel.add(confirmBtn, gbc);

        headerpanel.revalidate();
        headerpanel.repaint();

    }

    private void initRegularSpots() {
        headerpanel.removeAll();
        ArrayList<ParkingSpot> regularSpotList = parkingLot.getRegularSpotList();
        for (int i = 1; i <= regularSpotNumber; i++) {
            ParkingSpot spot = new ParkingSpot("R" + String.valueOf(i), ParkingType.REGULAR);
            regularSpotList.add(spot);
            parkingLot.getAllSpots().add(spot);
        }
        regularPanel = new JPanel();
        regularPanel.setBackground(Color.WHITE);
        regularPanel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        regularSpotButtons = new ArrayList<>();
        JButton newBtn = null;
        for (int i = 0; i < regularSpotNumber; i++) {
            ParkingSpot spot = parkingLot.getRegularSpotList().get(i);
            if (spot.getIsOccupied()) {
                newBtn = new JButton("<html>" + spot.getSpotId()
                        + "<br> Occupied <br>"
                        + spot.getParkedVehicle().getPlateNumber()
                        + "<br>Arrival time: <br>"
                        + spot.getParkedVehicle().getArrivalDate()
                        + "<br>" + spot.getParkedVehicle().getArrivalTime().toString().substring(11, 19)
                        + "</html>");
            } else {
                newBtn = new JButton(spot.getSpotId());
            }

            newBtn.setPreferredSize(new Dimension(110, 150));
            regularSpotButtons.add(newBtn);
            gbc.gridx = i % 5;
            gbc.gridy = i / 5;
            regularPanel.add(newBtn, gbc);

            newBtn.addActionListener(showPopupMenu(spotSubMenu));
        }
    }

    private void initOversizedSpots() {
        headerpanel.removeAll();

        ArrayList<ParkingSpot> oversizedSpotList = parkingLot.getOversizedSpotList();
        for (int i = 1; i <= oversizedSpotNumber; i++) {
            ParkingSpot spot = new ParkingSpot("O" + String.valueOf(i), ParkingType.OVERSIZED);
            oversizedSpotList.add(spot);
            parkingLot.getAllSpots().add(spot);

        }
        oversizedPanel = new JPanel();
        oversizedPanel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        oversizedPanel.setBackground(Color.WHITE);
        oversizedSpotButtons = new ArrayList<>();
        JButton newBtn = null;
        for (int i = 0; i < oversizedSpotNumber; i++) {
            ParkingSpot spot = parkingLot.getOversizedSpotList().get(i);
            if (spot.getIsOccupied()) {
                newBtn = new JButton("<html>" + spot.getSpotId()
                        + "<br> Occupied <br>"
                        + spot.getParkedVehicle().getPlateNumber()
                        + "<br>Arrival time: <br>"
                        + spot.getParkedVehicle().getArrivalDate()
                        + "<br>" + spot.getParkedVehicle().getArrivalTime().toString().substring(11, 19)
                        + "</html>");
            } else {
                newBtn = new JButton(spot.getSpotId());
            }

            newBtn.setPreferredSize(new Dimension(110, 150));
            oversizedSpotButtons.add(newBtn);
            gbc.gridx = i % 5;
            gbc.gridy = i / 5;
            oversizedPanel.add(newBtn, gbc);

            newBtn.addActionListener(showPopupMenu(spotSubMenu));
        }

    }

    public void showAllParkingSpots() {
        headerpanel.removeAll();
        tabbedPane = new JTabbedPane();
        
        JScrollPane regularScrollPane = new JScrollPane(regularPanel,
                JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
                JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        tabbedPane.addTab("Regular parking spots", regularScrollPane);

      
        JScrollPane oversizedScrollPane = new JScrollPane(oversizedPanel,
                JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
                JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

        tabbedPane.addTab("Oversized parking spots", oversizedScrollPane);

        tabbedPane.setSize(800, 400);
        headerpanel.setLayout(new BorderLayout());
        headerpanel.add(tabbedPane, BorderLayout.CENTER);
        headerpanel.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
        headerpanel.setBackground(Color.GRAY);
        headerpanel.revalidate();
        headerpanel.repaint();
        // headerpanel.setVisible(true);
        mainFrame.revalidate();
        mainFrame.repaint();
        mainFrame.setVisible(true);

     
    }

    private void exitApp() {
        System.exit(0);
    }

    public class parkCarActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            JPanel panel = new JPanel();
            panel.setLayout(new GridLayout(5, 2));

            JLabel typeLabel = new JLabel("Select a vehicle type: ");
            panel.add(typeLabel);

            JRadioButton radio1 = new JRadioButton("Regular");
            JRadioButton radio2 = new JRadioButton("Oversized");

            ButtonGroup radios = new ButtonGroup();
            radios.add(radio1);
            radios.add(radio2);
            panel.add(radio1);
            panel.add(radio2);

            JLabel plateLabel = new JLabel("Enter the plate number(eg. A12345): ");
            panel.add(plateLabel);

            JTextField plateField = new JTextField(20);
            panel.add(plateField);

            while (true) {
                int result = JOptionPane.showOptionDialog(
                        null,
                        panel,
                        null,
                        JOptionPane.YES_NO_OPTION,
                        JOptionPane.PLAIN_MESSAGE,
                        null,
                        new String[] { "Confirm", "Cancel" },
                        null);

                if (!(radio1.isSelected() || radio2.isSelected()) && plateField.getText().isEmpty()) {
                    break;
                    // if (radio1.isSelected() || radio2.isSelected()) {
                } else if ((radio1.isSelected() || radio2.isSelected()) && result == JOptionPane.YES_OPTION) {
                    if (parkingLot.getAllAvailableSpots() == null) {
                        JOptionPane.showMessageDialog(null, "No available spots.");
                        break;
                    } else if (plateField.getText().isEmpty()) {
                        JOptionPane.showMessageDialog(null, "Invalid plate number. Enter again.");
                    }

                    else if (!Character.isLetter(plateField.getText().charAt(0))) {
                        JOptionPane.showMessageDialog(null, "Invalid plate number. Enter again.");
                    } else {
                        String vehiclePlateNumber = plateField.getText();
                        ParkingType vehicleType = radio1.isSelected() ? ParkingType.REGULAR : ParkingType.OVERSIZED;
                        Vehicle vehicleToPark = null;
                        if (vehicleType == ParkingType.REGULAR) {
                            vehicleToPark = new Car(vehiclePlateNumber, vehicleType);
                            parkingLot.parkVehicle(vehicleToPark);
                            initRegularSpots();
                            showAllParkingSpots();
                        } else {
                            vehicleToPark = new Truck(vehiclePlateNumber, vehicleType);
                            parkingLot.parkVehicle(vehicleToPark);
                            initOversizedSpots();
                            showAllParkingSpots();
                            tabbedPane.setSelectedIndex(1);
                        }
                        break;
                    }
                } else {
                    break;
                }
            }
        }
    }
    public class findCarActionListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            JPanel platePanel = new JPanel();
            JLabel plateLabel = new JLabel("Enter the plate number(eg. A12345): ");
            platePanel.add(plateLabel);
            JTextField plateField = new JTextField(20);
            platePanel.add(plateField);

            while (true) {
                int plateResult = JOptionPane.showOptionDialog(
                        null,
                        platePanel,
                        null,
                        JOptionPane.YES_NO_OPTION,
                        JOptionPane.PLAIN_MESSAGE,
                        null,
                        new String[] { "Confirm", "Cancel" },
                        null);

                if (plateResult == JOptionPane.YES_OPTION && Character.isLetter(plateField.getText().charAt(0))) {
                    String vehiclePlateNumber = plateField.getText();
                    ParkingSpot spotFound = parkingLot.findVehicleByPlateNumber(vehiclePlateNumber);
                    if (spotFound != null) {
                        JOptionPane.showMessageDialog(null, "The vehicle is parked in spot " + spotFound.getSpotId());
                        break;

                    } else {
                        JOptionPane.showMessageDialog(null, "The plate number does not exist. Enter again.");
                    }
                } else if (plateResult == JOptionPane.YES_OPTION) {
                    JOptionPane.showMessageDialog(null, " Invalid plate number. Enter again.");
                } else {
                    break;
                }

            }
        }

    }

    public class removeCarActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            JPanel panel = new JPanel();
            panel.setLayout(new GridLayout(5, 2));
    
            JLabel typeLabel = new JLabel("Select a method: ");
            panel.add(typeLabel);
    
            JRadioButton radio1 = new JRadioButton("By vehicle plate number");
            JRadioButton radio2 = new JRadioButton("By parking spot id");
    
            ButtonGroup radios = new ButtonGroup();
            radios.add(radio1);
            radios.add(radio2);
            panel.add(radio1);
            panel.add(radio2);
    
            int result = JOptionPane.showOptionDialog(
                    null,
                    panel,
                    null,
                    JOptionPane.YES_NO_CANCEL_OPTION,
                    JOptionPane.PLAIN_MESSAGE,
                    null,
                    new String[] { "Confirm" },
                    null);
    
            // By plate number
            if (result == JOptionPane.YES_OPTION && radio1.isSelected()) {
                String vehiclePlateNumber = JOptionPane.showInputDialog("Enter the plate number(eg. A12345): ");
                if (vehiclePlateNumber != null && !vehiclePlateNumber.isEmpty()) {
                    ParkingSpot spot = parkingLot.findVehicleByPlateNumber(vehiclePlateNumber);
                    if (spot != null && spot.getIsOccupied()) {
                        ParkingTicket ticket = parkingLot.removeVehicleByPlateNumber(vehiclePlateNumber);
                            if (ticket != null) {
                                initRegularSpots();
                                initOversizedSpots();
                                showAllParkingSpots();
                               
                            } else {
                                JOptionPane.showMessageDialog(null, "Alert: No Car Found");
                                return;
                        }
                    } else {
                        JOptionPane.showMessageDialog(null, "Alert: This plate number is not found.");
                        return;
                }
            } else {
                JOptionPane.showMessageDialog(null, "Error: No Car");
            }
        } else if (result == JOptionPane.YES_OPTION && radio2.isSelected()) {
                String spotId = JOptionPane.showInputDialog("Enter the spot id(eg. R1/O1): ");
                if (spotId != null && !spotId.isEmpty()) {
                    ParkingTicket ticket = parkingLot.removeVehicleBySpotId(spotId);
                    if (ticket != null) {
                        initRegularSpots();
                        initOversizedSpots();
                        showAllParkingSpots();
                        tabbedPane.setSelectedIndex(1);
                        
                    } else {
                        JOptionPane.showMessageDialog(null, "Alert: No Car Found");
                        return;
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Error: Please enter a valid spot ID");
                }
        
            }

        }
    }

    private ActionListener showPopupMenu(JPopupMenu spotMenu) {

        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                spotMenu.show((JButton) e.getSource(), 0, ((JButton) e.getSource()).getHeight());
                JMenuItem item1 = (JMenuItem) (spotMenu.getComponent(0));
                item1.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent ee) {
                        if (regularSpotButtons.contains((JButton) e.getSource())) {
                            regularSpotNumber--;
                            initRegularSpots();
                            showAllParkingSpots();
                        } else if (oversizedSpotButtons.contains((JButton) e.getSource())) {
                            oversizedSpotNumber--;
                            initOversizedSpots();
                            showAllParkingSpots();
                            tabbedPane.setSelectedIndex(1);
                        }
                    }
                });

                // parkcar
                JMenuItem item2 = (JMenuItem) (spotMenu.getComponent(1));
                item2.addActionListener(new parkCarActionListener());

                JMenuItem item3 = (JMenuItem) (spotMenu.getComponent(2));
                item3.addActionListener(new removeCarActionListener());
            }
        };
    }

    private void deleteSpot() {
        JPanel panel = new JPanel();
        JLabel typeLabel = new JLabel("Select a spot type: ");
        panel.add(typeLabel);

        JRadioButton radio1 = new JRadioButton("Regular");
        JRadioButton radio2 = new JRadioButton("Oversized");

        ButtonGroup radios = new ButtonGroup();
        radios.add(radio1);
        radios.add(radio2);
        panel.add(radio1);
        panel.add(radio2);

        int result = JOptionPane.showOptionDialog(
                null,
                panel,
                null,
                JOptionPane.YES_NO_CANCEL_OPTION,
                JOptionPane.PLAIN_MESSAGE,
                null,
                new String[] { "Confirm" },
                null);

        if (result == JOptionPane.YES_OPTION && radio1.isSelected()) {
            String[] regularSpots = new String[regularSpotNumber];
            for (int i = 0; i < regularSpotNumber; i++) {
                regularSpots[i] = parkingLot.getRegularSpotList().get(i).getSpotId();
            }
            JComboBox<String> regularBox = new JComboBox<>(regularSpots);

            JPanel regularPanel = new JPanel();
            JLabel regularLabel = new JLabel("Select a spot: ");
            regularPanel.add(regularLabel);
            regularPanel.add(regularBox);
            int regularResult = JOptionPane.showOptionDialog(
                    null,
                    regularPanel,
                    null,
                    JOptionPane.YES_NO_CANCEL_OPTION,
                    JOptionPane.PLAIN_MESSAGE,
                    null,
                    new String[] { "Cancel", "Confirm" },
                    null);
            if (regularResult == 1) {
                String spotToDelete = (String) regularBox.getSelectedItem();
                for (int i = 0; i < parkingLot.getRegularSpotList().size(); i++) {
                    if (parkingLot.getRegularSpotList().get(i).getSpotId() == spotToDelete) {
                        parkingLot.getRegularSpotList().remove(i);
                    }
                }

                for (int i = 0; i < regularSpotButtons.size(); i++) {
                    if (regularSpotButtons.get(i).getText() == spotToDelete) {
                        regularSpotButtons.remove(i);
                    }
                }
                regularSpotNumber--;
                initRegularSpots();
                JOptionPane.showMessageDialog(regularPanel, "Success!");
                showAllParkingSpots();
            }

        } else if (result == JOptionPane.YES_OPTION && radio2.isSelected()) {
            tabbedPane.setSelectedIndex(1);

            JPanel oversizedPanel = new JPanel();
            JLabel oversizedLabel = new JLabel("Select a spot: ");
            oversizedPanel.add(oversizedLabel);

            String[] oversizedSpots = new String[oversizedSpotNumber];
            for (int i = 0; i < oversizedSpotNumber; i++) {
                oversizedSpots[i] = parkingLot.getOversizedSpotList().get(i).getSpotId();
            }
            JComboBox<String> oversizedBox = new JComboBox<>(oversizedSpots);

            oversizedPanel.add(oversizedBox);
            int oversizedResult = JOptionPane.showOptionDialog(
                    null,
                    oversizedPanel,
                    null,
                    JOptionPane.YES_NO_CANCEL_OPTION,
                    JOptionPane.PLAIN_MESSAGE,
                    null,
                    new String[] { "Cancel", "Confirm" },
                    null);

            if (oversizedResult == 1) {
                String spotToDelete = (String) oversizedBox.getSelectedItem();

                for (int i = 0; i < parkingLot.getOversizedSpotList().size(); i++) {
                    if (parkingLot.getOversizedSpotList().get(i).getSpotId() == spotToDelete) {
                        parkingLot.getOversizedSpotList().remove(i);
                    }
                }

                for (int i = 0; i < oversizedSpotButtons.size(); i++) {
                    if (oversizedSpotButtons.get(i).getText() == spotToDelete) {
                        oversizedSpotButtons.remove(i);
                    }
                }
                oversizedSpotNumber--;
                initOversizedSpots();
                JOptionPane.showMessageDialog(oversizedPanel, "Success!");
                showAllParkingSpots();
                tabbedPane.setSelectedIndex(1);

            }
        }
    }

    private void addSpot() {
        JPanel panel = new JPanel();
        JLabel typeLabel = new JLabel("Select a spot type: ");
        panel.add(typeLabel);

        JRadioButton radio1 = new JRadioButton("Regular");
        JRadioButton radio2 = new JRadioButton("Oversized");

        ButtonGroup radios = new ButtonGroup();
        radios.add(radio1);
        radios.add(radio2);
        panel.add(radio1);
        panel.add(radio2);

        int result = JOptionPane.showOptionDialog(
                null,
                panel,
                null,
                JOptionPane.YES_NO_CANCEL_OPTION,
                JOptionPane.PLAIN_MESSAGE,
                null,
                new String[] { "Confirm" },
                null);

        if (result == JOptionPane.YES_OPTION && radio1.isSelected()) {
            regularSpotNumber++;
            String newId = "R" + String.valueOf(regularSpotNumber);
            ParkingSpot spotToAdd = new ParkingSpot(newId, ParkingType.REGULAR);
            parkingLot.getRegularSpotList().add(spotToAdd);
            initRegularSpots();
            showAllParkingSpots();

        } else if (result == JOptionPane.YES_OPTION && radio2.isSelected()) {
            oversizedSpotNumber++;
            String newId = "C" + String.valueOf(oversizedSpotNumber);
            ParkingSpot spotToAdd = new ParkingSpot(newId, ParkingType.OVERSIZED);
            parkingLot.getOversizedSpotList().add(spotToAdd);
            initOversizedSpots();
            showAllParkingSpots();
            tabbedPane.setSelectedIndex(1);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == regularSpotNumBox) {
            regularSpotNumber = Integer.valueOf((String) regularSpotNumBox.getSelectedItem());

        } else if (e.getSource() == oversizedSpotNumBox) {
            oversizedSpotNumber = Integer.valueOf((String) oversizedSpotNumBox.getSelectedItem());
        }
    }

    @Override
    public void itemStateChanged(ItemEvent e) {
        if (e.getStateChange() == ItemEvent.SELECTED) {
            // JOptionPane op = new JOptionPane();
            // op.setMessage("You have successfully created " + e.getItem() + " parking
            // spot(s).");
            // JDialog dialog = op.createDialog("Confirm");
            // dialog.show();
        }

    }
}