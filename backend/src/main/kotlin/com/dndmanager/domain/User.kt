package com.dndmanager.domain

import io.quarkus.hibernate.orm.panache.kotlin.PanacheCompanion
import jakarta.persistence.*

@Entity
@Table(name = "users", indexes = [Index(name = "idx_sub",  columnList="sub", unique = true)])
open class User(
    @Column(nullable = false)
    var username: String,

    @Column(nullable = false, unique = true)
    var sub: String,

    @OneToMany(fetch = FetchType.LAZY, targetEntity = Character::class)
    @JoinColumn
    var characters: List<Character>,

    @OneToMany(fetch = FetchType.LAZY, targetEntity = Session::class)
    @JoinColumn
    var sessions: List<Session>


) : BaseEntity() {
    companion object : PanacheCompanion<User>
}
