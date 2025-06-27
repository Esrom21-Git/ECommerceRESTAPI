package org.yearup.data.mysql;

import org.yearup.models.Profile;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public abstract class MySqlDaoBase
{
    private DataSource dataSource;

    public MySqlDaoBase(DataSource dataSource)
    {
        this.dataSource = dataSource;
    }

    protected Connection getConnection() throws SQLException
    {
        return dataSource.getConnection();
    }

    // Helper method to close resources safely
    protected void closeResources(Connection connection, PreparedStatement statement, ResultSet resultSet)
    {
        if (resultSet != null) {
            try {
                resultSet.close();
            } catch (SQLException e) {
                // Log error but don't throw to avoid masking original exception
                System.err.println("Error closing ResultSet: " + e.getMessage());
            }
        }

        if (statement != null) {
            try {
                statement.close();
            } catch (SQLException e) {
                System.err.println("Error closing PreparedStatement: " + e.getMessage());
            }
        }

        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                System.err.println("Error closing Connection: " + e.getMessage());
            }
        }
    }

    // Overloaded helper method for cases without ResultSet
    protected void closeResources(Connection connection, PreparedStatement statement)
    {
        closeResources(connection, statement, null);
    }

    // Overloaded helper method for cases with only Connection
    protected void closeResources(Connection connection)
    {
        closeResources(connection, null, null);
    }

    // Helper method to execute a query and return the result set
    protected ResultSet executeQuery(String sql, Object... parameters) throws SQLException
    {
        Connection connection = getConnection();
        PreparedStatement statement = connection.prepareStatement(sql);

        // Set parameters if provided
        for (int i = 0; i < parameters.length; i++) {
            statement.setObject(i + 1, parameters[i]);
        }

        return statement.executeQuery();
    }

    // Helper method to execute an update/insert/delete statement
    protected int executeUpdate(String sql, Object... parameters) throws SQLException
    {
        Connection connection = null;
        PreparedStatement statement = null;

        try {
            connection = getConnection();
            statement = connection.prepareStatement(sql);

            // Set parameters if provided
            for (int i = 0; i < parameters.length; i++) {
                statement.setObject(i + 1, parameters[i]);
            }

            return statement.executeUpdate();
        } finally {
            closeResources(connection, statement);
        }
    }

    // Helper method to execute an insert and return generated keys
    protected int executeInsertWithGeneratedKeys(String sql, Object... parameters) throws SQLException
    {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet generatedKeys = null;

        try {
            connection = getConnection();
            statement = connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);

            // Set parameters if provided
            for (int i = 0; i < parameters.length; i++) {
                statement.setObject(i + 1, parameters[i]);
            }

            statement.executeUpdate();
            generatedKeys = statement.getGeneratedKeys();

            if (generatedKeys.next()) {
                return generatedKeys.getInt(1);
            } else {
                throw new SQLException("Creating record failed, no ID obtained.");
            }
        } finally {
            closeResources(connection, statement, generatedKeys);
        }
    }

    // Getter for DataSource (if needed by subclasses)
    protected DataSource getDataSource()
    {
        return dataSource;
    }

    public abstract Profile getByUserId(int userId);

    public abstract Profile update(int userId, Profile profile);

    public abstract void delete(int userId);
}
