import java.sql.*;

public class Database {
    private static String user;
    private static String password;
    private static String DBURL;
    private static int port;
    private static String DBname;

    public Database() {
        this.user = "bupp";
        this.password = "ooga123";
        this.DBURL = "localhost";
        this.port = 3306;
        this.DBname = "snakepoints";
    }
    public void insert(int score,String name) {
        try {
            Connection conn = DriverManager.getConnection("jdbc:mysql://"+DBURL+":"+port+"/"+DBname+"? allowPublicKeyRetrieval=true&useSSL=false&serverTimezone=UTC", user,password);
            Statement stmt = conn.createStatement();
            String insert = "INSERT INTO highscore (usrname, score) VALUES (\""+ name +"\"," + score + ")";
            stmt.executeUpdate(insert);
            conn.close();
            stmt.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
    public void get() {
        try {
            Connection conn = DriverManager.getConnection("jdbc:mysql://"+DBURL+":"+port+"/"+DBname+"? allowPublicKeyRetrieval=true&useSSL=false&serverTimezone=UTC", user,password);
            Statement stmt = conn.createStatement();
            String get = "SELECT * FROM highscore ORDER BY score DESC";
            ResultSet rset = stmt.executeQuery(get);
            while (rset.next()) {
                int id = rset.getInt("id");
                String name = rset.getString("usrname");
                int score = rset.getInt("score");
                System.out.println(id + ".| " + name + " | " + score + " points");
            }

            conn.close();
            stmt.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
}
