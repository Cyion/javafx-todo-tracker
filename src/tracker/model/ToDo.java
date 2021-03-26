package tracker.model;

import java.time.LocalDate;

public class ToDo {
	private long id;
	private String title;
	private String description;
	private LocalDate startDate;
	private LocalDate finishedDate;
	private boolean finished;
	private User user;
	
	public ToDo(long id, String title, String description, LocalDate startDate, LocalDate finishedDate, boolean finished, User user) {
		this(id, title, description, startDate, user);
		this.finishedDate = finishedDate;
		this.finished = finished;	
	}
	
	public ToDo(long id, String title, String description, LocalDate startDate, User user) {
		this.id = id;
		this.title = title;
		this.description = description;
		this.startDate = startDate;
		this.user = user;
	}
	
	public long getId() {
		return this.id;
	}
	
	public String getTitle() {
		return this.title;
	}
	
	public String getDescription() {
		return this.description;
	}
	
	public LocalDate getStartDate() {
		return this.startDate;
	}
	
	public LocalDate getFinishedDate() {
		return this.finishedDate;
	}
	
	public boolean isFinished() {
		return this.finished;
	}
	
	public User getUser() {
		return this.user;
	}

	@Override
	public String toString() {
		return this.getTitle();
	}
}
