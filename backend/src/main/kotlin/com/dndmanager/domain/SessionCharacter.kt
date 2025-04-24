package com.dndmanager.domain

import io.quarkus.hibernate.orm.panache.kotlin.PanacheCompanion
import jakarta.persistence.*

@Entity
@Table(name = "session_character")
open class SessionCharacter (

    @ManyToOne(fetch = FetchType.LAZY)
    var session: Session,

    @ManyToOne(fetch = FetchType.LAZY)
    var character: Character,

    @OneToMany(fetch = FetchType.LAZY, targetEntity = CharacterInventory::class)
    @JoinColumn(name = "inventory_id", nullable = false)
    var inventory: List<CharacterInventory>,

    var money: MutableList<Int>,

//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "abilities_id", nullable = false)
//    var abilities: CharacterAbility,

    @Column(nullable = false)
    var level: Short,

    @Column( nullable = false)
    var experience: Short,

    @Column(nullable = false)
    var health: Short,
) : BaseEntity()  {

    companion object : PanacheCompanion<SessionCharacter>

}