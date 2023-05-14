package com.stc.assignment.model;


import jakarta.persistence.*;
import java.util.Arrays;
import java.util.Objects;

@Entity
public class File {
    @Id
    @Column(name = "id")
    @SequenceGenerator(
            name = "file_sequence",
            sequenceName = "file_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "file_sequence"
    )
    private Long id;

    @Column(columnDefinition = "bytea")
    private byte[] file;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "item_id", referencedColumnName = "id")
    private Item item;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public byte[] getFile() {
        return file;
    }

    public void setFile(byte[] file) {
        this.file = file;
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public File(byte[] file, Item item) {
        this.file = file;
        this.item = item;
    }

    public File() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        File file1 = (File) o;
        return Objects.equals(id, file1.id) && Arrays.equals(file, file1.file) && Objects.equals(item, file1.item);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(id, item);
        result = 31 * result + Arrays.hashCode(file);
        return result;
    }

    @Override
    public String toString() {
        return "File{" +
                "id=" + id +
                ", file=" + Arrays.toString(file) +
                ", item=" + item +
                '}';
    }
}
