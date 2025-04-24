package com.dndmanager.domain

import io.quarkus.hibernate.orm.panache.kotlin.PanacheCompanion
import jakarta.persistence.*
import java.time.Instant

@Entity
@Table(name = "sessions")
open class Session(

    @Column(nullable = false)
    var name: String,

    @Column(nullable = false)
    var start: Instant,

    @Column(name = "last_updated")
    var lastUpdated: Instant?,

    @OneToMany(mappedBy = "session", fetch = FetchType.LAZY)
    var characters: MutableList<SessionCharacter> = mutableListOf(),

    @ManyToOne
    var author: User
) : BaseEntity() {

    companion object : PanacheCompanion<Session>

}