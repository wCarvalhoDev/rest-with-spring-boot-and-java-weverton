package com.weverton.rest_with_SpringBoot.controllers.docs;

import com.weverton.rest_with_SpringBoot.data.dto.BookDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

public interface BookControllerDocs {

    @Operation(
            summary = "Find all books",
            description = "Find all books.",
            tags = {"Books"},
            responses = {
                    @ApiResponse(
                            description = "success",
                            responseCode = "200",
                            content = {
                                    @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                                            array = @ArraySchema(schema = @Schema(implementation = BookDTO.class)))
                            }),
                    @ApiResponse(description = "No content", responseCode = "204", content = @Content),
                    @ApiResponse(description = "Bad request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
                    @ApiResponse(description = "Not found", responseCode = "404", content = @Content),
                    @ApiResponse(description = "Internal server error", responseCode = "500", content = @Content)
            })
    List<BookDTO> findAll();

    @Operation(
            summary = "Find a book",
            description = "Find a specific book by id.",
            tags = {"Books"},
            responses = {
                    @ApiResponse(
                            description = "success",
                            responseCode = "200",
                            content = {
                                    @Content(schema = @Schema(implementation = BookDTO.class))
                            }),
                    @ApiResponse(description = "No content", responseCode = "204", content = @Content),
                    @ApiResponse(description = "Bad request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
                    @ApiResponse(description = "Not found", responseCode = "404", content = @Content),
                    @ApiResponse(description = "Internal server error", responseCode = "500", content = @Content)
            })
    BookDTO findById(@PathVariable("id") Long id);

    @Operation(
            summary = "Add a book",
            description = "Adds a new book by passing in a JSON, XML or YML representation of the book.",
            tags = {"Books"},
            responses = {
                    @ApiResponse(
                            description = "success",
                            responseCode = "200",
                            content = {
                                    @Content(schema = @Schema(implementation = BookDTO.class))
                            }),
                    @ApiResponse(description = "No content", responseCode = "204", content = @Content),
                    @ApiResponse(description = "Bad request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
                    @ApiResponse(description = "Not found", responseCode = "404", content = @Content),
                    @ApiResponse(description = "Internal server error", responseCode = "500", content = @Content)
            })
    BookDTO create(@RequestBody BookDTO dto);

    @Operation(
            summary = "Update a book information",
            description = "Update a book information by passing in a JSON, XML or YML representation of the book.",
            tags = {"Books"},
            responses = {
                    @ApiResponse(
                            description = "success",
                            responseCode = "200",
                            content = {
                                    @Content(schema = @Schema(implementation = BookDTO.class))
                            }),
                    @ApiResponse(description = "No content", responseCode = "204", content = @Content),
                    @ApiResponse(description = "Bad request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
                    @ApiResponse(description = "Not found", responseCode = "404", content = @Content),
                    @ApiResponse(description = "Internal server error", responseCode = "500", content = @Content)
            })
    BookDTO update(@RequestBody BookDTO dto);

    @Operation(
            summary = "Delete a book information by id",
            description = "Delete a book by id.",
            tags = {"Books"},
            responses = {
                    @ApiResponse(
                            description = "success",
                            responseCode = "200",
                            content = {
                                    @Content(schema = @Schema(implementation = BookDTO.class))
                            }),
                    @ApiResponse(description = "No content", responseCode = "204", content = @Content),
                    @ApiResponse(description = "Bad request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
                    @ApiResponse(description = "Not found", responseCode = "404", content = @Content),
                    @ApiResponse(description = "Internal server error", responseCode = "500", content = @Content)
            })
    ResponseEntity<?> delete(@PathVariable("id") Long id);
}
