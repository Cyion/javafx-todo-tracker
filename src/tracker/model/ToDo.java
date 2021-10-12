package tracker.model;

import javafx.beans.property.*;
import java.time.LocalDate;
import java.time.Period;
import java.util.Objects;

public class ToDo {
	private final ReadOnlyLongWrapper id = new ReadOnlyLongWrapper(this, "id", -1);
	private final ReadOnlyStringWrapper title = new ReadOnlyStringWrapper(this, "title", "");
	private final ReadOnlyStringWrapper description = new ReadOnlyStringWrapper(this, "description", "");
	private final ReadOnlyObjectWrapper<LocalDate> startDate = new ReadOnlyObjectWrapper<>(this, "startDate", null);
	private final ReadOnlyObjectWrapper<LocalDate> finishedDate = new ReadOnlyObjectWrapper<>(this, "finishedDate", null);
	private final ReadOnlyBooleanWrapper finished = new ReadOnlyBooleanWrapper(this, "finished", false);
	private final ReadOnlyObjectWrapper<User> user = new ReadOnlyObjectWrapper<>(this, "user", null);
	
	private ToDo(Builder builder) {
		this.id.setValue(builder.id);
		this.title.setValue(builder.title);
		this.description.setValue(builder.description);
		this.startDate.setValue(builder.startDate);
		this.finishedDate.setValue(builder.finishedDate);
		this.finished.setValue(builder.finished);
		this.user.setValue(builder.user);
	}

	public ReadOnlyLongProperty idProperty() {
		return this.id.getReadOnlyProperty();
	}
	
	public long getId() {
		return this.id.getValue();
	}

	public ReadOnlyStringProperty titleProperty() {
		return this.title.getReadOnlyProperty();
	}
	
	public String getTitle() {
		return this.title.getValue();
	}

	public void setTitle(String title) {
		Objects.requireNonNull(title);
		this.title.setValue(title);
	}

	public ReadOnlyStringProperty descriptionProperty() {
		return this.description.getReadOnlyProperty();
	}
	
	public String getDescription() {
		return this.description.getValue();
	}

	public void setDescription(String description) {
		Objects.requireNonNull(description);
		this.description.setValue(description);
	}

	public ReadOnlyObjectProperty<LocalDate> startDateProperty() {
		return this.startDate.getReadOnlyProperty();
	}
	
	public LocalDate getStartDate() {
		return this.startDate.getValue();
	}

	public ReadOnlyObjectProperty<LocalDate> finishedDateProperty() {
		return this.finishedDate.getReadOnlyProperty();
	}

	public LocalDate getFinishedDate() {
		return this.finishedDate.getValue();
	}

	public void setFinishedDate(LocalDate finishedDate) {
		if (Objects.nonNull(finishedDate)) {
			if (Period.between(this.startDate.getValue(), finishedDate).isNegative()) {
				throw new IllegalArgumentException("finishedDate must be after startDate");
			}
			this.finished.set(true);
		} else {
			this.finished.set(false);
		}
		this.finishedDate.set(finishedDate);
	}

	public ReadOnlyBooleanProperty finishedProperty() {
		return this.finished.getReadOnlyProperty();
	}

	public boolean isFinished() {
		return this.finished.getValue();
	}


	public ReadOnlyObjectProperty<User> userProperty() {
		return this.user.getReadOnlyProperty();
	}
	
	public User getUser() {
		return this.user.getValue();
	}

	@Override
	public String toString() {
		return this.getTitle();
	}

	public static class Builder {
		private final long id;
		private final String title;
		private String description = "";
		private final LocalDate startDate;
		private LocalDate finishedDate;
		private boolean finished;
		private final User user;

		public Builder(long id, String title, LocalDate startDate, User user) {
			Objects.requireNonNull(title, "title");
			Objects.requireNonNull(startDate, "startDate");
			Objects.requireNonNull(user, "user");
			this.id = id;
			this.title = title;
			this.startDate = startDate;
			this.user = user;
		}

		public Builder setDescription(String description) {
			Objects.requireNonNull(description, "description");
			this.description = description;
			return this;
		}

		public Builder setFinishedDate(LocalDate finishedDate) {
			if (Objects.nonNull(finishedDate)) {
				if (Period.between(this.startDate, finishedDate).isNegative()) {
					throw new IllegalArgumentException("finishedDate must be after startDate");
				}
				this.finished = true;
			} else {
				this.finished = false;
			}
			this.finishedDate = finishedDate;
			return this;
		}

		public ToDo build() {
			return new ToDo(this);
		}
	}
}
