package com.marting.store.controller;

import com.marting.store.exception.ErrorResponse;
import com.marting.store.entity.abstractEntities.BaseEntity;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
import javax.validation.Valid;
import java.util.List;

public interface RESTController<T extends BaseEntity> {

    /**
     * GET request for returning the list of T entities
     *
     * @return List of T entities.
     */
    @GetMapping("/")
    @ResponseBody
    List<T> get();

    /**
     * GET request for returning a T entity by its ID
     * @param id The ID of the Entity
     * @return T entity if the entity is found
     * @throws EntityNotFoundException if the T entity is not found
     */
    @GetMapping( "/{id}")
    @ResponseBody
    T get(@PathVariable(value="id") Long id) throws EntityNotFoundException;

    /**
     * POST request for creating a new Entity of type T
     * @param newEntity new Entity of type T.
     * @return the new entity with an assigned ID
     * @throws MethodArgumentNotValidException if the entity field validation fails
     */
    @PostMapping("/")
    @ResponseBody
    T create(@Valid @RequestBody T newEntity) throws MethodArgumentNotValidException;

    /**
     * PUT request for updating an Entity of type T
     * @param id id of the entity to be updated
     * @param updatedEntity entity POJO for being updated
     * @return the entity updated
     * @throws EntityNotFoundException if it is not found an entity with the given ID
     */
    @PutMapping("/{id}")
    @ResponseBody
    T update(@PathVariable("id") Long id, @Validated @RequestBody T updatedEntity) throws EntityNotFoundException;

    /**
     * DELETE request for deleting an Entity of type T
     * @param id id of the entity to be deleted
     */
    @DeleteMapping("/{id}")
    void delete(@PathVariable("id") Long id);

    /**
     * Exception handler for EntityNotFoundException
     * @param exc The EntityNotFoundException triggered
     * @return Error Response with HttpStatus.NOT_FOUND
     */
    @ExceptionHandler({EntityNotFoundException.class})
    @ResponseBody
    static ResponseEntity<ErrorResponse> handleResourceNotFound(EntityNotFoundException exc){
        ErrorResponse error = new ErrorResponse();
        error.setStatus(HttpStatus.NOT_FOUND.value());
        error.setTimestamp(System.currentTimeMillis());
        error.setMessage("Entity not found -- " + exc.getMessage());
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }

    /**
     * Exception handler for EmptyResultDataAccessException
     * @param exc The EntityNotFoundException triggered
     * @return Error Response with HttpStatus.NOT_FOUND
     */
    @ExceptionHandler({EmptyResultDataAccessException.class})
    @ResponseBody
    static ResponseEntity<ErrorResponse> handleResourceNotFound(EmptyResultDataAccessException exc){
        ErrorResponse error = new ErrorResponse();
        error.setStatus(HttpStatus.NOT_FOUND.value());
        error.setTimestamp(System.currentTimeMillis());
        error.setMessage("Entity not found -- " + exc.getMessage());
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }

    /**
     * Exception handler for MethodArgumentNotValidException
     * @param exc The MethodArgumentNotValidException triggered
     * @return Error response with HttpStatus.BAD_REQUEST
     */
    @ExceptionHandler({MethodArgumentNotValidException.class})
    @ResponseBody
    static ResponseEntity<ErrorResponse> handleBadBodyInput(MethodArgumentNotValidException exc){
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setStatus(HttpStatus.BAD_REQUEST.value());
        errorResponse.setTimestamp(System.currentTimeMillis());
        BindingResult bindingResult = exc.getBindingResult();
        StringBuilder response = new StringBuilder("Bad input parameters. // ");
        for(ObjectError error: bindingResult.getAllErrors()){
            response.append(error.getObjectName()).append(": ").append(error.getDefaultMessage()).append(" // ");
        }
        errorResponse.setMessage(response.toString());
        return new ResponseEntity<>(errorResponse,HttpStatus.BAD_REQUEST);
    }


    @ExceptionHandler({HttpRequestMethodNotSupportedException.class})
    @ResponseBody
    static ResponseEntity<ErrorResponse> handeNotSupportedMethod(HttpRequestMethodNotSupportedException exc){
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setStatus(HttpStatus.NOT_IMPLEMENTED.value());
        errorResponse.setTimestamp(System.currentTimeMillis());
        errorResponse.setMessage("Method Not Supported");
        return new ResponseEntity<>(errorResponse,HttpStatus.NOT_IMPLEMENTED);
    }

}
