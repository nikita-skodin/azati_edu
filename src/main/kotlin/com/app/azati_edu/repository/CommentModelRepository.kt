package com.app.azati_edu.repository;

import com.app.azati_edu.model.CommentModel
import org.springframework.data.jpa.repository.JpaRepository

interface CommentModelRepository : JpaRepository<CommentModel, Long> {
}