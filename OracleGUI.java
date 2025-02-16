import javax.swing.*;
import javax.swing.plaf.nimbus.State;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.*;
import java.util.ArrayList;

import static com.sun.java.accessibility.util.AWTEventMonitor.addWindowListener;


public class OracleGUI implements ActionListener {
    private JButton drop;
    private JButton create;
    private JButton populate;
    private JButton query;
    private JButton searchGallery;
    private JButton searchArtist;
    private JButton searchArt;
    private JButton searchExhibition;
    private JButton updateGallery;
    private JButton addGallery;
    private JButton deleteGallery;

    private Connection conn1;

    OracleGUI(Connection conn1){
        this.conn1 = conn1;
        drop = new JButton();
        create = new JButton();
        populate = new JButton();
        query = new JButton();

        searchGallery = new JButton();
        searchArt = new JButton();
        searchArtist = new JButton();
        searchExhibition = new JButton();

        updateGallery = new JButton();
        addGallery = new JButton();
        deleteGallery = new JButton();

        drop.setBounds(20,20,150,50);
        drop.addActionListener(this);
        drop.setText("Drop tables");

        create.setBounds(20,80,150,50);
        create.addActionListener(this);
        create.setText("Create tables");

        populate.setBounds(20,140,150,50);
        populate.addActionListener(this);
        populate.setText("Populate tables");

        query.setBounds(20,200,150,50);
        query.addActionListener(this);
        query.setText("Query tables");

        searchGallery.setBounds(190, 20, 150, 50);
        searchGallery.addActionListener(this);
        searchGallery.setText("Search Gallery");

        searchArtist.setBounds(190, 80, 150, 50);
        searchArtist.addActionListener(this);
        searchArtist.setText("Search Artists");

        searchArt.setBounds(190, 140, 150, 50);
        searchArt.addActionListener(this);
        searchArt.setText("Search Artworks");

        searchExhibition.setBounds(190, 200, 150, 50);
        searchExhibition.addActionListener(this);
        searchExhibition.setText("Search Exhibitions");

        updateGallery.setBounds(360, 20, 150, 50);
        updateGallery.addActionListener(this);
        updateGallery.setText("Update Gallery");

        addGallery.setBounds(360, 80, 150, 50);
        addGallery.addActionListener(this);
        addGallery.setText("Add Gallery");

        deleteGallery.setBounds(360, 140, 150, 50);
        deleteGallery.addActionListener(this);
        deleteGallery.setText("Delete Gallery");



        JFrame frame = new JFrame();
        frame.setVisible(true);
        frame.setSize(545,400);
        frame.setTitle("User GUI");
        frame.setLayout(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(drop);
        frame.add(create);
        frame.add(populate);
        frame.add(query);
        frame.add(searchArt);
        frame.add(searchGallery);
        frame.add(searchArtist);
        frame.add(searchExhibition);
        frame.add(updateGallery);
        frame.add(addGallery);
        frame.add(deleteGallery);


        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                closeConnection();  // Close the connection before closing the window
                System.exit(0);  // Exit the application
            }
        });

    }

    private void closeConnection() {
        try {
            if (conn1 != null && !conn1.isClosed()) {
                conn1.close();  // Close the connection when the window is closing
                System.out.println("Connection closed.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }



    @Override
    public void actionPerformed(ActionEvent e){
        if(e.getSource() == drop){
            dropTable(conn1);
        }else if(e.getSource() == create){
            createTable(conn1);
        } else if(e.getSource() == populate){
            populateTable(conn1);
        } else if (e.getSource() == query){
            queryTable(conn1);
        } else if(e.getSource() == searchGallery){
            search_gallery(conn1);
        } else if(e.getSource() == searchArt){
            search_art(conn1);
        } else if(e.getSource() == searchArtist){
            search_artist(conn1);
        } else if(e.getSource() == searchExhibition){
            search_exhibition(conn1);
        } else if(e.getSource() == addGallery){
            openAddGalleryForm();
        } else if(e.getSource() == deleteGallery){
            openDeleteGalleryForm();
        } else {
            openUpdateGalleryForm();
        }
    }

    private void openUpdateGalleryForm() {
        JFrame updateGalleryFrame = new JFrame("Update Gallery");
        updateGalleryFrame.setSize(450, 500);
        updateGalleryFrame.setLayout(null);
        updateGalleryFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        // Labels and text fields for input
        JLabel labelID = new JLabel("Gallery ID:");
        labelID.setBounds(20, 30, 100, 30);
        JTextField textID = new JTextField();
        textID.setBounds(150, 30, 250, 30);

        JLabel labelName = new JLabel("Gallery Name:");
        labelName.setBounds(20, 80, 100, 30);
        JTextField textName = new JTextField();
        textName.setBounds(150, 80, 250, 30);

        JLabel labelStreet = new JLabel("Street:");
        labelStreet.setBounds(20, 130, 100, 30);
        JTextField textStreet = new JTextField();
        textStreet.setBounds(150, 130, 250, 30);

        JLabel labelCity = new JLabel("City:");
        labelCity.setBounds(20, 180, 100, 30);
        JTextField textCity = new JTextField();
        textCity.setBounds(150, 180, 250, 30);

        JLabel labelProvince = new JLabel("Province:");
        labelProvince.setBounds(20, 230, 100, 30);
        JTextField textProvince = new JTextField();
        textProvince.setBounds(150, 230, 250, 30);

        JLabel labelPostalCode = new JLabel("Postal Code:");
        labelPostalCode.setBounds(20, 280, 100, 30);
        JTextField textPostalCode = new JTextField();
        textPostalCode.setBounds(150, 280, 250, 30);

        JLabel labelCountry = new JLabel("Country:");
        labelCountry.setBounds(20, 330, 100, 30);
        JTextField textCountry = new JTextField();
        textCountry.setBounds(150, 330, 250, 30);

        // Submit button
        JButton submitButton = new JButton("Update");
        submitButton.setBounds(150, 400, 150, 30);
        submitButton.addActionListener(e -> {
            String galleryID = textID.getText();
            String galleryName = textName.getText();
            String street = textStreet.getText();
            String city = textCity.getText();
            String province = textProvince.getText();
            String postalCode = textPostalCode.getText();
            String country = textCountry.getText();

            // Validate that no fields are empty
            if (galleryID.isEmpty()) {
                JOptionPane.showMessageDialog(updateGalleryFrame, "Gallery ID field must be filled!", "Error", JOptionPane.ERROR_MESSAGE);
            } else {
                // Insert the gallery into the database
                updateGalleryToDatabase(galleryID, galleryName, street, city, province, postalCode, country, conn1);
                updateGalleryFrame.dispose(); // Close the form after adding
            }
        });

        // Add components to the frame
        updateGalleryFrame.add(labelID);
        updateGalleryFrame.add(textID);
        updateGalleryFrame.add(labelName);
        updateGalleryFrame.add(textName);
        updateGalleryFrame.add(labelStreet);
        updateGalleryFrame.add(textStreet);
        updateGalleryFrame.add(labelCity);
        updateGalleryFrame.add(textCity);
        updateGalleryFrame.add(labelProvince);
        updateGalleryFrame.add(textProvince);
        updateGalleryFrame.add(labelPostalCode);
        updateGalleryFrame.add(textPostalCode);
        updateGalleryFrame.add(labelCountry);
        updateGalleryFrame.add(textCountry);
        updateGalleryFrame.add(submitButton);

        updateGalleryFrame.setVisible(true);
    }

    private void updateGalleryToDatabase(String galleryID, String galleryName, String street, String city, String province, String postalCode, String country, Connection conn) {
        StringBuilder queryBuilder = new StringBuilder("UPDATE gallery SET ");
        ArrayList<Object> params = new ArrayList<>();

        // Dynamically build the SET clause based on non-empty fields
        if (!galleryName.isEmpty()) {
            queryBuilder.append("name = ?, ");
            params.add(galleryName);
        }
        if (!street.isEmpty()) {
            queryBuilder.append("street = ?, ");
            params.add(street);
        }
        if (!city.isEmpty()) {
            queryBuilder.append("city = ?, ");
            params.add(city);
        }
        if (!province.isEmpty()) {
            queryBuilder.append("province = ?, ");
            params.add(province);
        }
        if (!postalCode.isEmpty()) {
            queryBuilder.append("postalcode = ?, ");
            params.add(postalCode);
        }
        if (!country.isEmpty()) {
            queryBuilder.append("country = ?, ");
            params.add(country);
        }

        // Remove the last comma and space
        if (queryBuilder.charAt(queryBuilder.length() - 2) == ',') {
            queryBuilder.setLength(queryBuilder.length() - 2);
        }

        // Add WHERE clause
        queryBuilder.append(" WHERE galleryid = ?");

        // Add gallery ID to parameters
        params.add(Integer.parseInt(galleryID));

        // Create the prepared statement and set the parameters
        try (PreparedStatement pstmt = conn.prepareStatement(queryBuilder.toString())) {
            // Set the parameters for the query
            for (int i = 0; i < params.size(); i++) {
                pstmt.setObject(i + 1, params.get(i));
            }

            // Execute the update query
            int rowAffected = pstmt.executeUpdate();
            JOptionPane.showMessageDialog(null, "Updated " + rowAffected + " row.");
        } catch (SQLException e) {
            System.out.println("Error: " + e.getErrorCode());
        }
    }

    private void openDeleteGalleryForm() {
        JFrame deleteGallery = new JFrame("Delete Gallery");
        deleteGallery.setSize(400, 200);
        deleteGallery.setLayout(null);
        deleteGallery.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JLabel labelID = new JLabel("Enter Gallery ID to be removed");
        labelID.setBounds(20,30,200,30);
        JTextField textID = new JTextField();
        textID.setBounds(250,30, 50,30);

        JButton submitButton = new JButton("Delete");
        submitButton.setBounds(100, 70, 150, 30);
        submitButton.addActionListener(e -> {
            String galleryID = textID.getText();

            // Validate that no fields are empty
            if (galleryID.isEmpty()) {
                JOptionPane.showMessageDialog(deleteGallery, "All fields must be filled!", "Error", JOptionPane.ERROR_MESSAGE);
            } else {
                // Insert the gallery into the database
                deleteGalleryToDatabase(galleryID, conn1);
                deleteGallery.dispose(); // Close the form after adding
            }
        });

        deleteGallery.add(labelID);
        deleteGallery.add(textID);
        deleteGallery.add(submitButton);
        deleteGallery.setVisible(true);
    }

    private void deleteGalleryToDatabase(String galleryID, Connection conn) {
        String query = "DELETE FROM GALLERY WHERE GALLERYID = ?";
        try(PreparedStatement pstmt = conn.prepareStatement(query)){
            pstmt.setInt(1,Integer.parseInt(galleryID));
            // Execute the query
            int rowAffected = pstmt.executeUpdate();

            // Show message with the result
            JOptionPane.showMessageDialog(null, "Deleted " + rowAffected + " row.");
        } catch (SQLException e){
            System.out.println("Error: " + e.getErrorCode());
        }
    }


    private void openAddGalleryForm() {
        JFrame addGalleryFrame = new JFrame("Add Gallery");
        addGalleryFrame.setSize(450, 500);
        addGalleryFrame.setLayout(null);
        addGalleryFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        // Labels and text fields for input
        JLabel labelID = new JLabel("Gallery ID (*):");
        labelID.setBounds(20, 30, 100, 30);
        JTextField textID = new JTextField();
        textID.setBounds(150, 30, 250, 30);

        JLabel labelName = new JLabel("Gallery Name (*):");
        labelName.setBounds(20, 80, 100, 30);
        JTextField textName = new JTextField();
        textName.setBounds(150, 80, 250, 30);

        JLabel labelStreet = new JLabel("Street (*):");
        labelStreet.setBounds(20, 130, 100, 30);
        JTextField textStreet = new JTextField();
        textStreet.setBounds(150, 130, 250, 30);

        JLabel labelCity = new JLabel("City:");
        labelCity.setBounds(20, 180, 100, 30);
        JTextField textCity = new JTextField();
        textCity.setBounds(150, 180, 250, 30);

        JLabel labelProvince = new JLabel("Province:");
        labelProvince.setBounds(20, 230, 100, 30);
        JTextField textProvince = new JTextField();
        textProvince.setBounds(150, 230, 250, 30);

        JLabel labelPostalCode = new JLabel("Postal Code:");
        labelPostalCode.setBounds(20, 280, 100, 30);
        JTextField textPostalCode = new JTextField();
        textPostalCode.setBounds(150, 280, 250, 30);

        JLabel labelCountry = new JLabel("Country (*):");
        labelCountry.setBounds(20, 330, 100, 30);
        JTextField textCountry = new JTextField();
        textCountry.setBounds(150, 330, 250, 30);

        // Submit button
        JButton submitButton = new JButton("Add Gallery");
        submitButton.setBounds(150, 400, 150, 30);
        submitButton.addActionListener(e -> {
            String galleryID = textID.getText();
            String galleryName = textName.getText();
            String street = textStreet.getText();
            String city = textCity.getText();
            String province = textProvince.getText();
            String postalCode = textPostalCode.getText();
            String country = textCountry.getText();

            // Validate that no fields are empty
            if (galleryID.isEmpty() || galleryName.isEmpty() || street.isEmpty() || country.isEmpty()) {
                JOptionPane.showMessageDialog(addGalleryFrame, "All * fields must be filled!", "Error", JOptionPane.ERROR_MESSAGE);
            } else {
                // Insert the gallery into the database
                addGalleryToDatabase(galleryID, galleryName, street, city, province, postalCode, country, conn1);
                addGalleryFrame.dispose(); // Close the form after adding
            }
        });

        // Add components to the frame
        addGalleryFrame.add(labelID);
        addGalleryFrame.add(textID);
        addGalleryFrame.add(labelName);
        addGalleryFrame.add(textName);
        addGalleryFrame.add(labelStreet);
        addGalleryFrame.add(textStreet);
        addGalleryFrame.add(labelCity);
        addGalleryFrame.add(textCity);
        addGalleryFrame.add(labelProvince);
        addGalleryFrame.add(textProvince);
        addGalleryFrame.add(labelPostalCode);
        addGalleryFrame.add(textPostalCode);
        addGalleryFrame.add(labelCountry);
        addGalleryFrame.add(textCountry);
        addGalleryFrame.add(submitButton);

        addGalleryFrame.setVisible(true);
    }



    private void addGalleryToDatabase(String galleryID, String galleryName, String street, String city, String province, String postalCode, String country, Connection conn) {
        String query = "INSERT INTO gallery(GalleryID, Name, Street, City, Province, PostalCode, Country) VALUES(?, ?, ?, ?, ?, ?, ?)";

        try (PreparedStatement pstmt = conn.prepareStatement(query)) {
            // Set parameters for the prepared statement
            pstmt.setInt(1, Integer.parseInt(galleryID));  // Assuming galleryID is passed as a String
            pstmt.setString(2, galleryName);
            pstmt.setString(3, street);
            pstmt.setString(4, city.isEmpty() ? null : city);
            pstmt.setString(5, province.isEmpty() ? null : province);
            pstmt.setString(6, postalCode.isEmpty() ? null : postalCode);
            pstmt.setString(7, country);

            // Execute the query
            int rowAffected = pstmt.executeUpdate();

            // Show message with the result
            JOptionPane.showMessageDialog(null, "Inserted " + rowAffected + " row.");
        } catch (SQLException e) {
            // Handle exception and print error code
            System.out.println("Error: " + e.getErrorCode());
        }
    }

    private void execute_select(Connection conn, String query){
        try(Statement stmt = conn.createStatement()){
            String res = "";
            ResultSet rs = stmt.executeQuery(query);
            ResultSetMetaData rsMetaData = rs.getMetaData();
            int columnCount = rsMetaData.getColumnCount();

            while (rs.next()) {
//                System.out.print("Row: ");
                res += "Row: ";

                for (int i = 1; i <= columnCount; i++) {
                    //get the name of column
                    String columnName = rsMetaData.getColumnName(i);

                    //get the value of column

                    Object columnValue = rs.getObject(i);

//                    System.out.print(columnName + " : " + columnValue);
                    res += columnName + " : " + columnValue;

                    if (i < columnCount) {
//                        System.out.print(", ");
                        res += ", ";
                    }
                }
//                System.out.println();
                res += "\n";
            }

            JOptionPane.showMessageDialog(null,res);
        }catch (SQLException e){
            System.out.println("Error: " + e.getErrorCode());
        }
    }

    private void search_exhibition(Connection conn) {
        execute_select(conn, "SELECT * FROM EXHIBITION ORDER BY EXHIBITIONID ASC");
    }

    private void search_artist(Connection conn) {
        execute_select(conn, "SELECT * FROM ARTIST ORDER BY ARTISTID ASC");
    }

    private void search_art(Connection conn) {
        execute_select(conn, "SELECT * FROM ARTWORK ORDER BY ARTID ASC");
    }

    private void search_gallery(Connection conn) {
        execute_select(conn, "select * from GALLERY ORDER BY GALLERYID ASC");
    }

    private void dropTable(Connection conn){
//        conn = conn1;
        try(Statement stmt = conn.createStatement()) {
            conn.setAutoCommit(false);
            try{
                stmt.addBatch("DROP TABLE storage CASCADE CONSTRAINTS");
                stmt.addBatch("DROP TABLE orderable CASCADE CONSTRAINTS");
                stmt.addBatch("DROP TABLE artDisplay CASCADE CONSTRAINTS");
                stmt.addBatch("DROP TABLE exhibition CASCADE CONSTRAINTS");
                stmt.addBatch("DROP TABLE artwork CASCADE CONSTRAINTS");
                stmt.addBatch("DROP TABLE artist CASCADE CONSTRAINTS");
                stmt.addBatch("DROP TABLE gallery CASCADE CONSTRAINTS");

                int[] execute = stmt.executeBatch();

                conn.commit();

//                System.out.println("Drop table successfully.");
                JOptionPane.showMessageDialog(null,"Drop table successfully.");
            } catch (SQLException e){
                e.printStackTrace();
                try{
                    conn.rollback();
                } catch (SQLException ex){
                    ex.printStackTrace();
                }
            } finally {
                conn.setAutoCommit(true);
            }
        } catch (SQLException e){
            System.out.println("Error: " + e.getErrorCode());
        }
    }

    private void createTable(Connection conn){
        try(Statement stmt = conn.createStatement()) {
            conn.setAutoCommit(false);
            try{
                stmt.addBatch("CREATE TABLE gallery (\n" +
                        "               GalleryID       INT PRIMARY KEY,\n" +
                        "               Name            VARCHAR2(100) UNIQUE NOT NULL,\n" +
                        "               Street          VARCHAR2(100) NOT NULL,\n" +
                        "               City            VARCHAR2(25) DEFAULT 'Toronto',\n" +
                        "               Province        VARCHAR2(25) DEFAULT 'Ontario',\n" +
                        "               PostalCode      VARCHAR2(6) DEFAULT 'A0A0A0',\n" +
                        "               Country         VARCHAR2(25) DEFAULT 'Canada' NOT NULL,\n" +
                        "               CONSTRAINT chkStreet CHECK (REGEXP_LIKE (Street,\n" +
                        "      '^[0-9]+ [A-Z,a-z ]+$')),\n" +
                        "               CONSTRAINT chkPostal CHECK (REGEXP_LIKE (PostalCode,\n" +
                        "       '^[A-Z][0-9][A-Z][0-9][A-Z][0-9]$'))\n" +
                        ")");
                stmt.addBatch("CREATE TABLE artist (\n" +
                        "               ArtistID         INT PRIMARY KEY,\n" +
                        "               Name             VARCHAR2(100) NOT NULL,\n" +
                        "               PhoneNumber      VARCHAR2(10),\n" +
                        "               Email            VARCHAR2(320),\n" +
                        "               CONSTRAINT checkPhone CHECK (REGEXP_LIKE (PhoneNumber,\n" +
                        "      '^[0-9]+$')),\n" +
                        "               CONSTRAINT chkEmail CHECK (REGEXP_LIKE (Email,\n" +
                        "      '^[A-Za-z0-9.+-]+@[a-z]+\\.[a-z]+$'))\n" +
                        ")");
                stmt.addBatch("CREATE TABLE artwork (\n" +
                        "               ArtID            INT PRIMARY KEY,\n" +
                        "               Name             VARCHAR2(100),\n" +
                        "               Origin           VARCHAR2(25) DEFAULT 'Canada',\n" +
                        "               Art_type         VARCHAR2(25) DEFAULT 'Abstract',\n" +
                        "               Description      VARCHAR2(350),\n" +
                        "               DateCreated      Date NOT NULL,\n" +
                        "               DateAcquired     Date,\n" +
                        "               ArtistID         INT NOT NULL,\n" +
                        "               FOREIGN KEY (ArtistID) REFERENCES artist(ArtistID)\n" +
                        ")");
                stmt.addBatch("CREATE TABLE exhibition (\n" +
                        "               ExhibitionID    INT PRIMARY KEY,\n" +
                        "               Name            VARCHAR2(100) NOT NULL,\n" +
                        "               GalleryID       INT,\n" +
                        "               FOREIGN KEY (GalleryID) REFERENCES gallery(GalleryID),\n" +
                        "               CONSTRAINT uniqNamePerGal UNIQUE (Name, GalleryID)\n" +
                        ")");
                stmt.addBatch("CREATE TABLE artDisplay (\n" +
                        "               ExhibitionID     INT,\n" +
                        "               ArtID            INT,\n" +
                        "               PRIMARY KEY (ExhibitionID, ArtID),\n" +
                        "               FOREIGN KEY (ExhibitionID) REFERENCES exhibition\n" +
                        "       (ExhibitionID),\n" +
                        "               FOREIGN KEY (ArtID) REFERENCES artwork(ArtID)\n" +
                        ")");
                stmt.addBatch("CREATE TABLE orderable (\n" +
                        "               OrderID         INT PRIMARY KEY,\n" +
                        "               AvailDate       DATE,\n" +
                        "               Price           NUMBER(15,2) NULL,\n" +
                        "               GalleryID       INT NOT NULL,\n" +
                        "               ArtID           INT NOT NULL,\n" +
                        "               FOREIGN KEY (GalleryID) REFERENCES gallery(GalleryID),\n" +
                        "               FOREIGN KEY (ArtID) REFERENCES artwork(ArtID)\n" +
                        ")");
                stmt.addBatch("CREATE TABLE storage (\n" +
                        "               StorageID       INT PRIMARY KEY,\n" +
                        "               StorageDate     DATE NOT NULL,\n" +
                        "               GalleryID       INT NOT NULL,\n" +
                        "               ArtID           INT NOT NULL,\n" +
                        "               FOREIGN KEY (GalleryID) REFERENCES gallery(GalleryID),\n" +
                        "               FOREIGN KEY (ArtID) REFERENCES artwork(ArtID)\n" +
                        ")");

                int[] execute = stmt.executeBatch();

                conn.commit();

//                System.out.println("Create tables successfully.");
                JOptionPane.showMessageDialog(null,"Create table successfully.");

            } catch (SQLException e){
                e.printStackTrace();
                try{
                    conn.rollback();
                } catch (SQLException ex){
                    ex.printStackTrace();
                }
            } finally {
                conn.setAutoCommit(true);
            }
        } catch (SQLException e){
            System.out.println("Error: " + e.getErrorCode());
        }
    }

    private void populateTable(Connection conn){
        try(Statement stmt = conn.createStatement()) {
            conn.setAutoCommit(false);
            try{
                // Add ALTER TABLE statements to the batch
                stmt.addBatch("ALTER TABLE artwork MODIFY Name VARCHAR2(100)");
                stmt.addBatch("ALTER TABLE gallery MODIFY Street VARCHAR2(100)");
                stmt.addBatch("ALTER TABLE artist MODIFY Name VARCHAR2(100)");
                stmt.addBatch("ALTER TABLE artist MODIFY Email VARCHAR2(100)");
                stmt.addBatch("ALTER TABLE gallery MODIFY Name VARCHAR2(100)");
                stmt.addBatch("ALTER TABLE artwork MODIFY Description VARCHAR2(500)");
                stmt.addBatch("ALTER TABLE exhibition MODIFY Name VARCHAR2(100)");

                // Add INSERT INTO statements for gallery
                stmt.addBatch("INSERT INTO gallery(GalleryID, Name, Street, City, Province, PostalCode, Country) "
                        + "VALUES(1, 'The Art Gallery of Ontario', '317 Dundas St W', 'Toronto', 'ON', 'M5T1G4', 'Canada')");
                stmt.addBatch("INSERT INTO gallery(GalleryID, Name, Street, City, Province, PostalCode, Country) "
                        + "VALUES(2, 'McMichael Canadian Art Collection', '10365 Islington Ave', 'Kleinburg', 'ON', 'L0J1C0', 'Canada')");
                stmt.addBatch("INSERT INTO gallery(GalleryID, Name, Street, City, Province, PostalCode, Country) "
                        + "VALUES(3, 'The Power Plant Contemporary Art Gallery', '231 Queens Quay W', 'Toronto', 'ON', 'M5J2G8', 'Canada')");

                // Add INSERT INTO statements for artist
                stmt.addBatch("INSERT INTO artist VALUES(1000, 'Pablo Picasso', NULL, NULL)");
                stmt.addBatch("INSERT INTO artist VALUES(1001, 'Takashi Murakami', '5468715320', 'takashi.murakhmi@gmail.com')");
                stmt.addBatch("INSERT INTO artist VALUES(1002, 'Michelangelo', NULL, NULL)");
                stmt.addBatch("INSERT INTO artist VALUES(1003, 'Vincent van Gogh', NULL, NULL)");
                stmt.addBatch("INSERT INTO artist VALUES(1004, 'Johannes Vermeer', NULL, NULL)");
                stmt.addBatch("INSERT INTO artist VALUES(1005, 'Tom Thomson', NULL, NULL)");

                // Add INSERT INTO statements for artwork
                stmt.addBatch("INSERT INTO artwork VALUES(100, 'Guernica', 'Spain', 'Abstract', NULL, TO_DATE('1937','YYYY'), TO_DATE('2021','YYYY'), 1000)");
                stmt.addBatch("INSERT INTO artwork VALUES(101, 'Girl before a Mirror', 'Spain', 'Cubism', NULL, TO_DATE('1932','YYYY'), TO_DATE('2020','YYYY'), 1000)");
                stmt.addBatch("INSERT INTO artwork VALUES(102, 'Flowers', 'Japan', DEFAULT, NULL, TO_DATE('2002','YYYY'), TO_DATE('2023','YYYY'), 1001)");
                stmt.addBatch("INSERT INTO artwork VALUES(103, 'Army of Mushrooms', 'Japan', DEFAULT, NULL, TO_DATE('2003','YYYY'), TO_DATE('2024','YYYY'), 1001)");
                stmt.addBatch("INSERT INTO artwork VALUES(104, 'Creation of Adam', 'Italy', 'Classic', NULL, TO_DATE('1512','YYYY'), TO_DATE('2017','YYYY'), 1002)");
                stmt.addBatch("INSERT INTO artwork VALUES(105, 'The Torment of Saint Anthony', 'Italy', 'Classic', NULL, TO_DATE('1488','YYYY'), TO_DATE('2010','YYYY'), 1002)");
                stmt.addBatch("INSERT INTO artwork VALUES(106, 'The Starry Night', 'Dutch', 'Modern', NULL, TO_DATE('1889','YYYY'), TO_DATE('2024','YYYY'), 1003)");
                stmt.addBatch("INSERT INTO artwork VALUES(107, 'Wheatfield with Crows', 'Dutch', 'Modern', NULL, TO_DATE('1890','YYYY'), TO_DATE('2012','YYYY'), 1003)");
                stmt.addBatch("INSERT INTO artwork VALUES(108, 'Girl with a Pearl Earring', 'Dutch', 'Baroque', NULL, TO_DATE('1665','YYYY'), TO_DATE('2024','YYYY'), 1004)");
                stmt.addBatch("INSERT INTO artwork VALUES(109, 'The Jack Pine', DEFAULT, 'Nouveau', NULL, TO_DATE('1916','YYYY'), TO_DATE('2024','YYYY'), 1005)");

                // Add INSERT INTO statements for exhibition
                stmt.addBatch("INSERT INTO exhibition VALUES(1, 'Moment in Modernism', 1)");
                stmt.addBatch("INSERT INTO exhibition VALUES(2, 'River of Dreams', 2)");
                stmt.addBatch("INSERT INTO exhibition VALUES(3, 'Floating Sea Palace', 3)");

                // Add INSERT INTO statements for artDisplay
                stmt.addBatch("INSERT INTO artDisplay VALUES(2, 100)");
                stmt.addBatch("INSERT INTO artDisplay VALUES(2, 101)");
                stmt.addBatch("INSERT INTO artDisplay VALUES(1, 102)");
                stmt.addBatch("INSERT INTO artDisplay VALUES(1, 103)");
                stmt.addBatch("INSERT INTO artDisplay VALUES(3, 104)");

                // Add INSERT INTO statements for orderable
                stmt.addBatch("INSERT INTO orderable VALUES(112, TO_DATE('2024-09-30','YYYY-MM-DD'), 1000, 1, 106)");
                stmt.addBatch("INSERT INTO orderable VALUES(114, TO_DATE('2024-11-01','YYYY-MM-DD'), 2500, 3, 108)");
                stmt.addBatch("INSERT INTO orderable VALUES(115, TO_DATE('2024-10-20','YYYY-MM-DD'), 4300, 2, 109)");


                // Add INSERT INTO statements for storage
                stmt.addBatch("INSERT INTO storage VALUES(1, TO_DATE('2018-05-24','YYYY-MM-DD'), 1, 105)");
                stmt.addBatch("INSERT INTO storage VALUES(2, TO_DATE('2020-12-22','YYYY-MM-DD'), 2, 107)");


                // Add UPDATE statements
                stmt.addBatch("UPDATE artwork SET Art_type = 'Cubism' WHERE ArtID = 100");
                stmt.addBatch("UPDATE artwork SET Art_type = 'Renaissance' WHERE ArtID = 105");

                int[] execute = stmt.executeBatch();
//                System.out.println("Populate tables successfully.");
                JOptionPane.showMessageDialog(null,"Populate tables successfully.");

            } catch (SQLException e){
                e.printStackTrace();
                try{
                    conn.rollback();
                } catch (SQLException ex){
                    ex.printStackTrace();
                }
            } finally {
                conn.setAutoCommit(true);
            }
        } catch (SQLException e){
            System.out.println("Error: " + e.getErrorCode());
        }
    }

    private void queryTable(Connection conn){
        try(Statement stmt = conn.createStatement()){
            // put all the select statement into the array
            String[] command = {"SELECT g.GalleryID, g.Name AS Gallery, COUNT(aw.ArtID) AS Num_Artworks " +
                    "FROM gallery g, artwork aw " +
                    "WHERE EXISTS " +
                    "(SELECT * " +
                    "FROM exhibition e, artDisplay ad " +
                    "WHERE ad.ExhibitionID = e.ExhibitionID " +
                    "AND e.GalleryID = g.GalleryID " +
                    "AND ad.ArtID = aw.ArtID) " +
                    "OR EXISTS " +
                    "(SELECT * " +
                    "FROM orderable o " +
                    "WHERE o.ArtID = aw.ArtID " +
                    "AND o.GalleryID = g.GalleryID) " +
                    "OR EXISTS " +
                    "(SELECT * " +
                    "FROM storage s " +
                    "WHERE s.ArtID = aw.ArtID " +
                    "AND s.GalleryID = g.GalleryID) " +
                    "GROUP BY g.GalleryID, g.Name " +
                    "ORDER BY Num_Artworks DESC, GalleryID ASC",

                    "SELECT g.GalleryID, aw.ArtID " +
                            "FROM gallery g, artwork aw " +
                            "WHERE EXISTS " +
                            "(SELECT * " +
                            "FROM exhibition e, artDisplay ad " +
                            "WHERE ad.ExhibitionID = e.ExhibitionID " +
                            "AND e.GalleryID = g.GalleryID " +
                            "AND ad.ArtID = aw.ArtID) " +
                            "UNION " +
                            "(SELECT o.GalleryID, o.ArtID " +
                            "FROM orderable o) " +
                            "UNION " +
                            "(SELECT s.GalleryID, s.ArtID " +
                            "FROM storage s) " +
                            "ORDER BY GalleryID, ArtID",

                    "SELECT Art, Id " +
                            "FROM " +
                            "(SELECT a.Name AS Art, a.ArtID as Id " +
                            "FROM artwork a " +
                            "MINUS " +
                            "(SELECT a.Name, a.ArtID " +
                            "FROM artwork a, orderable o " +
                            "WHERE o.ArtID = a.ArtID " +
                            "UNION " +
                            "SELECT a.Name, a.ARTID " +
                            "FROM artwork a, storage s " +
                            "WHERE a.ArtID = s.ArtID))",

                    "SELECT art_type, COUNT(ArtID) " +
                            "FROM artwork " +
                            "GROUP BY art_type " +
                            "HAVING COUNT(ArtID) >= 2",

                    "WITH CountryArtCounts AS" +
                            "(SELECT origin, COUNT(*) AS ArtCount " +
                            "FROM artwork " +
                            "GROUP BY origin) " +
                            "SELECT AVG(ArtCount) AS AverageArtCount, STDDEV(ArtCount) AS OriginCountStdDev " +
                            "FROM CountryArtCounts"};

            String res = "";

            //execute each statement, not the best runtime efficiency
            for (int i = 0; i < command.length; i++) {
                int pos = i + 1;
//                System.out.println("QUERY " + pos);
                res += "QUERY " + pos + "\n";

                ResultSet rs = stmt.executeQuery(command[i]);
                ResultSetMetaData rsMetaData = rs.getMetaData();
                int columnCount = rsMetaData.getColumnCount();

                while (rs.next()) {
//                    System.out.print("Row: ");
                    res += "Row: ";

                    for (int j = 1; j <= columnCount; j++) {
                        //get the name of column
                        String columnName = rsMetaData.getColumnName(j);

                        //get the value of column

                        Object columnValue = rs.getObject(j);

//                        System.out.print(columnName + " : " + columnValue);
                        res += columnName + " : " + columnValue;

                        if (j < columnCount) {
                            System.out.print(", ");
                            res += ", ";
                        }
                    }
//                    System.out.println();
                    res += "\n";
                }
            }
            JOptionPane.showMessageDialog(null,res);


        } catch (SQLException e){
            System.out.println("Error: " + e.getErrorCode());
        }
    }


}
