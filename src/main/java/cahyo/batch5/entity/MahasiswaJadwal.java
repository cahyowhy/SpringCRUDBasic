package cahyo.batch5.entity;

public class MahasiswaJadwal {
    private int id;
    private Mahasiswa mahasiswa;
    private MatakuliahKelas matakuliahKelas;
    private Dosen dosen;
    private Matakuliah matakuliah;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Mahasiswa getMahasiswa() {
        return mahasiswa;
    }

    public void setMahasiswa(Mahasiswa mahasiswa) {
        this.mahasiswa = mahasiswa;
    }

    public MatakuliahKelas getMatakuliahKelas() {
        return matakuliahKelas;
    }

    public void setMatakuliahKelas(MatakuliahKelas matakuliahKelas) {
        this.matakuliahKelas = matakuliahKelas;
    }

    public Dosen getDosen() {
        return dosen;
    }

    public void setDosen(Dosen dosen) {
        this.dosen = dosen;
    }

    public Matakuliah getMatakuliah() {
        return matakuliah;
    }

    public void setMatakuliah(Matakuliah matakuliah) {
        this.matakuliah = matakuliah;
    }
}
