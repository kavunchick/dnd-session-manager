package com.dndmanager.domain

import com.dndmanager.domain.helper.Alignment
import io.quarkus.hibernate.orm.panache.kotlin.PanacheCompanion
import jakarta.persistence.*

@Entity
@Table(name = "characters")
open class Character(

    @Column(nullable = false)
    var name: String,

    @Column(columnDefinition = "TEXT")
    var background: String?,

    @Column(columnDefinition = "TEXT")
    var ideals: String?,

    @Column(columnDefinition = "TEXT")
    var bonds: String?,

    @Column(columnDefinition = "TEXT")
    var flaws: String?,

    @Column(columnDefinition = "TEXT")
    var imageURI: String?,

    @Column(columnDefinition = "TEXT", name = "personality_traits")
    var personalityTraits: String?,

    var alignment: Alignment,

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "character_class", nullable = false)
    var characterClass: Class,

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(nullable = false)
    var race: Race,

    var stats: MutableList<Int>,

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "author_id", nullable = false)
    var createdBy: User

) : BaseEntity() {

    override fun isTrusted(sub: String) = createdBy == User.findBySub(sub)

    companion object : PanacheCompanion<Character>

}