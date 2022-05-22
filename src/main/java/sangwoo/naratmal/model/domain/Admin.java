package sangwoo.naratmal.model.domain;

import lombok.Getter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Getter
public class Admin {
    @Id
    @Column(name = "admin_id")
    private String id;
    private String password;
}
