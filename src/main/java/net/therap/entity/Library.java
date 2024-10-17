package net.therap.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author shahriarmohaiminul
 * @since 9/7/24
 */
@Entity
public class Library extends Persistence {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "library_seq_gen")
    @SequenceGenerator(name = "library_seq_gen", sequenceName = "library_seq", allocationSize = 1)
    private int id;

    private String name;

    private int updateCount;

    @Version
    private int version;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "book_id")
    private Book book;

    private Date updated;

    private String updatedBy;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getUpdateCount() {
        return updateCount;
    }

    public void setUpdateCount(int updateCount) {
        this.updateCount = updateCount;
    }

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public Date getUpdated() {
        return updated;
    }

    public void setUpdated(Date updated) {
        this.updated = updated;
    }

    public String getUpdatedBy() {
        return updatedBy;
    }

    public void setUpdatedBy(String updatedBy) {
        this.updatedBy = updatedBy;
    }

    public void increaseUpdateCount() {
        this.updateCount++;
    }

    public String isIssueExists() {

        if(this.getBook().getUpdateCount() != this.updateCount) {
            return "YES";
        }

        return "No";
    }
}
