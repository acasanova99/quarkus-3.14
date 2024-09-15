package com.example.repository;

import com.example.entity.User;
import io.smallrye.mutiny.Uni;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.hibernate.reactive.mutiny.Mutiny;

import java.util.List;

@ApplicationScoped
public class UserRepository {
    @Inject
    Mutiny.SessionFactory sf;

    public Uni<User> create(User user) {
        return sf.withTransaction((s, t) -> s.persist(user))
                .replaceWith(sf.withTransaction((s, t) -> s.find(User.class, user.getName())));
    }

    public Uni<User> findByName(String name) {
        return sf.withTransaction((s, t) -> s.find(User.class, name));
    }

    public Uni<List<User>> listAll() {
        return sf.withSession(s -> s.createNamedQuery("User.listAll", User.class).getResultList());
    }

    public Uni<Integer> delete(final String name) {
        return sf.withTransaction((s, t) -> s.createNamedQuery("User.delete").setParameter("name", name).executeUpdate());
    }
}
