package tracker.model;

import java.time.LocalDate;

public class ToDo {
	private final long id;
	private String title;
	private String description;
	private final LocalDate startDate;
	private LocalDate finishedDate;
	private boolean finished;
	private final User user;
	
	public ToDo(long id, String title, String description, LocalDate startDate, LocalDate finishedDate, User user) {
		this(id, title, description, startDate, user);
		if (finishedDate != null) {
			this.finishedDate = finishedDate;
			this.finished = true;
		}
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

	public void setTitle(String title) {
		if (title != null && !title.isEmpty()) {
			this.title = title;
		}
	}

	public void setDescription(String description) {
		if (description != null) {
			this.description = description;
		}
	}

	public void setFinished() {
		if (!this.finished) {
			this.finished = true;
			this.finishedDate = LocalDate.now();
		}
	}

	@Override
	public String toString() {
		return this.getTitle();
	}
}
