package com.NomanSanjari.Chatter.controllers;

import com.NomanSanjari.Chatter.models.Session;
import com.NomanSanjari.Chatter.models.User;
import com.NomanSanjari.Chatter.repositories.SessionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/sessions")
public class SessionController {
    private final SessionRepository sessionRepository;

    /**
     * Creates a SessionController instance using the sessionRepository interface
     *
     * @param sessionRepository SessionRepository object created at initialization
     */
    @Autowired
    public SessionController(SessionRepository sessionRepository) {
        this.sessionRepository = sessionRepository;
    }

    /**
     * Creates a Session document in the MongoDB database
     *
     * @param session Data from request body, namely, session name
     *
     * @return Session object upon successful execution and HTTP code 201 CREATED
     */
    @PostMapping("/createSession")
    public Mono<ResponseEntity<Session>> createSession(@RequestBody Session session) {
        return sessionRepository.save(session)
                .map(savedSession -> new ResponseEntity<>(savedSession, HttpStatus.CREATED));
    }

    /**
     * Returns a Session document from the MongoDB database
     * Uses Session id stored in the browser upon creation to find the document
     *
     * @param id Data from request path, namely, id of the session
     *
     * @return User object upon successful execution HTTP code 302 FOUND
     */
    @GetMapping("/getSession/{id}")
    public Mono<ResponseEntity<Session>> getUser(@PathVariable String id) {
        return sessionRepository.findById(id)
                .map(readSession -> new ResponseEntity<>(readSession, HttpStatus.FOUND))
                .defaultIfEmpty(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    /**
     * Updates attributes of a Session document in the MongoDB database
     *
     * @param id Data from request path, namely, id of the session
     * @param user Data from request body, namely, new user
     *
     * @return Updated Session upon successful execution and HTTP code 200 OK
     */
    @PutMapping("/updateSession/{id}/addUserA")
    public Mono<ResponseEntity<Session>> updateSessionUserA(@PathVariable String id, @RequestBody User user) {
        return sessionRepository.findById(id)
                .flatMap(existingSession -> {
                    if (user.getUsername() != null) {
                        existingSession.setUserA(user);
                    }
                    return sessionRepository.save(existingSession);
                })
                .map(updatedSession -> new ResponseEntity<>(updatedSession, HttpStatus.OK))
                .defaultIfEmpty(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    /**
     * Updates attributes of a Session document in the MongoDB database
     *
     * @param id Data from request path, namely, id of the session
     * @param user Data from request body, namely, new user
     *
     * @return Updated Session upon successful execution and HTTP code 200 OK
     */
    @PutMapping("/updateSession/{id}/addUserB")
    public Mono<ResponseEntity<Session>> updateSessionUserB(@PathVariable String id, @RequestBody User user) {
        return sessionRepository.findById(id)
                .flatMap(existingSession -> {
                    if (user.getUsername() != null) {
                        existingSession.setUserA(user);
                    }
                    return sessionRepository.save(existingSession);
                })
                .map(updatedSession -> new ResponseEntity<>(updatedSession, HttpStatus.OK))
                .defaultIfEmpty(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    /**
     * Deletes a Session document from the MongoDB database
     *
     * @param id Data from request path, namely, id of the session
     * @return HttpStatus code, NO_CONTENT if success, NOT_FOUND if not successful
     */
    @DeleteMapping("/deleteSession/{id}")
    public Mono<ResponseEntity<Object>> deleteUser(@PathVariable String id) {
        return sessionRepository.findById(id)
                .map(deletedSession -> new ResponseEntity<>(HttpStatus.NO_CONTENT))
                .defaultIfEmpty(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
}
