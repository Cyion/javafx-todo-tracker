package tracker.util;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import tracker.controller.DBController;
import tracker.model.ToDo;
import tracker.model.User;

public class DBUtil {

	private static final String CREATE_USER_TABLE = "CREATE TABLE IF NOT EXISTS user ("
			+ "email VARCHAR PRIMARY KEY,"
			+ "password VARCHAR NOT NULL,"
			+ "firstname VARCHAR NOT NULL,"
			+ "lastname VARCHAR NOT NULL)";
	private static final String CREATE_TODO_TABLE = "CREATE TABLE IF NOT EXISTS todos (" + "id IDENTITY PRIMARY KEY,"
			+ "title VARCHAR NOT NULL," + "description VARCHAR," + "startDate DATE NOT NULL," + "endDate Date,"
			+ "finished BOOLEAN DEFAULT FALSE,"
			+ "user VARCHAR NOT NULL,"
			+ "FOREIGN KEY (user) references user(email))";
	private static final String INSERT_TODO = "INSERT INTO todos (title, description, startDate, user) VALUES (?, ?, ?, ?)";
	private static final String SELECT_ALL_UNFINISHED_TODOS = "SELECT * FROM todos WHERE finished = FALSE AND user = ?";
	private static final String SELECT_ALL_FINISHED_TODOS = "SELECT * FROM todos WHERE finished = TRUE AND user = ?";
	private static final String DELETE_FROM_TODOS = "DELETE FROM todos WHERE id = ?";
	private static final String UPDATE_TODO = "UPDATE todos SET title = ?, description = ? WHERE id = ?";
	private static final String UPDATE_TODO_SET_FINISHED = "UPDATE todos SET endDate = ?, finished = TRUE WHERE id = ?";
	private static final String SELECT_USER = "SELECT email, firstname, lastname FROM user WHERE email LIKE ? AND password LIKE ?";
	private static final String INSERT_USER = "INSERT INTO user (email, password, firstname, lastname) VALUES (?, ?, ?, ?)";
	
	private DBUtil() {

	}

	private static void createToDosTable() {
		try {
			Statement stmt = DBController.getInstance().openConnection().createStatement();
			stmt.executeUpdate(CREATE_TODO_TABLE);
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBController.getInstance().closeConnenction();
		}
	}
	
	private static void createUserTable() {
		try {
			Statement stmt = DBController.getInstance().openConnection().createStatement();
			stmt.executeUpdate(CREATE_USER_TABLE);
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBController.getInstance().closeConnenction();
		}
	}

	public static void createTables() {
		createUserTable();
		createToDosTable();
	}
	
	public static void insertToDo(String title, String description, LocalDate startDate, User user) {
		try {
			PreparedStatement ps = DBController.getInstance().openConnection().prepareStatement(INSERT_TODO);
			ps.setString(1, title);
			ps.setString(2, description);
			ps.setDate(3, Date.valueOf(startDate));
			ps.setString(4, user.getEmail());
			ps.executeUpdate();
			ps.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBController.getInstance().closeConnenction();
		}
	}

	public static ObservableList<ToDo> getAllUnfinishedTodosByUser(User user) {
		ObservableList<ToDo> data = FXCollections.observableArrayList();
		try {
			PreparedStatement ps = DBController.getInstance().openConnection().prepareStatement(SELECT_ALL_UNFINISHED_TODOS);
			ps.setString(1, user.getEmail());
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				ToDo todo = new ToDo(rs.getLong("id"), rs.getString("title"), rs.getString("description"),
						rs.getDate("startDate").toLocalDate(),
						rs.getDate("endDate") != null ? rs.getDate("endDate").toLocalDate() : null,
						rs.getBoolean("finished"), user);
				data.add(todo);
			}
			ps.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBController.getInstance().closeConnenction();
		}
		return data;
	}
	
	public static ObservableList<ToDo> getAllFinishedTodosByUser(User user) {
		ObservableList<ToDo> data = FXCollections.observableArrayList();
		try {
			PreparedStatement ps = DBController.getInstance().openConnection().prepareStatement(SELECT_ALL_FINISHED_TODOS);
			ps.setString(1, user.getEmail());
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				ToDo todo = new ToDo(rs.getLong("id"), rs.getString("title"), rs.getString("description"),
						rs.getDate("startDate").toLocalDate(),
						rs.getDate("endDate") != null ? rs.getDate("endDate").toLocalDate() : null,
						rs.getBoolean("finished"), user);
				data.add(todo);
			}
			ps.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBController.getInstance().closeConnenction();
		}
		return data;
	}

	public static void deleteTodo(long id) {
		try {
			PreparedStatement ps = DBController.getInstance().openConnection().prepareStatement(DELETE_FROM_TODOS);
			ps.setLong(1, id);
			ps.executeUpdate();
			ps.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBController.getInstance().closeConnenction();
		}
	}
	
	public static void updateTodo(long id, String title, String description) {
		try {
			PreparedStatement ps = DBController.getInstance().openConnection().prepareStatement(UPDATE_TODO);
			ps.setString(1, title);
			ps.setString(2, description);
			ps.setLong(3, id);
			ps.executeUpdate();
			ps.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBController.getInstance().closeConnenction();
		}
	}
	
	public static void updateTodoSetFinished(long id, LocalDate finishedDate) {
		try {
			PreparedStatement ps = DBController.getInstance().openConnection().prepareStatement(UPDATE_TODO_SET_FINISHED);
			ps.setDate(1, Date.valueOf(finishedDate));
			ps.setLong(2, id);
			ps.executeUpdate();
			ps.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBController.getInstance().closeConnenction();
		}
	}
	
	public static void insertUser(String email, String password, String firstname, String lastname) {
		try {
			PreparedStatement ps = DBController.getInstance().openConnection().prepareStatement(INSERT_USER);
			ps.setString(1, email);
			ps.setString(2, password);
			ps.setString(3, firstname);
			ps.setString(4, lastname);
			ps.executeUpdate();
			ps.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBController.getInstance().closeConnenction();
		}
	}

	public static void insertUser(User user, String password) {
		try {
			PreparedStatement ps = DBController.getInstance().openConnection().prepareStatement(INSERT_USER);
			ps.setString(1, user.getEmail());
			ps.setString(2, password);
			ps.setString(3, user.getFirstname());
			ps.setString(4, user.getLastname());
			ps.executeUpdate();
			ps.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBController.getInstance().closeConnenction();
		}
	}
	
	public static User getUser(String email, String password) {
		try {
			PreparedStatement ps = DBController.getInstance().openConnection().prepareStatement(SELECT_USER);
			ps.setString(1, email);
			ps.setString(2, password);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				return new User(rs.getString("email"), rs.getString("firstname"), rs.getString("lastname"));
			}
			ps.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBController.getInstance().closeConnenction();
		}
		return null;
	}
}
