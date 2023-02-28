package com.NomanSanjari.Chatter.controllers;

import com.NomanSanjari.Chatter.models.User;
import com.NomanSanjari.Chatter.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/users")
public class UserController {
    private final UserRepository userRepository;

    /**
     * Creates a UserController instance using the userRepository interface
     *
     * @param userRepository UserRepository object created at initialization
     */
    @Autowired
    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    /**
     * Creates a User document in the MongoDB database
     *
     * @param user Data from request body, namely, username
     *
     * @return User object upon successful execution and HTTP code 201 CREATED
     */
    @PostMapping("/createUser")
    public Mono<ResponseEntity<User>> createUser(@RequestBody User user) {
        return userRepository.save(user)
                .map(savedUser -> new ResponseEntity<>(savedUser, HttpStatus.CREATED));
    }

    /**
     * Returns a User document from the MongoDB database
     * Uses User id stored in the browser upon creation to find the document
     *
     * @param id Data from request path, namely, id of the user
     *
     * @return User object upon successful execution HTTP code 302 FOUND
     */
    @GetMapping("/getUser/{id}")
    public Mono<ResponseEntity<User>> getUser(@PathVariable String id) {
        return userRepository.findById(id)
                .map(readUser -> new ResponseEntity<>(readUser, HttpStatus.FOUND))
                .defaultIfEmpty(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    /**
     * Updates attributes of a User document in the MongoDB database
     *
     * @param id Data from request path, namely, id of the user
     * @param user Data from request body, namely, new username
     *
     * @return Updated User object upon successful execution and HTTP code 200 OK
     */
    @PutMapping("/updateUser/{id}")
    public Mono<ResponseEntity<User>> updateUser(@PathVariable String id, @RequestBody User user) {
        return userRepository.findById(id)
                .flatMap(existingUser -> {
                    if (user.getUsername() != null) {
                        existingUser.setUsername(user.getUsername());
                    }
                    return userRepository.save(existingUser);
                })
                .map(updatedUser -> new ResponseEntity<>(updatedUser, HttpStatus.OK))
                .defaultIfEmpty(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    /**
     * Deletes a User document from the MongoDB database
     *
     * @param id Data from request path, namely, id of the user
     * @return HttpStatus code, NO_CONTENT if success, NOT_FOUND if not successful
     */
    @DeleteMapping("/deleteUser/{id}")
    public Mono<ResponseEntity<Object>> deleteUser(@PathVariable String id) {
        return userRepository.findById(id)
                .map(deletedUser -> new ResponseEntity<>(HttpStatus.NO_CONTENT))
                .defaultIfEmpty(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
}
