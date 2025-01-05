package org.example.studyregistry;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Task extends Registry{
  private String title;
  private String description;
  private String author;
  private LocalDateTime date;

  public Task(String title, String description, String author, LocalDateTime date) {
    this.title = title;
    this.name = title;
    this.description = description;
    this.author = author;
    this.date = date;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
    this.name = title; // Update name field as well when title is changed.
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public String getAuthor() {
    return author;
  }

  public void setAuthor(String author) {
    this.author = author;
  }

  public LocalDateTime getDate() {
    return date;
  }

  public void setDate(LocalDateTime date) {
    this.date = date;
  }

  public void setTitleAndDescription(String newTitle, String newDescription) {
    this.title = newTitle;
    this.description = newDescription;
   }

  // Added method to format date in a human-readable way
  public String getFormattedDate() {
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
    return formatter.format(date);
  }

  public boolean isOverdue() {
    LocalDateTime now = LocalDateTime.now();
    return this.date.isBefore(now);
  }

  // Added toString method to provide a more informative representation of the Task object
  @Override
  public String toString() {
    return "Task{" +
        "title='" + title + '\'' +
        ", description='" + description + '\'' +
        ", author='" + author + '\'' +
        ", date=" + date +
        '}';
  }
}