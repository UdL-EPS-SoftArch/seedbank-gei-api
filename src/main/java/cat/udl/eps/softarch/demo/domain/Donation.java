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

    //TODO: Uncomment when Donor and Batch are implemented

//    @ManyToOne
//    @JoinColumn(name = "donor_id")
//    private Donor donor;

//    @ManyToOne
//    @JoinColumn(name = "batch_id")
//    private Batch batch;

//    public Donation(Donor donor, Batch batch) {
//        this.donor = donor;
//        this.batch = batch;
//    }

    public Long getId() {
        return id;
    }

//    public Donor getDonor() {
//        return donor;
//    }
//
//    public Batch getBatch() {
//        return batch;
//    }
//

}