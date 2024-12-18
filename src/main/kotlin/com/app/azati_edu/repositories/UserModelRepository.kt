package com.app.azati_edu.repositories;

import com.app.azati_edu.models.UserModel
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository

@Repository
interface UserModelRepository : JpaRepository<UserModel, Long> {
    fun existsByUsernameOrEmail(username: String, email: String): Boolean

    @Query(
        "SELECT CASE WHEN COUNT(u) > 0 THEN TRUE ELSE FALSE END " +
                "FROM UserModel u WHERE (u.username = :username OR u.email = :email) " +
                "AND u.id <> :id"
    )
    fun existsByUsernameOrEmailAndIdNot(
        @Param("username") username: String,
        @Param("email") email: String,
        @Param("id") id: Long
    ): Boolean
}