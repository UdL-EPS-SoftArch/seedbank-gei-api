package cat.udl.eps.softarch.demo.domain;


import javax.persistence.*;

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

}