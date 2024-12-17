package com.app.azati_edu.repositories;

import com.app.azati_edu.models.UserModel
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface UserModelRepository : JpaRepository<UserModel, Long> {
}