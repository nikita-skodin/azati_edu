package com.app.azati_edu.controller

import com.app.azati_edu.config.postModelToView
import com.app.azati_edu.service.PostService
import com.app.azati_edu.dto.PostViewDTO
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
    fun createPost(@RequestBody view: PostViewDTO): ResponseEntity<PostViewDTO> {
        val createdPost = postService.createPost(view)
        return ResponseEntity.ok(modelMapper.postModelToView(createdPost))
    }

    @GetMapping("/{id}")
    fun getPost(@PathVariable id: Long): ResponseEntity<PostViewDTO> {
        val post = postService.getPostById(id)
        return ResponseEntity.ok(modelMapper.postModelToView(post))
    }

    @PutMapping("/{id}")
    fun updatePost(@PathVariable id: Long, @RequestBody view: PostViewDTO): ResponseEntity<PostViewDTO> {
        val updatedPost = postService.updatePost(id, view)
        return ResponseEntity.ok(modelMapper.postModelToView(updatedPost))
    }

    @DeleteMapping("/{id}")
    fun deletePost(@PathVariable id: Long): ResponseEntity<Boolean> {
        val isDeleted = postService.deletePost(id)
        return ResponseEntity.ok(isDeleted)
    }
}