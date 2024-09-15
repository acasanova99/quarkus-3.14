package com.example.service;

import com.example.dto.UserDto;
import com.example.mapper.UserMapper;
import com.example.repository.UserRepository;
import io.smallrye.mutiny.Uni;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Path("/user")
@Produces(MediaType.APPLICATION_JSON)
@ApplicationScoped
@RequiredArgsConstructor
public class UserResource {
    private final UserRepository userRepository;
    private final UserMapper userMapper;

    @GET
    @Path("/all")
    public Uni<List<UserDto>> getAll() {
        return userRepository.listAll()
                .map(listUser -> listUser.stream()
                        .map(userMapper::fromEntity)
                        .toList()
                );
    }
}
