package ua.nure.ki.ytretiakov.unigraph.data.model;

import javax.persistence.*;
import java.util.Objects;

@MappedSuperclass
public abstract class IDEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(unique = true)
    protected Long id;

    public IDEntity() {

    }

    public Long getId() {
        return id;
    }

    public void setId(final Long id) {
        this.id = id;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (!(o instanceof IDEntity)) return false;
        IDEntity entity = (IDEntity) o;
        return getId().equals(entity.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }
}
