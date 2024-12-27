package com.app.azati_edu.repository;

import com.app.azati_edu.model.PostModel
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface PostModelRepository : JpaRepository<PostModel, Long> {
}