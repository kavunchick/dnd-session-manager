package com.dndmanager.domain

import io.quarkus.hibernate.orm.panache.kotlin.PanacheCompanion
import jakarta.persistence.*

@Entity
@Table(name = "character_abilities")
open class CharacterAbility(

    @Column(nullable = false)
    var value: Short,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(nullable = false)
    var ability: Ability,

    @Column(name = "session_character_id", nullable = false)
    var sessionCharacterId: Long,
) : BaseEntity() {

    companion object : PanacheCompanion<CharacterAbility>

}