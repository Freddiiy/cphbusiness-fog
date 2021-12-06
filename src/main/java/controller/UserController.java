package controller;

import at.favre.lib.crypto.bcrypt.BCrypt;
import model.*;
import persistance.Database;

import javax.servlet.http.HttpSession;
import java.nio.charset.StandardCharsets;
import java.security.SecureRandom;
import java.sql.*;
import java.text.DecimalFormat;

public class UserController {
    private final Database database;

    public UserController() {
        this.database = Database.getInstance();
    }

    public boolean isLoggedIn(HttpSession session, User user) {
        return session.getAttribute("email").equals(user.getEmail()) && user.getPassword().equals(getUserFromDb((String) session.getAttribute("email"), user.getPassword()).getPassword());
    }

    //Insert data
    public void insertUserToDb(User user) {
        String sql = "INSERT INTO Users (email, password, role, sessionID, fname, lname) VALUES(?, ?, ?, ?, ?, ?)";

        try (Connection connection = database.connect()) {
            PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            ps.setString(1, user.getEmail());
            ps.setString(2, hashPassword(user.getPassword()));
            ps.setString(4, user.getRole());
            ps.setString(5, user.getSessionID());
            ps.setString(6, user.getFname());
            ps.setString(7, user.getLname());
            ps.executeUpdate();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }

    public User getUserFromDb(String email, String password) {
        String sql = "SELECT id_user, email, password, role, sessionID, fname, lname from Users WHERE email = ?";

        try (Connection connection = database.connect()) {
            PreparedStatement ps = connection.prepareStatement(sql);

            ps.setString(1, email);

            ResultSet resultSet = ps.executeQuery();
            if (resultSet.next()) {
                int id = resultSet.getInt("id_user");
                String emailFromDb = resultSet.getString("email");
                String fnameFromDb = resultSet.getString("fname");
                String lnameFromDb = resultSet.getString("lname");
                String passwordFromDb = resultSet.getString("password");
                String roleFromDb = resultSet.getString("role");
                String sessionIDFromDb = resultSet.getString("sessionID");

                if (email.equals(emailFromDb) && matchHashedPassword(password, passwordFromDb)) {
                    return new User(id, emailFromDb, fnameFromDb, lnameFromDb, roleFromDb, sessionIDFromDb);
                }

            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }

    public String getUserRole(String sessionId) {
        String sql = "SELECT (role) from Users WHERE sessionID = ?";

        try (Connection connection = database.connect()) {
            PreparedStatement ps = connection.prepareStatement(sql);

            ps.setString(1, sessionId);

            ResultSet resultSet = ps.executeQuery();
            if (resultSet.next()) {
                return resultSet.getString("role");
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }

    public void updateSessionID(String email, String sessionID) {
        String sql = "UPDATE Users SET sessionID = ? WHERE email = ?";

        try (Connection connection = database.connect()) {
            PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            ps.setString(1, sessionID);
            ps.setString(2, email);

            ps.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }

    // Checks
    public boolean emailExists(String email) {
        String sql = "SELECT COUNT(*) FROM Users WHERE email = ?";

        try (Connection connection = database.connect()) {
            PreparedStatement ps = connection.prepareStatement(sql);

            ps.setString(1, email);

            ResultSet resultSet = ps.executeQuery();

            if (resultSet.next()) {
                return resultSet.getInt("COUNT(*)") > 0;
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return true;
    }

    public boolean validateSession(HttpSession session) {
        String sessionID = session.getId();

        String sql = "SELECT email, role, sessionID FROM Users WHERE sessionID = ?";
        try (Connection connection = database.connect()) {
            PreparedStatement ps = connection.prepareStatement(sql);

            ps.setString(1, sessionID);

            ResultSet resultSet = ps.executeQuery();
            if (resultSet.next()) {
                return sessionID.equals(resultSet.getString("sessionID"));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return false;
    }

    public byte[] getSalt() {
        byte[] salt = new byte[16];
        SecureRandom secureRandom = new SecureRandom();
        secureRandom.nextBytes(salt);
        return salt;
    }

    public String hashPassword(String password) {
        byte[] bcryptChars = BCrypt.withDefaults().hash(6, getSalt(), password.getBytes(StandardCharsets.UTF_8));

        return new String(bcryptChars, StandardCharsets.UTF_8);
    }

    public boolean matchHashedPassword(String password, String hash) {
        BCrypt.Result result = BCrypt.verifyer().verify(password.toCharArray(), hash);
        return result.verified;
    }
}
