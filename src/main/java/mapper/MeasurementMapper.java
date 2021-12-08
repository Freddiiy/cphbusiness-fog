package mapper;

import model.Measurement;
import persistance.Database;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MeasurementMapper {
    private final Database database;

    public MeasurementMapper() {
        this.database = Database.getInstance();
    }

    public List<Integer> getLengthList() {
        String sql = "SELECT length FROM CarportValues";

        List<Integer> list = new ArrayList<>();

        try (Connection connection = database.connect()) {
            PreparedStatement ps = connection.prepareStatement(sql);

            ResultSet resultSet = ps.executeQuery();
            while (resultSet.next() && !resultSet.wasNull()) {
                int length = resultSet.getInt("length");
                if (length != 0) {
                    list.add(length);
                }
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

    public List<Integer> getWidthList() {
        String sql = "SELECT width FROM CarportValues";

        List<Integer> list = new ArrayList<>();

        try (Connection connection = database.connect()) {
            PreparedStatement ps = connection.prepareStatement(sql);

            ResultSet resultSet = ps.executeQuery();
            while (resultSet.next() && !resultSet.wasNull()) {
                int width = resultSet.getInt("width");
                if (width != 0) {
                    list.add(width);
                }
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

    public List<Integer> getShedLengthList() {
        String sql = "SELECT shed_length FROM CarportValues";

        List<Integer> list = new ArrayList<>();

        try (Connection connection = database.connect()) {
            PreparedStatement ps = connection.prepareStatement(sql);

            ResultSet resultSet = ps.executeQuery();
            while (resultSet.next() && !resultSet.wasNull()) {
                int shedLength = resultSet.getInt("shed_length");
                if (shedLength != 0) {
                    list.add(shedLength);
                }
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

    public List<Integer> getShedWidthList() {
        String sql = "SELECT shed_width FROM CarportValues";

        List<Integer> list = new ArrayList<>();

        try (Connection connection = database.connect()) {
            PreparedStatement ps = connection.prepareStatement(sql);

            ResultSet resultSet = ps.executeQuery();
            while (resultSet.next() && !resultSet.wasNull()) {
                int shedWidth = resultSet.getInt("shed_width");
                if (shedWidth != 0) {
                    list.add(shedWidth);
                }
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

    public Measurement getAllMeasurement() {
       return new Measurement(this.getLengthList(), this.getWidthList(), this.getShedLengthList(), this.getShedWidthList());
    }

}
