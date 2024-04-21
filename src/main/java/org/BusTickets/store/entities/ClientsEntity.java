package org.BusTickets.store.entities;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.proxy.HibernateProxy;

import java.util.Objects;
import java.util.Set;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "Clients")
public class ClientsEntity extends UsersEntity{

    @Column(nullable = false)
    String lastName;
    @Column(nullable = false)
    String firstName;
    String patronymic;
    @Column(unique = true,nullable = false)
    String email;
    @Column(nullable = false)
    String phone;
    @OneToMany(cascade = CascadeType.ALL)
    private Set<TicketsEntity> tickets;
    @OneToMany(cascade = CascadeType.ALL)
    private Set<OrdersEntity> orders;

    @Override
    public final boolean equals(Object o) {
        if (this == o) return true;
        if (o == null) return false;
        Class<?> oEffectiveClass = o instanceof HibernateProxy ? ((HibernateProxy) o).getHibernateLazyInitializer().getPersistentClass() : o.getClass();
        Class<?> thisEffectiveClass = this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass() : this.getClass();
        if (thisEffectiveClass != oEffectiveClass) return false;
        ClientsEntity that = (ClientsEntity) o;
        return getId() != null && Objects.equals(getId(), that.getId());
    }

    @Override
    public final int hashCode() {
        return this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass().hashCode() : getClass().hashCode();
    }
}
