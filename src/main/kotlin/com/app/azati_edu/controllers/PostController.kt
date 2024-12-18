package com.app.azati_edu.controllers

import com.app.azati_edu.config.postModelToView
import com.app.azati_edu.services.PostService
import com.app.azati_edu.views.PostView
import org.modelmapper.ModelMapper
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("api/v1/posts")
class PostController(
    private val postService: PostService,
    private val modelMapper: ModelMapper
) {
    @PostMapping
    fun createPost(@RequestBody view: PostView): ResponseEntity<PostView> {
        val createdPost = postService.createPost(view)
        return ResponseEntity.ok(modelMapper.postModelToView(createdPost))
    }

    @GetMapping("/{id}")
    fun getPost(@PathVariable id: Long): ResponseEntity<PostView> {
        val post = postService.getPostById(id)
        return ResponseEntity.ok(modelMapper.postModelToView(post))
    }

    @PutMapping("/{id}")
    fun updatePost(@PathVariable id: Long, @RequestBody view: PostView): ResponseEntity<PostView> {
        val updatedPost = postService.updatePost(id, view)
        return ResponseEntity.ok(modelMapper.postModelToView(updatedPost))
    }

    @DeleteMapping("/{id}")
    fun deletePost(@PathVariable id: Long): ResponseEntity<Boolean> {
        val isDeleted = postService.deletePost(id)
        return ResponseEntity.ok(isDeleted)
    }
}