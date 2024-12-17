package com.app.azati_edu.repositories;

import com.app.azati_edu.models.CommentModel
import org.springframework.data.jpa.repository.JpaRepository

interface CommentModelRepository : JpaRepository<CommentModel, Long> {
}