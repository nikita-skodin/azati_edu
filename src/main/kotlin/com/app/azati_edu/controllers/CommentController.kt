package com.app.azati_edu.controllers

import com.app.azati_edu.config.commentModelToView
import com.app.azati_edu.services.CommentService
import com.app.azati_edu.views.CommentView
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
    fun createComment(@RequestBody view: CommentView): ResponseEntity<CommentView> {
        val createdComment = commentService.createComment(view)
        return ResponseEntity.ok(modelMapper.commentModelToView(createdComment))
    }

    @GetMapping("/{id}")
    fun getComment(@PathVariable id: Long): ResponseEntity<CommentView> {
        val comment = commentService.getCommentById(id)
        return ResponseEntity.ok(modelMapper.commentModelToView(comment))
    }

    @PutMapping("/{id}")
    fun updateComment(@PathVariable id: Long, @RequestBody view: CommentView): ResponseEntity<CommentView> {
        val updatedComment = commentService.updateComment(id, view)
        return ResponseEntity.ok(modelMapper.commentModelToView(updatedComment))
    }

    @DeleteMapping("/{id}")
    fun deleteComment(@PathVariable id: Long): ResponseEntity<Boolean> {
        val isDeleted = commentService.deleteComment(id)
        return ResponseEntity.ok(isDeleted)
    }
}
