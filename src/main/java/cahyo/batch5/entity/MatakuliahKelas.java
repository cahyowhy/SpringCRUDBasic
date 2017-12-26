package cahyo.batch5.entity;

import java.util.Date;

public class MatakuliahKelas {
    private int id;
    private String name;
    private Dosen dosen;
    private String room;
    private Matakuliah matakuliah;
    private Date createdAt;

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

    public Dosen getDosen() {
        return dosen;
    }

    public void setDosen(Dosen dosen) {
        this.dosen = dosen;
    }

    public String getRoom() {
        return room;
    }

    public void setRoom(String room) {
        this.room = room;
    }

    public Matakuliah getMatakuliah() {
        return matakuliah;
    }

    public void setMatakuliah(Matakuliah matakuliah) {
        this.matakuliah = matakuliah;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    @Override
    public String toString() {
        return "MatakuliahKelas{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", dosen=" + dosen +
                ", room='" + room + '\'' +
                ", matakuliah=" + matakuliah +
                ", createdAt=" + createdAt +
                '}';
    }
}
