package mapper;

import persistance.Database;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MeasurementMapper {
    private final Database database;

    public MeasurementMapper() {
        this.database = Database.getInstance();
    }

    public List<Integer> getLengthList(String sessionId) {
        String sql = "SELECT length FROM CarportValues";

        List<Integer> list = new ArrayList<>();

        try (Connection connection = database.connect()) {
            PreparedStatement ps = connection.prepareStatement(sql);

            ResultSet resultSet = ps.executeQuery();
            while (resultSet.next()) {
                list.add(resultSet.getInt("length"));
            }
            if (list.isEmpty()) {
                return null;
            }
            return list;

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }

    public List<Integer> getWidthList(String sessionId) {
        String sql = "SELECT width FROM CarportValues";

        List<Integer> list = new ArrayList<>();

        try (Connection connection = database.connect()) {
            PreparedStatement ps = connection.prepareStatement(sql);

            ResultSet resultSet = ps.executeQuery();
            while (resultSet.next()) {
                list.add(resultSet.getInt("length"));
            }
            if (list.isEmpty()) {
                return null;
            }
            return list;

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }

    public List<Integer> getShedLengthList(String sessionId) {
        String sql = "SELECT shed_length FROM CarportValues";

        List<Integer> list = new ArrayList<>();

        try (Connection connection = database.connect()) {
            PreparedStatement ps = connection.prepareStatement(sql);

            ResultSet resultSet = ps.executeQuery();
            while (resultSet.next()) {
                list.add(resultSet.getInt("length"));
            }
            if (list.isEmpty()) {
                return null;
            }
            return list;

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }

    public List<Integer> getShedWidthList(String sessionId) {
        String sql = "SELECT shed_width FROM CarportValues";

        List<Integer> list = new ArrayList<>();

        try (Connection connection = database.connect()) {
            PreparedStatement ps = connection.prepareStatement(sql);

            ResultSet resultSet = ps.executeQuery();
            while (resultSet.next()) {
                list.add(resultSet.getInt("length"));
            }
            if (list.isEmpty()) {
                return null;
            }
            return list;

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }

}
