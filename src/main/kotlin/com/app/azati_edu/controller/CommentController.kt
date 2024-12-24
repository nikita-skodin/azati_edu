package com.app.azati_edu.controller

import com.app.azati_edu.config.commentModelToView
import com.app.azati_edu.service.CommentService
import com.app.azati_edu.dto.CommentModelDTO
import org.modelmapper.ModelMapper
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("api/v1/comments")
class CommentController(
    private val commentService: CommentService,
    private val modelMapper: ModelMapper
) {
    @PostMapping
    fun createComment(@RequestBody view: CommentModelDTO): ResponseEntity<CommentModelDTO> {
        val createdComment = commentService.createComment(view)
        return ResponseEntity.ok(modelMapper.commentModelToView(createdComment))
    }

    @GetMapping("/{id}")
    fun getComment(@PathVariable id: Long): ResponseEntity<CommentModelDTO> {
        val comment = commentService.getCommentById(id)
        return ResponseEntity.ok(modelMapper.commentModelToView(comment))
    }

    @PutMapping("/{id}")
    fun updateComment(@PathVariable id: Long, @RequestBody view: CommentModelDTO): ResponseEntity<CommentModelDTO> {
        val updatedComment = commentService.updateComment(id, view)
        return ResponseEntity.ok(modelMapper.commentModelToView(updatedComment))
    }

    @DeleteMapping("/{id}")
    fun deleteComment(@PathVariable id: Long): ResponseEntity<Boolean> {
        val isDeleted = commentService.deleteComment(id)
        return ResponseEntity.ok(isDeleted)
    }
}
