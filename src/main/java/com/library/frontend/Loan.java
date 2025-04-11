package com.library.frontend;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

class Loan {
    private IntegerProperty id;
    private StringProperty bookTitle;
    private StringProperty memberName;
    private StringProperty issueDate;
    private StringProperty returnDate;
    private StringProperty status;

    public Loan(int id, String bookTitle, String memberName, String issueDate, String returnDate, String status) {
        this.id = new SimpleIntegerProperty(id);
        this.bookTitle = new SimpleStringProperty(bookTitle);
        this.memberName = new SimpleStringProperty(memberName);
        this.issueDate = new SimpleStringProperty(issueDate);
        this.returnDate = new SimpleStringProperty(returnDate);
        this.status = new SimpleStringProperty(status);
    }

    public int getId() { return id.get(); }
    public void setId(int id) { this.id.set(id); }
    public IntegerProperty idProperty() { return id; }

    public String getBookTitle() { return bookTitle.get(); }
    public void setBookTitle(String bookTitle) { this.bookTitle.set(bookTitle); }
    public StringProperty bookTitleProperty() { return bookTitle; }

    public String getMemberName() { return memberName.get(); }
    public void setMemberName(String memberName) { this.memberName.set(memberName); }
    public StringProperty memberNameProperty() { return memberName; }

    public String getIssueDate() { return issueDate.get(); }
    public void setIssueDate(String issueDate) { this.issueDate.set(issueDate); }
    public StringProperty issueDateProperty() { return issueDate; }

    public String getReturnDate() { return returnDate.get(); }
    public void setReturnDate(String returnDate) { this.returnDate.set(returnDate); }
    public StringProperty returnDateProperty() { return returnDate; }

    public String getStatus() { return status.get(); }
    public void setStatus(String status) { this.status.set(status); }
    public StringProperty statusProperty() { return status; }
}
