package cat.udl.eps.softarch.demo.domain;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.Collection;
import java.util.Collections;

@Entity
@DiscriminatorValue("donor")
public class Donor extends User {

    @OneToMany(mappedBy = "donor")
    private Collection<Donation> donations;


    public Collection<Donation> getDonations() {
        return Collections.unmodifiableCollection(this.donations);
    }

    // TODO: Delete this after the donation pull request is done
    @Entity
    class Donation {
        @Id
        private Long id;

        public void setId(Long id) {
            this.id = id;
        }

        public Long getId() {
            return id;
        }
    }

}
