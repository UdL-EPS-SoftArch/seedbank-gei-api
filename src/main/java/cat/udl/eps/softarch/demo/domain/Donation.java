package cat.udl.eps.softarch.demo.domain;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Donation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

//    @ManyToOne
//    @JoinColumn(name = "donor_id")
//    private Donor donor;

//    @ManyToOne
//    @JoinColumn(name = "batch_id")
//    private Batch batch;

    public Donation() {
    }

//    public Donation(Donor donor, Batch batch) {
//        this.donor = donor;
//        this.batch = batch;
//    }

    // getters and setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

//    public Donor getDonor() {
//        return donor;
//    }
//
//    public void setDonor(Donor donor) {
//        this.donor = donor;
//    }
//
//    public Batch getBatch() {
//        return batch;
//    }
//
//    public void setBatch(Batch batch) {
//        this.batch = batch;
//    }

}