package tracker.database;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import tracker.model.ToDo;
import tracker.model.User;

public class DBUtil {

	private static final String CREATE_USER_TABLE = "CREATE TABLE IF NOT EXISTS user ("
			+ "email VARCHAR PRIMARY KEY,"
			+ "password VARCHAR NOT NULL,"
			+ "firstname VARCHAR NOT NULL,"
			+ "lastname VARCHAR NOT NULL)";
	private static final String CREATE_TODO_TABLE = "CREATE TABLE IF NOT EXISTS todo ("
			+ "id IDENTITY PRIMARY KEY,"
			+ "title VARCHAR NOT NULL,"
			+ "description VARCHAR NOT NULL,"
			+ "startDate DATE NOT NULL,"
			+ "finishedDate Date,"
			+ "finished BOOLEAN DEFAULT FALSE,"
			+ "user VARCHAR NOT NULL,"
			+ "FOREIGN KEY (user) references user(email))";
	private static final String INSERT_TODO = "INSERT INTO todo (title, description, startDate, finishedDate, finished, user) VALUES (?, ?, ?, ?, ?, ?)";
	private static final String GET_ALL_UNFINISHED_TODOS_WITH_USER = "SELECT * FROM todo WHERE finished = FALSE AND user = ?";
	private static final String GET_ALL_FINISHED_TODOS_WITH_USER = "SELECT * FROM todo WHERE finished = TRUE AND user = ?";
	private static final String DELETE_TODO_WITH_ID = "DELETE FROM todo WHERE id = ?";
	private static final String UPDATE_TODO_WITH_ID = "UPDATE todo SET title = ?, description = ?, finishedDate = ?, finished = ? WHERE id = ?";
	private static final String UPDATE_TODO_SET_FINISHED_WITH_ID = "UPDATE todo SET finishedDate = ?, finished = TRUE WHERE id = ?";
	private static final String GET_USER_WITH_EMAIL_AND_PASSWORD = "SELECT email, firstname, lastname FROM user WHERE email LIKE ? AND password LIKE ?";
	private static final String INSERT_USER = "INSERT INTO user (email, password, firstname, lastname) VALUES (?, ?, ?, ?)";
	
	private DBUtil() {

	}

	private static void createToDoTable() {
		try {
			final Statement stmt = DBController.getInstance().openConnection().createStatement();
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
			final Statement stmt = DBController.getInstance().openConnection().createStatement();
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
		createToDoTable();
	}
	
	public static void insertToDo(ToDo todo) {
		try {
			final PreparedStatement ps = DBController.getInstance().openConnection().prepareStatement(INSERT_TODO);
			ps.setString(1, todo.getTitle());
			ps.setString(2, todo.getDescription());
			ps.setDate(3, Date.valueOf(todo.getStartDate()));
			if (todo.isFinished()) {
				ps.setDate(4, Date.valueOf(todo.getFinishedDate()));
			} else {
				ps.setNull(4, Types.DATE);
			}
			ps.setBoolean(5, todo.isFinished());
			ps.setString(6, todo.getUser().getEmail());
			ps.executeUpdate();
			ps.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBController.getInstance().closeConnenction();
		}
	}

	public static List<ToDo> getAllUnfinishedTodosByUser(User user) {
		final List<ToDo> data = new ArrayList<>();
		try {
			final PreparedStatement ps = DBController.getInstance().openConnection().prepareStatement(GET_ALL_UNFINISHED_TODOS_WITH_USER);
			ps.setString(1, user.getEmail());
			final ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				ToDo.Builder builder = new ToDo.Builder(rs.getLong("id"), rs.getString("title"), rs.getDate("startDate").toLocalDate(), user)
						.setDescription(rs.getString("description"))
						.setFinishedDate(Objects.nonNull(rs.getDate("finishedDate")) ? rs.getDate("finishedDate").toLocalDate() : null);
				data.add(builder.build());
			}
			ps.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBController.getInstance().closeConnenction();
		}
		System.out.println(data);
		return data;
	}
	
	public static List<ToDo> getAllFinishedTodosByUser(User user) {
		final List<ToDo> data = new ArrayList<>();
		try {
			final PreparedStatement ps = DBController.getInstance().openConnection().prepareStatement(GET_ALL_FINISHED_TODOS_WITH_USER);
			ps.setString(1, user.getEmail());
			final ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				ToDo.Builder builder = new ToDo.Builder(rs.getLong("id"), rs.getString("title"), rs.getDate("startDate").toLocalDate(), user)
						.setDescription(rs.getString("description"))
						.setFinishedDate(Objects.nonNull(rs.getDate("finishedDate")) ? rs.getDate("finishedDate").toLocalDate() : null);
				data.add(builder.build());
			}
			ps.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBController.getInstance().closeConnenction();
		}
		return data;
	}

	public static void deleteTodo(ToDo todo) {
		try {
			final PreparedStatement ps = DBController.getInstance().openConnection().prepareStatement(DELETE_TODO_WITH_ID);
			ps.setLong(1, todo.getId());
			ps.executeUpdate();
			ps.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBController.getInstance().closeConnenction();
		}
	}

	public static void updateTodo(ToDo todo) {
		try {
			final PreparedStatement ps = DBController.getInstance().openConnection().prepareStatement(UPDATE_TODO_WITH_ID);
			ps.setString(1, todo.getTitle());
			ps.setString(2, todo.getDescription());
			if (todo.isFinished()) {
				ps.setDate(3, Date.valueOf(todo.getFinishedDate()));
			} else {
				ps.setNull(3, Types.DATE);
			}
			ps.setBoolean(4, todo.isFinished());
			ps.setLong(5, todo.getId());
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
			final PreparedStatement ps = DBController.getInstance().openConnection().prepareStatement(INSERT_USER);
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
			final PreparedStatement ps = DBController.getInstance().openConnection().prepareStatement(GET_USER_WITH_EMAIL_AND_PASSWORD);
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
