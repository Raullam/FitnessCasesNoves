package spdvi.fitnesscasesnoves.dto;


/**
 *
 * @author aleja
 */
public class Usuari {
    private int id;
    private String nom;
    private String email;
    private String password;
    private byte[] foto;
    private boolean Isinstructor;

    public Usuari(int id, String nom, String email, String password, byte[] foto, boolean Isinstructor) {
        this.id = id;
        this.nom = nom;
        this.email = email;
        this.password = password;
        this.foto = foto;
        this.Isinstructor = Isinstructor;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public byte[] getFoto() {
        return foto;
    }

    public void setFoto(byte[] foto) {
        this.foto = foto;
    }

    public boolean isIsinstructor() {
        return Isinstructor;
    }

    public void setIsinstructor(boolean Isinstructor) {
        this.Isinstructor = Isinstructor;
    }

   
}
