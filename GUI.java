
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.*;
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



        buttons[6].addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showHomeScreen();
            }
        });
        buttons[7].addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                exitApp();
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

        // spotMenu.add("Park vehicle").addActionListener(ee -> {
        // park();
        // });
        // spotMenu.add("Unpark vehicle").addActionListener(ee -> {
        // unpark();
        // });
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

        ArrayList<ParkingSpot> regularSpotList = parkingLot.getRegularSpotList();
        for (int i = 1; i <= regularSpotNumber; i++) {
            ParkingSpot spot = new ParkingSpot("R" + String.valueOf(i), ParkingType.REGULAR);
            regularSpotList.add(spot);
        }
        regularPanel = new JPanel();
        regularPanel.setBackground(Color.WHITE);
        regularPanel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        regularSpotButtons = new ArrayList<>();
        for (int i = 0; i < regularSpotNumber; i++) {
            JButton newBtn = new JButton("R" + String.valueOf(i + 1));
            regularSpotButtons.add(newBtn);
            gbc.gridx = i % 10;
            gbc.gridy = i / 10;
            regularPanel.add(newBtn, gbc);
            newBtn.addActionListener(showPopupMenu(spotSubMenu));
        }
    }

    private void initOversizedSpots() {
        ArrayList<ParkingSpot> oversizedSpotList = parkingLot.getOversizedSpotList();
        for (int i = 1; i <= oversizedSpotNumber; i++) {
            ParkingSpot spot = new ParkingSpot("C" + String.valueOf(i), ParkingType.OVERSIZED);
            oversizedSpotList.add(spot);
        }
        oversizedPanel = new JPanel();
        oversizedPanel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        oversizedPanel.setBackground(Color.WHITE);
        oversizedSpotButtons = new ArrayList<>();
        for (int i = 0; i < oversizedSpotNumber; i++) {
            JButton newBtn = new JButton("C" + String.valueOf(i + 1));
            oversizedSpotButtons.add(newBtn);
            gbc.gridx = i % 10;
            gbc.gridy = i / 10;
            oversizedPanel.add(newBtn, gbc);
            newBtn.addActionListener(showPopupMenu(spotSubMenu));
        }

    }

    public void showAllParkingSpots() {
        headerpanel.removeAll();
        tabbedPane = new JTabbedPane();
        // for (int i = 0; i < regularSpotButtons.size(); i++) {
        // regularPanel.add(regularSpotButtons.get(i));
        // }
        // for (int i = 0; i < regularSpotNumber; i++) {
        // JButton newBtn = new JButton("R" + String.valueOf(i + 1));
        // regularSpotButtons.add(newBtn);
        //
        // newBtn.addActionListener(showPopupMenu(spotSubMenu));
        // }
        JScrollPane regularScrollPane = new JScrollPane(regularPanel,
                JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
                JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        tabbedPane.addTab("Regular parking spots", regularScrollPane);

        // for (int i = 0; i < oversizedSpotButtons.size(); i++) {
        // oversizedPanel.add(oversizedSpotButtons.get(i));
        // }

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

        // for (ParkingSpot : parking.getRegularSpotList()) {
        //
        // }
        //
        // for (ParkingSpot : parking.getRegularSpotList()) {
        //
        // }
    }

    private void exitApp() {
        System.exit(0);
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
                            // regularSpotButtons.remove((JButton) e.getSource());
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
