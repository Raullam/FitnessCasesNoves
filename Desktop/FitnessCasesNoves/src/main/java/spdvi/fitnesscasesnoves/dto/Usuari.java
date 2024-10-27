package spdvi.fitnesscasesnoves.dto;


/**
 *
 * @author aleja
 */
public class Usuari {
    private int id;
    private String nom;
    private String email;
    private String passwordHash;
    private byte[] foto;
    private boolean Isinstructor;

    public Usuari(int id, String nom, String email, String passwordHash, boolean Isinstructor) {
        this.id = id;
        this.nom = nom;
        this.email = email;
        this.passwordHash = passwordHash;
        this.foto = null;
        this.Isinstructor = Isinstructor;
    }

    public Usuari(){}

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

    public String getPasswordHash() {
        return passwordHash;
    }

    public void setPasswordHash(String password) {
        this.passwordHash = password;
    }

    public byte[] getFoto() {
        return foto;
    }

    public void setFoto(byte[] foto) {
        this.foto = foto;
    }

    public boolean isInstructor() {
        return Isinstructor;
    }

    public void setIsinstructor(boolean Isinstructor) {
        this.Isinstructor = Isinstructor;
    }
    

   
}
